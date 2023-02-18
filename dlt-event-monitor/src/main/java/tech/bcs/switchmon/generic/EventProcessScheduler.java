package tech.bcs.switchmon.generic;

import io.reactivex.Flowable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.web3j.abi.EventEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import tech.bcs.switchmon.generic.processor.ProcessorRegistry;

import java.math.BigInteger;

@Slf4j
@Service
public class EventProcessScheduler {
    private final Web3j web3j;
    private final ProcessorRegistry processorRegistry;

    public EventProcessScheduler(Web3j web3j, ProcessorRegistry processorRegistry) {
        this.web3j = web3j;
        this.processorRegistry = processorRegistry;
    }

    public void scheduleEventProcessor(String contractAddress, String eventSignature, BigInteger fromBlock) {
        EthFilter filter =
                new EthFilter(new DefaultBlockParameterNumber(fromBlock), DefaultBlockParameterName.LATEST, contractAddress)
                        .addSingleTopic(EventEncoder.buildEventSignature(eventSignature));
        Flowable<Log> logObservable = web3j.ethLogFlowable(filter);
        log.info("Creating observer for \"" + eventSignature + "\" at \"" + contractAddress + "\"");
        logObservable.subscribe(log -> processorRegistry.processorForEvent(log.getTopics().get(0)).process(log));
    }

}
