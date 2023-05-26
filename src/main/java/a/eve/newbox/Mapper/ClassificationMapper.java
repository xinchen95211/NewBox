package a.eve.newbox.Mapper;

import a.eve.newbox.pojo.Classification;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ClassificationMapper extends BaseMapper<Classification> {
    @Insert("CREATE TABLE if not exists classification(" +
            "id BIGINT primary key," +
            "name CHARACTER VARYING(255)," +
            "is_del INTEGER," +
            "create_time TIMESTAMP(6)," +
            "update_time TIMESTAMP(6)" +
            ");")
    void createTable();
}
