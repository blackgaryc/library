package com.blackgaryc.library.myservice;

import com.blackgaryc.library.domain.file.UploadRankResult;

import java.util.Date;
import java.util.List;

public interface UserRankService {
    List<UploadRankResult> getRankList(Date startDate);
}
