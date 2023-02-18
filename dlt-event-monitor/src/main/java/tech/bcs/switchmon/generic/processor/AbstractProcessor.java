package tech.bcs.switchmon.generic.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.web3j.protocol.core.methods.response.Log;

@Slf4j
public abstract class AbstractProcessor implements IProcessor {
    protected RabbitTemplate rabbitTemplate;

    protected AbstractProcessor(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void process(Log llog) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            rabbitTemplate.convertAndSend("directExg", "event." + getKey(), mapper.writeValueAsString(llog));
        } catch (Exception e) {
            log.error("Cannot send object to queue", e);
        }
    }

}
