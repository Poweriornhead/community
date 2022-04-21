package lift.ydq.commuity.community.dto;

import lift.ydq.commuity.community.model.User;
import lombok.Data;

/**
 * @author YDQ
 * @create 2022-04-14 13:54
 */
@Data
public class CommentDTO {
    private Long id;
    private Long parentId;
    private Integer type;
    private Long commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private String content;
    private User user;
    private Integer commentCount;
}
