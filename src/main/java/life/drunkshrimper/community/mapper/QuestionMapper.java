package life.drunkshrimper.community.mapper;

import life.drunkshrimper.community.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,tag) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);

    @Select("select * from question limit #{offset},#{size}")
    List<Question> list(@Param("offset") Integer offset, @Param("size") Integer size);

    @Select("select count(1) from question")
    Integer count();

    @Select("select * from question where creator = #{creator} limit #{offset},#{size}")
    List<Question> listByCreator(@Param("creator") Integer creator, @Param("offset") Integer offset, @Param("size") Integer size);

    @Select("select count(1) from question where creator=#{creator}")
    Integer countByCreator(@Param("creator") Integer creator);

    @Select("select * from question where id = #{id}")
    Question selectByPrimaryKey(Integer id);

    @Update("update question set title=#{title},description=#{description},gmt_modified=#{gmtModified},tag=#{tag} where id=#{id}")
    void update(Question question);
}