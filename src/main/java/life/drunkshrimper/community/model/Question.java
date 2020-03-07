package life.drunkshrimper.community.model;

import lombok.Data;

/**
 * @description:
 * @author: lipan
 * @time: 2020/1/4 10:38
 */
@Data
public class Question {

    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
}