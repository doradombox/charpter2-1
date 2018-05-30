package priv.scor.common;

import io.swagger.annotations.ApiModelProperty;
import priv.scor.common.exception.Code;

/**
 * @createBy Edgar
 * @date 2018/5/24
 * @Package_name priv.scor.common
 * @describe 统一返回结果集
 */
public class Result<T> {

    @ApiModelProperty(required = true)
    private Code code = Code.SUCCESS;

    @ApiModelProperty(value="描述")
    private String message = "操作成功";

    @ApiModelProperty("具体数据")
    private T data;

    public Code getCode(){
        return this.code;
    }

    public void setCode(Code code){
        this.code = code;
    }

    public String getMessage(){
        if(null == message){
            if(null != code){
                this.message = code.getText();
            }
        }

        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData(){
        return this.data;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }

    public static <T> Result<T> success()
    {
        return success(null);
    }

    public static <T> Result<T> success(T data)
    {
        Result<T> res = new Result<>();
        res.setCode(Code.SUCCESS);
        res.setMessage(Code.SUCCESS.getText());
        res.setData(data);
        return res;
    }

    public static <T> Result<T> fail(Code code)
    {
        Result<T> res = new Result<>();
        res.setCode(Code.FAIL);
        res.setMessage(Code.FAIL.getText());
        return res;
    }

}
