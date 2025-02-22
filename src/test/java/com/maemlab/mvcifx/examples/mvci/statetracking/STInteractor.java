package com.maemlab.mvcifx.examples.mvci.statetracking;

import com.maemlab.mvcifx.examples.data.DAONirvana;
import com.maemlab.mvcifx.examples.data.Nirvana;
import com.maemlab.mvcifx.exception.MVCIException;
import com.maemlab.mvcifx.mvci.Interactor;
import com.maemlab.mvcifx.examples.data.NirvanaModel;

import java.util.List;

public class STInteractor implements Interactor {
    private final STModel model;
    private List<Nirvana> nirvanas;

    public STInteractor(STModel model) {
        this.model = model;
    }

    @Override
    public void fetchData() throws MVCIException {
        nirvanas = DAONirvana.getInstance().getPersons();
    }

    @Override
    public void updateModelAfterFetchingData() {
        this.model.getFetchedNirvanaModelList().setAll(nirvanas.stream().map(NirvanaModel::toModel).toList());
    }
}
