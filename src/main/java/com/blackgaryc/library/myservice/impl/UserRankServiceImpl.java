package com.blackgaryc.library.myservice.impl;

import com.blackgaryc.library.domain.file.UploadRankResult;
import com.blackgaryc.library.mapper.BookUploadRequestMapper;
import com.blackgaryc.library.myservice.UserRankService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UserRankServiceImpl implements UserRankService {
    @Resource
    BookUploadRequestMapper mapper;

    @Override
    public List<UploadRankResult> getRankList(Date startDate) {
        return mapper.getRankList(startDate);
    }
}
