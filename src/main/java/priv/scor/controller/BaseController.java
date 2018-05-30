package priv.scor.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import priv.scor.common.Result;
import priv.scor.common.exception.AppException;
import priv.scor.common.exception.Code;

public abstract class BaseController {
    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);
    
    @ExceptionHandler(value=Exception.class)
    @ResponseBody
    public Result<String> exception(Exception e){
        Result<String> result = new Result<>();
        if(e instanceof AppException){
            AppException ae = (AppException)e;
            result.setMessage(ae.getMessage());
            Code code = ae.getCode();
            result.setCode(code);
            if(!Code.NOT_LOGIN .equals(code)){
                logger.error(ae.getMessage(),ae);
            }
        }else {
            logger.error(e.getMessage(),e);
            result.setMessage(e.getMessage());
            result.setCode(Code.FAIL);
        }
        
        return result;
    }

}
