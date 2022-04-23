package lift.ydq.commuity.community.dto;

import lombok.Data;

/**
 * @author YDQ
 * @create 2022-04-23 16:15
 */
@Data
public class QuestionQueryDTO {
    private Integer page;
    private Integer size;
    private String search;
}
