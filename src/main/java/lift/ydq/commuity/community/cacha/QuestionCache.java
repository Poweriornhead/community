package lift.ydq.commuity.community.cacha;

import lift.ydq.commuity.community.dto.QuestionDTO;
import lift.ydq.commuity.community.mapper.QuestionExtMapper;
import lift.ydq.commuity.community.mapper.UserMapper;
import lift.ydq.commuity.community.model.Question;
import lift.ydq.commuity.community.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author YDQ
 * @create 2022-04-25 15:47
 */
@Service
@Slf4j
public class QuestionCache {
    @Autowired
    private QuestionExtMapper questionExtMapper;
    @Autowired
    private UserMapper userMapper;

    private static Cache<String, List<QuestionDTO>> cacheQuestions = CacheBuilder.newBuilder()
            .maximumSize(100)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .removalListener(entity -> log.info("QUESTIONS_CACHE_REMOVE:{}", entity.getKey()))
            .build();

    public List<QuestionDTO> getStickies() {
        List<QuestionDTO> stickies;
        try {
            stickies = cacheQuestions.get("sticky", () -> {
                List<Question> questions = questionExtMapper.selectSticky();
                if (questions != null && questions.size() != 0) {
                    List<QuestionDTO> questionDTOS = new ArrayList<>();
                    for (Question question : questions) {
                        User user = userMapper.selectByPrimaryKey(question.getCreator());
                        QuestionDTO questionDTO = new QuestionDTO();
                        BeanUtils.copyProperties(question, questionDTO);
                        questionDTO.setUser(user);
                        questionDTO.setDescription("");
                        questionDTOS.add(questionDTO);
                    }
                    return questionDTOS;
                } else {
                    return Lists.newArrayList();
                }
            });
        } catch (Exception e) {
            log.error("getStickies error", e);
            return Lists.newArrayList();
        }
        return stickies;
    }
}

