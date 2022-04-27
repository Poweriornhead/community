package lift.ydq.commuity.community.controller;

import lift.ydq.commuity.community.dto.PaginationDTO;
import lift.ydq.commuity.community.dto.UserDTO;
import lift.ydq.commuity.community.service.QuestionService;
import lift.ydq.commuity.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author YDQ
 * @create 2022-04-25 22:49
 */
@Controller
public class UserSapceController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    @GetMapping("/userspace/{accountId}")
    public String userspace(Model model,
                            @PathVariable(name = "accountId") String accountId){
        UserDTO userDTO = userService.selectByAccountId(accountId);
        List<String> userTags = questionService.listTag(userDTO.getId());
        PaginationDTO paginationDTO = questionService.list(userDTO.getId());
        model.addAttribute("paginationDTO",paginationDTO);
        //model.addAttribute("accountId",accountId);
        model.addAttribute("tags",userTags);
        model.addAttribute("user",userDTO);
        return "userspace";
    }
}
