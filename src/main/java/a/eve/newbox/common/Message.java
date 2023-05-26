package a.eve.newbox.common;

import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 公共提示框
 */
public class Message {
    private static final Stage MESSAGE_STAGE = new Stage();
    private static final Timer timer = new Timer();

    static {
        MESSAGE_STAGE.initModality(Modality.APPLICATION_MODAL);
        MESSAGE_STAGE.setTitle("");
        MESSAGE_STAGE.initStyle(StageStyle.TRANSPARENT);
        MESSAGE_STAGE.setAlwaysOnTop(true);  // 添加此设置
    }

    public static boolean confirm(String msg) {
        AtomicBoolean confirmed = new AtomicBoolean(false);
        // 创建消息面板
        BorderPane pane = new BorderPane();
//        pane.setPrefSize(400, 200);
        // 创建消息文本

//        pane.setStyle("-fx-background-color: transparent;");
        pane.setStyle("-fx-background-color: pink;");
        Text text = new Text(msg);
        text.setFont(new Font(30.00));
        text.setWrappingWidth(100);

        text.wrappingWidthProperty().bind(pane.widthProperty());
        text.setTextAlignment(TextAlignment.CENTER);
        // 创建按钮
        Button confirmButton = new Button("确定");
        Button cancelButton = new Button("取消");
            confirmButton.setFont(new Font(20.00));
         cancelButton.setFont(new Font(20.00));
        confirmButton.setOnAction(event -> {
            confirmed.set(true);
            MESSAGE_STAGE.close();
        });
        cancelButton.setOnAction(event -> {
            MESSAGE_STAGE.close();
        });
        // 添加到面板
        pane.setCenter(text);
        GridPane gridPane = new GridPane();
        gridPane.add(cancelButton,0,0);
        gridPane.add(confirmButton,1,0);
        GridPane.setHalignment(cancelButton, HPos.CENTER);
        GridPane.setValignment(cancelButton, VPos.CENTER);
        GridPane.setHalignment(confirmButton, HPos.CENTER);
        GridPane.setValignment(confirmButton, VPos.CENTER);
        GridPane.setHgrow(confirmButton, Priority.ALWAYS);
        GridPane.setVgrow(confirmButton, Priority.ALWAYS);
        GridPane.setHgrow(cancelButton, Priority.ALWAYS);
        GridPane.setVgrow(cancelButton, Priority.ALWAYS);
        pane.setBottom(gridPane);
        // 显示确认框
        Scene scene = new Scene(pane);
        scene.setFill(Color.TRANSPARENT);
        MESSAGE_STAGE.setScene(scene);
        MESSAGE_STAGE.showAndWait();
        return confirmed.get();
    }
    public static void msg(String msg) {
        // 创建消息面板
        BorderPane pane = new BorderPane();
//        pane.setPrefSize(400, 200);

//        pane.setStyle("-fx-background-color: pink;");

//        pane.setStyle("-fx-background-color: transparent;");
        pane.setStyle("-fx-background-color: pink;");
        // 创建消息文本
        Text text = new Text(msg);
        text.setWrappingWidth(100);



        text.setFont(new Font(30.00));
        text.wrappingWidthProperty().bind(pane.widthProperty());
        text.setTextAlignment(TextAlignment.CENTER);

        pane.setCenter(text);
        Scene scene = new Scene(pane);
        scene.setFill(Color.TRANSPARENT);
        MESSAGE_STAGE.setScene(scene);
        MESSAGE_STAGE.show();
        close();
    }
    public static void close(){
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(MESSAGE_STAGE::close);
            }
        }, 2000);
    }


    public static String updata(String name) {
        AtomicReference<String> s = new AtomicReference<>("");
        // 创建消息面板
        BorderPane pane = new BorderPane();
        pane.setPrefSize(400, 200);
        // 创建消息文本

//        pane.setStyle("-fx-background-color: transparent;");
        pane.setStyle("-fx-background-color: pink;");

        TextField textField = new TextField(name);
        textField.setText(name);
        textField.setPrefSize(200, 60);


        // 创建按钮
        Button confirmButton = new Button("确定");
        Button cancelButton = new Button("取消");
        confirmButton.setFont(new Font(20.00));
        cancelButton.setFont(new Font(20.00));

        confirmButton.setOnAction(event -> {
            s.set(textField.getText());
            MESSAGE_STAGE.close();
        });
        cancelButton.setOnAction(event -> {
            s.set(name);
            MESSAGE_STAGE.close();
        });


        // 添加到面板
        pane.setCenter(textField);
        GridPane gridPane = new GridPane();
        gridPane.add(cancelButton,0,0);
        gridPane.add(confirmButton,1,0);
        GridPane.setHalignment(cancelButton, HPos.CENTER);
        GridPane.setValignment(cancelButton, VPos.CENTER);
        GridPane.setHalignment(confirmButton, HPos.CENTER);
        GridPane.setValignment(confirmButton, VPos.CENTER);
        GridPane.setHgrow(confirmButton, Priority.ALWAYS);
        GridPane.setVgrow(confirmButton, Priority.ALWAYS);
        GridPane.setHgrow(cancelButton, Priority.ALWAYS);
        GridPane.setVgrow(cancelButton, Priority.ALWAYS);
        pane.setBottom(gridPane);
        // 显示确认框
        Scene scene = new Scene(pane);
        scene.setFill(Color.TRANSPARENT);
        MESSAGE_STAGE.setScene(scene);
        MESSAGE_STAGE.showAndWait();
        return s.get();
    }
}
