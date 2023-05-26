package a.eve.newbox.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {

        try {
            String password = (String) metaObject.getValue("password");
            password = DataUtil.encode(password);
            metaObject.setValue("password",password);
        } catch (Exception e) {

        }


        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime", LocalDateTime.now());

    }

    @Override
    public void updateFill(MetaObject metaObject) {

        try {
            String password = (String) metaObject.getValue("password");
            password = DataUtil.encode(password);
            metaObject.setValue("password",password);
        } catch (Exception e) {

        }


        metaObject.setValue("updateTime", LocalDateTime.now());

    }
}
