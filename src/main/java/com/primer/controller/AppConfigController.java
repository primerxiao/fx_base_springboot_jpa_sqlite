package com.primer.controller;

import com.primer.common.annotation.AppConfigAnnotation;
import com.primer.entity.AppConfig;
import com.primer.service.AppConfigService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Objects;

/**
 * app配置处理类，提供给其它controller继承
 *
 * @author HIFeng
 */
public class AppConfigController {

    @Autowired
    public AppConfigService appConfigService;

    /**
     * 保存配置到数据库
     */
    @FXML
    public void saveAppConfig() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            AppConfigAnnotation configAnnotation = field.getDeclaredAnnotation(AppConfigAnnotation.class);
            if (!Objects.isNull(configAnnotation)) {
                //适用于配置
                field.setAccessible(true);
                String fieldName = field.getName();
                //获取数据库的配置
                AppConfig byConfigCode = appConfigService.findByConfigCode(this.getClass().getSimpleName() + "_" +WordUtils.capitalize(fieldName));
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
                appConfigService.save(byConfigCode);
            }
        }
    }

    /**
     * 初始化配置到程序
     */
    public void initAppConfig(AppConfigController appConfigController) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //配置的code为类名+字段名
        Field[] declaredFields = appConfigController.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            AppConfigAnnotation configAnnotation = declaredField.getDeclaredAnnotation(AppConfigAnnotation.class);
            if (!Objects.isNull(configAnnotation)) {
                //适用于配置
                declaredField.setAccessible(true);
                String fieldName = declaredField.getName();
                //获取数据库的配置
                AppConfig byConfigCode = appConfigService.findByConfigCode(appConfigController.getClass().getSimpleName() + "_" +WordUtils.capitalize(fieldName));
                if (Objects.isNull(byConfigCode)) {
                    //获取到配置为空不做处理
                    continue;
                }
                //获取对应属性在controller中的get函数，以便获取相关对象
                Method fieldGetMethod = appConfigController.getClass().getMethod("get" + WordUtils.capitalize(fieldName));
                if (Objects.isNull(fieldGetMethod)) {
                    continue;
                }
                if (declaredField.getGenericType().toString().equals("class javafx.scene.control.TextField")) {
                    TextField textField = (TextField) fieldGetMethod.invoke(appConfigController);
                    textField.setText(byConfigCode.getConfigValue());
                    continue;
                }
            }
        }
    }

    private final Object getFieldConfigValue(Field field) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
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

}
