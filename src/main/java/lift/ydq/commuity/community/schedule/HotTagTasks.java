package lift.ydq.commuity.community.schedule;

import lift.ydq.commuity.community.cacha.HotTagCacha;
import lift.ydq.commuity.community.mapper.QuestionMapper;
import lift.ydq.commuity.community.model.Question;
import lift.ydq.commuity.community.model.QuestionExample;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author YDQ
 * @create 2022-04-25 11:42
 */
@Component
@Slf4j
public class HotTagTasks {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private HotTagCacha hotTagCacha;
    @Scheduled(fixedRate = 5000)
    public void hotTagSchedule(){
        int offset = 0;
        int limit = 20;
        log.info("hotTagSchedule start {}",new Date());
        List<Question> list = new ArrayList<>();
        Map<String, Integer> priorities = new HashMap<>();
        while (offset == 0 || list.size() == limit){
            list = questionMapper.selectByExampleWithRowbounds(new QuestionExample(), new RowBounds(offset,limit));
            for (Question question : list) {
              String[] tags = StringUtils.split(question.getTag(),",");
                for (String tag : tags) {
                    Integer priority = priorities.get(tag);
                    if (priority != null){
                        priorities.put(tag, priority + 5 + question.getCommentCount());
                    }else {
                        priorities.put(tag, 5 + question.getCommentCount());
                    }
                }
            }
        offset += limit;
        }

        priorities.forEach(
                (k, v)->{
                System.out.print(k);
                System.out.print(":");
                System.out.print(v);
                System.out.println();
        });
        hotTagCacha.updateTags(priorities);

        log.info("hotTagSchedule stop {}",new Date());

    }
}
