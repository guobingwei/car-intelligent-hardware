package com.autocar.intelligent.hardware.domain.view;

import java.util.Date;
import java.util.List;

/**
 * Created by guobingwei on 18/4/29.
 */
public class CarDataVO {
    private List<Data> dataList;

    public List<Data> getDataList() {
        return dataList;
    }

    public void setDataList(List<Data> dataList) {
        this.dataList = dataList;
    }


    public  static class Data {
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
            return "Data{" +
                    "id=" + id +
                    ", temperature=" + temperature +
                    ", backDistance=" + backDistance +
                    ", mtime=" + mtime +
                    ", ctime=" + ctime +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "CarDataVO{" +
                "dataList=" + dataList +
                '}';
    }
}
