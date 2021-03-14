package com.redhat.supersonic.corebanking.model;

import java.math.BigDecimal;
import java.lang.String;

public class Transfer {
    
    public String key;
    public java.lang.String sourceId;
    public String destinationId;
    public BigDecimal amount;
    public String timestamp;

    public Transfer(String key, String sourceId, String destinationId, BigDecimal amount , String timestamp) {
        this.key = key;
        this.sourceId = sourceId;
        this.destinationId = destinationId;
        this.amount= amount;
        this.timestamp = timestamp;
    }

}