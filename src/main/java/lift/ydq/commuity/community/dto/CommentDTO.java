package lift.ydq.commuity.community.dto;

import lombok.Data;

/**
 * @author YDQ
 * @create 2022-04-10 16:09
 */
@Data
public class CommentDTO {
    private Long parentId;
    private String content;
    private Integer type;
}
