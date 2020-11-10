package com.primer.controller;

import com.jfoenix.controls.JFXComboBox;
import com.primer.MainApplication;
import com.primer.common.annotation.AppAutoConfig;
import com.primer.common.annotation.AppDictAnnotation;
import com.primer.dao.AppConfigDao;
import com.primer.dao.AppDictDao;
import com.primer.entity.AppConfig;
import com.primer.entity.AppDict;
import com.primer.service.BaseService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.apache.commons.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * controller的父类，提供如下功能：
 * 1、配置的字段保存和装载
 *
 * @author HIFeng
 */
public class BaseController {

    @Autowired
    public BaseService baseService;

    @Autowired
    public AppConfigDao appConfigDao;

    @Autowired
    public AppDictDao appDictDao;

    /**
     * 保存配置到数据库
     */
    @FXML
    public void saveAppConfig() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            AppAutoConfig configAnnotation = field.getDeclaredAnnotation(AppAutoConfig.class);
            if (!Objects.isNull(configAnnotation)) {
                //适用于配置
                field.setAccessible(true);
                String fieldName = field.getName();
                //获取数据库的配置
                AppConfig byConfigCode = appConfigDao.findById(this.getClass().getSimpleName() + "_" + WordUtils.capitalize(fieldName)).orElse(null);
                if (Objects.isNull(byConfigCode)) {
                    //获取到配置为空不做处理
                    byConfigCode = new AppConfig();
                }
                Object fieldConfigValue = getFieldConfigValue(field);
                if (Objects.isNull(fieldConfigValue)) {
                    continue;
                }
                byConfigCode.setConfigCode(this.getClass().getSimpleName()+"_"+WordUtils.capitalize(fieldName));
                byConfigCode.setConfigClass(field.getGenericType().toString());
                byConfigCode.setConfigValue(String.valueOf(fieldConfigValue));
                appConfigDao.save(byConfigCode);
            }
        }
    }

    /**
     * 初始化配置到程序
     */
    public void initAppConfig() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //配置的code为类名+字段名
        Field[] declaredFields = this.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            loadAppConfig(declaredField);
            loadCombox(declaredField);
        }
    }

    private void loadAppConfig(Field declaredField) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        //自动装载配置
        AppAutoConfig configAnnotation = declaredField.getDeclaredAnnotation(AppAutoConfig.class);
        if (!Objects.isNull(configAnnotation)) {
            //适用于配置
            declaredField.setAccessible(true);
            String fieldName = declaredField.getName();
            //获取数据库的配置
            AppConfig byConfigCode = appConfigDao.findById(this.getClass().getSimpleName() + "_" + WordUtils.capitalize(fieldName)).orElse(null);
            if (Objects.isNull(byConfigCode)) {
                //获取到配置为空不做处理
                return;
            }
            //获取对应属性在controller中的get函数，以便获取相关对象
            Method fieldGetMethod = this.getClass().getMethod("get" + WordUtils.capitalize(fieldName));
            if (Objects.isNull(fieldGetMethod)) {
                return;
            }
            if (declaredField.getGenericType().toString().equals("class javafx.scene.control.TextField")) {
                TextField textField = (TextField) fieldGetMethod.invoke(this);
                textField.setText(byConfigCode.getConfigValue());
                return;
            }
        }
    }
    private void loadCombox(Field declaredField) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        //自动装载配置
        AppDictAnnotation appDictAnnotation = declaredField.getDeclaredAnnotation(AppDictAnnotation.class);
        if (!Objects.isNull(appDictAnnotation)) {
            //适用于配置
            declaredField.setAccessible(true);
            String fieldName = declaredField.getName();

            String groupCode = appDictAnnotation.groupCode();
            List<AppDict> allByGroupCode = appDictDao.findAllByGroupCode(groupCode);
            if (allByGroupCode == null || allByGroupCode.size() < 1) {
                return;
            }
            //获取对应属性在controller中的get函数，以便获取相关对象
            Method fieldGetMethod = this.getClass().getMethod("get" + WordUtils.capitalize(fieldName));
            if (Objects.isNull(fieldGetMethod)) {
                return;
            }
            ObservableList<String> objects = FXCollections.observableArrayList(allByGroupCode.stream().map(e -> e.getDictCode()).collect(Collectors.toList()));
            if (declaredField.getGenericType().toString().contains("com.jfoenix.controls.JFXComboBox<java.lang.String>")) {
                JFXComboBox jfxComboBox = (JFXComboBox) fieldGetMethod.invoke(this);
                jfxComboBox.getItems().setAll(objects);
                return;
            }
        }
    }
    private Object getFieldConfigValue(Field field) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method fieldGetMethod = this.getClass().getMethod("get" + WordUtils.capitalize(field.getName()));
        if (Objects.isNull(fieldGetMethod)) {
            return null;
        }
        if (field.getGenericType().toString().equals("class javafx.scene.control.TextField")) {
            TextField textField = (TextField) fieldGetMethod.invoke(this);
            return textField.getText();
        }
        return null;
    }

    /**
     * 退出应用
     * @param actionEvent
     */
    @FXML
    public void closeApp(ActionEvent actionEvent) {
        MainApplication.getStage().close();
    }

}
