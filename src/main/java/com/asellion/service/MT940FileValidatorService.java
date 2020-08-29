package com.asellion.service;

import com.asellion.domain.InvalidRecord;
import com.asellion.domain.MT940Record;
import com.asellion.helper.FileReader;
import com.asellion.helper.FileReaderFactory;
import com.asellion.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service : MT940FileValidatorService
 * This service is reading, parsing the .csv or .xml file data to the MT940Record and validate it and return the InvalidReocrd data back.
 */

@Service
public class MT940FileValidatorService {

    Logger logger = LoggerFactory.getLogger("MT940FileValidatorService");
    static final String DUPLICATE_REASON = "Record exists %d times.Account Number %s";
    static final String NEGATIVE_END_BALANCE_REASON = "End Balance is %f, Negative balance is not allowed.";
    static final String UNMATCHED_END_BALANCE_REASON = "End Balance is %f, but it should be %f.";

    /**
     * validateMT940File service will validate the Simplified MT940 file. File format can be .csv or .xml
     * @param file
     * @return
     */
    public List<InvalidRecord> validateMT940File(MultipartFile file) throws Exception {
        List<InvalidRecord> lstInvalidRecords = new ArrayList<InvalidRecord>();
        FileReader fileReader = FileReaderFactory.getFileReader(FileUtil.getFileExtension(file.getOriginalFilename()));
        if(fileReader == null) {
            throw new Exception("File Format is neither .csv nor .xml");
        }
        List<MT940Record> lstMT940Records = fileReader.readFileData(file);

        lstInvalidRecords = lstMT940Records.stream().map(mt940Record -> {
            return validate(mt940Record,lstMT940Records);
        }).filter(rec->rec != null)
                .collect(Collectors.toList());
        lstInvalidRecords.stream().forEach(invalidRecord -> {
            logger.info("Reference no:"+invalidRecord.getTxnReferenceNo()+" is invalild, Failure Reason :"+invalidRecord.getFailureReason());
        });

        return lstInvalidRecords;
    }

    /**
     * validate method validate the MT940Record, and return the InvalidRecord.
     * @param mt940Record
     * @param lstMT940Records
     * @return
     */
    private InvalidRecord validate(MT940Record mt940Record,List<MT940Record> lstMT940Records) {
        InvalidRecord invalidRecord = null;
        int noOfTimesRecordExisis = Collections.frequency(lstMT940Records,mt940Record);
        Double actualBalance = mt940Record.getInitialBalance().doubleValue() + mt940Record.getTxnAmount().doubleValue();
        actualBalance = new BigDecimal(actualBalance.toString()).setScale(2,RoundingMode.HALF_UP).doubleValue();
        StringBuilder failureMessage = new StringBuilder();
        logger.info("Record Balance :"+mt940Record.getBalance().doubleValue()+", Calculated Balance :"+actualBalance.doubleValue());
        if(noOfTimesRecordExisis>1) {
            failureMessage.append(String.format(DUPLICATE_REASON,noOfTimesRecordExisis,mt940Record.getAccountNumber()));
        } else if(mt940Record.getBalance() < 0d) {
            failureMessage.append(String.format(NEGATIVE_END_BALANCE_REASON,mt940Record.getBalance()));
        } else if(mt940Record.getBalance().doubleValue() != actualBalance.doubleValue()) {
            failureMessage.append(String.format(UNMATCHED_END_BALANCE_REASON,mt940Record.getBalance().doubleValue(),actualBalance.doubleValue()));
        }

        if(failureMessage.length() >0) {
            invalidRecord = new InvalidRecord();
            invalidRecord.setFailureReason(failureMessage.toString());
            invalidRecord.setTxnReferenceNo(mt940Record.getTxnReferenceNo());
        }
        return invalidRecord;
    }
}
