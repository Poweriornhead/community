package lift.ydq.commuity.community.controller;

import lift.ydq.commuity.community.dto.PaginationDTO;
import lift.ydq.commuity.community.dto.QuestionDTO;
import lift.ydq.commuity.community.interceptor.SessionInterceptor;
import lift.ydq.commuity.community.mapper.UserMapper;
import lift.ydq.commuity.community.model.Question;
import lift.ydq.commuity.community.model.User;
import lift.ydq.commuity.community.service.NotificationService;
import lift.ydq.commuity.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author YDQ
 * @create 2022-04-06 14:21
 */
@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/profile/{action}")
    public String profiles(HttpServletRequest request,
                          @PathVariable(name = "action") String action,
                          Model model,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "5") Integer size){
        User user = (User) request.getSession().getAttribute("user");
        if (user == null){
            return "redirect:/";
        }
        if ("questions".equals(action)){
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName","我的贴子");
            PaginationDTO paginationDTO = questionService.list(user.getId(),page,size);
            model.addAttribute("pagination",paginationDTO);

        }
        if ("replies".equals(action)){
            PaginationDTO paginationDTO = notificationService.list(user.getId(),page,size);
            Long unreadCount = notificationService.unreadCount(user.getId());
            model.addAttribute("section", "replies");
            model.addAttribute("pagination",paginationDTO);
            model.addAttribute("unreadCount", unreadCount);
            model.addAttribute("sectionName","最新回复");
        }


        if ("followQuestion".equals(action)){
            PaginationDTO paginationDTO = questionService.folowList(user.getId(),page,size);
            Long unreadCount = notificationService.unreadCount(user.getId());
            model.addAttribute("section", "followQuestion");
            model.addAttribute("pagination",paginationDTO);
            model.addAttribute("unreadCount", unreadCount);
            model.addAttribute("sectionName","我的收藏");
        }
        if ("followUser".equals(action)){
            PaginationDTO paginationDTO = questionService.userList(user.getId(),page,size);
            Long unreadCount = notificationService.unreadCount(user.getId());
            model.addAttribute("section", "followUser");
            model.addAttribute("pagination",paginationDTO);
            model.addAttribute("unreadCount", unreadCount);
            model.addAttribute("sectionName","我的收藏");
        }

        return "profile";

    }

    @ResponseBody
    @RequestMapping(value = "/profile/delete" ,method = RequestMethod.GET)
    public Object delete(Long Id){
        System.out.println(Id);
        questionService.delete(Id);
        return "profile";
    }

}
