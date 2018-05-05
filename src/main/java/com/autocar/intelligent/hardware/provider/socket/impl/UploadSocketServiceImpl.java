package com.autocar.intelligent.hardware.provider.socket.impl;

import com.alibaba.fastjson.JSON;
import com.autocar.intelligent.hardware.domain.model.CarDataUploadModel;
import com.autocar.intelligent.hardware.provider.socket.UploadSocketService;
import com.autocar.intelligent.hardware.service.HardwareUploadDataService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by guobingwei on 18/5/4.
 */
@Service
public class UploadSocketServiceImpl implements UploadSocketService {
    private static Logger logger = LoggerFactory.getLogger(UploadSocketServiceImpl.class);

    @Resource
    private HardwareUploadDataService hardwareUploadService;

    @Override
    public Object handleReceive(String JSONString) {
        if(StringUtils.isEmpty(JSONString)) {
            logger.warn("handleReceive str=null.....");
            return null;
        }

        // 数据上报
        CarDataUploadModel carDataUploadModel = JSON.parseObject(JSONString, CarDataUploadModel.class);
        hardwareUploadService.insert(carDataUploadModel);
        return "success";
    }
}
