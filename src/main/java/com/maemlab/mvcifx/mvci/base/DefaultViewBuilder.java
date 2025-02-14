package com.maemlab.mvcifx.mvci.base;

import com.maemlab.mvcifx.mvci.Model;
import com.maemlab.mvcifx.mvci.ViewBuilder;
import javafx.scene.layout.Region;

/**
 * A default implementation of ViewBuilder that provides standard handling for common Model properties.
 * <p>This class serves as an optional intermediate layer between ViewBuilder and concrete implementations,
 * offering ready-to-use listener setup for standard model events.</p>
 *
 * <p>Users of this class can either:
 * <ul>
 *   <li>Use it directly and implement only the helper methods and build()</li>
 *   <li>Override setupModelListeners() for custom behavior</li>
 *   <li>Skip it entirely and extend ViewBuilder for complete control</li>
 * </ul>
 *
 * @param <M> The type of Model associated with this builder, must extend the base Model class
 */
public abstract class DefaultViewBuilder<M extends Model> extends ViewBuilder<M> {

    /**
     * Sets up model listeners for the specified root region.
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
    @Override
    protected void setupModelListeners(Region root) {
        root.sceneProperty().addListener((obsScene, oldScene, newScene) ->{
            if (newScene != null) {// Error handling
                newScene.windowProperty().addListener((obsWindow, oldWindow, newWindow) -> {
                    if (newWindow != null) {
                        model.errorProperty().addListener((obs, oldVal, newVal) -> {
                            if (newVal != null) {
                                handleError(newVal);
                                model.setError(null); // Reset error after handling
                            }
                        });

                        // Delete request handling
                        model.deleteRequestedProperty().addListener((obs, oldVal, newVal) -> {
                            if (newVal) {
                                boolean confirmed = handleDeleteRequest();
                                model.setDeleteConfirmed(confirmed);
                                model.setDeleteRequested(false);
                            }
                        });

                        // Save request handling
                        model.getSaveRequestedProperty().addListener((obs, oldVal, newVal) -> {
                            if (newVal) {
                                boolean success = handleSaveRequest();
                                model.setSaveDone(success);
                                model.setSaveRequested(false);
                            }
                        });

                        // Quit request handling
                        model.quitRequestedProperty().addListener((obs, oldVal, newVal) -> {
                            if (newVal) {
                                boolean confirmed = handleQuitRequest();
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