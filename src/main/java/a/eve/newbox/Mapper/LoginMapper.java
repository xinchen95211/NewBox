package a.eve.newbox.Mapper;

import a.eve.newbox.pojo.Login;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper extends BaseMapper<Login> {
    @Insert("CREATE TABLE if not exists login (" +
            "id BIGINT primary key," +
            "password  CHARACTER VARYING(255)," +
            "create_time TIMESTAMP(6)," +
            "update_time TIMESTAMP(6)" +
            ");")
    void createTable();
}
