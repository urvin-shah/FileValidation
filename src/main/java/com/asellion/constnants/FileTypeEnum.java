package com.asellion.constnants;

public enum FileTypeEnum {
    CSVFileType("csv"),
    XMLFileType("xml");

    private String fileType;
    FileTypeEnum(String fileType) {
           this.fileType=fileType;
    }
    public String getFileType() {
        return this.fileType;
    }
}
