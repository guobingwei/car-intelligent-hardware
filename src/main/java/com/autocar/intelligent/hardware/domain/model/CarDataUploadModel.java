package com.autocar.intelligent.hardware.domain.model;


import com.alibaba.fastjson.JSON;

import java.util.Date;

/**
 * Created by guobingwei on 18/4/29.
 */

public class CarDataUploadModel {
    private Integer id;
    private Double temperature;
    private Double backDistance;
    private Date mtime;
    private Date ctime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getBackDistance() {
        return backDistance;
    }

    public void setBackDistance(Double backDistance) {
        this.backDistance = backDistance;
    }

    public Date getMtime() {
        return mtime;
    }

    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    @Override
    public String toString() {
        return "CarDataUploadModel{" +
                "id=" + id +
                ", temperature=" + temperature +
                ", backDistance=" + backDistance +
                ", mtime=" + mtime +
                ", ctime=" + ctime +
                '}';
    }

    public static void main(String[] args) {
        CarDataUploadModel carIntelligentHardwareUploadModel = new CarDataUploadModel();
        carIntelligentHardwareUploadModel.setBackDistance(433.32);
        carIntelligentHardwareUploadModel.setTemperature(29.33);
        System.out.println(JSON.toJSONString(carIntelligentHardwareUploadModel));
    }
}
