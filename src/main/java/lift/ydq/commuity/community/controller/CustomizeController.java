package lift.ydq.commuity.community.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;

/**
 * @author YDQ
 * @create 2022-04-09 18:30
 */
@Controller("/error")
public class CustomizeController implements ErrorController {

    public String getErrorPath(){
        return "error";
    }
}
