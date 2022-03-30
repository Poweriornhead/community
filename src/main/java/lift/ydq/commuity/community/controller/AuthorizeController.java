package lift.ydq.commuity.community.controller;

import lift.ydq.commuity.community.dto.AccesstokenDTO;
import lift.ydq.commuity.community.dto.GithubUser;
import lift.ydq.commuity.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author YDQ
 * @create 2022-03-30 14:48
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code")  String code,
                       @RequestParam(name = "state") String state){
        AccesstokenDTO accessTokenDto =new AccesstokenDTO();
        accessTokenDto.setClient_id("ea93ff98e309ed3a77b6");
        accessTokenDto.setClient_secret("08bf766871e0ae32f3aa4355e342a34dc7140324");
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_uri("http://localhost:8080/callback");
        accessTokenDto.setState(state);
        String accessToken =githubProvider.getAccessToken(accessTokenDto);
        GithubUser user = githubProvider.gitUser(accessToken);
        System.out.println(user.getName());
        return "index";
    };
}
