package org.nutz.module;

import org.nutz.bean.Android_DetectionHistoryData;
import org.nutz.bean.Record;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.*;
import org.slf4j.Logger;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by yangyang on 2017/11/16.
 */

@IocBean
@At("/api/grassland")
@Ok("json")
@Fail("http:500")
@Encoding(input = "UTF-8", output = "UTF-8")
public class GrassLand {

    @Inject
    private Dao dao;

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(GrassLand.class);

    @At
    @AdaptBy(type = JsonAdaptor.class)
    public NutMap realTime(@Param("timeInterval") long timeInterval) {
        NutMap map = new NutMap();
        NutMap data = new NutMap();
        try {
            Date date = new Date();
            date.setTime(timeInterval);
            LOGGER.info("草场环境监控timeInterval:{}", date);
            Cnd cnd = Cnd.where("create_date_time", ">", date);
            List<Record> list = dao.query(Record.class, cnd);
            //初始化空气温度
            Double airTemperature = 0.0;
            //初始化空气湿度
            Double airHumidity = 0.0;
            //初始化二氧化碳浓度
            Integer illumination = 0;
            //初始化土壤水分
            Double soilMoisture = 0.0;
            //处理平均数据
            if (null != list && list.size() > 0) {
                for (Record record : list) {
                    airTemperature += record.getAirTemperature();
                    airHumidity += record.getAirHumidity();
                    illumination += record.getIllumination();
                    soilMoisture += record.getSoilMoisture();
                }
                airTemperature = airTemperature / list.size();
                airHumidity = airHumidity / list.size();
                illumination = illumination / list.size();
                soilMoisture = soilMoisture / list.size();
            }
            DecimalFormat df = new DecimalFormat(".#");
            data.setv("airTemperature", df.format(airTemperature));
            data.setv("airHumidity", df.format(airHumidity));
            data.setv("illumination",illumination);
            data.setv("soilMoisture", df.format(soilMoisture));
            return map.setv("code", 200).setv("info", "success").setv("data", data);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return map.setv("code", 200).setv("info", "error").setv("data", data);
        }
    }

}
