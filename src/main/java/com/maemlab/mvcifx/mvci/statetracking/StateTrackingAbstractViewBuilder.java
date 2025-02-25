package com.maemlab.mvcifx.mvci.statetracking;

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
 *   <li>{@code error} - Triggers error handling when an exception or error occurs</li>
 *   <li>{@code deleteRequested} - Manages delete confirmation dialogs and updates {@code deleteConfirmed}</li>
 *   <li>{@code saveRequested} - Handles save operations and updates {@code saveDone}</li>
 *   <li>{@code quitRequested} - Manages application exit confirmation and updates {@code quitConfirmed}</li>
 *   <li>{@code performActionAfterDeletion} - Controls whether an action should be executed after successful deletion</li>
 *   <li>{@code performActionAfterSave} - Controls whether an action should be executed after successful save</li>
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
 *   <li>When using post-operation actions, set appropriate callbacks using {@link #setOnSaveConfirmed(Runnable)}
 *       or {@link #setOnDeleteConfirmed(Runnable)}</li>
 * </ol>
 *
 * <p>Example implementation:
 * <pre>{@code
 * public class CustomViewBuilder extends StateTrackingAbstractViewBuilder<CustomModel> {
 *     public CustomViewBuilder(CustomModel model) {
 *         super(model);
 *         // Set up post-operation callbacks if needed
 *           setOnSaveConfirmed(() -> { // do something });
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
 * @see javafx.stage.Window
 * @see javafx.scene.layout.Region
 */
public abstract class StateTrackingAbstractViewBuilder<M extends StateTrackingModel> extends ViewBuilder<M> {
    protected Runnable onSaveConfirmed;
    protected Runnable onDeleteConfirmed;

    /**
     * Creates a new ViewBuilder instance with the specified {@link StateTrackingModel}.
     * The provided model will be used to track application state and trigger UI updates.
     *
     * @param model The {@link StateTrackingModel} instance to track. Must not be null.
     * @throws IllegalArgumentException if the model is null
     */
    public StateTrackingAbstractViewBuilder(M model) {
        super(model);// Set the inherited protected field
    }

    /**
     * Setups the action that should be taken after a successful save completion.
     * <p>This callback will be executed automatically when all of these conditions are met:
     * <ul>
     *   <li>{@code model.saveRequestedProperty()} is set to true</li>
     *   <li>{@link #handleSaveRequest(Window)} returns true</li>
     *   <li>{@code model.isPerformActionAfterSave()} is set to true</li>
     * </ul>
     * <p>If {@code model.isPerformActionAfterSave()} is true but this callback is not set,
     * an {@link IllegalStateException} will be thrown when a save request is processed.
     * <p>This method should typically be called during the ViewBuilder's initialization
     * (typically in Controller constructor) when post-save actions are required.
     * @param callback the callback that will be called after successful save
     * @see StateTrackingModel#isPerformActionAfterSave()
     */
    public void setOnSaveConfirmed(Runnable callback) {
        this.onSaveConfirmed = callback;
    }

    /**
     * Setups the action that should be taken after a successful deletion completion.
     * <p>This callback will be executed automatically when all of these conditions are met:
     * <ul>
     *   <li>{@code model.deleteRequestedProperty()} is set to true</li>
     *   <li>{@link #handleDeleteRequest(Window)} returns true</li>
     *   <li>{@code model.isPerformActionAfterDeletion()} is set to true</li>
     * </ul>
     * <p>If {@code model.isPerformActionAfterDeletion()} is true but this callback is not set,
     * an {@link IllegalStateException} will be thrown when a delete request is processed.
     * <p>This method should typically be called during the ViewBuilder's initialization
     * (typically in Controller constructor) when post-deletion actions are required.
     *
     * @param callback the callback that will be called after successful deletion
     * @see StateTrackingModel#isPerformActionAfterDeletion()
     */
    public void setOnDeleteConfirmed(Runnable callback) {
        this.onDeleteConfirmed = callback;
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
     *       {@code deleteConfirmed}</li>
     *   <li>Save handling: When {@code model.saveRequestedProperty()} becomes true,
     *       {@link #handleSaveRequest(Window)} is called and the result is stored in
     *       {@code saveDone}</li>
     *   <li>Quit confirmation: When {@code model.quitRequestedProperty()} becomes true,
     *       {@link #handleQuitRequest(Window)} is called and the result is stored in
     *       {@code quitConfirmed}</li>
     * </ul>
     *
     * <p>Post-operation action handling:
     * <ul>
     *   <li>If {@code model.isPerformActionAfterDeletion()} is true and the delete operation is successful
     *       (returns true from handleDeleteRequest), the action defined by {@link #setOnDeleteConfirmed(Runnable)}
     *       will automatically run</li>
     *   <li>If {@code model.isPerformActionAfterSave()} is true and the save operation is successful
     *       (returns true from handleSaveRequest), the action defined by {@link #setOnSaveConfirmed(Runnable)}
     *       will automatically run</li>
     *   <li>An {@link IllegalStateException} is thrown if {@code model.isPerformActionAfterDeletion()} or
     *       {@code model.isPerformActionAfterSave()} is true but the corresponding callback is not set</li>
     * </ul>
     * <p>The method automatically resets request flags after handling them to prevent repeated triggers.
     * All handlers are called on the JavaFX Application Thread as they involve UI operations.
     *
     * @param root The root node of the view hierarchy. Must not be null.
     * @throws IllegalArgumentException if root is null
     * @throws IllegalStateException if a post-operation action is requested but no callback is set
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
                                if (confirmed) {
                                    if (model.isPerformActionAfterDeletion() && onDeleteConfirmed == null) {
                                        throw new IllegalStateException(
                                                "Action after deletion was requested but no callback was set. Call setOnDeleteConfirmed first.");
                                    }
                                    if (model.isPerformActionAfterDeletion()) {
                                        onDeleteConfirmed.run();
                                    }
                                }
                                model.setDeleteConfirmed(confirmed);
                                model.setDeleteRequested(false);
                            }
                        });

                        // Save request handling
                        model.saveRequestedProperty().addListener((obs, oldVal, saveRequested) -> {
                            if (saveRequested) {
                                var success = handleSaveRequest(newWindow);
                                model.setSaveDone(success);
                                model.setSaveRequested(false);

                                if (success) {
                                    if (model.isPerformActionAfterSave() && onSaveConfirmed == null) {
                                        throw new IllegalStateException(
                                                "Action after save was requested but no callback was set. Call setOnSaveConfirmed first.");
                                    }
                                    if (model.isPerformActionAfterSave()) {
                                        onSaveConfirmed.run();
                                    }
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
     * {@code error} changes to a non-null value. The error will be automatically cleared
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
     * when the model's {@code deleteRequested} becomes true. The result is stored in
     * the model's {@code deleteConfirmed}.
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
     * {@code saveRequested} becomes true. The result is stored in the model's
     * {@code saveDone}.
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
     * {@code quitRequested} becomes true. The result is stored in the model's
     * {@code quitConfirmed}.
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