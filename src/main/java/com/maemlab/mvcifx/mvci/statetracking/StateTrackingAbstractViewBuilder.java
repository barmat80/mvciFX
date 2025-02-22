package com.maemlab.mvcifx.mvci.statetracking;

import com.maemlab.mvcifx.mvci.Model;
import com.maemlab.mvcifx.mvci.ViewBuilder;
import javafx.scene.layout.Region;
import javafx.stage.Window;
import javafx.util.Builder;

/**
 * An abstract implementation of {@link ViewBuilder} that provides automated state tracking and UI response capabilities
 * for JavaFX views in the MVCI framework. This class handles common UI state changes by automatically responding to
 * {@link StateTrackingModel} property changes, managing user interactions and system events.
 *
 * <p>The builder automatically sets up window-level listeners for the following {@link StateTrackingModel} properties:
 * <ul>
 *   <li>{@code errorProperty} - Triggers error handling when an exception or error occurs</li>
 *   <li>{@code deleteRequestedProperty} - Manages delete confirmation dialogs and updates {@code deleteConfirmedProperty}</li>
 *   <li>{@code saveRequestedProperty} - Handles save operations and updates {@code saveDoneProperty}</li>
 *   <li>{@code quitRequestedProperty} - Manages application exit confirmation and updates {@code quitConfirmedProperty}</li>
 * </ul>
 *
 * <p>This builder is designed to work with window-level dialogs and alerts, automatically waiting for the scene
 * and window to be available before setting up state tracking. It provides a structured approach to handling
 * common application states while allowing customization of specific behaviors.
 *
 * <p>To implement this class, you must:
 * <ol>
 *   <li>Override the abstract handling methods to define specific behaviors:
 *     <ul>
 *       <li>{@link #handleError(Window, Throwable)} - Define error display/logging</li>
 *       <li>{@link #handleDeleteRequest(Window)} - Implement delete confirmation logic</li>
 *       <li>{@link #handleSaveRequest(Window)} - Implement save operation handling</li>
 *       <li>{@link #handleQuitRequest(Window)} - Define application exit behavior</li>
 *     </ul>
 *   </li>
 *   <li>Implement the {@link Builder#build()} method to create your view hierarchy</li>
 *   <li>Call {@link #setupModelListeners(Region)} in your build method to enable state tracking</li>
 * </ol>
 *
 * <p>Example implementation:
 * <pre>{@code
 * public class CustomViewBuilder extends StateTrackingAbstractViewBuilder<CustomModel> {
 *     public CustomViewBuilder(CustomModel model) {
 *         super(model);
 *     }
 *
 *     @Override
 *     public Region build() {
 *         VBox root = new VBox();
 *         // Add your UI components here
 *         setupModelListeners(root); // Enable state tracking
 *         return root;
 *     }
 *
 *     @Override
 *     public void handleError(Window parentWindow, Throwable error) {
 *         Alert alert = new Alert(Alert.AlertType.ERROR);
 *         alert.setContentText(error.getMessage());
 *         alert.showAndWait();
 *     }
 *
 *     @Override
 *     public boolean handleDeleteRequest(Window parentWindow) {
 *         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
 *         alert.setContentText("Are you sure you want to delete?");
 *         return alert.showAndWait()
 *                    .filter(response -> response == ButtonType.OK)
 *                    .isPresent();
 *     }
 *
 *     // Implement other required methods...
 * }
 * }</pre>
 *
 * @param <M> The type of {@link StateTrackingModel} this builder manages
 *
 * @see ViewBuilder
 * @see StateTrackingModel
 * @see Model
 * @see javafx.stage.Window
 * @see javafx.scene.layout.Region
 */
public abstract class StateTrackingAbstractViewBuilder<M extends StateTrackingModel> extends ViewBuilder<M> {

    /**
     * Creates a new ViewBuilder instance with the specified {@link StateTrackingModel}.
     * The provided model will be used to track application state and trigger UI updates.
     *
     * @param model The {@link Model} instance to track. Must not be null.
     * @throws IllegalArgumentException if the model is null
     */
    public StateTrackingAbstractViewBuilder(M model) {
        super(model);// Set the inherited protected field
    }

    /**
     * Sets up window-level state tracking listeners for the specified root region.
     * <p>This method must be called from the {@link #build()} method implementation after creating the root node but before returning it.
     * <p>The method establishes a chain of listeners that wait for the scene and window to be available
     * before setting up state tracking. This ensures that window-level dialogs can be properly parented
     * and positioned. The state tracking includes:
     * <ul>
     *   <li>Error handling: When {@code model.errorProperty()} changes to a non-null value,
     *       {@link #handleError(Window, Throwable)} is called and the error is automatically cleared</li>
     *   <li>Delete confirmation: When {@code model.deleteRequestedProperty()} becomes true,
     *       {@link #handleDeleteRequest(Window)} is called and the result is stored in
     *       {@code deleteConfirmedProperty}</li>
     *   <li>Save handling: When {@code model.saveRequestedProperty()} becomes true,
     *       {@link #handleSaveRequest(Window)} is called and the result is stored in
     *       {@code saveDoneProperty}</li>
     *   <li>Quit confirmation: When {@code model.quitRequestedProperty()} becomes true,
     *       {@link #handleQuitRequest(Window)} is called and the result is stored in
     *       {@code quitConfirmedProperty}</li>
     * </ul>
     * <p>Save handling includes automatic window closing behavior:
     * <ul>
     *   <li>If {@code model.closeAfterSaveProperty()} is true and the save operation is successful
     *       (returns true from {@link #handleSaveRequest}), the window will automatically close</li>
     *   <li>The window remains open if the save operation fails or if {@code closeAfterSaveProperty}
     *       is false</li>
     *   <li>Window closing occurs after the model's save-related properties are updated</li>
     * </ul>
     * <p>The method automatically resets request flags after handling them to prevent repeated triggers.
     * All handlers are called on the JavaFX Application Thread as they involve UI operations.
     *
     * @param root The root node of the view hierarchy. Must not be null.
     * @throws IllegalArgumentException if root is null
     */
    protected void setupModelListeners(Region root) {
        root.sceneProperty().addListener((obsScene, oldScene, newScene) ->{
            if (newScene != null) {// Error handling
                newScene.windowProperty().addListener((obsWindow, oldWindow, newWindow) -> {
                    if (newWindow != null) {
                        model.errorProperty().addListener((obs, oldVal, error) -> {
                            if (error != null) {
                                handleError(newWindow, error);
                                model.setError(null); // Reset error after handling
                            }
                        });

                        // Delete request handling
                        model.deleteRequestedProperty().addListener((obs, oldVal, requesting) -> {
                            if (requesting) {
                                var confirmed = handleDeleteRequest(newWindow);
                                model.setDeleteConfirmed(confirmed);
                                model.setDeleteRequested(false);
                            }
                        });

                        // Save request handling
                        model.getSaveRequestedProperty().addListener((obs, oldVal, saveDone) -> {
                            if (saveDone) {
                                var success = handleSaveRequest(newWindow);
                                model.setSaveDone(success);
                                model.setSaveRequested(false);

                                // Close window if save was successful and closeAfterSave is true
                                if (success && model.isCloseAfterSave()) {
                                    newWindow.hide();
                                }
                            }
                        });

                        // Quit request handling
                        model.quitRequestedProperty().addListener((obs, oldVal, requesting) -> {
                            if (requesting) {
                                var confirmed = handleQuitRequest(newWindow);
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
     * Handles errors reported by the model. This method is called automatically when the model's
     * {@code errorProperty} changes to a non-null value. The error will be automatically cleared
     * after this method returns.
     *
     * <p>Implementations should display or log the error appropriately, typically using a dialog
     * or alert. Since this method is called on the JavaFX Application Thread, it's safe to show
     * dialogs directly.
     *
     * @param parentWindow The window that should parent any dialogs shown. Never null.
     * @param error The error to handle. Never null.
     */
    public abstract void handleError(Window parentWindow, Throwable error);

    /**
     * Handles delete confirmation requests from the model. This method is called automatically
     * when the model's {@code deleteRequestedProperty} becomes true. The result is stored in
     * the model's {@code deleteConfirmedProperty}.
     *
     * <p>Implementations should prompt the user for confirmation, typically using a confirmation
     * dialog. Since this method is called on the JavaFX Application Thread, it's safe to show
     * dialogs directly.
     *
     * @param parentWindow The window that should parent any dialogs shown. Never null.
     * @return true if the delete operation is confirmed, false otherwise
     */
    public abstract boolean handleDeleteRequest(Window parentWindow);

    /**
     * Handles save requests from the model. This method is called automatically when the model's
     * {@code saveRequestedProperty} becomes true. The result is stored in the model's
     * {@code saveDoneProperty}.
     *
     * <p>Implementations should perform the save operation and optionally show progress or
     * completion dialogs. Since this method is called on the JavaFX Application Thread,
     * long-running operations should be performed on a background thread.
     *
     * @param parentWindow The window that should parent any dialogs shown. Never null.
     * @return true if the save operation was successful, false otherwise
     */
    public abstract boolean handleSaveRequest(Window parentWindow);

    /**
     * Handles quit requests from the model. This method is called automatically when the model's
     * {@code quitRequestedProperty} becomes true. The result is stored in the model's
     * {@code quitConfirmedProperty}.
     *
     * <p>Implementations should prompt for confirmation if there are unsaved changes and perform
     * any necessary cleanup. Since this method is called on the JavaFX Application Thread,
     * it's safe to show dialogs directly.
     *
     * @param parentWindow The window that should parent any dialogs shown. Never null.
     * @return true if the application should quit, false to cancel
     */
    public abstract boolean handleQuitRequest(Window parentWindow);
}