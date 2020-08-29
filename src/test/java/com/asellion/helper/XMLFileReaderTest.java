package com.asellion.helper;

import org.junit.Test;

import static org.junit.Assert.*;

public class XMLFileReaderTest {

    XMLFileReader xmlFileReader = new XMLFileReader();

    @Test
    public void readFileData_NullFile() {
        assertNull(xmlFileReader.readFileData(null));
    }
}