package com.maemlab.mvcifx.exception;

import com.maemlab.mvcifx.mvci.Interactor;

/**
 * Abstract implementation of the {@code Exception} class.
 * This exception is thrown by {@link Interactor#fetchData()}.
 *
 * <p>Users of Interactor interface can override constructors for custom behavior.
 *
 * @see com.maemlab.mvcifx.mvci.Interactor
 */
public abstract class MVCIException extends Exception {
}
