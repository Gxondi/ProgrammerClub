package com.hyh.club.oss.entity;

public class FileInfo {
    private String fileName;

    private String etag;

    private boolean directoryFlag;

    public boolean isDirectoryFlag() {
        return directoryFlag;
    }

    public void setDirectoryFlag(boolean directoryFlag) {
        this.directoryFlag = directoryFlag;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
