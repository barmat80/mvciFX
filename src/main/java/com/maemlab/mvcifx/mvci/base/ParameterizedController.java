package com.maemlab.mvcifx.mvci.base;

import com.maemlab.mvcifx.mvci.Controller;

/**
 * A specialized {@code Controller} interface that supports initialization based on constructor parameters
 * @see Controller
 */

public interface ParameterizedController extends Controller {
    /**
     * Loads initial data based on constructor parameters.
     * This method should be called at the end of the controller's constructor.
     *
     * <p>Unlike {@link DataSourceController#lookup(Runnable)}, which responds to user actions, this method
     * is specifically intended for initialization-time data loading. It can perform
     * either synchronous or asynchronous operations depending on the implementation's
     * needs (such as etching initial data based on constructor parameters).
     */
    void load();
}
