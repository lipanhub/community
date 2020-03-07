package life.drunkshrimper.community.controller;

import life.drunkshrimper.community.dto.QuestionDTO;
import life.drunkshrimper.community.mapper.UserMapper;
import life.drunkshrimper.community.model.User;
import life.drunkshrimper.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @description:
 * @author: lipan
 * @time: 2020/1/2 22:48
 */
@Controller
public class IndexController {

    @Autowired(required = false)
    private UserMapper userMapper;
    @Autowired(required = false)
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest req, Model model) {

        Cookie[] cookies = req.getCookies();
        if (null != cookies && 0 != cookies.length) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (null != user) {
                        req.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }

        List<QuestionDTO> questionDTOList = questionService.list();

        model.addAttribute("questions", questionDTOList);

        return "index";
    }
}
