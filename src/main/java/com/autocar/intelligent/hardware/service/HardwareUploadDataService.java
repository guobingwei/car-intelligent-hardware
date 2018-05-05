package com.autocar.intelligent.hardware.service;

import com.autocar.intelligent.hardware.domain.model.CarDataUploadModel;

import java.util.List;

/**
 * Created by guobingwei on 18/4/29.
 */
public interface HardwareUploadDataService {

    /***
     * 获取数据上报列表，按时间倒序排列
     * @return List<CarDataUploadModel> 数据列表
     */
    List<CarDataUploadModel> getList();

    /***
     * 插入数据
     * @param hardwareUploadModel
     * @return success 是否成功
     */
    boolean insert(CarDataUploadModel hardwareUploadModel);
}
