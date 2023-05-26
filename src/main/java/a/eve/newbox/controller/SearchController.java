package a.eve.newbox.controller;

import a.eve.newbox.NewBoxApplication;
import a.eve.newbox.Services.UserDataServices;
import a.eve.newbox.common.Copy;
import a.eve.newbox.common.DataUtil;
import a.eve.newbox.common.Message;
import a.eve.newbox.pojo.UserData;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.javafxsupport.AbstractFxmlController;
import com.example.javafxsupport.FxmlController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static javafx.scene.input.MouseButton.PRIMARY;

@FxmlController("search.fxml")
public class SearchController extends AbstractFxmlController implements Initializable {
    @FXML
    public ListView userdata_view;
    @FXML
    public ImageView search_button;
    @FXML
    public TextField search_text;

    public ObservableList<UserData> body_view_items = FXCollections.observableArrayList();

    @Autowired
    private UserDataServices userDataServices;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        search_text.textProperty().addListener((observable, oldValue, newValue) -> search());
        //设置不可缩放
        stage.setResizable(false);
        searchinit();


        userdata_view.setCellFactory(items -> {
            ListCell<UserData> cell = new ListCell<UserData>() {
                @Override
                public void updateItem(UserData item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                        setStyle("-fx-background-color: LIGHTBLUE;");   // 设置背景色
                    } else {

                        if (isSelected()) {  // 选中时
                            setStyle("-fx-background-color:LIGHTGOLDENRODYELLOW;"); // 选中时设置背景色
                        }else {
                            setStyle("-fx-background-color: LIGHTBLUE;");
                        }
                        setFont(new Font(21.00));
                        setText(item.getName() + "\n●" + item.getAccount());
                        setTextFill(Color.BLACK);

                    }
                }
            };
//            cell.setStyle("-fx-background-color: LIGHTBLUE;");
            return cell;
        });
        userdata_view.setItems(body_view_items);

        //密码数据相关
        MenuItem item1 = new MenuItem("复制为文本");
        item1.setOnAction(event -> {
            String data = "1";
            handleSelection(data);
        });
        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().add(item1);
        userdata_view.setOnContextMenuRequested(e -> {
            if (body_view_items != null && body_view_items.size() > 0){
                contextMenu.show(userdata_view, e.getScreenX(), e.getScreenY());
            }
        });
        userdata_view.setOnMouseClicked(event -> {
            UserData selectedItem = (UserData) userdata_view.getSelectionModel().getSelectedItem();
            if (event.getButton() == PRIMARY){
                if (contextMenu.isShowing()){
                    contextMenu.hide();
                }
            }
            if (event.getClickCount() == 2){
                //打开model
                ViewController view = NewBoxApplication.show_and_getView(ViewController.class);
                view.show(selectedItem);
            }

        });



    }
    public void searchinit(){
        search_text.setText("");
        body_view_items.removeAll(body_view_items);
    }
    public void search(){
        body_view_items.removeAll(body_view_items);
        //搜索条件
        String panding = search_text.getText();
        //使用services进行搜索
        LambdaQueryWrapper<UserData> lam = new LambdaQueryWrapper<>();
        lam.like(UserData::getAccount,panding).or().like(UserData::getName,panding).or().like(UserData::getNotes,panding);
//        lam.and(lams -> lams.like().or().like(User::getApp,panding).or().like(User::getPassword,panding).or().like(User::getNotes,panding));
        List<UserData> list1 = userDataServices.list(lam);

        List<UserData> decrypt = DataUtil.decrypt(list1);


        body_view_items.addAll(decrypt);
    }

    private void handleSelection(String data) {
        ObservableList<UserData> selectedItems = userdata_view.getSelectionModel().getSelectedItems();
        switch (data) {
            case "1":
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < selectedItems.size(); i++) {
                    UserData userData = selectedItems.get(i);
                    sb.append((i+1) + ".");
                    sb.append("名称").append(userData.getName()).append("\n");
                    sb.append("账户").append(userData.getAccount()).append("\n");
                    sb.append("密码").append(userData.getPassword()).append("\n");
                }
                Copy.copy_to_bard(sb.toString());
                Message.msg("复制成功");
                break;
            case "2":
                if (Message.confirm("确定要删除数据吗?")){
                    boolean b =userDataServices.removeBatchByIds(selectedItems);
                    if (b){
                        body_view_items.removeAll(selectedItems);}
                    Message.msg("数据删除成功");
                    break;
                }else {
                    Message.msg("已取消");
                }
            case "3":
                System.out.println(3);
        }
    }
}
