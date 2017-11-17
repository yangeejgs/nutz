package org.nutz.utils;

import org.nutz.ioc.loader.annotation.IocBean;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by yangyang7 on 2017/11/17.
 */

@IocBean
public class DateUtil {

    public String getWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int weekday = c.get(Calendar.DAY_OF_WEEK);
        String week = "";
        switch (weekday) {
            case 1:
                week = "周日";
                break;
            case 2:
                week = "周一";
                break;
            case 3:
                week = "周二";
                break;
            case 4:
                week = "周三";
                break;
            case 5:
                week = "周四";
                break;
            case 6:
                week = "周五";
                break;
            case 7:
                week = "周六";
        }
        return week;
    }

}
