package life.drunkshrimper.community.dto;

import lombok.Data;

/**
 * @description:
 * @author: lipan
 * @time: 2020/1/3 22:52
 */
@Data
public class AccessTokenDTO {

    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
