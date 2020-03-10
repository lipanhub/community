package life.drunkshrimper.community.dto;

import lombok.Data;


/**
 * @author 001
 */
@Data
public class QuestionQueryDTO {
    private String search;
    private String sort;
    private Long time;
    private String tag;
    private Integer page;
    private Integer size;
}
