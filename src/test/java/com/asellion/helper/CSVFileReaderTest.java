package com.asellion.helper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.*;

public class CSVFileReaderTest {


    CSVFileReader csvFileReader = new CSVFileReader();

    @Test
    public void readFileData_NullFile() {
        assertNull(csvFileReader.readFileData(null));
    }

    @Test(expected = NumberFormatException.class)
    public void readFileData_InappropriateFileFormat() {
        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "hello.csv",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World! \n Hello, World".getBytes()
        );
        csvFileReader.readFileData(file);
    }
}