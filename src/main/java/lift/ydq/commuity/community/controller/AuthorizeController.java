package lift.ydq.commuity.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author YDQ
 * @create 2022-03-30 14:48
 */
@Controller
public class AuthorizeController {
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code")  String code,
                       @RequestParam(name = "state") String state){
        return "index";
    };
}
