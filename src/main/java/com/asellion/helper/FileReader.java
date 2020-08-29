package com.asellion.helper;

import com.asellion.domain.MT940Record;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
public interface FileReader {
    List<MT940Record> readFileData(MultipartFile file);
}
