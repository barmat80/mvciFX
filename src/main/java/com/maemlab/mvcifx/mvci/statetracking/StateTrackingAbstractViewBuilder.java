package com.maemlab.mvcifx.mvci.statetracking;

import com.maemlab.mvcifx.mvci.ViewBuilder;
import javafx.scene.layout.Region;

/**
 * An abstract implementation of ViewBuilder that provides state tracking capabilities, handling StateTrackingModel properties.
 * <p>This class serves as an optional intermediate layer between ViewBuilder and concrete implementations,
 * offering ready-to-use listener setup for standard model events.</p>
 *
 * <p>Users of this class can either:
 * <ul>
 *   <li>Use it directly and implement only the helper methods and build()</li>
 *   <li>Override setupModelListeners() for custom behavior</li>
 * </ul>
 * @see ViewBuilder
 * @see StateTrackingModel
 */
public abstract class StateTrackingAbstractViewBuilder<M extends StateTrackingModel> extends ViewBuilder<M> {

    public StateTrackingAbstractViewBuilder(M model) {
        this.model = model;  // Set the inherited protected field
    }

    /**
     * Sets up model listeners for the specified root region.
     * <p>This method should be called from the build() method implementation to establish necessary bindings and listeners for model properties.
     * <p><b>Implementation note</b>: This method waits for the scene and the window to be available, setting the listener for the root element
     *
     * <pre>root.sceneProperty().addListener((obs, oldScene, newScene) -> {
     *     if (newScene != null) {
     *                 newScene.windowProperty().addListener((obs1, oldWindow, newWindow) -> {
     *                      if (newWindow != null) {
     *                          Stage stage = (Stage) newWindow;
     *                          // Error/Deleting/Save/Quit handling
     *                      }
     *                 });
     *     }
     * };</pre>
     *
     * @param root The root Region of the view hierarchy
     */
    protected void setupModelListeners(Region root) {
        root.sceneProperty().addListener((obsScene, oldScene, newScene) ->{
            if (newScene != null) {// Error handling
                newScene.windowProperty().addListener((obsWindow, oldWindow, newWindow) -> {
                    if (newWindow != null) {
                        model.errorProperty().addListener((obs, oldVal, error) -> {
                            if (error != null) {
                                handleError(error);
                                model.setError(null); // Reset error after handling
                            }
                        });

                        // Delete request handling
                        model.deleteRequestedProperty().addListener((obs, oldVal, requesting) -> {
                            if (requesting) {
                                var confirmed = handleDeleteRequest();
                                model.setDeleteConfirmed(confirmed);
                                model.setDeleteRequested(false);
                            }
                        });

                        // Save request handling
                        model.getSaveRequestedProperty().addListener((obs, oldVal, saveDone) -> {
                            if (saveDone) {
                                var success = handleSaveRequest();
                                model.setSaveDone(success);
                                model.setSaveRequested(false);
                            }
                        });

                        // Quit request handling
                        model.quitRequestedProperty().addListener((obs, oldVal, requesting) -> {
                            if (requesting) {
                                var confirmed = handleQuitRequest();
                                model.setQuitConfirmed(confirmed);
                                model.setQuitRequested(false);
                            }
                        });
                    }
                });
            }
        });
    }

    /**
     * Handles errors reported by the model. Default implementation shows an error dialog.
     * Override this method to provide custom error handling.
     *
     * @param error The error to handle
     */
    public abstract void handleError(Throwable error);

    /**
     * Handles delete requests from the model. Default implementation shows a confirmation dialog.
     * Override this method to provide custom delete handling.
     *
     * @return true if delete is confirmed, false otherwise
     */
    public abstract boolean handleDeleteRequest();

    /**
     * Handles save requests from the model. Default implementation returns true.
     * Override this method to provide custom save handling.
     *
     * @return true if save is successful, false otherwise
     */
    public abstract boolean handleSaveRequest();

    /**
     * Handles quit requests from the model. Default implementation shows a confirmation dialog.
     * Override this method to provide custom quit handling.
     *
     * @return true if quit is confirmed, false otherwise
     */
    public abstract boolean handleQuitRequest();
}