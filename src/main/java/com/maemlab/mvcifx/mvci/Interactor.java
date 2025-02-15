package com.maemlab.mvcifx.mvci;

import com.maemlab.mvcifx.exception.MVCIException;

/**
 * The {@code Interactor} interface defines the business logic layer in the MVCI framework.
 * It acts as a mediator between the Controller and data sources, handling all data operations
 * and business rules.
 * <p>For a deeper explanation of the MCVI framework and how it works,
 * please see <a href="https://www.pragmaticcoding.ca/javafx/elements/mvci-quick">PragmaticCoding.ca: JavaFX: Quick Guide to MVCI</a></p>
 *
 * @see Controller
 * @see Model
 */
public interface Interactor {
    /**
     * Retrieves all available data without any specific filtering.
     *
     * @throws MVCIException if data retrieval fails
     */
    void fetchData() throws MVCIException;

    /**
     * Updates the underlying model after a standard data fetch operation.
     * This method ensures that the Model reflects the latest data state and triggers
     * appropriate UI updates through property bindings.
     */
    void updateModelAfterFetchingData();
}
