package life.drunkshrimper.community.dto;

import lombok.Data;

/**
 * @description:
 * @author: lipan
 * @time: 2020/1/3 23:21
 */
@Data
public class GithubUser {

    private String name;
    private Long id;
    private String bio;
    private String avatarUrl;
}
