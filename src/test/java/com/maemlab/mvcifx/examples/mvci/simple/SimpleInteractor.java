package com.maemlab.mvcifx.examples.mvci.simple;

import com.maemlab.mvcifx.examples.data.Nirvana;
import com.maemlab.mvcifx.mvci.Interactor;
import com.maemlab.mvcifx.examples.data.DAONirvana;
import com.maemlab.mvcifx.examples.data.NirvanaModel;

import java.util.List;

public class SimpleInteractor implements Interactor {
    private final SimpleModel model;
    private List<Nirvana> nirvanas;

    public SimpleInteractor(SimpleModel model) {
        this.model = model;
    }

    @Override
    public void fetchData() {
        nirvanas = DAONirvana.getInstance().getPersons();
    }

    @Override
    public void updateModelAfterFetchingData() {
        this.model.getFetchedPersonModelList().setAll(nirvanas.stream().map(NirvanaModel::toModel).toList());
    }
}
