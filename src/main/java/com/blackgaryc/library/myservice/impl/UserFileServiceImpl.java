package com.blackgaryc.library.myservice.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.blackgaryc.library.core.error.FileDownloadTimesEndException;
import com.blackgaryc.library.entity.FileEntity;
import com.blackgaryc.library.myservice.UserFileService;
import com.blackgaryc.library.service.FileService;
import com.blackgaryc.library.service.MinioClientService;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.errors.*;
import io.minio.http.Method;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class UserFileServiceImpl implements UserFileService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final FileService fileService;
    private final MinioClientService minioClientService;
    private static final String IP_DOWNLOAD_PREFIX = "download_count_ip:";
    private static final String User_DOWNLOAD_PREFIX = "download_count_uid:";
    private static final String X_REAL_IP = "X-Real-IP";
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public UserFileServiceImpl(FileService fileService, MinioClientService minioClientService) {
        this.fileService = fileService;
        this.minioClientService = minioClientService;
    }

    @Override
    public String download(String fileId) throws FileDownloadTimesEndException {
        // limit download times
        //when no user login, update download times by redis, use user's real ip address
        if (!StpUtil.isLogin()) {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            String remoteAddr = request.getRemoteAddr();
            String realIp = request.getHeader(X_REAL_IP);
            //如果存在x-real-ip则优先x-real-ip,其次是remoteAddr
            String key = IP_DOWNLOAD_PREFIX + (Strings.isNotBlank(realIp) ? realIp : remoteAddr);
            checkDownloadTimes(key,0);
        }
        //when user has login,update download times by redis using user id
        else {
            log.info("user：{} , download file.", StpUtil.getLoginId());
            String key = User_DOWNLOAD_PREFIX + StpUtil.getLoginIdAsString();
            checkDownloadTimes(key,10);
        }
        //generate download url
        FileEntity file = fileService.getById(fileId);
        String preSignedUrl;
        try {
            preSignedUrl = minioClientService.getPresignedUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .expiry(15, TimeUnit.MINUTES)
                            .method(Method.GET)
                            .object(file.getObject())
            );
        } catch (ServerException e) {
            throw new RuntimeException(e);
        } catch (InsufficientDataException e) {
            throw new RuntimeException(e);
        } catch (ErrorResponseException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (InvalidResponseException e) {
            throw new RuntimeException(e);
        } catch (XmlParserException e) {
            throw new RuntimeException(e);
        } catch (InternalException e) {
            throw new RuntimeException(e);
        }
        return preSignedUrl;
    }

    private void checkDownloadTimes(String key,int maxDownloadTimes) throws FileDownloadTimesEndException {
        String s = stringRedisTemplate.opsForValue().get(key);
        //如果不存在设置值
        if (Objects.isNull(s)) {
            stringRedisTemplate.opsForValue().set(key, "0", 24, TimeUnit.HOURS);
        }
        String s1 = stringRedisTemplate.opsForValue().get(key);
        if (Objects.isNull(s1)) {
            log.error("download counter can't work...");
        } else {
            long numberOfDownloadTimes = Long.parseLong(s1);
            if (numberOfDownloadTimes >= maxDownloadTimes) {
                log.warn("key: {} 日下载量已达到上限。", key);
                throw new FileDownloadTimesEndException();
            }
            //increase
            Long count = stringRedisTemplate.opsForValue().increment(key);
            log.info("download counter:" + key + "\t" + count);
        }
    }
}

