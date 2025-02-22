package com.maemlab.mvcifx.examples.mvci.statetracking;

import com.maemlab.mvcifx.examples.mvci.util.TableViewUtil;
import com.maemlab.mvcifx.mvci.statetracking.StateTrackingAbstractViewBuilder;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.util.Optional;
import java.util.function.Consumer;

public class STViewBuilder extends StateTrackingAbstractViewBuilder<STModel> {
    private Consumer<Runnable> lookupAction;
    private Runnable saveAction;
    private Runnable deleteAction;
    private Runnable quitAction;

    public STViewBuilder(STModel model) {
        super(model);// This will set the model field in ViewBuilder
    }

    public void setActions(Consumer<Runnable> lookupAction, Runnable saveAction, Runnable deleteAction, Runnable quitAction) {
        this.lookupAction = lookupAction;
        this.saveAction = saveAction;
        this.deleteAction = deleteAction;
        this.quitAction = quitAction;
    }

    @Override
    public Region build() {
        var button = new Button("Search");
        button.setOnAction(evt -> lookupAction.accept(() -> button.setDisable(false)));

        var table = TableViewUtil.createTableView();
        table.setItems(model.getFetchedNirvanaModelList());
        var selectedItemProperty = table.getSelectionModel().selectedItemProperty();
        model.selectedItemProperty().bind(selectedItemProperty);

        BooleanBinding isItemSelected = Bindings.createBooleanBinding(
                () -> !table.getSelectionModel().isEmpty(),
                table.getSelectionModel().selectedItemProperty()
        );

        var buttonSave = new Button("Save");
        buttonSave.setOnAction(evt -> saveAction.run());
        buttonSave.disableProperty().bind(isItemSelected.not());

        var buttonDelete = new Button("Delete");
        buttonDelete.setOnAction(evt -> deleteAction.run());
        buttonDelete.disableProperty().bind(isItemSelected.not());

        var buttonQuit = new Button("Quit");
        buttonQuit.setOnAction(evt -> quitAction.run());

        var hbox = new HBox();
        hbox.getChildren().add(buttonSave);
        hbox.getChildren().add(buttonDelete);
        hbox.getChildren().add(buttonQuit);

        var vbox = new VBox();
        vbox.getChildren().add(button);
        vbox.getChildren().add(table);
        vbox.getChildren().add(hbox);

        setupModelListeners(vbox);

        return vbox;
    }

    @Override
    public void handleError(Throwable error) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setHeaderText("Error");
        a.setContentText(error.getMessage());
        a.show();
    }

    @Override
    public boolean handleDeleteRequest() {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setHeaderText("Delete");
        a.setContentText("Do you want to delete " + model.getDescription() + "? (Click Yes to receive an Error Message)");
        Optional<ButtonType> result = a.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    @Override
    public boolean handleSaveRequest() {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText("Save");
        a.setContentText("You're saving " + model.getDescription());
        a.show();
        return true;
    }

    @Override
    public boolean handleQuitRequest() {
        Alert a = new Alert(Alert.AlertType.WARNING);
        a.setHeaderText("Quit");
        a.setContentText("Quit?");
        a.show();
        return false;
    }


}
