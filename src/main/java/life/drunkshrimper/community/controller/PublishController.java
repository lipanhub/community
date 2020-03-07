package life.drunkshrimper.community.controller;


import life.drunkshrimper.community.mapper.QuestionMapper;
import life.drunkshrimper.community.mapper.UserMapper;
import life.drunkshrimper.community.model.Question;
import life.drunkshrimper.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


@Controller
public class PublishController {

    @Autowired(required = false)
    private QuestionMapper questionMapper;
    @Autowired(required = false)
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            HttpServletRequest req,
            Model model) {

        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);

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

        User user = null;
        Cookie[] cookies = req.getCookies();
        if (null != cookies && 0 != cookies.length) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    user = userMapper.findByToken(token);
                    break;
                }
            }
        }

        if (null == user) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);

        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(user.getGmtCreate());

        questionMapper.create(question);

        return "redirect:/";
    }
}
