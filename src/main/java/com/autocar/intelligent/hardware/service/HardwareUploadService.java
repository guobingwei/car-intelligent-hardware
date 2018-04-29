package com.autocar.intelligent.hardware.service;

import com.autocar.intelligent.hardware.domain.model.CarIntelligentHardwareUploadModel;

import java.util.List;

/**
 * Created by guobingwei on 18/4/29.
 */
public interface HardwareUploadService {

    /***
     * 获取数据上报列表，按时间倒序排列
     * @return List<CarIntelligentHardwareUploadModel> 数据列表
     */
    List<CarIntelligentHardwareUploadModel> getList();

    /***
     * 插入数据
     * @param hardwareUploadModel
     * @return success 是否成功
     */
    boolean insert(CarIntelligentHardwareUploadModel hardwareUploadModel);
}
