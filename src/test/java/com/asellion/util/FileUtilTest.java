package com.asellion.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class FileUtilTest {

    @Test
    public void getFileExtension_success() {
        assertEquals("csv",FileUtil.getFileExtension("records.csv"));
        assertEquals("xml",FileUtil.getFileExtension("records.xml"));
        assertEquals("html",FileUtil.getFileExtension("records.html"));
    }

    @Test
    public void getFileExtension_BlankOrNull() {
        assertNull(FileUtil.getFileExtension(""));
        assertNull(FileUtil.getFileExtension(null));
    }
}