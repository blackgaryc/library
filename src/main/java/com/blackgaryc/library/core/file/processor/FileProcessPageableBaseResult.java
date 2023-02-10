package com.blackgaryc.library.core.file.processor;

public class FileProcessPageableBaseResult extends FileProcessBaseResult implements IFileProcessPageableResult {
    private Integer numberOfPage;
    @Override
    public Integer getNumberOfPage() {
        return this.numberOfPage;
    }

    public void setNumberOfPage(Integer numberOfPage) {
        this.numberOfPage = numberOfPage;
    }

    public FileProcessPageableBaseResult(IFileProcessBaseResult result) {
        this.setMimetype(result.getMimetype());
        this.setExtension(result.getExtension());
        this.setFilename(result.getFilename());
        this.setMd5(result.getMd5());
        this.setSize(result.getSize());
        this.setUploadUid(result.getUploadUid());
        this.setObjectKey(result.getObjectKey());
    }

    public FileProcessPageableBaseResult() {

    }

    @Override
    public String toString() {
        return "FileProcessPageableBaseResult{" +
                "filename='" + this.getFilename() + '\'' +
                ", objectKey='" + this.getObjectKey() + '\'' +
                ", uploadUid='" + this.getUploadUid() + '\'' +
                ", extension='" + this.getExtension() + '\'' +
                ", mimetype='" + this.getMimetype() + '\'' +
                ", md5='" + this.getMd5() + '\'' +
                ", size=" + this.getSize() +
                ", numberOfPage=" + this.numberOfPage +
                '}';
    }
}
