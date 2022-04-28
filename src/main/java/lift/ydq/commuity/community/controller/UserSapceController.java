package lift.ydq.commuity.community.controller;

import lift.ydq.commuity.community.dto.PaginationDTO;
import lift.ydq.commuity.community.dto.UserDTO;
import lift.ydq.commuity.community.mapper.FollowMapper;
import lift.ydq.commuity.community.model.Follow;
import lift.ydq.commuity.community.model.User;
import lift.ydq.commuity.community.service.QuestionService;
import lift.ydq.commuity.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private FollowMapper followMapper;

    @GetMapping("/userspace/{accountId}")
    public Object userspace(Model model,
                            HttpServletRequest request,
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

    @ResponseBody
    @RequestMapping(value = "/userspace/follow", method = RequestMethod.GET)
    public String follow(Long followers, Integer type,
                         HttpServletRequest request,
                         Model model){
        User user = (User) request.getSession().getAttribute("user");
        if (user == null){
            model.addAttribute("error","请先登录");
            return "userspace";
        }

        Follow follow = new Follow();
        follow.setFollowers(followers);
        follow.setRequester(user.getId());
        follow.setType(type);
        follow.setGmtCreate(System.currentTimeMillis());
        userService.createFollow(follow);
        return "userspace";
    }
}
