package com.xtm.email.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @author:藏剑
 * @date:2019/12/3 19:22
 */
public class CalDay {
    public static Integer getNumDay(){
        LocalDate today=LocalDate.now();
        LocalDate initDay=LocalDate.of(2019,9,28);  // 纪念日开始时间
        return (int) initDay.until(today, ChronoUnit.DAYS);
    }
}
