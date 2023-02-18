package tech.bcs.switchmon.generic.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import tech.bcs.switchmon.generic.EventProcessScheduler;

import java.io.FileNotFoundException;
import java.math.BigInteger;

@Slf4j
@Service
public class EventScheduler implements ApplicationListener<ContextRefreshedEvent> {

    private final ConfigReader configReader;
    private final EventProcessScheduler eventProcessScheduler;
    private final AmqpAdmin amqpAdmin;

    public EventScheduler(ConfigReader configReader, EventProcessScheduler eventProcessScheduler, AmqpAdmin amqpAdmin) {
        this.configReader = configReader;
        this.eventProcessScheduler = eventProcessScheduler;
        this.amqpAdmin = amqpAdmin;
    }

    public void schedule() throws FileNotFoundException {
        Contracts contracts = configReader.readYamlConfig();
        for (Contract contract : contracts.getContracts()) {
            for (Event event : contract.getEvents()) {
                registerQueue(contract.getName() + "_" + event.getName());
                eventProcessScheduler.scheduleEventProcessor(contract.getAddress(), event.getName(), BigInteger.valueOf(event.getStart()));
            }
        }
    }

    public void registerQueue(String queueNameSig) {
        String queueName = queueNameSig.substring(0, queueNameSig.indexOf("("));
        Queue queue = new Queue(queueName, true);
        Binding binding = new Binding(queueName, Binding.DestinationType.QUEUE, "directExg", "event." + queueName, null);
        amqpAdmin.declareQueue(queue);
        amqpAdmin.declareBinding(binding);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            schedule();
        } catch (FileNotFoundException e) {
            log.error("Cannot schedule config.", e);
        }
    }
}
