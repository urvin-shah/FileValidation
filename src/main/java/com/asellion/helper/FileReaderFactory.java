package com.asellion.helper;

import com.asellion.constnants.FileTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FileReaderFactory {
    static Logger logger = LoggerFactory.getLogger("FileReaderFactory");

    public static FileReader getFileReader(String fileType) {
        logger.info("Input file Type :"+fileType);
        if(fileType.equalsIgnoreCase(FileTypeEnum.CSVFileType.getFileType())){
            return new CSVFileReader();
        } else if (fileType.equalsIgnoreCase(FileTypeEnum.XMLFileType.getFileType())) {
            return new XMLFileReader();
        }
        return null;
    }
}
