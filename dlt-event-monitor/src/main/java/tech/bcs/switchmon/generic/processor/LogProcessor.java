package tech.bcs.switchmon.generic.processor;

import lombok.extern.slf4j.Slf4j;
import org.web3j.protocol.core.methods.response.Log;

@Slf4j
public class LogProcessor implements IProcessor {
    @Override
    public void process(Log llog) {
        String logId = llog.getTransactionHash() + llog.getLogIndex();
        log.info("Generic Logger: " + logId);
    }

    @Override
    public String getKey() {
        return "Log";
    }
}
