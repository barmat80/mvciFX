package com.maemlab.mvcifx.exception;

import com.maemlab.mvcifx.mvci.Interactor;

/**
 * Abstract implementation of the {@code Exception} class.
 * This exception is thrown by {@link Interactor#fetchData()}.
 *
 * <p>Users of Interactor interface can override constructors for custom behavior.
 *
 * @see Interactor
 */
public abstract class MVCIException extends Exception {
    /**
     * Constructs a new MVCI exception with the specified detail message.
     *
     * @param message the detail message. The detail message is saved for later retrieval by the {@link #getMessage()} method.
     */
    public MVCIException(String message) {
        super(message);
    }

    /**
     * Constructs a new MVCI exception with the specified detail message and cause.
     *
     * @param message the detail message (which is saved for later retrieval by the {@link #getMessage()} method).
     * @param err the cause (which is saved for later retrieval by the {@link #getCause()} method). A {@code null} value is permitted
     * and indicates that the cause is nonexistent or unknown.
     */
    public MVCIException(String message, Throwable err) {
        super(message, err);
    }

    /**
     * Constructs a new MVCI exception with an empty message and the specified cause.
     *
     * @param e the cause (which is saved for later retrieval by the {@link #getCause()} method). A {@code null} value is permitted
     *          and indicates that the cause is nonexistent or unknown.
     */
    public MVCIException(Exception e) {
        super("", e);
    }
}
