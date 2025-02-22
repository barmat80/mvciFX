package com.maemlab.mvcifx.examples.mvci.statetracking;

import com.maemlab.mvcifx.mvci.statetracking.StateTrackingAbstractViewBuilder;
import com.maemlab.mvcifx.mvci.statetracking.StateTrackingDataSourceAbstractController;

public abstract class CommonController<M extends STModel, I extends STInteractor, B extends StateTrackingAbstractViewBuilder<M>>
        extends StateTrackingDataSourceAbstractController<M, I, B> {

    protected CommonController(M model, I interactor, B viewBuilder) {
        super(model, interactor, viewBuilder);
    }

    protected void debug(String title, String msg) {
        System.out.println(this.getClass().getCanonicalName() + "." + title + ": " + msg);
    }

    protected void error(String title, Exception ex) {
        System.out.println(this.getClass().getCanonicalName()  + "." + title + "\n" + ex.getMessage());
    }

    protected void error(String title, Throwable throwable) {
        System.err.println(this.getClass().getCanonicalName() + "." + title + "\n" + throwable.getMessage());
    }
}
