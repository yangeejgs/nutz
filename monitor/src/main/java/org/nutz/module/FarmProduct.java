package org.nutz.module;

import org.nutz.bean.Android_DetectionHistoryData;
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
@At("/api/farmProduct")
@Ok("json")
@Fail("http:500")
@Encoding(input = "UTF-8", output = "UTF-8")
public class FarmProduct {

    @Inject
    private Dao dao;

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(FarmProduct.class);

    @At
    @AdaptBy(type = JsonAdaptor.class)
    public NutMap realTime(@Param("timeInterval") long timeInterval) {
        NutMap map = new NutMap();
        NutMap data = new NutMap();
        try {
            Date date = new Date();
            date.setTime(timeInterval);
            LOGGER.info("农产品种植监控timeInterval:{}", date);
            Cnd cnd = Cnd.where("create_date_time", ">", date);
            List<Android_DetectionHistoryData> list = dao.query(Android_DetectionHistoryData.class, cnd);
            //初始化空气温度
            Double airTemperature = 0.0;
            //初始化空气湿度
            Double airHumidity = 0.0;
            //初始化二氧化碳浓度
            Integer carbonDioxideConcentration = 0;
            //初始化土壤水分
            Double soilMoisture = 0.0;
            //处理平均数据
            if (null != list && list.size() > 0) {
                for (Android_DetectionHistoryData android_detectionHistoryData : list) {
                    airTemperature += android_detectionHistoryData.getAirTemperature();
                    airHumidity += android_detectionHistoryData.getAirHumidity();
                    carbonDioxideConcentration += android_detectionHistoryData.getCarbonDioxideConcentration();
                    soilMoisture += android_detectionHistoryData.getSoilMoisture();
                }
                airTemperature = airTemperature / list.size();
                airHumidity = airHumidity / list.size();
                carbonDioxideConcentration = carbonDioxideConcentration / list.size();
                soilMoisture = soilMoisture / list.size();
            }
            DecimalFormat df = new DecimalFormat(".#");
            data.setv("airTemperature", df.format(airTemperature));
            data.setv("airHumidity", df.format(airHumidity));
            data.setv("carbonDioxideConcentration", carbonDioxideConcentration);
            data.setv("soilMoisture", df.format(soilMoisture));
            return map.setv("code", 200).setv("info", "success").setv("data", data);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return map.setv("code", 200).setv("info", "error").setv("data", data);
        }
    }

}
