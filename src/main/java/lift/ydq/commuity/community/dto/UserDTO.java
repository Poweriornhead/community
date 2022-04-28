package lift.ydq.commuity.community.dto;

import lombok.Data;

/**
 * @author YDQ
 * @create 2022-04-26 12:00
 */
@Data
public class UserDTO {
    private Long id;
    private String accountId;
    private String avatarUrl;
    private String name;
    private Integer questionCount;
    private Integer commentCount;
    private Integer followerCount;
}
