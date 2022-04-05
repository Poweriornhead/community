package lift.ydq.commuity.community.dto;

import lombok.Data;

/**
 * @author YDQ
 * @create 2022-03-30 19:36
 */
@Data
public class AccesstokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;

}
