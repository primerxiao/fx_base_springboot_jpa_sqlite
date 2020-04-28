package com.primer.controller;

import com.primer.common.annotation.AppConfigAnnotation;
import com.primer.entity.AppConfig;
import com.sun.xml.internal.bind.v2.model.core.ID;
import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Data;
import lombok.SneakyThrows;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * app的主界面控制器
 *
 * @author HIFeng
 */
@Data
@FXMLController
public class MainController extends AppConfigController implements Initializable {

    @FXML
    @AppConfigAnnotation
    public TextField testTextField;
    @FXML
    public ListView<String> listview;
    @FXML
    public TableView<AppConfig> tableView = new TableView<>();
    @FXML
    public TableColumn<AppConfig, String> tableColumn1 = new TableColumn<>();
    @FXML
    public TableColumn<AppConfig, String> tableColumn2 = new TableColumn<>();
    @FXML
    public TableColumn<AppConfig, String> tableColumn3 = new TableColumn<>();


    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initAppConfig(this);

        listview.getItems().add("1");
        listview.getItems().add("1");
        listview.getItems().add("1");
        listview.getItems().add("1");
        listview.getItems().add("1");

        //list
        tableColumn1.setCellValueFactory(new PropertyValueFactory<>("configCode"));
        tableColumn2.setCellValueFactory(new PropertyValueFactory<>("configClass"));
        tableColumn3.setCellValueFactory(new PropertyValueFactory<>("configValue"));
        ObservableList<AppConfig> appConfigs = FXCollections.observableArrayList();
        appConfigs.addAll(appConfigService.findAll());
        tableView.setItems(appConfigs);
    }

    public static void main(String[] args) {
        System.out.println(TextField.class.getName());
        System.out.println(TextArea.class.getSimpleName());
        System.out.println(ListView.class.getSimpleName());

    }
}