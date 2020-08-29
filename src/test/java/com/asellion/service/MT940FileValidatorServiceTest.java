package com.asellion.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.junit.Assert.*;

public class MT940FileValidatorServiceTest {


    MT940FileValidatorService mt940FileValidatorService = new MT940FileValidatorService();

    @Test(expected = Exception.class)
    public void validateMT940File_NullFile() throws Exception{
        mt940FileValidatorService.validateMT940File(null);
    }

    @Test(expected = Exception.class)
    public void validateMT940File_NonCvsAndXmlFiles() throws Exception{
        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );
        mt940FileValidatorService.validateMT940File(file);
    }
}