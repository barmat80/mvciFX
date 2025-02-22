package com.maemlab.mvcifx.examples.mvci.simple;

import com.maemlab.mvcifx.mvci.base.DataSourceController;
import javafx.concurrent.Task;
import javafx.scene.layout.Region;

public class SimpleController implements DataSourceController {
    private final SimpleInteractor interactor;
    private final SimpleViewBuilder viewBuilder;
    private Region region;

    public SimpleController() {
        var model = new SimpleModel();
        interactor = new SimpleInteractor(model);
        viewBuilder = new SimpleViewBuilder(model, this::lookup);
    }

    @Override
    public Region getView() {
        if (this.region == null) {
            this.region = this.viewBuilder.build();
        }
        return this.region;
    }

    @Override
    public void lookup(Runnable innerRunnable) {
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                interactor.fetchData();
                return null;
            }
        };

        task.setOnSucceeded(evt -> this.interactor.updateModelAfterFetchingData());

        task.setOnFailed(evt -> System.out.println(task.getException()));

        var taskThread = new Thread(task);
        taskThread.start();
    }
}
