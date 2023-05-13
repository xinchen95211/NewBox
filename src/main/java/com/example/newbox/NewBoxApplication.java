package com.example.newbox;

import com.example.javafxsupport.AbstractSupportJAVAFX;
import com.example.newbox.controller.LoginController;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NewBoxApplication{

    public static void main(String[] args) {
        AbstractSupportJAVAFX.launcher(NewBoxApplication.class,LoginController.class,args);
   }




}
