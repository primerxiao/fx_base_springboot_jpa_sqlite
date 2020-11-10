package com.primer;

import com.primer.common.config.FxBaseSplashScreen;
import com.primer.view.MainView;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.application.Platform;
import javafx.stage.StageStyle;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;


/**
 * app启动类
 * @author HIFeng
 */
@SpringBootApplication
@EnableJpaRepositories
public class MainApplication extends AbstractJavaFxApplicationSupport {

    public static void main(String[] args) throws IOException {
        //Platform.setImplicitExit(false);

        launch(MainApplication.class, MainView.class, new FxBaseSplashScreen("/image/timg.gif"), args);
        MainApplication.getStage().setResizable(false);
        MainApplication.getStage().initStyle(StageStyle.UNDECORATED);
    }
}
