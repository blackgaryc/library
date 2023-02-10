package com.blackgaryc.library.core.file.processor;

import com.blackgaryc.library.core.error.FileProcessorNotSupportException;
import io.minio.MinioClient;
import org.apache.tika.Tika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashMap;

@Component
public class ProcessorFactory implements InitializingBean {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private HashMap<Class<? extends IGeneralFileProcessor>, IGeneralFileProcessor> processorHashMap = new HashMap<>();
    @Resource
    MinioClient minioClient;
    @Resource
    Tika tika;

    @Override
    public void afterPropertiesSet() throws Exception {
        processorHashMap.put(MinioGeneralFileProcessor.class, new MinioGeneralFileProcessor(tika,this.minioClient));
    }

    public IFileProcessBaseResult process(IFileInfo file) throws FileProcessorNotSupportException {
        Collection<IGeneralFileProcessor> values = processorHashMap.values();
        for (IGeneralFileProcessor processor : values) {
            if (processor.support(file.getClass())) {
                log.info("find processor:" + processor);
                return processor.process(file);
            }
            //do nothing to continue
        }
        //unable to find any processor usable
        throw new FileProcessorNotSupportException("");
    }
}
