package priv.scor.common.exception;

/**
 * @createBy Edgar
 * @date 2018/5/24
 * @Package_name priv.scor.common.exception
 */
public class AppException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 106618982690055608L;
    private Code code;

    public AppException(Code code){
        super(code.getText());
        this.code = code;
    }

    public AppException(String arg0){
        super(arg0);
        this.code = Code.FAIL;
    }

    public AppException(Code code, String arg0) {
        super(arg0);
        this.code = code;
    }

    public Code getCode() {
        return code;
    }

}
