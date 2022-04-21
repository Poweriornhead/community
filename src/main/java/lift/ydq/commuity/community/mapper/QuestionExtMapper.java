package lift.ydq.commuity.community.mapper;


import lift.ydq.commuity.community.model.Question;

import java.util.List;

public interface QuestionExtMapper {
    int incView(Question record);
    int incCommentCount(Question record);
    List<Question> selectRelated(Question question);

}