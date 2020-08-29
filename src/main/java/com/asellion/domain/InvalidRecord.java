package com.asellion.domain;

/**
 * Domain Object : InvalidRecord : This is object will be return after validating the .csv or .xml file.
 */
public class InvalidRecord {
    private long txnReferenceNo;
    private String failureReason = "";

    public long getTxnReferenceNo() {
        return txnReferenceNo;
    }

    public void setTxnReferenceNo(long txnReferenceNo) {
        this.txnReferenceNo = txnReferenceNo;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }
}
