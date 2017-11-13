package org.nutz.bean;

import org.nutz.dao.entity.annotation.*;

/**
 * Created by yangyang7 on 2017/11/13.
 */

@Table("t_record")
public class Record {

    @Id
    private Integer id;

    @Name
    private String RecID;

    @Column
    private String ItemIndex;

    @Column
    private String ItemName;

    @Column
    private String SampleName;

    @Column
    private String ChannelID;

    @Column
    private String CalcResult;

    @Column
    private String TxtResult;

    @Column
    private String Supplier;

    @Column
    private String AbsorbX;

    @Column
    private String TestLimit;

    @Column
    private String CheckResult;

    @Column
    private String UintName;

    @Column
    private String Uint;

    @Column
    private String ProducePlace;

    @Column
    private String CodeId;

    @Column
    private String CheckPerson;

    @Column
    private String TestTime;

    @Column
    private String TestDate;

    @Column
    private String Temperature;

    @Column
    private String Humidity;

    @Column
    private String GPS;

    @Column
    private String NcItemName;

    @Column
    private String NcValue;

    @Column
    private String Result;

    @Column
    private String TypeName;

    @Column
    private String memo;

    // 任务号
    @Column
    private String DTI_ID;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRecID() {
        return RecID;
    }

    public void setRecID(String recID) {
        RecID = recID;
    }

    public String getItemIndex() {
        return ItemIndex;
    }

    public void setItemIndex(String itemIndex) {
        ItemIndex = itemIndex;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getSampleName() {
        return SampleName;
    }

    public void setSampleName(String sampleName) {
        SampleName = sampleName;
    }

    public String getChannelID() {
        return ChannelID;
    }

    public void setChannelID(String channelID) {
        ChannelID = channelID;
    }

    public String getCalcResult() {
        return CalcResult;
    }

    public void setCalcResult(String calcResult) {
        CalcResult = calcResult;
    }

    public String getTxtResult() {
        return TxtResult;
    }

    public void setTxtResult(String txtResult) {
        TxtResult = txtResult;
    }

    public String getSupplier() {
        return Supplier;
    }

    public void setSupplier(String supplier) {
        Supplier = supplier;
    }

    public String getAbsorbX() {
        return AbsorbX;
    }

    public void setAbsorbX(String absorbX) {
        AbsorbX = absorbX;
    }

    public String getTestLimit() {
        return TestLimit;
    }

    public void setTestLimit(String testLimit) {
        TestLimit = testLimit;
    }

    public String getCheckResult() {
        return CheckResult;
    }

    public void setCheckResult(String checkResult) {
        CheckResult = checkResult;
    }

    public String getUintName() {
        return UintName;
    }

    public void setUintName(String uintName) {
        UintName = uintName;
    }

    public String getUint() {
        return Uint;
    }

    public void setUint(String uint) {
        Uint = uint;
    }

    public String getProducePlace() {
        return ProducePlace;
    }

    public void setProducePlace(String producePlace) {
        ProducePlace = producePlace;
    }

    public String getCodeId() {
        return CodeId;
    }

    public void setCodeId(String codeId) {
        CodeId = codeId;
    }

    public String getCheckPerson() {
        return CheckPerson;
    }

    public void setCheckPerson(String checkPerson) {
        CheckPerson = checkPerson;
    }

    public String getTestTime() {
        return TestTime;
    }

    public void setTestTime(String testTime) {
        TestTime = testTime;
    }

    public String getTestDate() {
        return TestDate;
    }

    public void setTestDate(String testDate) {
        TestDate = testDate;
    }

    public String getTemperature() {
        return Temperature;
    }

    public void setTemperature(String temperature) {
        Temperature = temperature;
    }

    public String getHumidity() {
        return Humidity;
    }

    public void setHumidity(String humidity) {
        Humidity = humidity;
    }

    public String getGPS() {
        return GPS;
    }

    public void setGPS(String GPS) {
        this.GPS = GPS;
    }

    public String getNcItemName() {
        return NcItemName;
    }

    public void setNcItemName(String ncItemName) {
        NcItemName = ncItemName;
    }

    public String getNcValue() {
        return NcValue;
    }

    public void setNcValue(String ncValue) {
        NcValue = ncValue;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        Result = result;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getDTI_ID() {
        return DTI_ID;
    }

    public void setDTI_ID(String DTI_ID) {
        this.DTI_ID = DTI_ID;
    }
}
