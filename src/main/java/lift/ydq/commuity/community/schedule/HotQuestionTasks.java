package lift.ydq.commuity.community.schedule;

import lift.ydq.commuity.community.cacha.HotQuestionCache;
import lift.ydq.commuity.community.mapper.QuestionMapper;
import lift.ydq.commuity.community.model.Question;
import lift.ydq.commuity.community.model.QuestionExample;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author YDQ
 * @create 2022-04-25 19:38
 */
@Component
@Slf4j
public class HotQuestionTasks {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private HotQuestionCache questionCache;

    @Scheduled(fixedRate = 1000L * 60 * 60 * 24 * 7)
    public void hotQuestionSchedule() {
        int offset = 0;
        int limit = 20;
        log.info("hotQuestionSchedule start {}", new Date());
        List<Question> list = new ArrayList<>();
        Map<Long, Integer> priorities = new HashMap<>();
        while (offset == 0 || list.size() == limit) {
            list = questionMapper.selectByExampleWithRowbounds(new QuestionExample(), new RowBounds(offset, limit));
            for (Question question : list) {
                Integer priority = priorities.get(question.getId());
                if (priority != null) {
                    priorities.put(question.getId(), priority + 5 + question.getViewCount());
                } else {
                    priorities.put(question.getId(), 5 + question.getViewCount());
                }
            }
            offset += limit;
        }

//        priorities.forEach(
//                (k, v)->{
//                System.out.print(k);
//                System.out.print(":");
//                System.out.print(v);
//                System.out.println();
//        });
        questionCache.updateQuestion(priorities);
        log.info("hotQuestionSchedule stop {}", new Date());

    }
}
