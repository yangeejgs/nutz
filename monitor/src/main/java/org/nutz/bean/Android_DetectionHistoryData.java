package org.nutz.bean;

import org.nutz.dao.entity.annotation.*;

/**
 * Created by yangyang on 2017/11/12.
 */
@Table("t_android_detectionhistorydata")
public class Android_DetectionHistoryData extends BasePojo {

    @Id
    private Integer id;

    /**
     * 检测项目
     */
    @Column("detection_item")
    private String detectionItem;

    /**
     * 样品名称
     */
    @Column("sample_name")
    private String sampleName;

    /**
     * 结果判定
     */
    @Column("detection_result")
    private String detectionResult;

    /**
     * 企业标识 前期未提供接口下传空值
     */
    @Column("enterprise_unid")
    private String enterpriseUnid;

    /**
     * 企业名称 前期先不提供接口，请直接传你们录入的企业名称
     */
    @Column("enterprise_name")
    private String enterpriseName;

    /**
     * 检测日期含时间 时间格式YYYY-mm-dd hh:MM:ss 如果未传系统会自动默认日期最小值
     */
    @Column("detection_date")
    private String detectionDate;

    /**
     * 限量值（标准值）
     */
    @Column("standard_value")
    private String standardValue;

    /**
     * 抑制率
     */
    @Column("inhibition_rate")
    private String inhibitionRate;

    /**
     * 浓度
     */
    @Column("concentration")
    private String concentration;

    /**
     * 检测单位
     */
    @Column("detection_unit_name")
    private String detectionUnitName;

    /**
     * 检测人员 调用登录接口后返回值中的Uuid属性  修改了监测人员uuid
     */
    @Column("person_uuid")
    private String personUuid;

    /**
     * 检测人员 调用登录接口后返回值中的Name属性
     */
    @Column("person_name")
    private String personName;

    /**
     * 设备序列号
     */
    @Column("device_serial_number")
    private String deviceSerialNumber;

    /**
     * 空气温度
     */
    @Column("air_temperature")
    @ColDefine(type = ColType.FLOAT, width = 10, precision = 1)
    @Default("0")
    private Double airTemperature;

    /**
     * 空气湿度
     */
    @Column("air_humidity")
    @ColDefine(type = ColType.FLOAT, width = 10, precision = 1)
    @Default("0")
    private Double airHumidity;

    /**
     * 二氧化碳浓度
     */
    @Column("carbon_dioxide_concentration")
    @Default("0")
    private Integer carbonDioxideConcentration;

    /**
     * 土壤水分
     */
    @Column("soil_moisture")
    @ColDefine(type = ColType.FLOAT, width = 10, precision = 1)
    @Default("0")
    private Double soilMoisture;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDetectionItem() {
        return detectionItem;
    }

    public void setDetectionItem(String detectionItem) {
        this.detectionItem = detectionItem;
    }

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public String getDetectionResult() {
        return detectionResult;
    }

    public void setDetectionResult(String detectionResult) {
        this.detectionResult = detectionResult;
    }

    public String getEnterpriseUnid() {
        return enterpriseUnid;
    }

    public void setEnterpriseUnid(String enterpriseUnid) {
        this.enterpriseUnid = enterpriseUnid;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getDetectionDate() {
        return detectionDate;
    }

    public void setDetectionDate(String detectionDate) {
        this.detectionDate = detectionDate;
    }

    public String getStandardValue() {
        return standardValue;
    }

    public void setStandardValue(String standardValue) {
        this.standardValue = standardValue;
    }

    public String getInhibitionRate() {
        return inhibitionRate;
    }

    public void setInhibitionRate(String inhibitionRate) {
        this.inhibitionRate = inhibitionRate;
    }

    public String getConcentration() {
        return concentration;
    }

    public void setConcentration(String concentration) {
        this.concentration = concentration;
    }

    public String getDetectionUnitName() {
        return detectionUnitName;
    }

    public void setDetectionUnitName(String detectionUnitName) {
        this.detectionUnitName = detectionUnitName;
    }

    public String getPersonUuid() {
        return personUuid;
    }

    public void setPersonUuid(String personUuid) {
        this.personUuid = personUuid;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getDeviceSerialNumber() {
        return deviceSerialNumber;
    }

    public void setDeviceSerialNumber(String deviceSerialNumber) {
        this.deviceSerialNumber = deviceSerialNumber;
    }

    public Double getAirTemperature() {
        return airTemperature;
    }

    public void setAirTemperature(Double airTemperature) {
        this.airTemperature = airTemperature;
    }

    public Double getAirHumidity() {
        return airHumidity;
    }

    public void setAirHumidity(Double airHumidity) {
        this.airHumidity = airHumidity;
    }

    public Integer getCarbonDioxideConcentration() {
        return carbonDioxideConcentration;
    }

    public void setCarbonDioxideConcentration(Integer carbonDioxideConcentration) {
        this.carbonDioxideConcentration = carbonDioxideConcentration;
    }

    public Double getSoilMoisture() {
        return soilMoisture;
    }

    public void setSoilMoisture(Double soilMoisture) {
        this.soilMoisture = soilMoisture;
    }
}
