package com.maemlab.mvcifx.mvci.base;

import com.maemlab.mvcifx.mvci.Controller;
import com.maemlab.mvcifx.mvci.Interactor;

/**
 * A specialized {@code Controller} interface that defines asynchronous data retrieval capabilities
 * using a background Task to interact with a data source (e.g.: a database).
 * @see Controller
 * @see Interactor
 */

public interface DataSourceController extends Controller {
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
}
