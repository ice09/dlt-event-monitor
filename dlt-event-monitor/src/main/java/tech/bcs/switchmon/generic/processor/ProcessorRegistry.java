package tech.bcs.switchmon.generic.processor;

import org.springframework.stereotype.Service;
import org.web3j.abi.EventEncoder;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProcessorRegistry {

    private Map<String, IProcessor> processorMap;

    ProcessorRegistry() {
        processorMap = new HashMap<>();
    }

    public void registerEventProcessor(String topic, IProcessor eventProcessor) {
        processorMap.put(EventEncoder.buildEventSignature(topic), eventProcessor);
    }

    public IProcessor processorForEvent(String topic) {
        if (processorMap.containsKey(topic)) {
            return processorMap.get(topic);
        } else {
            return new LogProcessor();
        }
    }

}
