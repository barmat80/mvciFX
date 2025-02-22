package com.maemlab.mvcifx.mvci.statetracking;

import com.maemlab.mvcifx.mvci.Interactor;
import com.maemlab.mvcifx.mvci.base.ParameterizedController;
import javafx.scene.layout.Region;

/**
 * An abstract implementation of the {@link ParameterizedController} interface that provides state tracking capabilities.
 * This class coordinates constructor-based initialization and interactions between state-aware ViewBuilder, Model, and Interactor components
 *
 * <p>This implementation is specifically designed to work with components that support state tracking:
 * <ul>
 *   <li>A {@link StateTrackingModel} for maintaining component state
 *   <li>A {@link StateTrackingAbstractViewBuilder} for UI updates based on state changes
 *   <li>An {@link Interactor} for business logic and data operations
 * </ul>
 *
 * @param <M> The type of StateTrackingModel this controller manages
 * @param <I> The type of Interactor this controller uses
 * @param <B> The type of StateTrackingAbstractViewBuilder this controller uses
 *
 * @see ParameterizedController
 * @see StateTrackingModel
 * @see StateTrackingAbstractViewBuilder
 * @see Interactor
 */
public abstract class StateTrackingParameterizedAbstractController<M extends StateTrackingModel, I extends Interactor, B extends StateTrackingAbstractViewBuilder<M>>
        implements ParameterizedController {
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
    public StateTrackingParameterizedAbstractController(M model, I interactor, B viewBuilder) {
        if (model == null || interactor == null || viewBuilder == null) {
            throw new IllegalArgumentException("Model, Interactor and ViewBuilder must not be null");
        }

        this.model = model;
        this.interactor = interactor;
        this.viewBuilder = viewBuilder;
    }

    @Override
    public Region getView() {
        if (region == null) {
            region = viewBuilder.build();
        }
        return region;
    }

    @Override
    public abstract void load();
}
