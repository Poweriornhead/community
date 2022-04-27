package lift.ydq.commuity.community.controller;

import lift.ydq.commuity.community.cacha.HotQuestionCache;
import lift.ydq.commuity.community.cacha.HotTagCacha;
import lift.ydq.commuity.community.dto.HotTagDTO;
import lift.ydq.commuity.community.dto.PaginationDTO;
import lift.ydq.commuity.community.dto.QuestionDTO;
import lift.ydq.commuity.community.mapper.UserMapper;
import lift.ydq.commuity.community.model.Question;
import lift.ydq.commuity.community.model.User;
import lift.ydq.commuity.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author YDQ
 * @create 2022-03-25 17:57
 */
@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private HotTagCacha hotTagCacha;

    @Autowired
    private HotQuestionCache hotQuestionCache;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "10") Integer size,
                        @RequestParam(name = "search", required = false) String search,
                        @RequestParam(name = "tag", required = false) String tag,
                        @RequestParam(name = "sort", required = false) String sort) {
        PaginationDTO pagination = questionService.list(search, tag, sort, page, size);
        List<String> tags = hotTagCacha.getHots();
        List<QuestionDTO> questions =  hotQuestionCache.getHotQuestions();
        model.addAttribute("pagination", pagination);
        model.addAttribute("search", search);
        model.addAttribute("tag", tag);
        model.addAttribute("tags", tags);
        model.addAttribute("sort", sort);
        model.addAttribute("questions",questions);
        return "index";
    }
}
