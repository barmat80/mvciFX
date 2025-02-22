package com.maemlab.mvcifx.examples.mvci.simple;

import com.maemlab.mvcifx.examples.mvci.util.TableViewUtil;
import com.maemlab.mvcifx.mvci.ViewBuilder;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.util.function.Consumer;

public class SimpleViewBuilder extends ViewBuilder<SimpleModel> {
    private final Consumer<Runnable> lookupAction;

    public SimpleViewBuilder(SimpleModel model, Consumer<Runnable> lookupAction) {
        super(model);
        this.lookupAction = lookupAction;
    }

    @Override
    public Region build() {
        var button = new Button("Search");
        button.setOnAction(evt -> lookupAction.accept(() -> button.setDisable(false)));

        var table = TableViewUtil.createTableView();
        table.setItems(model.getFetchedPersonModelList());

        var vbox = new VBox();
        vbox.getChildren().add(button);
        vbox.getChildren().add(table);
        return vbox;
    }
}
