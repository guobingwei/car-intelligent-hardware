package com.autocar.intelligent.hardware.common.convert;

import com.autocar.intelligent.hardware.domain.model.CarDataUploadModel;
import com.autocar.intelligent.hardware.domain.view.CarDataVO;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guobingwei on 18/4/29.
 */
public class UploadDataConvert {

    public static List<CarDataVO.Data> modelToVo(List<CarDataUploadModel> hardwareUploadModels) {
        if (CollectionUtils.isEmpty(hardwareUploadModels)) {
            return new ArrayList<>();
        }

        List<CarDataVO.Data> carDataVOS = new ArrayList<>();
        hardwareUploadModels.forEach(t -> {
            CarDataVO.Data data = new CarDataVO.Data();
            data.setBackDistance(t.getBackDistance());
            data.setCtime(t.getCtime());
            data.setTemperature(t.getTemperature());
            carDataVOS.add(data);
        });
        return carDataVOS;
    }
}
