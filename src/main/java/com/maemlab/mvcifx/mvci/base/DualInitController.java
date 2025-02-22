package com.maemlab.mvcifx.mvci.base;

import com.maemlab.mvcifx.mvci.Controller;

/**
 * A specialized  {@code Controller} interface that combines both data source interaction and parameter-based initialization capabilities
 * @see Controller
 * @see DataSourceController
 * @see ParameterizedController
 */
public interface DualInitController extends DataSourceController, ParameterizedController {
    // No additional methods needed - combines both patterns
}
