package lift.ydq.commuity.community.exception;

/**
 * @author YDQ
 * @create 2022-04-09 17:58
 */
public class CustomizeException extends RuntimeException{
    private String message;
    private Integer code;

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }


    @Override
    public String getMessage(){
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
