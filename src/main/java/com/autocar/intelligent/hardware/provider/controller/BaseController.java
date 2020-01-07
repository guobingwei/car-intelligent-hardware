package com.autocar.intelligent.hardware.provider.controller;

import com.autocar.intelligent.hardware.domain.enums.BizExceptionEnum;
import com.autocar.intelligent.hardware.domain.exception.BizException;
import com.autocar.intelligent.hardware.provider.response.ApiResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Seven on 12/29/16 12:03
 * mail:
 */
public abstract class BaseController {
    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

//    public abstract Logger getLogger(Logger logger);

    /**
     * 公共的异常捕获，以及返回的处理
     *
     * @param prefix 日志的前缀，便于区分接口
     * @param e      异常
     * @return 返回异常时的数据
     */
    protected ApiResponse getExceptionResponse(String prefix, Exception e) {

        BizExceptionEnum resultEnum = BizExceptionEnum.SERVER_EXCEPTION;
        String errorMsg = BizExceptionEnum.SERVER_EXCEPTION.getError();
        if (e instanceof BizException) {
            BizException bizException = (BizException) e;
            if (bizException.getErrorDtlEnum() != null) {
                resultEnum = bizException.getErrorDtlEnum();
                errorMsg = resultEnum.getError();
            }
            if (StringUtils.isNotBlank(bizException.getDetailErrorMsg())) {
                errorMsg = StringUtils.isNotBlank(bizException.getDetailErrorMsg()) ? bizException.getDetailErrorMsg() : bizException.getMsg();
            }
            logger.warn(String.format("%s\t%s", StringUtils.defaultString(prefix), errorMsg));
        } else {
            logger.error(String.format("%s\t%s", StringUtils.defaultString(prefix), errorMsg), e);
        }
        return ApiResponse.buildFailure(resultEnum.getCode(), errorMsg);
    }
}
