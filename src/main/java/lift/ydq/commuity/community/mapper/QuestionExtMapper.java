package lift.ydq.commuity.community.mapper;


import lift.ydq.commuity.community.dto.QuestionQueryDTO;
import lift.ydq.commuity.community.model.Question;

import java.util.List;

public interface QuestionExtMapper {
    int incView(Question record);
    int incCommentCount(Question record);
    List<Question> selectRelated(Question question);

    Integer countBySearch(QuestionQueryDTO questionQueryDTO);

    List<Question> selectBySearch(QuestionQueryDTO questionQueryDTO);

    List<Question> selectSticky();

    Integer selectQuestionCount(Long userId);
}