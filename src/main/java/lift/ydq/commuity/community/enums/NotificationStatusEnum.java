package lift.ydq.commuity.community.enums;

/**
 * @author YDQ
 * @create 2022-04-21 14:54
 */
public enum NotificationStatusEnum {
    UNREAD(0),READ(1);
    private int status;

    public int getStatus() {
        return status;
    }

    NotificationStatusEnum(int status) {
        this.status = status;
    }
}
