package life.drunkshrimper.community.controller;

import life.drunkshrimper.community.cache.HotTagCache;
import life.drunkshrimper.community.dto.PaginationDTO;
import life.drunkshrimper.community.mapper.UserMapper;
import life.drunkshrimper.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    @Autowired(required = false)
    private HotTagCache hotTagCache;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "10") Integer size,
                        @RequestParam(name = "search", required = false) String search,
                        @RequestParam(name = "tag", required = false) String tag,
                        @RequestParam(name = "sort", required = false) String sort) {
        PaginationDTO pagination = questionService.list(search, tag, sort, page, size);
        List<String> tags = hotTagCache.getHots();
        model.addAttribute("pagination", pagination);
        model.addAttribute("search", search);
        model.addAttribute("tag", tag);
        model.addAttribute("tags", tags);
        model.addAttribute("sort", sort);
        return "index";
    }
}
