package life.drunkshrimper.community.controller;


import life.drunkshrimper.community.dto.QuestionDTO;
import life.drunkshrimper.community.exception.CustomizeErrorCode;
import life.drunkshrimper.community.exception.CustomizeException;
import life.drunkshrimper.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * @author 001
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    //@Autowired
    //private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") String id, Model model) {
        Integer questionId = null;
        try {
            questionId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new CustomizeException(CustomizeErrorCode.INVALID_INPUT);
        }
        QuestionDTO questionDTO = questionService.getById(questionId);
        //List<QuestionDTO> relatedQuestions = questionService.selectRelated(questionDTO);
        //List<CommentDTO> comments = commentService.listByTargetId(questionId, CommentTypeEnum.QUESTION);
        //累加阅读数
        questionService.incView(questionId);
        model.addAttribute("question", questionDTO);
        //model.addAttribute("comments", comments);
        //model.addAttribute("relatedQuestions", relatedQuestions);
        return "question";
    }
}
