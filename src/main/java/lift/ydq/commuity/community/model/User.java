package lift.ydq.commuity.community.model;

import lombok.Data;

/**
 * @author YDQ
 * @create 2022-03-31 19:19
 */
@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;

}
