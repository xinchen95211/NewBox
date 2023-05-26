package a.eve.newbox.config;

import a.eve.newbox.Services.LoginServices;
import a.eve.newbox.common.DataUtil;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Autowired
    private LoginServices loginServices;
    //密码加密的bean
    @Bean
    public BasicTextEncryptor basicTextEncryptor(){
        BasicTextEncryptor basicTextEncryptor1 = new BasicTextEncryptor();
        String passWord = null;
        try {
            passWord = loginServices.getById(1L).getLoginpassword();
        } catch (Exception e) {
            passWord = "0";
        }
        basicTextEncryptor1.setPassword(passWord);
        DataUtil.setBasicTextEncryptor(basicTextEncryptor1);
        return basicTextEncryptor1;
    }
}
