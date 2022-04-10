package lift.ydq.commuity.community.exception;

/**
 * @author YDQ
 * @create 2022-04-09 17:58
 */
public class CustomizeException extends RuntimeException{
    private String message;

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.message = errorCode.getMessage();
    }
    public CustomizeException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
