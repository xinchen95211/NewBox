package a.eve.newbox.common;

import a.eve.newbox.Services.UserDataServices;
import a.eve.newbox.pojo.Classification;
import a.eve.newbox.pojo.UserData;
import javafx.scene.control.ListCell;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataUtil {


    private static BasicTextEncryptor basicTextEncryptor;

    @Autowired
    public DataUtil(BasicTextEncryptor basicTextEncryptor) {
        this.basicTextEncryptor = basicTextEncryptor;
    }



    public static String encode(String password){
        return basicTextEncryptor.encrypt(password);
    }

    /**
     * 通用数据解密方法
     */

    public static List<UserData> decrypt(List<UserData> list){
        List<UserData> blist = new ArrayList<>(list);
        blist = blist.stream().map(items -> {
            String password = basicTextEncryptor.decrypt(items.getPassword());
            items.setPassword(password);
            return items;
        }).collect(Collectors.toList());
        return blist;
    }

    /**
     * 加密
     * @param list
     * @return
     */
    public static List<UserData> encrypt(List<UserData> list){
        List<UserData> blist = new ArrayList<>(list);
        blist = blist.stream().map(items -> {
            String password = basicTextEncryptor.encrypt(items.getPassword());
            items.setPassword(password);
            return items;
        }).collect(Collectors.toList());
        return blist;
    }



    private static Object call(Object items) {
        ListCell<Classification> cell = new ListCell<Classification>() {
            @Override
            public void updateItem(Classification item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("-fx-background-color: pink;");
                } else {
                    if (isSelected()) {
                        setStyle("-fx-background-color: lightblue;"); // 选中时设置背景色
                    } else {
                        setStyle("-fx-background-color: pink;");  // 非选中时无样式
                    }
                    setFont(new Font(22.00));
                    setText(item.getName());
                    setTextFill(Color.BLACK);
                }
            }
        };
        return cell;
    }

    public static BasicTextEncryptor basicTextEncryptor() {
        return basicTextEncryptor;
    }

    public static void setBasicTextEncryptor(BasicTextEncryptor basicTextEncryptor) {
        DataUtil.basicTextEncryptor = basicTextEncryptor;
    }

}
