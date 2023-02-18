package tech.bcs.switchmon.generic.processor;

import org.web3j.protocol.core.methods.response.Log;

public interface IProcessor {
    void process(Log log);

    String getKey();

}
