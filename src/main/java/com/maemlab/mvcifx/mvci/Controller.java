package com.maemlab.mvcifx.mvci;

import javafx.scene.layout.Region;

/**
 * The base {@code Controller} interface for the MVCI (Model-View-Controller-Interactor) framework.
 * Controllers are responsible for instantiating the View, Model and Interactor components and coordinating interactions
 * between them.
 * <p>For a deeper explanation of the MCVI framework and how it works,
 * please see <a href="https://www.pragmaticcoding.ca/javafx/elements/mvci-quick">PragmaticCoding.ca: JavaFX: Quick Guide to MVCI</a></p>
 *
 * @param <M> The type of Model associated with this controller, must extend the base {@link Model} class
 *
 * @see Model
 * @see Interactor
 * @see ViewBuilder
 */
public interface Controller<M extends Model> {
    /**
     * Retrieves the root visual component of the associated View.
     *
     * @return A JavaFX Region representing the root node of the view hierarchy
     */
    Region getView();
}
