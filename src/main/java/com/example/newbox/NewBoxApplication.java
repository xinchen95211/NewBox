package com.example.newbox;






import com.example.javafxsupport.AbstractSupportJAVAFX;
import com.example.javafxsupport.GUIState;
import com.example.javafxsupport.SpringJavaFX;



import com.example.newbox.controller.LoginController;

import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class NewBoxApplication{
    /**
     * 调用静态方法启动
     */
    public static void main(String[] args) {
        AbstractSupportJAVAFX.launcher(NewBoxApplication.class,LoginController.class,args);
    }
}

