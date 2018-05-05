package com.autocar.intelligent.hardware.service.impl;

import com.autocar.intelligent.hardware.dao.groovy.HardwareUploadMapper;
import com.autocar.intelligent.hardware.domain.model.CarDataUploadModel;
import com.autocar.intelligent.hardware.service.HardwareUploadDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by guobingwei on 18/4/29.
 */
@Service
public class HardwareUploadDataServiceImpl implements HardwareUploadDataService {

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
