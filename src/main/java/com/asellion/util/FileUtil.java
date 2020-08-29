package com.asellion.util;

public class FileUtil {

    /**
     * getFileExtension method will return the file extension from the input filename.
     * @param filename
     * @return
     */
    public static String getFileExtension(String filename) {
        if(filename!= null && !filename.equalsIgnoreCase("")) {
            return filename.substring(filename.lastIndexOf(".")+1);
        }
        return null;
    }
}
