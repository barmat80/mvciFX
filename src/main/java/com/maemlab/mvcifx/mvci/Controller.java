package com.maemlab.mvcifx.mvci;

import javafx.scene.layout.Region;

/**
 * The base {@code Controller} interface for the MVCI (Model-View-Controller-Interactor) framework.
 * Controllers are responsible for instantiating the View, Model, and Interactor components and coordinating interactions
 * between them.
 * <p>For a deeper explanation of the MCVI framework and how it works,
 * please see <a href="https://www.pragmaticcoding.ca/javafx/elements/mvci-quick">PragmaticCoding.ca: JavaFX: Quick Guide to MVCI</a></p>
 * @see Model
 * @see Interactor
 */
public interface Controller {
    /**
     * Retrieves the root visual component of the associated View.
     *
     * @return A JavaFX Region representing the root node of the view hierarchy
     */
    Region getView();

    /**
     * Performs asynchronous data retrieval using a background Task to interact with a data source (e.g.: a database)
     * through the {@link Interactor}. This method is designed to be triggered by UI components
     * in response to user actions (like button clicks, menu selections).
     *
     * <p>The method handles threading concerns automatically, ensuring that database operations
     * occur off the JavaFX Application Thread while UI updates are properly dispatched back
     * to it.
     *
     * @param innerRunnable A callback to be executed on the JavaFX Application Thread after
     *                      the database operation completes successfully
     */
    void lookup(Runnable innerRunnable);

    /**
     * Initializes the controller by performing necessary startup data retrievals.
     * This method should be called at the end of the controller's constructor to
     * load initial data based on constructor parameters.
     *
     * <p>Unlike {@link #lookup(Runnable)}, which responds to user actions, this method
     * is specifically intended for initialization-time data loading. It can perform
     * either synchronous or asynchronous operations depending on the implementation's
     * needs (such as etching initial data based on constructor parameters).
     */
    void load();
}
