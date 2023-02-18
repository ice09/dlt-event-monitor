package tech.bcs.switchmon.switchcontract.processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.web3j.protocol.core.methods.response.Log;
import tech.bcs.switchmon.generic.processor.AbstractProcessor;
import tech.bcs.switchmon.generic.processor.ProcessorRegistry;

@Slf4j
@Service
public class SwitchProcessor extends AbstractProcessor {

    public SwitchProcessor(ProcessorRegistry processorRegistry, RabbitTemplate rabbitTemplate) {
        super(rabbitTemplate);
        processorRegistry.registerEventProcessor("SwitchTurned(address,uint8)", this);
    }

    @Override
    public void process(Log llog) {
        String logId = llog.getTransactionHash() + llog.getLogIndex();
        log.info("SwitchTurned: " + logId);
        super.process(llog);
    }

    @Override
    public String getKey() {
        return "Switch_SwitchTurned";
    }
}
