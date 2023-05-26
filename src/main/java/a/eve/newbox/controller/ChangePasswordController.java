package a.eve.newbox.controller;

import a.eve.newbox.Services.LoginServices;
import a.eve.newbox.Services.UserDataServices;
import a.eve.newbox.common.DataUtil;
import a.eve.newbox.common.Message;
import a.eve.newbox.pojo.Login;
import a.eve.newbox.pojo.UserData;
import com.example.javafxsupport.AbstractFxmlController;
import com.example.javafxsupport.FxmlController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@FxmlController("ChangePassword.fxml")
public class ChangePasswordController extends AbstractFxmlController implements Initializable {
    @FXML
    public PasswordField password_one;
    @FXML
    public PasswordField password_two;
    @FXML
    public Button pass_btn;
    @FXML
    public Button ok_btn;
    @FXML
    public PasswordField password_old;
    @FXML
    public Label msg_text;

    private boolean flag = true;

    @Autowired
    private LoginServices loginServices;
    @Autowired
    private BasicTextEncryptor basicTextEncryptor;
    @Autowired
    private UserDataServices userDataServices;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        stage.initStyle(StageStyle.UTILITY);
        stage.setResizable(false);
        ok_btn.setOnAction(event -> changePassword());
        pass_btn.setOnAction(event -> pass());

    }
    public void inits(){
        password_old.setDisable(false);
        password_old.setText("");
        password_one.setText("");
        password_two.setText("");
    }

    public void first_login(){
        //旧密码设置为不可选中
        password_old.setDisable(true);
        flag = false;
        stage.setOnCloseRequest(Event ->{
            System.exit(0);
        });
    }
    private void pass() {
      if (!flag){
          Message.msg("您还没设置密码，请设置后再使用吧");
      }else {
          stage.close();
      }
    }

    /**
     * 修改密码
     */

    public void changePassword(){
        String password_oneText = password_one.getText();
        String password_twoText = password_two.getText();
        //先判断输入密码是否为空
        if ("".equals(password_oneText) || "".equals(password_twoText)){
            Message.msg("密码不能为空");
            return;
        }
        //判断密码长度是否太长或太短
        if (password_twoText.length() < 6 || password_oneText.length() < 6){
            Message.msg("密码太短了,换一个吧");
            return;
        }
        if (password_twoText.length() > 100 || password_oneText.length() > 100){
            Message.msg("密码太长了,换一个");
            return;
        }
        //判断密码一和二是否相等
        if (!password_twoText.equals(password_oneText)){
            Message.msg("两次密码输入不一致,请确认一下");
            return;
        }
        String key = DigestUtils.md5DigestAsHex(password_twoText.getBytes());
        //判断旧密码是否为正确
        if (flag){
            Login byId = loginServices.getById(1L);
            if (!(DigestUtils.md5DigestAsHex(password_old.getText().getBytes())).equals(byId.getLoginpassword())){
                Message.msg("原密码错误");
                return;
            }
            if (key.equals(byId.getLoginpassword())){
                Message.msg("新密码不能和旧密码一致");
                return;
            }
        }

        if (Message.confirm("您确定就是这个密码了吗？")){
            boolean b;
            if (!flag){
                //更改密钥
                BasicTextEncryptor basicTextEncryptor1 = new BasicTextEncryptor();
                basicTextEncryptor1.setPassword(key);
                basicTextEncryptor = basicTextEncryptor1;
                DataUtil.setBasicTextEncryptor(basicTextEncryptor);
            }else {
                //更新密码
                List<UserData> list = null;
                try {
                    list = userDataServices.list();
                } catch (Exception e) {
                    list = null;
                }
                if (list != null){
                    //解密数据
                    List<UserData> decrypt = DataUtil.decrypt(list);


                    BasicTextEncryptor basicTextEncryptor1 = new BasicTextEncryptor();
                    basicTextEncryptor1.setPassword(key);
                    basicTextEncryptor = basicTextEncryptor1;
                    DataUtil.setBasicTextEncryptor(basicTextEncryptor);
//                    List<UserData> encrypt = DataUtil.encrypt(decrypt);
                    userDataServices.updateBatchById(decrypt);
                }
            }
            Login login = new Login();
            login.setLoginpassword(key);
            login.setId(1L);
            if (!flag){
                b = loginServices.save(login);
            }else {
                b = loginServices.updateById(login);
            }
            if (b){
                Message.msg("修改成功");
                flag = true;
                stage.close();
            }else {
                Message.msg("修改失败");
            }

        }else {
            Message.msg("已取消");
        }

    }
}
