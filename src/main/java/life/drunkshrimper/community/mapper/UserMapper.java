package life.drunkshrimper.community.mapper;

import life.drunkshrimper.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @description:
 * @author: lipan
 * @time: 2020/1/4 10:36
 */
@Mapper
public interface UserMapper {

    @Insert("insert into user(account_id,name,token,gmt_create,gmt_modified) values(#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(String token);
}
