package com.maemlab.mvcifx.mvci;

import javafx.scene.layout.Region;
import javafx.util.Builder;

/**
 * The abstract base class for MVCI view builders that construct JavaFX UI components
 * based on a provided Model instance.
 *
 * <p>This class provides the foundation for creating view hierarchies in the MVCI framework,
 * with built-in support for model listener setup and property binding.
 *
 * @param <M> The type of Model associated with this builder, must extend the base {@link Model} class
 */
public abstract class ViewBuilder<M extends Model> implements Builder<Region> {

    /**
     * The model instance associated with this builder.
     * Protected access allows derived classes to access the model directly.
     */
    protected M model;

    /**
     * Sets up model listeners for the specified root region.
     * This method should be called from the build() method implementation
     * to establish necessary bindings and listeners for model properties.
     *
     * @param root The root Region of the view hierarchy
     */
    protected abstract void setupModelListeners(Region root);
}
