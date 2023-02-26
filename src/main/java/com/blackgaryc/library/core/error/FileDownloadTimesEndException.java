package com.blackgaryc.library.core.error;

public class FileDownloadTimesEndException extends FileException{
    public FileDownloadTimesEndException() {
        super("今日下载量已达到上限，请明日继续下载。");
    }
    public FileDownloadTimesEndException(String ip){
        super("您所处的ip["+ip+"]今日下载已达到上线，请明日继续下载。");
    }
}
