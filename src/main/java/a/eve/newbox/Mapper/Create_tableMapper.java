package a.eve.newbox.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * 只为初始化数据
 */
@Mapper
public interface Create_tableMapper {
    @Insert("CREATE TABLE if not exists classification(" +
            "id BIGINT primary key," +
            "name CHARACTER VARYING(255)," +
            "is_del INTEGER," +
            "create_time TIMESTAMP(6)," +
            "update_time TIMESTAMP(6)" +
            ");" +
            "CREATE TABLE if not exists intermediate (" +
            "id bigint primary key,"+
            "userdataid BIGINT," +
            "classificationid bigint" +
            ");"+
            "CREATE TABLE if not exists login (" +
            "id BIGINT primary key," +
            "loginpassword  CHARACTER VARYING(255)," +
            "create_time TIMESTAMP(6)," +
            "update_time TIMESTAMP(6)" +
            ");"+
            "CREATE TABLE if not exists userdata (" +
            "id BIGINT primary key," +
            "name CHARACTER VARYING(255)," +
            "account CHARACTER VARYING(255)," +
            "password CHARACTER VARYING(4096)," +
            "notes CHARACTER VARYING(4096)," +
            "is_del INTEGER," +
            "create_time TIMESTAMP(6)," +
            "update_time TIMESTAMP(6)" +
            ");"
    )
    void createTable();
}
