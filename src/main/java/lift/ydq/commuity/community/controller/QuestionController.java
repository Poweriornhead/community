package lift.ydq.commuity.community.controller;

import lift.ydq.commuity.community.dto.CommentDTO;
import lift.ydq.commuity.community.dto.QuestionDTO;
import lift.ydq.commuity.community.enums.CommentTypeEnum;
import lift.ydq.commuity.community.service.CommentService;
import lift.ydq.commuity.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author YDQ
 * @create 2022-04-07 14:18
 */
@Controller
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @Autowired
    CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id,
                           Model model){
        QuestionDTO questionDTO = questionService.getById(id);
        List<QuestionDTO> relatedQusetions = questionService.selectRelated(questionDTO);
        List<CommentDTO> comments = commentService.listByTagetId(id, CommentTypeEnum.QUESTION);

        //阅读数
        questionService.incView(id);
        model.addAttribute("question",questionDTO);
        model.addAttribute("comments",comments);
        model.addAttribute("relatedQusetions",relatedQusetions);
        return "question";
    }
}
