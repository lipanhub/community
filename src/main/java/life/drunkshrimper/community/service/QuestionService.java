package life.drunkshrimper.community.service;

import life.drunkshrimper.community.dto.QuestionDTO;
import life.drunkshrimper.community.mapper.QuestionMapper;
import life.drunkshrimper.community.mapper.UserMapper;
import life.drunkshrimper.community.model.Question;
import life.drunkshrimper.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by codedrinker on 2019/5/7.
 */
@Service
public class QuestionService {

    @Autowired(required = false)
    private QuestionMapper questionMapper;
    @Autowired(required = false)
    private UserMapper userMapper;


    public List<QuestionDTO> list() {

        List<QuestionDTO> questionDTOList = new ArrayList<QuestionDTO>();

        List<Question> questionList = questionMapper.list();
        for (Question question : questionList) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setUser(user);
            BeanUtils.copyProperties(question, questionDTO);

            questionDTOList.add(questionDTO);
        }

        return questionDTOList;
    }
}
