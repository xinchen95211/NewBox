package a.eve.newbox.Mapper;

import a.eve.newbox.pojo.UserData;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDataMapper extends BaseMapper<UserData> {
    @Insert("CREATE TABLE if not exists userdata (" +
            "id BIGINT primary key," +
            "name CHARACTER VARYING(255)," +
            "account CHARACTER VARYING(255)," +
            "password CHARACTER VARYING(255)," +
            "notes CHARACTER VARYING(255)," +
            "is_del INTEGER," +
            "create_time TIMESTAMP(6)," +
            "update_time TIMESTAMP(6)" +
            ");")
    void createTable();
}
