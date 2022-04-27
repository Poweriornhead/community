package lift.ydq.commuity.community.cacha;

import lift.ydq.commuity.community.dto.HotTagDTO;
import lift.ydq.commuity.community.dto.QuestionDTO;
import lift.ydq.commuity.community.mapper.QuestionMapper;
import lift.ydq.commuity.community.model.Question;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author YDQ
 * @create 2022-04-25 19:31
 */
@Component
@Data
public class HotQuestionCache {
    @Autowired
    private QuestionMapper questionMapper;

    private Map<String, Integer> questions = new HashMap<>();
    private List<QuestionDTO> hotQuestions = new ArrayList<>();

    public  void updateQuestion(Map<Long, Integer> questions) {
        int max = 5;
        PriorityQueue<HotTagDTO> priorityQueue = new PriorityQueue<>(max);

        questions.forEach((id, priority) ->{
            HotTagDTO hotTagDTO = new HotTagDTO();
            hotTagDTO.setId(id);
            hotTagDTO.setPriority(priority);
            if (priorityQueue.size() < max){
                priorityQueue.add(hotTagDTO);
            }else {
                HotTagDTO minHot = priorityQueue.peek();
                if (hotTagDTO.compareTo(minHot) > 0){
                    priorityQueue.poll();
                    priorityQueue.add(hotTagDTO);
                }
            }

        });

        List<Long> sortedTags = new ArrayList<>();

        HotTagDTO poll = priorityQueue.poll();
        while (poll != null){
            sortedTags.add(0,poll.getId());
            poll = priorityQueue.poll();
        }
        System.out.println(sortedTags);
        for (Long id : sortedTags){
            Question question =  questionMapper.selectByPrimaryKey(id);
            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setTitle(question.getTitle());
            questionDTO.setId(question.getId());
            hotQuestions.add(questionDTO);
        };
    }
}
