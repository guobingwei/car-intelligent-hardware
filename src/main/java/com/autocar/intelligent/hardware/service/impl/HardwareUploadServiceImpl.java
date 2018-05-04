package com.autocar.intelligent.hardware.service.impl;

import com.autocar.intelligent.hardware.dao.groovy.HardwareUploadMapper;
import com.autocar.intelligent.hardware.domain.model.CarDataUploadModel;
import com.autocar.intelligent.hardware.service.HardwareUploadService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by guobingwei on 18/4/29.
 */
@Service
public class HardwareUploadServiceImpl implements HardwareUploadService {

    @Resource
    private HardwareUploadMapper hardwareUploadMapper;

    @Override
    public List<CarDataUploadModel> getList() {
        return hardwareUploadMapper.getList();
    }

    @Override
    public boolean insert(CarDataUploadModel carDataUploadModel) {
        return hardwareUploadMapper.insert(carDataUploadModel);
    }
}
