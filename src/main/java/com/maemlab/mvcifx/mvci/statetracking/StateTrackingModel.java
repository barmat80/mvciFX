package com.maemlab.mvcifx.mvci.statetracking;

import com.maemlab.mvcifx.mvci.Model;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * An implementation of the {@link Model} interface that provides state tracking through observable properties.
 * These properties can be bound to UI components to automatically reflect application state changes.
 *
 * @see Model
 * @see StateTrackingAbstractViewBuilder
 * @see javafx.beans.property.Property
 */
public class StateTrackingModel implements Model {
    private final ObjectProperty<Throwable> error = new SimpleObjectProperty<>();
    private final BooleanProperty deleteRequested = new SimpleBooleanProperty(false);
    private final BooleanProperty deleteConfirmed = new SimpleBooleanProperty(false);
    private final BooleanProperty saveRequested = new SimpleBooleanProperty(false);
    private final BooleanProperty saveDone = new SimpleBooleanProperty(false);
    private final BooleanProperty quitRequested = new SimpleBooleanProperty(false);
    private final BooleanProperty quitConfirmed = new SimpleBooleanProperty(false);

    /**
     * A property that controls whether an action should be taken after a successful deletion operation.
     * This property is particularly useful for modal dialogs, forms, or child MVCI components that should
     * take an action upon successful completion.
     * <p>When this property is set to true, an action must be defined through {@link StateTrackingAbstractViewBuilder#setOnDeleteConfirmed},
     * otherwise an {@link IllegalStateException} will be thrown.
     * <p>This property should typically be configured in the Controller rather than the ViewBuilder
     * to maintain proper separation of concerns and allow for dynamic behavior changes.
     */
    private final BooleanProperty performActionAfterDeletion = new SimpleBooleanProperty(false);

    /**
     * A property that controls whether an action should be taken after a successful save operation.
     * This property is particularly useful for modal dialogs, forms, or child MVCI components that should
     * take an action upon successful completion.
     * <p>When this property is set to true, an action must be defined through {@link StateTrackingAbstractViewBuilder#setOnSaveConfirmed},
     * otherwise an {@link IllegalStateException} will be thrown.
     * <p>This property should typically be configured in the Controller rather than the ViewBuilder
     * to maintain proper separation of concerns and allow for dynamic behavior changes.
     */
    private final BooleanProperty performActionAfterSave = new SimpleBooleanProperty(false);

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
    public BooleanProperty deleteRequestedProperty() {
        return deleteRequested;
    }

    public void setDeleteRequested(boolean b) {
        deleteRequested.set(b);
    }

    /**
     * Gets the property indicating whether a delete operation has been completed.
     * @return a BooleanProperty tracking delete confirmation state
     */
    public BooleanProperty deleteConfirmedProperty() {
        return deleteConfirmed;
    }

    public void setDeleteConfirmed(boolean b) {
        deleteConfirmed.set(b);
    }

    /**
     * Gets the property indicating whether a save operation has been requested.
     * @return a BooleanProperty tracking save request state
     */
    public BooleanProperty saveRequestedProperty() {
        return saveRequested;
    }

    public void setSaveRequested(boolean b) {
        saveRequested.set(b);
    }

    /**
     * Gets the property indicating whether a save operation has been completed.
     * @return a BooleanProperty tracking save completion state
     */
    public BooleanProperty saveDoneProperty() {
        return saveDone;
    }

    public void setSaveDone(boolean b) {
        saveDone.set(b);
    }

    /**
     * Gets the property indicating whether a quit operation has been requested.
     * @return a BooleanProperty tracking quit request state
     */
    public BooleanProperty quitRequestedProperty() {
        return quitRequested;
    }

    public void setQuitRequested(boolean b) {
        quitRequested.set(b);
    }

    /**
     * Gets the property indicating whether a quit operation has been completed.
     * @return a BooleanProperty tracking quit confirmation state
     */
    public BooleanProperty quitConfirmedProperty() {
        return quitConfirmed;
    }

    public void setQuitConfirmed(boolean b) {
        quitConfirmed.set(b);
    }

    public BooleanProperty performActionAfterDeletionProperty() {
        return performActionAfterDeletion;
    }

    public boolean isPerformActionAfterDeletion() {
        return performActionAfterDeletion.get();
    }

    public void setPerformActionAfterDeletion(boolean b) {
        performActionAfterDeletion.set(b);
    }

    public BooleanProperty performActionAfterSaveProperty() {
        return performActionAfterSave;
    }

    public void setPerformActionAfterSave(boolean b) {
        performActionAfterSave.set(b);
    }

    public boolean isPerformActionAfterSave() {
        return performActionAfterSave.get();
    }
}
