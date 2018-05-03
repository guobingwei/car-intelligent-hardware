package com.autocar.intelligent.hardware.dao.groovy

import com.autocar.intelligent.hardware.config.DataSourceConfig
import groovy.sql.Sql
import org.springframework.stereotype.Service

import javax.annotation.Resource

/**
 * Created by guobingwei on 18/4/29.
 */
@Service
class HardwareUploadMapper {

    @Resource
    private def dateSource

    public List getList() {
        dateSource.rows("select * from car_intelligent_hardware_upload order by id desc limit 20000")
    }

    public boolean insert(Map obj) {
        return dateSource.executeInsert("INSERT INTO car_intelligent_hardware_upload(`temperature`, `back_distance`, `ctime`) VALUES (?, ?, ?)", "a", "b", new Date())
    }
}
