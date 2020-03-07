package life.drunkshrimper.community.model;

import lombok.Data;

/**
 * @description:
 * @author: lipan
 * @time: 2020/1/4 10:38
 */
@Data
public class User {

    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;
}
