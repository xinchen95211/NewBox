package a.eve.newbox.controller;

import a.eve.newbox.NewBoxApplication;
import a.eve.newbox.Services.ClassificationServices;
import a.eve.newbox.Services.IntermediateServices;
import a.eve.newbox.Services.UserDataServices;
import a.eve.newbox.common.Copy;
import a.eve.newbox.common.DataUtil;
import a.eve.newbox.common.Message;
import a.eve.newbox.pojo.Classification;
import a.eve.newbox.pojo.Intermediate;
import a.eve.newbox.pojo.UserData;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.javafxsupport.AbstractFxmlController;
import com.example.javafxsupport.FxmlController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

import static javafx.scene.input.MouseButton.PRIMARY;
import static javafx.scene.input.MouseButton.SECONDARY;

@FxmlController("Home.fxml")
public class HomeController extends AbstractFxmlController implements Initializable {
    @FXML
    private ListView class_view;
    @FXML
    private ListView userdata_view;

    @FXML
    public ImageView add_button;
    @FXML
    public ImageView search_button;
    @FXML
    public ImageView menu_button;

    @Autowired
    private UserDataServices userDataServices;

    @Autowired
    private ClassificationServices classificationServices;

    @Autowired
    private IntermediateServices intermediateServices;

    //密码数据存放
    public ObservableList<UserData> userdata_view_items = FXCollections.observableArrayList();
    //分类数据存放
    public ObservableList<Classification> class_view_items = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Classification classification = new Classification();
        Classification classification2 = new Classification();

        classification.setId(-1L);
        classification.setName("全部分类");

        classification2.setId(-2L);
        classification2.setName("管理分类");
        class_view_items.add(classification);
        class_view_items.add(classification2);


        //设置不可缩放
        stage.setResizable(false);
        //退出方法
        stage.setOnCloseRequest(events -> {
            shutdown();
            System.exit(3);
        });


        add_button.setOnMouseClicked(event -> {
            add();
        });
        search_button.setOnMouseClicked(event -> {
            search();
        });
        menu_button.setOnMouseClicked(event -> {
            menu();
        });




        class_view.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        //分类

        class_view.setCellFactory(items -> {
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
        });



        //密码数据相关
        MenuItem item1 = new MenuItem("复制为文本");
        item1.setOnAction(event -> {
            String data = "1";
            handleSelection(data);
        });
        MenuItem item2 = new MenuItem("删除数据");
        item2.setOnAction(event -> {
            String data = "2";
            handleSelection(data);
        });
        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().add(item1);
        contextMenu.getItems().add(item2);
        userdata_view.setOnContextMenuRequested(e -> {
            if (userdata_view_items != null && userdata_view_items.size() > 0){
                contextMenu.show(userdata_view, e.getScreenX(), e.getScreenY());
            }
        });




        userdata_view.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
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
        List<Classification> list = classificationServices.list();
        class_view_items.addAll(list);
        Rendered_data(null);




        class_view.setOnMouseClicked(Event -> {
           Classification selectedItem = (Classification) class_view.getSelectionModel().getSelectedItem();
           if ((selectedItem.getId()) == -1L){
                Rendered_data(null);
                return;
            }else if ((selectedItem.getId()) == -2L){
               ClassifiController classifiController = NewBoxApplication.show_and_getView(ClassifiController.class);
               classifiController.bind();
               return;
           }else {
               List<Intermediate> for_classifiID = intermediateServices.get_for_classifiID(selectedItem.getId());
               Rendered_data(for_classifiID);
               return;
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


        class_view.setItems(class_view_items);
        userdata_view.setItems(userdata_view_items);
    }

    private void add() {
        ModelController modelController = NewBoxApplication.show_and_getView(ModelController.class);
        modelController.add();
    }

    private void menu() {
        NewBoxApplication.showView(MenuController.class);
    }

    private void search() {
        SearchController searchController = NewBoxApplication.show_and_getView(SearchController.class);
        searchController.searchinit();
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
                        boolean b1 = userdata_view_items.removeAll(selectedItems);
                        if (b1){
                            for (UserData selectedItem : selectedItems) {
                                intermediateServices.remove_for_userdataID(selectedItem.getId());
                            }
                        }
                    }
                    Message.msg("数据删除成功");
                    break;
                }else {
                    Message.msg("已取消");
                }
            case "3":
                System.out.println(3);
        }
    }
    //渲染数据
    public void Rendered_data(List<Intermediate> ifi){
        userdata_view_items.removeAll(userdata_view_items);
        if (ifi != null){
            List<UserData> userData = userDataServices.list_for_intermedia(ifi);
            List<UserData> decrypt = DataUtil.decrypt(userData);
            userdata_view_items.addAll(decrypt);
        }else {
            List<UserData> list1 = userDataServices.list();
            List<UserData> decrypt = DataUtil.decrypt(list1);
            userdata_view_items.addAll(decrypt);
        }

    }
}
