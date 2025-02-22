/**
 * Provides specialized Controller interfaces of the MVCI framework.
 *
 * <p>This package extends the base MVCI framework with controllers that handle specific initialization and data interaction patterns:
 * <ul>
 *   <li>{@link com.maemlab.mvcifx.mvci.base.DataSourceController}: Manages asynchronous data retrieval
 *   through background task for data source interactions
 *   <li>{@link com.maemlab.mvcifx.mvci.base.ParameterizedController}: Enables controller initialization using constructor parameters
 *   <li>{@link com.maemlab.mvcifx.mvci.base.DualInitController}: Combines both data source interaction and parameter-based initialization capabilities
 * </ul>
 *
 * <p>These interfaces build upon the core MVCI components to provide more specialized controller behaviors.
 *
 * @see com.maemlab.mvcifx.mvci
 */
package com.maemlab.mvcifx.mvci.base;