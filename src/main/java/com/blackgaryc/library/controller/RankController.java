package com.blackgaryc.library.controller;

import com.blackgaryc.library.core.result.BaseResult;
import com.blackgaryc.library.core.result.Results;
import com.blackgaryc.library.myservice.UserRankService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Calendar;

@RestController
@RequestMapping("/rank")
public class RankController {
    @Resource
    UserRankService userRankService;

    /**
     * 上传图书排行榜
     * @param type 日/周/月排行
     * @return 结果
     */
    @GetMapping("book/upload")
    public BaseResult getBookUploadRank(@RequestParam(defaultValue = "week") String type){
        Calendar calendar = Calendar.getInstance();
        switch (type) {
            case "day" -> calendar.roll(Calendar.DAY_OF_YEAR,-1);
            case "week" -> calendar.roll(Calendar.WEEK_OF_YEAR, -1);
            case "month" -> calendar.roll(Calendar.MONTH, -1);
        }
        return Results.successData(userRankService.getRankList(calendar.getTime()));
    }
}
