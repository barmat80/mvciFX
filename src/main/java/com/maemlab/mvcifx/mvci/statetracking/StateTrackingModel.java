package com.maemlab.mvcifx.mvci.statetracking;

import com.maemlab.mvcifx.mvci.Model;
import javafx.beans.property.ObjectProperty;
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
    private final ObjectProperty<Boolean> deleteRequested = new SimpleObjectProperty<>(false);
    private final ObjectProperty<Boolean> deleteConfirmed = new SimpleObjectProperty<>(false);
    private final ObjectProperty<Boolean> saveRequested = new SimpleObjectProperty<>(false);
    private final ObjectProperty<Boolean> saveDone = new SimpleObjectProperty<>(false);

    /**
     * A property that controls whether the window should automatically close after a successful save operation.
     * This property is particularly useful for modal dialogs, forms, or child MVCI components that should
     * close themselves upon successful completion.
     *
     * <p>When this property is set to true:
     * <ul>
     *   <li>The window will automatically close after a successful save operation</li>
     *   <li>The window will remain open if the save operation fails</li>
     *   <li>The window closing occurs after all save-related properties are updated</li>
     * </ul>
     *
     * <p>This property should typically be configured in the Controller rather than the ViewBuilder
     * to maintain proper separation of concerns and allow for dynamic behavior changes.
     */
    private final ObjectProperty<Boolean> closeAfterSave = new SimpleObjectProperty<>(false);
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

    /**
     * Gets the property indicating whether the window should be closed after a successful save.
     * This is particularly useful for popup windows or child MVCI components.
     * @return a BooleanProperty controlling post-save window behavior
     */
    public ObjectProperty<Boolean> closeAfterSaveProperty() {
        return closeAfterSave;
    }

    public void setCloseAfterSave(boolean close) {
        closeAfterSave.set(close);
    }

    public boolean isCloseAfterSave() {
        return closeAfterSave.get();
    }
}
