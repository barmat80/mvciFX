package com.maemlab.mvcifx.mvci.base;

import com.maemlab.mvcifx.mvci.Controller;
import com.maemlab.mvcifx.mvci.Interactor;
import com.maemlab.mvcifx.mvci.Model;
import com.maemlab.mvcifx.mvci.ViewBuilder;
import javafx.scene.layout.Region;

/**
 * Abstract implementation of the Controller interface that provides default behavior
 * for common MVCI operations. This class handles the standard interactions between
 * ViewBuilder, Model, and Interactor components.
 *
 * @param <M> The type of Model this controller manages
 * @param <I> The type of Interactor this controller uses
 * @param <B> The type of ViewBuilder this controller uses
 */
public abstract class AbstractController<M extends Model, I extends Interactor, B extends ViewBuilder<M>>
        implements Controller {
    protected final M model;
    protected final I interactor;
    protected final B viewBuilder;
    protected Region region;

    /**
     * Creates a new controller instance with the specified components.
     *
     * @param model The Model instance
     * @param interactor The Interactor instance
     * @param viewBuilder The ViewBuilder instance
     * @throws IllegalArgumentException if any parameter is null
     */
    public AbstractController(M model, I interactor, B viewBuilder) {
        if (model == null || interactor == null || viewBuilder == null) {
            throw new IllegalArgumentException("Model, Interactor, and ViewBuilder must not be null");
        }

        this.model = model;
        this.interactor = interactor;
        this.viewBuilder = viewBuilder;
    }

    @Override
    public Region getView() {
        if (this.region == null) {
            this.region = this.viewBuilder.build();
        }
        return this.region;
    }

    @Override
    public abstract void lookup(Runnable innerRunnable);

    @Override
    public abstract void load();
}
