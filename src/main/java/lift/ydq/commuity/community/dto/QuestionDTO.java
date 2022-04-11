package lift.ydq.commuity.community.dto;

import lift.ydq.commuity.community.model.User;
import lombok.Data;

/**
 * @author YDQ
 * @create 2022-04-02 15:04
 */
@Data
public class QuestionDTO {
    private Long id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Long creator;
    private Integer viewCount;
    private Integer likeCount;
    private Integer commentCount;
    private User user;
}
