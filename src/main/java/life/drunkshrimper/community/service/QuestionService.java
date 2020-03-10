package life.drunkshrimper.community.service;

import life.drunkshrimper.community.dto.PaginationDTO;
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


    public PaginationDTO list(Integer page, Integer size) {

        PaginationDTO paginationDTO = new PaginationDTO();

        Integer totalCount = (int) questionMapper.count();
        Integer totalPage;
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }

        Integer offset = size * (page - 1);
        List<QuestionDTO> questionDTOList = new ArrayList<QuestionDTO>();
        List<Question> questionList = questionMapper.list(offset,size);
        for (Question question : questionList) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setUser(user);
            BeanUtils.copyProperties(question, questionDTO);
            questionDTOList.add(questionDTO);
        }

        paginationDTO.setData(questionDTOList);
        paginationDTO.setPagination(totalPage, page);

        return paginationDTO;
    }

    public PaginationDTO list(Integer creator, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();

        Integer totalCount = (int) questionMapper.countByCreator(creator);
        Integer totalPage;
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }

        Integer offset = size * (page - 1);
        List<QuestionDTO> questionDTOList = new ArrayList<QuestionDTO>();
        List<Question> questionList = questionMapper.listByCreator(creator,offset,size);
        for (Question question : questionList) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setUser(user);
            BeanUtils.copyProperties(question, questionDTO);
            questionDTOList.add(questionDTO);
        }

        paginationDTO.setData(questionDTOList);
        paginationDTO.setPagination(totalPage, page);

        return paginationDTO;
    }

    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        User user = userMapper.findById(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }


    public void createOrUpdate(Question question) {
        if (question.getId() == null) {
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setViewCount(0);
            question.setLikeCount(0);
            question.setCommentCount(0);
            questionMapper.create(question);
        } else {
            Question dbQuestion = questionMapper.selectByPrimaryKey(question.getId());
            dbQuestion.setGmtModified(System.currentTimeMillis());
            dbQuestion.setTitle(question.getTitle());
            dbQuestion.setDescription(question.getDescription());
            dbQuestion.setTag(question.getTag());
            questionMapper.update(dbQuestion);
        }
    }
}
