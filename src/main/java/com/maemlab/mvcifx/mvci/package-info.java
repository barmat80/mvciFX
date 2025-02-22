/**
 * Defines the core contracts of the MVCI (Model-View-Controller-Interactor) framework.
 *
 * <p>This package contains the fundamental interfaces and classes that establish
 * the MVCI framework for JavaFX applications. The framework separates concerns into:
 * <ul>
 *   <li>{@link com.maemlab.mvcifx.mvci.Model}: Holds application state and data
 *   <li>{@link com.maemlab.mvcifx.mvci.ViewBuilder}: Defines the user interface structure (via ViewBuilder)
 *   <li>{@link com.maemlab.mvcifx.mvci.Controller}: Coordinates interactions between components
 *   <li>{@link com.maemlab.mvcifx.mvci.Interactor}: Handles business logic and data operations
 * </ul>
 *
 * <p>For a deeper explanation of the MVCI framework and how it works,
 * please visit the
 * <a href="https://www.pragmaticcoding.ca/javafx/elements/mvci-quick">Quick Guide to MVCI at PragmaticCoding.ca</a>
 *
 * @see com.maemlab.mvcifx.mvci.base
 */
package com.maemlab.mvcifx.mvci;