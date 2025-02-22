package com.maemlab.mvcifx.examples.app;

import javafx.application.Application;
import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.stage.Stage;

public class MVCIApp extends Application  {
    @Override
    public void start(Stage stage) {
        var antialiasing = Platform.isSupported(ConditionalFeature.SCENE3D) ? SceneAntialiasing.BALANCED : SceneAntialiasing.DISABLED;
        var scene = new Scene(new MVCIViewBuilder().build(), 500, 400, false, antialiasing);
        stage.setTitle("TEST");
        stage.setScene(scene);
        Platform.runLater(() -> {
            stage.show();
            stage.requestFocus();
        });
    }
}
