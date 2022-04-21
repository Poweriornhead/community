package lift.ydq.commuity.community.controller;

import lift.ydq.commuity.community.dto.NotificationDTO;
import lift.ydq.commuity.community.enums.NotificationTypeEnum;
import lift.ydq.commuity.community.model.User;
import lift.ydq.commuity.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

/**
 * @author YDQ
 * @create 2022-04-21 19:09
 */
@Controller
public class NotificationController {
    @Autowired
    NotificationService notificationService;
    @GetMapping("/notification/{id}")
    public String profile(HttpServletRequest request,
                          @PathVariable(name = "id") Long id
                          ){
        User user = (User) request.getSession().getAttribute("user");
        if (user == null){
            return "redirect:/";
        }

        NotificationDTO notificationDTO = notificationService.read(id,user);

        if (NotificationTypeEnum.REPLY_COMMENT.getType() == notificationDTO.getType()
            ||NotificationTypeEnum.REPLY_QUESTION.getType() == notificationDTO.getType()){
            return "redirect:/question/"+ notificationDTO.getOuterid();
        }else {
            return "redirect:/";
        }
    }
}
