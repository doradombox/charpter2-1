package priv.scor.common.exception;

/**
 * @createBy Edgar
 * @date 2018/5/24
 * @Package_name priv.scor.common.exception
 */
public enum Code {
    SUCCESS("成功"),
    FAIL("失败"),
    NOT_LOGIN("未登录");

    private String text;
    private Code(String text){
        this.text = text;
    }

    public String getText(){
        return this.text;
    }

}
