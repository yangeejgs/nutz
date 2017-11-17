package org.nutz.module;

import org.nutz.bean.Android_DetectionHistoryData;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.*;
import org.nutz.utils.DateUtil;
import org.slf4j.Logger;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    @Inject
    private DateUtil dateUtil;

    private final static Integer NODE_NUM = 7;

    private final static long ONE_DAY = 3600 * 24 * 1000;

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
            Cnd cnd = Cnd.where("create_date_time", ">=", date);
            List<Android_DetectionHistoryData> list = dao.query(Android_DetectionHistoryData.class, cnd);
            //处理数据
            processingData(list, data);
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
        map.setv("code", 200);
        map.setv("info", "success");
        NutMap data = new NutMap();
        map.setv("data", data);
        Date date = new Date();
        //构建data数据
        if (timeInterval == ONE_DAY) { //周表
            createWeekData(timeInterval, date, data);
        } else if (timeInterval >= ONE_DAY) { //月表
            createMonthData(timeInterval, date, data);
        } else if (timeInterval < ONE_DAY) { //日表
            createDayDate(timeInterval, date, data);
        }
        return map;
    }

    private void createWeekData(long timeInterval, Date date, NutMap nutMap) {
        List<List> data = new ArrayList<>();
        List<String> axis = new ArrayList<>();
        nutMap.put("axis", axis);
        nutMap.put("data", data);
        List<Object> airTemperatureList = new ArrayList<>();
        List<Object> airHumidityList = new ArrayList<>();
        List<Object> carbonDioxideConcentrationList = new ArrayList<>();
        List<Object> soilMoistureList = new ArrayList<>();
        data.add(airTemperatureList);
        data.add(airHumidityList);
        data.add(carbonDioxideConcentrationList);
        data.add(soilMoistureList);
        long time = date.getTime();
        for (int i = NODE_NUM; i > 0; i--) {
            long time2 = time - timeInterval * i;
            Date date2 = new Date(time2);
            long time3 = 0;
            Date date3 = null;
            if (i != 0) {
                time3 = time - timeInterval * (i - 1);
                date3 = new Date(time3);
            } else {
                time3 = time - timeInterval * (i);
                date3 = new Date(time3);
            }
            axis.add(dateUtil.getWeek(date3));
            Cnd cnd = Cnd.where("create_date_time", ">=", date2).and("create_date_time", "<=", date3);
            List<Android_DetectionHistoryData> list = dao.query(Android_DetectionHistoryData.class, cnd);
            //处理数据
            NutMap averageData = new NutMap();
            processingData(list, averageData);
            airTemperatureList.add(averageData.get("airTemperature"));
            airHumidityList.add(averageData.get("airHumidity"));
            carbonDioxideConcentrationList.add(averageData.get("carbonDioxideConcentration"));
            soilMoistureList.add(averageData.get("soilMoisture"));
        }
    }

    private void createMonthData(long timeInterval, Date date, NutMap nutMap) {
        List<List> data = new ArrayList<>();
        List<String> axis = new ArrayList<>();
        nutMap.put("axis", axis);
        nutMap.put("data", data);
        List<Object> airTemperatureList = new ArrayList<>();
        List<Object> airHumidityList = new ArrayList<>();
        List<Object> carbonDioxideConcentrationList = new ArrayList<>();
        List<Object> soilMoistureList = new ArrayList<>();
        data.add(airTemperatureList);
        data.add(airHumidityList);
        data.add(carbonDioxideConcentrationList);
        data.add(soilMoistureList);
        long time = date.getTime();
        for (int i = NODE_NUM; i > 0; i--) {
            //构建axis
            //7个节点前
            long time2 = time - timeInterval * i;
            Date date2 = new Date(time2);
            long time3 = 0;
            Date date3 = null;
            if (i != 0) {
                time3 = time - timeInterval * (i - 1);
                date3 = new Date(time3);
            } else {
                time3 = time - timeInterval * (i);
                date3 = new Date(time3);
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String format = simpleDateFormat.format(new Date(time3));
            String day = format.split("-")[2] + "号";
            axis.add(day);
            Cnd cnd = Cnd.where("create_date_time", ">=", date2).and("create_date_time", "<=", date3);
            List<Android_DetectionHistoryData> list = dao.query(Android_DetectionHistoryData.class, cnd);
            //处理数据
            NutMap averageData = new NutMap();
            processingData(list, averageData);
            airTemperatureList.add(averageData.get("airTemperature"));
            airHumidityList.add(averageData.get("airHumidity"));
            carbonDioxideConcentrationList.add(averageData.get("carbonDioxideConcentration"));
            soilMoistureList.add(averageData.get("soilMoisture"));
        }
    }

    private void createDayDate(long timeInterval, Date date, NutMap nutMap) {
        List<List> data = new ArrayList<>();
        List<String> axis = new ArrayList<>();
        nutMap.put("axis", axis);
        nutMap.put("data", data);
        List<Object> airTemperatureList = new ArrayList<>();
        List<Object> airHumidityList = new ArrayList<>();
        List<Object> carbonDioxideConcentrationList = new ArrayList<>();
        List<Object> soilMoistureList = new ArrayList<>();
        data.add(airTemperatureList);
        data.add(airHumidityList);
        data.add(carbonDioxideConcentrationList);
        data.add(soilMoistureList);
        long time = date.getTime();
        for (int i = NODE_NUM - 1; i > 0; i--) {
            //构建axis
            //7个节点前
            long time2 = time - timeInterval * i;
            Date date2 = new Date(time2);
            long time3 = 0;
            Date date3 = null;
            if (i != 0) {
                time3 = time - timeInterval * (i - 1);
                date3 = new Date(time3);
            } else {
                time3 = time - timeInterval * (i);
                date3 = new Date(time3);
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
            String hour = simpleDateFormat.format(new Date(time3));
            axis.add(hour);
            Cnd cnd = Cnd.where("create_date_time", ">=", date2).and("create_date_time", "<=", date3);
            List<Android_DetectionHistoryData> list = dao.query(Android_DetectionHistoryData.class, cnd);
            //处理数据
            NutMap averageData = new NutMap();
            processingData(list, averageData);
            airTemperatureList.add(averageData.get("airTemperature"));
            airHumidityList.add(averageData.get("airHumidity"));
            carbonDioxideConcentrationList.add(averageData.get("carbonDioxideConcentration"));
            soilMoistureList.add(averageData.get("soilMoisture"));
        }
    }

    private void processingData(List<Android_DetectionHistoryData> list, NutMap data) {
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
        DecimalFormat df = new DecimalFormat("#.#");
        data.setv("airTemperature", df.format(airTemperature));
        data.setv("airHumidity", df.format(airHumidity));
        data.setv("carbonDioxideConcentration", carbonDioxideConcentration);
        data.setv("soilMoisture", df.format(soilMoisture));
    }

}
