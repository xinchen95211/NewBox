package a.eve.newbox.common;

import a.eve.newbox.Mapper.Create_tableMapper;
import a.eve.newbox.NewBoxApplication;
import a.eve.newbox.Services.LoginServices;
import a.eve.newbox.controller.ChangePasswordController;
import a.eve.newbox.pojo.Login;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

@Component
@Slf4j
public class LoginUtil {
//    @Autowired
    private static Create_tableMapper create_tableMapper;

    //    @Autowired
    private static LoginServices loginServices;


    public LoginUtil(Create_tableMapper create_tableMapper, LoginServices loginServices) {
        this.create_tableMapper = create_tableMapper;
        this.loginServices = loginServices;
    }

    public static void first_login_check(){
        //判断用户是否是第一次登陆
        try {
            Login list = loginServices.getById(1L);
            if (list == null){
                first_login();
            }
        } catch (Exception e) {
            first_login();
        }
    }

    private static void first_login() {
        log.info("用户为首次登陆,正在执行默认方法");
        create_tableMapper.createTable();
        //弹出第一次设置密码
        Message.msg("欢迎您首次登陆,请设置密码");
        ChangePasswordController view = NewBoxApplication.show_and_getView(ChangePasswordController.class);
        view.first_login();
    }

    public static boolean Login_check(String password){
       String key = DigestUtils.md5DigestAsHex(password.getBytes());
        Login list = loginServices.getById(1L);
        if (key.equals(list.getLoginpassword())){
            return true;
        }
        return false;
    }
}

