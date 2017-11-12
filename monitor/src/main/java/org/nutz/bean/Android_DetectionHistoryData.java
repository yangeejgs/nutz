package org.nutz.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * Created by yangyang on 2017/11/12.
 */
@Table("t_android_detectionhistorydata")
public class Android_DetectionHistoryData {

    @Id
    private Integer id;

    /**
     * 检测项目
     */
    @Column
    private String detectionItem;

    /**
     * 样品名称
     */
    @Column
    private String sampleName;

    /**
     * 结果判定
     */
    @Column
    private String detectionResult;

    /**
     * 企业标识 前期未提供接口下传空值
     */
    @Column
    private String enterpriseUnid;

    /**
     * 企业名称 前期先不提供接口，请直接传你们录入的企业名称
     */
    @Column
    private String enterpriseName;

    /**
     * 检测日期含时间 时间格式YYYY-mm-dd hh:MM:ss 如果未传系统会自动默认日期最小值
     */
    @Column
    private String detectionDate;

    /**
     * 限量值（标准值）
     */
    @Column
    private String standardValue;

    /**
     * 抑制率
     */
    @Column
    private String inhibitionRate;

    /**
     * 浓度
     */
    @Column
    private String concentration;

    /**
     * 检测单位
     */
    @Column
    private String detectionUnitName;

    /**
     * 检测人员 调用登录接口后返回值中的Uuid属性  修改了监测人员uuid
     */
    @Column
    private String personUuid;

    /**
     * 检测人员 调用登录接口后返回值中的Name属性
     */
    @Column
    private String personName;

    /**
     * 设备序列号
     */
    @Column
    private String deviceSerialNumber;

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
}
