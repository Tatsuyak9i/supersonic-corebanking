package com.redhat.supersonic.corebanking.util;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;
import com.redhat.supersonic.corebanking.model.Transfer;

public class TransferDeserializer extends ObjectMapperDeserializer<Transfer> {
    public TransferDeserializer(){
        // pass the class to the parent.
        super(Transfer.class);
    }
}