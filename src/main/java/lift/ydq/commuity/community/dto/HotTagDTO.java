package lift.ydq.commuity.community.dto;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

/**
 * @author YDQ
 * @create 2022-04-25 13:40
 */
@Data
public class HotTagDTO implements Comparable{
    private String name;
    private Integer priority;
    private Long id;

    @Override
    public int compareTo(@NotNull Object o) {
        return this.getPriority() - ((HotTagDTO) o).getPriority();
    }
}
