package com.maemlab.mvcifx.examples.mvci.statetracking;

import com.maemlab.mvcifx.examples.data.NirvanaModel;
import com.maemlab.mvcifx.mvci.statetracking.StateTrackingModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class STModel extends StateTrackingModel {
    private final ObservableList<NirvanaModel> fetchedNirvanaModelList = FXCollections.observableArrayList();
    private final ObjectProperty<NirvanaModel> selectedItem = new SimpleObjectProperty<>();

    public ObservableList<NirvanaModel> getFetchedNirvanaModelList() {
        return fetchedNirvanaModelList;
    }

    public ObjectProperty<NirvanaModel> selectedItemProperty() {
        return selectedItem;
    }

    public String getDescription() {return selectedItem.get().getName() + " " + selectedItem.get().getSurname();}
}
