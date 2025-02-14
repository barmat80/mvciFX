package com.maemlab.mvcifx.mvci;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * The abstract {@code Model} class represents the data and state in the MVCI framework.
 * It provides observable properties that can be bound to UI components and track the
 * application's state changes.
 * <p>This implementation uses JavaFX properties to enable automatic UI updates
 * when the model's state changes.
 * <p>For a deeper explanation of the MCVI framework and how it works,
 * please see <a href="https://www.pragmaticcoding.ca/javafx/elements/mvci-quick">PragmaticCoding.ca: JavaFX: Quick Guide to MVCI</a></p>
 *
 * @see Controller
 * @see Interactor
 */

public abstract class Model {
    private final ObjectProperty<Throwable> error = new SimpleObjectProperty<>();
    private final ObjectProperty<Boolean> deleteRequested = new SimpleObjectProperty<>(false);
    private final ObjectProperty<Boolean> deleteConfirmed = new SimpleObjectProperty<>(false);
    private final ObjectProperty<Boolean> saveRequested = new SimpleObjectProperty<>(false);
    private final ObjectProperty<Boolean> saveDone = new SimpleObjectProperty<>(false);
    private final ObjectProperty<Boolean> quitRequested = new SimpleObjectProperty<>(false);
    private final ObjectProperty<Boolean> quitConfirmed = new SimpleObjectProperty<>(false);

    /**
     * Gets the property tracking error states in the application.
     * @return an ObjectProperty containing any error that has occurred
     */
    public ObjectProperty<Throwable> errorProperty() {
        return error;
    }

    public void setError(Throwable throwable) {
        error.set(throwable);
    }

    /**
     * Gets the property indicating whether a delete operation has been requested.
     * @return a BooleanProperty tracking delete request state
     */
    public ObjectProperty<Boolean> deleteRequestedProperty() {
        return deleteRequested;
    }

    public void setDeleteRequested(boolean b) {
        deleteRequested.set(b);
    }

    /**
     * Gets the property indicating whether a delete operation has been completed.
     * @return a BooleanProperty tracking delete confirmation state
     */
    public ObjectProperty<Boolean> deleteConfirmedProperty() {
        return deleteConfirmed;
    }

    public void setDeleteConfirmed(boolean b) {
        deleteConfirmed.set(b);
    }

    /**
     * Gets the property indicating whether a save operation has been requested.
     * @return a BooleanProperty tracking save request state
     */
    public ObjectProperty<Boolean> getSaveRequestedProperty() {
        return saveRequested;
    }

    public void setSaveRequested(boolean b) {
        saveRequested.set(b);
    }

    /**
     * Gets the property indicating whether a save operation has been completed.
     * @return a BooleanProperty tracking save completion state
     */
    public ObjectProperty<Boolean> saveDoneProperty() {
        return saveDone;
    }

    public void setSaveDone(boolean b) {
        saveDone.set(b);
    }

    /**
     * Gets the property indicating whether a quit operation has been requested.
     * @return a BooleanProperty tracking quit request state
     */
    public ObjectProperty<Boolean> quitRequestedProperty() {
        return quitRequested;
    }

    public void setQuitRequested(boolean b) {
        quitRequested.set(b);
    }

    /**
     * Gets the property indicating whether a quit operation has been completed.
     * @return a BooleanProperty tracking quit confirmation state
     */
    public ObjectProperty<Boolean> quitConfirmedProperty() {
        return quitConfirmed;
    }

    public void setQuitConfirmed(boolean b) {
        quitConfirmed.set(b);
    }
}
