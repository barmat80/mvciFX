package com.maemlab.mvcifx.examples.app;

import com.maemlab.mvcifx.examples.mvci.simple.SimpleController;
import com.maemlab.mvcifx.examples.mvci.statetracking.STController;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Builder;

public class MVCIViewBuilder implements Builder<Region> {
    private final Region simpleViewBuilder = new SimpleController().getView();
    private final Region stViewBuilder = STController.create().getView();

    @Override
    public Region build() {
        var bp = new BorderPane();
        bp.setLeft(createLeft());
        bp.setCenter(createCenter());
        return bp;
    }

    private Node createLeft() {
        var btn = buildMenuItem("Simple Example", simpleViewBuilder);
        btn.setSelected(true);
        var btn1 = buildMenuItem("State Tracking Example", stViewBuilder);

        buildToggleButton(btn, btn1);

        return buildContainerPane(buildMenuBox(btn, btn1));
    }

    private static void buildToggleButton(ToggleButton btn, ToggleButton btn1) {
        var tg = new ToggleGroup();
        tg.getToggles().add(btn);
        tg.getToggles().add(btn1);
    }

    private ToggleButton buildMenuItem(String label, Node node) {
        var btn = new ToggleButton(label);
//        btn.setGraphicTextGap(10);
        btn.setAlignment(Pos.CENTER_LEFT);
        node.visibleProperty().bind(btn.selectedProperty());
        return btn;
    }

    private static VBox buildMenuBox(ToggleButton btn, ToggleButton btn1) {
        var vbox = new VBox();
        vbox.setAlignment(Pos.TOP_LEFT);
        vbox.setFillWidth(true);
        vbox.getChildren().add(btn);
        vbox.getChildren().add(btn1);
        return vbox;
    }

    private static ScrollPane buildContainerPane(VBox vbox) {
        var bp = new BorderPane();
        bp.setCenter(vbox);

        var scrollPane = new ScrollPane();
        scrollPane.setContent(bp);
        return scrollPane;
    }

    private Node createCenter() {
        return new StackPane(simpleViewBuilder, stViewBuilder);
    }
}
