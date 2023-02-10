package com.blackgaryc.library.core.file.processor;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class FileProcessorFactory {
    private static HashMap<String,IFileProcessor> processorHashMap=new HashMap<>();
    public static IFileProcessor getInstance(String extensionOrMimetype){
        return processorHashMap.get(extensionOrMimetype);
    }
    public void apply(IFileProcessor processor){
        for (String s1 : processor.getSupportedExtensions()) {
            processorHashMap.put(s1, processor);
        }
        for (String s1 : processor.getSupportedMimetypes()) {
            processorHashMap.put(s1, processor);
        }
    }
}
