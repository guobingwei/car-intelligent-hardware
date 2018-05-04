package com.autocar.intelligent.hardware.provider.controller;

import com.autocar.intelligent.hardware.domain.model.CarDataUploadModel;
import com.autocar.intelligent.hardware.domain.view.CarDataVO;
import com.autocar.intelligent.hardware.provider.response.ApiResponse;
import com.autocar.intelligent.hardware.service.HardwareUploadService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by guobingwei on 18/4/29.
 */
@RestController
@RequestMapping(value = "/autocar")
public class DataController extends BaseController {

    @Resource
    private HardwareUploadService hardwareUploadService;

    /***
     * 健康检查接口
     * @return
     */
    @GetMapping(value = "/test/alive")
    public ApiResponse<Boolean> test() {
        return ApiResponse.buildSuccess();
    }

    /***
     * 查询上报数据列表
     * @return
     */
    @GetMapping(value = "/upload/list")
    public ApiResponse<CarDataVO> getList() {
        try {
            List<CarDataUploadModel> dataList = hardwareUploadService.getList();
            return ApiResponse.buildSuccess(dataList);
        } catch (Exception e) {
            return getExceptionResponse("查询上报数据错误", e);
        }
    }

    /***
     * 数据上报
     * @return
     */
    @GetMapping(value = "/data/upload")
    public ApiResponse<Boolean> uploadData() {
        return ApiResponse.buildSuccess();
    }
}
