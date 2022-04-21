package lift.ydq.commuity.community.dto;

import lombok.Data;


/**
 * @author YDQ
 * @create 2022-04-21 15:25
 */
@Data
public class NotificationDTO {
    private Long id;
    private Long gmtCreate;
    private Integer status;
    private Long notifier;
    private String notifierName;
    private String outerTitle;
    private String typeName;
    private Long outerid;
    private Integer type;

}
