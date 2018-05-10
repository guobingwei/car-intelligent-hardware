package com.autocar.intelligent.hardware.domain.param;

/**
 * Created by guobingwei on 18/5/10.
 */

public class CarDataUploadParam {
    private Double temperature;
    private Double backDistance;

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

    @Override
    public String toString() {
        return "CarDataUploadParam{" +
                "temperature=" + temperature +
                ", backDistance=" + backDistance +
                '}';
    }
}
