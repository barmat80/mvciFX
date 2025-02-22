package com.maemlab.mvcifx.examples.mvci.util;

import com.maemlab.mvcifx.examples.data.NirvanaModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TableViewUtil {
    public static TableView<NirvanaModel> createTableView() {
        TableColumn<NirvanaModel, String> name = createColumn("Name");
        TableColumn<NirvanaModel, String> surname = createColumn("Surname");
        TableColumn<NirvanaModel, Integer> age = createColumn("Age");
        name.setCellValueFactory(column -> column.getValue().nameProperty());
        surname.setCellValueFactory(column -> column.getValue().surnameProperty());
        age.setCellValueFactory(column -> column.getValue().ageProperty().asObject());

        TableView<NirvanaModel> table = new TableView<>();
        table.getColumns().add(name);
        table.getColumns().add(surname);
        table.getColumns().add(age);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
        return table;
    }

    public static <S, T> TableColumn<S, T> createColumn(String label) {
        TableColumn<S, T> col = new TableColumn<>(label);
        col.setText(label);
        col.setMinWidth(150);
        col.setPrefWidth(150);
        col.setResizable(true);
        col.setSortable(false);
        return col;
    }
}
