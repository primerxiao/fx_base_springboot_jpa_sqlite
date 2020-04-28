package com.primer.controller;

import com.primer.common.annotation.AppConfigAnnotation;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lombok.Data;
import lombok.SneakyThrows;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * app的主界面控制器
 * @author HIFeng
 */
@Data
@FXMLController
public class MainController extends AppConfigController implements Initializable {

    @FXML
    @AppConfigAnnotation
    public TextField testTextField;
    @FXML
    public ListView listview;

    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initAppConfig(this);
        listview.getItems().add("1");
        listview.getItems().add("1");
        listview.getItems().add("1");
        listview.getItems().add("1");
        listview.getItems().add("1");
    }

    public static void main(String[] args) {
        System.out.println(TextField.class.getName());
        System.out.println(TextArea.class.getSimpleName());
        System.out.println(ListView.class.getSimpleName());

    }
}