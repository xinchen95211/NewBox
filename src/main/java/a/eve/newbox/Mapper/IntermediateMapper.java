package a.eve.newbox.Mapper;

import a.eve.newbox.pojo.Intermediate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IntermediateMapper extends BaseMapper<Intermediate> {
    @Insert("CREATE TABLE if not exists intermediate (" +
            "userdataid BIGINT," +
            "classificationid bigint " +
            ");")
    void createTable();
}
