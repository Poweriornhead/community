package lift.ydq.commuity.community.enums;

import java.lang.reflect.Type;

/**
 * @author YDQ
 * @create 2022-04-10 16:59
 */
public enum CommentTypeEnum {
    QUESTION(1),
    COMMENT(2);
    private Integer type;

    public static boolean isExist(Integer type) {
        for ( CommentTypeEnum commentTypeEnum : CommentTypeEnum.values()){
            if(commentTypeEnum.getType() == type)
                return true;
        }
        return false;
    }

    public Integer getType(){
        return type;
    }
    CommentTypeEnum(Integer type) {
        this.type = type;
    }
}
