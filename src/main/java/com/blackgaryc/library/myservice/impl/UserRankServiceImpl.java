package com.blackgaryc.library.myservice.impl;

import com.blackgaryc.library.domain.file.UploadRankResult;
import com.blackgaryc.library.mapper.LogFileUploadMapper;
import com.blackgaryc.library.myservice.UserRankService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UserRankServiceImpl implements UserRankService {
    @Resource
    LogFileUploadMapper mapper;

    @Override
    public List<UploadRankResult> getRankList(Date startDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(startDate);
            return mapper.getRankList(format);
    }
}
