package lift.ydq.commuity.community.exception;

/**
 * @author YDQ
 * @create 2022-04-09 18:13
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode{
    QUESTION_NOT_FOUND(2001,"贴子不见了，换一个吧"),
    TARGET_PARAM_NOT_FOUND(2002,"为选中然后问题进行评论或回复"),
    NO_LOGIN(2003,"未登录不能评论"),
    SYS_ERROR(2004,"出错了。。。。"),
    TARGET_PARAM_WRONG(2005,"评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006,"回复的评论不存在"),
    COMMENT_IS_EMPTY(2007,"回复内容不能为空");




    @Override
    public String getMessage(){
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    private Integer code;
    private String message;

    CustomizeErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
