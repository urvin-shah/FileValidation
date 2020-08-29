package com.asellion.helper;

import com.asellion.domain.MT940Record;
import com.asellion.domain.MT940Records;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.util.ArrayList;
import java.util.List;


/**
 * XMLFileReader is a FileReader class which read and parse the XML data to the MT940Record objects
 */

@Component
public class XMLFileReader implements FileReader {

    Logger logger = LoggerFactory.getLogger("XMLFileReader");

    @Override
    public List<MT940Record> readFileData(MultipartFile file) {
        List<MT940Record> lstMT940Mt940Records = null;
        if(file != null) {
            try {

                JAXBContext jaxbContext = JAXBContext.newInstance(MT940Records.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

                MT940Records mt940Records = (MT940Records) jaxbUnmarshaller.unmarshal(file.getInputStream());
                lstMT940Mt940Records = mt940Records.getLstMTMt940Records();
            } catch (Exception je) {
                logger.error("XMLFileReader:" + je.getMessage());
            }
        }
        return lstMT940Mt940Records;
    }
}
