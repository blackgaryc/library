package com.blackgaryc.library.core.error;

public class MinioObjectKeyGenerateException extends MinioException{
    public MinioObjectKeyGenerateException(String type) {
        super("未定义的对象路径生成器:"+type);
    }

}
