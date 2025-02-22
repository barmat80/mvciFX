package com.maemlab.mvcifx.mvci;

import javafx.scene.layout.Region;
import javafx.util.Builder;

/**
 * The abstract base class for MVCI view builders that construct JavaFX UI components
 * based on a provided {@link Model} instance.
 *
 * <p>This class provides the foundation for creating view hierarchies in the MVCI framework
 *
 * @param <M> The type of Model associated with this builder, must extend the base {@link Model} class
 *
 * @see Model
 */
public abstract class ViewBuilder<M extends Model> implements Builder<Region> {

    /**
     * The model instance associated with this builder.
     * Protected access allows derived classes to access the model directly.
     */
    protected M model;

    /**
     * Creates a new ViewBuilder instance with the specified {@link Model}.
     *
     * @param model The {@link Model} instance
     */
    public ViewBuilder(M model) {
        if (model == null) {
            throw new IllegalArgumentException("Model must not be null");
        }

        this.model = model;
    }
}
