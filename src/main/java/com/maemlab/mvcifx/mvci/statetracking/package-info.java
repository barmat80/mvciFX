/**
 * Provides state-tracking implementations of the MVCI framework.
 *
 * <p>This package implements state-aware versions of core MVCI components. These implementations include:
 * <ul>
 *   <li>{@link com.maemlab.mvcifx.mvci.statetracking.StateTrackingModel}: Implements state tracking management through observable properties,
 *   that automatically reflect application state changes
 *   <li>{@link com.maemlab.mvcifx.mvci.statetracking.StateTrackingAbstractViewBuilder}: Provides view building functionality
 *       with automated model state observation and UI updates
 *   <li>{@link com.maemlab.mvcifx.mvci.statetracking.StateTrackingDataSourceAbstractController}: Provides state tracking capabilities,
 *   combining asynchronous data retrieval through background task for data source interactions
 *   <li>{@link com.maemlab.mvcifx.mvci.statetracking.StateTrackingParameterizedAbstractController}: Provides state tracking capabilities,
 *   enabling controller initialization using constructor parameters
 * </ul>
 *
 * <p>These implementations work together to provide a cohesive state management solution for MVCI applications.
 *
 * @see com.maemlab.mvcifx.mvci
 * @see com.maemlab.mvcifx.mvci.base
 */
package com.maemlab.mvcifx.mvci.statetracking;