package com.maemlab.mvcifx.examples.mvci.simple;

import com.maemlab.mvcifx.mvci.Model;
import com.maemlab.mvcifx.examples.data.NirvanaModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SimpleModel implements Model {
    private final ObservableList<NirvanaModel> fetchedNirvanaModelList = FXCollections.observableArrayList();

    public ObservableList<NirvanaModel> getFetchedPersonModelList() {
        return fetchedNirvanaModelList;
    }
}
