package com.asellion.helper;

import com.asellion.domain.MT940Record;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * CSVFileReader is a FileReader class which read and parse the CSV data to the MT940Record objects
 */
@Component
public class CSVFileReader implements FileReader {

    Logger logger = LoggerFactory.getLogger("CVSFileReader");

    @Override
    public List<MT940Record> readFileData(MultipartFile file) {
        List<MT940Record> lstMT940Records = null;
        if(file != null) {
            try {
                Reader reader = new InputStreamReader(file.getInputStream());
                CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
                lstMT940Records = csvReader.readAll().stream().map(cvsRec -> {
                    MT940Record mt940Record = new MT940Record();
                    mt940Record.setTxnReferenceNo(Long.valueOf(cvsRec[0]));
                    mt940Record.setAccountNumber(cvsRec[1]);
                    mt940Record.setInitialBalance(Double.valueOf(cvsRec[3]));
                    mt940Record.setTxnAmount(Double.valueOf(cvsRec[4]));
                    mt940Record.setBalance(Double.valueOf(cvsRec[5]));
                    return mt940Record;
                }).collect(Collectors.toList());
            } catch (IOException ioe) {
                logger.error("CVS File Reading error" + ioe.getMessage());
            }
        }
        return lstMT940Records;
    }
}