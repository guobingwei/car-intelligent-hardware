package com.autocar.intelligent.hardware.dao.groovy

import com.autocar.intelligent.hardware.domain.model.CarDataUploadModel
import org.springframework.stereotype.Service

import javax.annotation.Resource

/**
 * Created by guobingwei on 18/4/29.
 */
@Service
class HardwareUploadMapper {

    @Resource
    private def dateSource

    public List<CarDataUploadModel> getList() {
        return dateSource.rows("select * from car_intelligent_hardware_upload order by id desc limit 20000")
    }

    public boolean insert(CarDataUploadModel carDataUploadModel) {
        return dateSource.executeInsert("INSERT INTO car_intelligent_hardware_upload(`temperatures`, `back_distance`, `ctime`) " +
                "VALUES (?, ?, ?)",
                carDataUploadModel.temperature, carDataUploadModel.backDistance, new Date())
    }
}
