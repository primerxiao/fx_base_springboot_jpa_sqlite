package com.primer.common.config;

import de.felixroske.jfxsupport.SplashScreen;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 启动图片配置类
 *
 * @author HIFeng
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class FxBaseSplashScreen extends SplashScreen {

    /**
     * 图片路径
     */
    private String imagePath;

    @Override
    public Parent getParent() {
        ImageView imageView = new ImageView(this.getClass().getResource(this.getImagePath()).toExternalForm());
        ProgressBar splashProgressBar = new ProgressBar();
        splashProgressBar.setPrefWidth(imageView.getImage().getWidth());
        VBox vbox = new VBox();
        vbox.getChildren().addAll(new Node[]{imageView, splashProgressBar});
        return vbox;
    }

    @Override
    public boolean visible() {
        return true;
    }

    @Override
    public String getImagePath() {
        return this.imagePath;
    }
}

