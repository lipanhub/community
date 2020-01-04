package life.drunkshrimper.community.controller;

import life.drunkshrimper.community.mapper.UserMapper;
import life.drunkshrimper.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author: lipan
 * @time: 2020/1/2 22:48
 */
@Controller
public class IndexController {

    @Autowired(required = false)
    private UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest req){

        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if("token".equals(cookie.getName())){
                String token = cookie.getValue();
                User user = userMapper.findByToken(token);
                if(null != user){
                    req.getSession().setAttribute("user",user);
                }
                break;
            }
        }
        return "index";
    }
}
