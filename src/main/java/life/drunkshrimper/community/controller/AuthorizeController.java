package life.drunkshrimper.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description:
 * @author: lipan
 * @time: 2020/1/3 22:38
 */
@Controller
public class AuthorizeController {

    @RequestMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,@RequestParam(name = "state")String state){

        return "index";
    }
}
