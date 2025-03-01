package com.maemlab.mvcifx.examples.mvci.statetracking;

import javafx.concurrent.Task;

public class STController extends CommonController<STModel, STInteractor, STViewBuilder> {

    private STController(STModel model, STInteractor interactor, STViewBuilder viewBuilder) {
        super(model, interactor, viewBuilder);
        initialize();
    }

    public static STController create() {
        var model = new STModel();
        var interactor = new STInteractor(model);
        var viewBuilder= new STViewBuilder(model);
        var controller = new STController(model, interactor, viewBuilder);
        viewBuilder.setActions(controller::lookup, controller::save, controller::delete, controller::quit);
        return controller;
    }

    private void initialize() {
        this.model.deleteConfirmedProperty().addListener((obs, old, confirmed) -> {
            if (confirmed) {
                handleDelete();
            }
        });

        this.model.saveCompleteProperty().addListener((obs, old, saveDone) -> {
            if (saveDone) {
                handleSave();
            }
        });
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

    private void delete() {
        model.setDeleteRequested(true);
    }

    private void handleDelete() {
        //interactor.delete();
        model.setError(new RuntimeException("ERROR"));
    }

    private void save() {
        //interactor.save();
        model.setSaveRequested(true);
    }

    private void handleSave() {
        //interactor.save();
        model.setError(new RuntimeException("ERROR"));
        model.setSaveComplete(false);
    }

    private void quit() {
        model.setQuitRequested(true);
    }
}
