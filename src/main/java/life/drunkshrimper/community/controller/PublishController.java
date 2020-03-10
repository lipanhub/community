package life.drunkshrimper.community.controller;


import life.drunkshrimper.community.dto.QuestionDTO;
import life.drunkshrimper.community.model.Question;
import life.drunkshrimper.community.model.User;
import life.drunkshrimper.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


@Controller
public class PublishController {

    @Autowired(required = false)
    private QuestionService questionService;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            HttpServletRequest request,
            @RequestParam(value = "id", required = false) Integer id,
            Model model) {

        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);

        User user = (User) request.getSession().getAttribute("user");

        if (null == user) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }

        if (null == title || "".equals(title)) {
            model.addAttribute("error", "title can not be null");
            return "publish";
        }
        if (null == description || "".equals(description)) {
            model.addAttribute("error", "description can not be null");
            return "publish";
        }
        if (null == tag || "".equals(tag)) {
            model.addAttribute("error", "tag can not be null");
            return "publish";
        }



        Question question = new Question();
        question.setId(id);
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());

        questionService.createOrUpdate(question);

        return "redirect:/";
    }

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Integer id,
                       Model model) {
        QuestionDTO question = questionService.getById(id);
        model.addAttribute("title", question.getTitle());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("tag", question.getTag());
        model.addAttribute("id", question.getId());
        model.addAttribute("tags", question.getTag());
        return "publish";
    }
}
