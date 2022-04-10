package lift.ydq.commuity.community.exception;

/**
 * @author YDQ
 * @create 2022-04-09 18:13
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode{
    QUESTION_NOT_FOUND("贴子不见了，换一个吧");

    @Override
    public String getMessage(){
        return message;
    }

    private String message;

    CustomizeErrorCode(String message) {
        this.message = message;
    }
}
