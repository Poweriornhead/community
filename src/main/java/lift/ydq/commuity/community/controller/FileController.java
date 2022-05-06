package lift.ydq.commuity.community.controller;

import lift.ydq.commuity.community.dto.FileDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author YDQ
 * @create 2022-04-22 16:50
 */
@Controller
public class FileController {
    @RequestMapping("/file/upload")
    @ResponseBody
    public FileDTO upload(){
        FileDTO fileDTO = new FileDTO();
        fileDTO.setSuccess(1);
        //fileDTO.setUrl("/images/loading.gif)");
        return fileDTO;
    }
}
