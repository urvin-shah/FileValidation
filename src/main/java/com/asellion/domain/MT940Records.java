package com.asellion.domain;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "records")
@XmlAccessorType (XmlAccessType.FIELD)
public class MT940Records {

    @XmlElement(name = "record")
    private List<MT940Record> lstMTMt940Records;

    public List<MT940Record> getLstMTMt940Records() {
        return lstMTMt940Records;
    }

    public void setLstMTMt940Records(List<MT940Record> lstMTMt940Records) {
        this.lstMTMt940Records = lstMTMt940Records;
    }
}
