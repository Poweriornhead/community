package lift.ydq.commuity.community.provider;

import com.alibaba.fastjson.JSON;
import lift.ydq.commuity.community.dto.AccesstokenDTO;
import lift.ydq.commuity.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author YDQ
 * @create 2022-03-30 15:35
 */
@Component
public class GithubProvider {
    public String getAccessToken(AccesstokenDTO accesstokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON.toJSONString(accesstokenDTO),mediaType);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            //System.out.println(string);//access_token=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&scope=user&token_type=bearer
            String token = string.split("&")[0].split("=")[1];
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
        }

        public GithubUser gitUser(String accessToken){
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.github.com/user")
                    .header("Authorization", "token " + accessToken)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                String string = response.body().string();
                GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
                return githubUser;
                } catch (IOException e) {
            }
            return null;
        }
}


