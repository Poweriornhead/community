package lift.ydq.commuity.community.dto;

import lombok.Data;

import java.util.List;

/**
 * @author YDQ
 * @create 2022-04-18 18:02
 */
@Data
public class TagDTO {
    private String categoryName;
    private List<String> tags;
}
