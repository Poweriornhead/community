package lift.ydq.commuity.community.dto;

import lombok.Data;

/**
 * @author YDQ
 * @create 2022-04-27 15:58
 */
@Data
public class FollowDTO {
    private Long id;
    private Long followers;
    private Long requester;
    private Long gmtCreate;
    private Boolean isFollow = false;
}
