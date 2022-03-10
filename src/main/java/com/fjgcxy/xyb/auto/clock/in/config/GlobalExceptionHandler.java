package com.fjgcxy.xyb.auto.clock.in.config;



import com.fjgcxy.xyb.auto.clock.in.exception.BaseException;
import com.fjgcxy.xyb.auto.clock.in.exception.NoAuthException;
import com.fjgcxy.xyb.auto.clock.in.model.vo.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 身份异常 - Token相关
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({NoAuthException.class})
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<String> exceptionAdviceNoAuth(Exception ex) {
        return BaseResponse.noAuth();
    }

    /**
     * 业务异常处理
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(BaseException.class)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<String> exceptionAdvice(BaseException ex) {
        return BaseResponse.no(ex.getMessage());
    }

    /**
     * 业务异常处理
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(InterruptedException.class)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<String> exceptionAdvice(InterruptedException ex) {
        return BaseResponse.no("系统繁忙，请稍后再试。");
    }


    /**
     * 全局异常处理
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<String> exceptionAdvice(Exception ex) {
        ex.printStackTrace();
        return BaseResponse.no("服务器出错啦 ~");
    }


    /**
     * 用来处理bean validation异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public BaseResponse<String> resolveConstraintViolationException(ConstraintViolationException ex) {
        ex.printStackTrace();
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        if (!CollectionUtils.isEmpty(constraintViolations)) {
            StringBuilder msgBuilder = new StringBuilder();
            for (ConstraintViolation constraintViolation : constraintViolations) {
                msgBuilder.append(constraintViolation.getMessage()).append(",");
            }
            String errorMessage = msgBuilder.toString();
//            if (errorMessage.length() > 1) {
//                errorMessage = errorMessage.substring(0, errorMessage.length() - 1);
//                errorMessage = errorMessage.split(",")[0];
//            }
            return BaseResponse.no(errorMessage.substring(0, errorMessage.length() - 1));
        }
        return BaseResponse.no(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse<String> resolveMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
//

        List<ObjectError> objectErrors = ex.getBindingResult().getAllErrors();
        if (!CollectionUtils.isEmpty(objectErrors)) {
            StringBuilder msgBuilder = new StringBuilder();
            for (ObjectError objectError : objectErrors) {
                FieldError fieldError = (FieldError) objectError;
                msgBuilder.append("【").append(fieldError.getField()).append("】 ").append(objectError.getDefaultMessage()).append(",");
            }
            String errorMessage = msgBuilder.toString();
//            if (errorMessage.length() > 1) {
//                errorMessage = errorMessage.substring(0, errorMessage.length() - 1);
//                errorMessage = errorMessage.split(",")[0];
//            }
            return BaseResponse.no(errorMessage.substring(0, errorMessage.length() - 1));
        }
        return BaseResponse.no(ex.getMessage());
    }
}
