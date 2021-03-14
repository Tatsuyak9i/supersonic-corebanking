package com.redhat.supersonic.corebanking.model;

import java.lang.String;

public class TransferResult {
    
    public Transfer transfer;
    public String timestamp;
    public String result;

    public TransferResult(Transfer transfer , String timestamp, String result) {
        this.transfer = transfer;
        this.timestamp = timestamp;
        this.result = result;
    }

}