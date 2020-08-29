package com.asellion.domain;

import javax.xml.bind.annotation.*;
import java.util.Objects;


/**
 * Domain Object : MT940Record : This is the Simplified MT940 record object.
 */

@XmlRootElement(name="record")
public class MT940Record {

    private long txnReferenceNo;

    private String accountNumber;

    private Double initialBalance;

    private Double txnAmount;

    private Double balance;

    public long getTxnReferenceNo() {
        return txnReferenceNo;
    }

    @XmlAttribute(name = "reference")
    public void setTxnReferenceNo(long txnReferenceNo) {
        this.txnReferenceNo = txnReferenceNo;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    @XmlElement(name = "accountNumber")
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getInitialBalance() {
        return initialBalance;
    }

    @XmlElement(name = "startBalance")
    public void setInitialBalance(Double initialBalance) {
        this.initialBalance = initialBalance;
    }

    public Double getTxnAmount() {
        return txnAmount;
    }

    @XmlElement(name = "mutation")
    public void setTxnAmount(Double txnAmount) {
        this.txnAmount = txnAmount;
    }

    public Double getBalance() {
        return balance;
    }

    @XmlElement(name = "endBalance")
    public void setBalance(Double balance) {
        this.balance = balance;
    }


    @Override
    public String toString() {
        return "MT940Record{" +
                "txnReferenceNo=" + txnReferenceNo +
                ", accountNumber='" + accountNumber + '\'' +
                ", initialBalance=" + initialBalance +
                ", txnAmount=" + txnAmount +
                ", balance=" + balance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MT940Record)) return false;
        MT940Record that = (MT940Record) o;
        return getTxnReferenceNo() == that.getTxnReferenceNo();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getTxnReferenceNo());
    }
}
