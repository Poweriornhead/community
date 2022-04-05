package lift.ydq.commuity.community.model;

import lombok.Data;
import org.w3c.dom.Text;

/**
 * @author YDQ
 * @create 2022-04-01 14:15
 */
@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer likeCount;
    private Integer commentCount;
}
