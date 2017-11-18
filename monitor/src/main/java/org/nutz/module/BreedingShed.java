package org.nutz.module;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.*;
import org.slf4j.Logger;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yangyang on 2017/11/16.
 */

@IocBean
@At("/api/breedingShed")
@Ok("json")
@Fail("http:500")
@Encoding(input = "UTF-8", output = "UTF-8")
public class BreedingShed {

    @Inject
    private Dao dao;

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(BreedingShed.class);

    @At
    @AdaptBy(type = JsonAdaptor.class)
    public NutMap realTime(@Param("timeInterval") long timeInterval) {
        NutMap map = new NutMap();
        NutMap data = new NutMap();
        try {
            Date date = new Date();
            date.setTime(timeInterval);
            LOGGER.info("农产品种植监控timeInterval:{}", date);
//            Cnd cnd = Cnd.where("create_date_time", ">", date);
//            List<Android_DetectionHistoryData> list = dao.query(Android_DetectionHistoryData.class, cnd);
            //初始化空气温度
            Double airTemperature = 0.0;
            //初始化空气湿度
            Double airHumidity = 0.0;
            //初始化二氧化碳浓度
            Integer carbonDioxideConcentration = 0;
            //初始化土壤水分
            Double ammoniaConcentration = 0.0;
//            //处理平均数据
//            if (null != list && list.size() > 0) {
//                for (Android_DetectionHistoryData android_detectionHistoryData : list) {
//                    airTemperature += android_detectionHistoryData.getAirTemperature();
//                    airHumidity += android_detectionHistoryData.getAirHumidity();
//                    carbonDioxideConcentration += android_detectionHistoryData.getCarbonDioxideConcentration();
//                    soilMoisture += android_detectionHistoryData.getSoilMoisture();
//                }
//                airTemperature = airTemperature / list.size();
//                airHumidity = airHumidity / list.size();
//                carbonDioxideConcentration = carbonDioxideConcentration / list.size();
//                soilMoisture = soilMoisture / list.size();
//            }
            airTemperature = 20.2;
            airHumidity = 95.7;
            carbonDioxideConcentration = 824;
            ammoniaConcentration = 0.77;
            DecimalFormat df1 = new DecimalFormat(".#");
            DecimalFormat df2 = new DecimalFormat(".#");
            data.setv("airTemperature", df1.format(airTemperature));
            data.setv("airHumidity", df1.format(airHumidity));
            data.setv("carbonDioxideConcentration", carbonDioxideConcentration);
            data.setv("soilMoisture", df2.format(ammoniaConcentration));
            return map.setv("code", 200).setv("info", "success").setv("data", data);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return map.setv("code", 200).setv("info", "error").setv("data", data);
        }
    }

    @At
    @AdaptBy(type = JsonAdaptor.class)
    public NutMap history(@Param("timeInterval") long timeInterval) {
        NutMap map = new NutMap();
        NutMap data = new NutMap();
        List<Object> axis = new ArrayList<>();
        List<Object> dataList = new ArrayList<>();
        data.setv("axis", axis);
        data.setv("data", dataList);
        map.setv("code", 200).setv("info", "success").setv("data", data);
        axis.add("19日");
        axis.add("29日");
        axis.add("09日");
        axis.add("19日");
        axis.add("29日");
        axis.add("08日");
        axis.add("18日");
        List<Object> list1 = new ArrayList<>();
        List<Object> list2 = new ArrayList<>();
        List<Object> list3 = new ArrayList<>();
        List<Object> list4 = new ArrayList<>();
        list1.add("20.1");
        list1.add("22.3");
        list1.add("19.5");
        list1.add("30.5");
        list1.add("25.3");
        list1.add("26.3");
        list1.add("27.6");

        list2.add("106.2");
        list2.add("40.6");
        list2.add("107.4");
        list2.add("80.5");
        list2.add("102.3");
        list2.add("100.3");
        list2.add("96.6");

        list3.add(1079);
        list3.add(1001);
        list3.add(900);
        list3.add(1200);
        list3.add(1300);
        list3.add(1099);
        list3.add(1100);

        list4.add("40.2");
        list4.add("20.6");
        list4.add("45.8");
        list4.add("55.5");
        list4.add("64.3");
        list4.add("60.3");
        list4.add("57.4");

        dataList.add(list1);
        dataList.add(list2);
        dataList.add(list3);
        dataList.add(list4);

        return map;
    }

}
