package org.javafxgod.cafe; // Your project's package

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.geometry.Insets;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
// No import needed for MenuItem if it's in the same package

public class CafeController {

    @FXML
    private TilePane menuTilePane;

    @FXML
    private ListView<OrderItem> orderListView; // Changed from MenuItem to OrderItem

    @FXML
    private Label totalLabel;

    // The list now holds OrderItem objects, which track quantity
    private ObservableList<OrderItem> currentOrder;
    private List<MenuItem> menuItems;

    @FXML
    public void initialize() {
        // Initialize the list for items in the current order
        currentOrder = FXCollections.observableArrayList();
        orderListView.setItems(currentOrder);

        // Use a custom cell factory for a better UI, passing a reference to this controller
        orderListView.setCellFactory(param -> new OrderItemCell(this));

        // Load all available menu items
        loadMenuItems();

        // Create the UI tiles for each menu item
        populateMenu();

        // The ListView click listener is no longer needed as buttons handle interaction
    }

    // Inner class to represent an item in the order with a quantity
    public static class OrderItem {
        private final MenuItem menuItem;
        private final IntegerProperty quantity;

        public OrderItem(MenuItem menuItem) {
            this.menuItem = menuItem;
            this.quantity = new SimpleIntegerProperty(1);
        }

        public MenuItem getMenuItem() { return menuItem; }
        public int getQuantity() { return quantity.get(); }
        public IntegerProperty quantityProperty() { return quantity; }
        public void incrementQuantity() { quantity.set(quantity.get() + 1); }
        public void decrementQuantity() { quantity.set(quantity.get() - 1); }
        public double getTotalPrice() { return menuItem.getPrice() * getQuantity(); }

        // Override equals to find items based on the menu item's name
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            OrderItem orderItem = (OrderItem) o;
            return Objects.equals(menuItem.getName(), orderItem.getMenuItem().getName());
        }

        @Override
        public int hashCode() {
            return Objects.hash(menuItem.getName());
        }
    }

    // Inner class for the custom ListCell UI with + and - buttons
    public class OrderItemCell extends ListCell<OrderItem> {
        private final HBox hbox = new HBox(10);
        private final Label nameLabel = new Label();
        private final Label priceLabel = new Label();
        private final Pane spacer = new Pane();

        // UI for quantity controls
        private final Button minusButton = new Button("-");
        private final Label quantityLabel = new Label();
        private final Button plusButton = new Button("+");
        private final HBox quantityControl = new HBox(5);

        private OrderItem currentItem;

        public OrderItemCell(CafeController controller) {
            super();

            // Configure quantity controls
            quantityControl.setAlignment(Pos.CENTER);
            quantityControl.getChildren().addAll(minusButton, quantityLabel, plusButton);
            minusButton.getStyleClass().add("quantity-button");
            plusButton.getStyleClass().add("quantity-button");
            quantityLabel.getStyleClass().add("quantity-label");

            // Configure main layout
            HBox.setHgrow(spacer, Priority.ALWAYS);
            hbox.getChildren().addAll(nameLabel, spacer, quantityControl, priceLabel);
            nameLabel.getStyleClass().add("order-cell-name");
            priceLabel.getStyleClass().add("order-cell-price");

            // Set button actions to call controller methods
            plusButton.setOnAction(event -> {
                if (currentItem != null) {
                    controller.incrementOrderItem(currentItem);
                }
            });
            minusButton.setOnAction(event -> {
                if (currentItem != null) {
                    controller.decrementOrderItem(currentItem);
                }
            });
        }

        @Override
        protected void updateItem(OrderItem item, boolean empty) {
            super.updateItem(item, empty);
            currentItem = item; // Keep a reference to the current item

            if (empty || item == null) {
                setText(null);
                setGraphic(null);
            } else {
                nameLabel.setText(item.getMenuItem().getName());

                // Use a listener to automatically update the cell when quantity changes
                item.quantityProperty().addListener((obs, oldVal, newVal) -> {
                    quantityLabel.setText(String.valueOf(newVal.intValue()));
                    priceLabel.setText(String.format("$%.2f", item.getTotalPrice()));
                });

                quantityLabel.setText(String.valueOf(item.getQuantity()));
                priceLabel.setText(String.format("$%.2f", item.getTotalPrice()));
                setGraphic(hbox);
            }
        }
    }


    /**
     * Loads the predefined list of menu items.
     */
    private void loadMenuItems() {
        menuItems = new ArrayList<>();
        String basePath = "/cafe/icons/";
        menuItems.add(new MenuItem("Espresso", 2.50, basePath + "test.jpg"));
        menuItems.add(new MenuItem("Latte", 3.50, basePath + "test.jpg"));
        menuItems.add(new MenuItem("Cappuccino", 3.25, basePath + "test.jpg"));
        menuItems.add(new MenuItem("Mocha", 4.00, basePath + "test.jpg"));
        menuItems.add(new MenuItem("Classic Burger", 8.50, basePath + "test.jpg"));
        menuItems.add(new MenuItem("Cheese Burger", 9.25, basePath + "test.jpg"));
        menuItems.add(new MenuItem("Bacon Burger", 9.75, basePath + "test.jpg"));
        menuItems.add(new MenuItem("Veggie Burger", 8.75, basePath + "test.jpg"));
        menuItems.add(new MenuItem("Fries", 3.00, basePath + "test.jpg"));
        menuItems.add(new MenuItem("Croissant", 2.75, basePath + "test.jpg"));
        menuItems.add(new MenuItem("Muffin", 2.50, basePath + "test.jpg"));
        menuItems.add(new MenuItem("Soda", 2.00, basePath + "test.jpg"));
    }

    /**
     * Creates and displays a UI tile for each menu item.
     */
    private void populateMenu() {
        for (MenuItem item : menuItems) {
            VBox menuItemBox = createMenuItemBox(item);
            menuTilePane.getChildren().add(menuItemBox);
        }
    }

    /**
     * Creates a VBox representing a single menu item tile.
     */
    private VBox createMenuItemBox(MenuItem item) {
        VBox box = new VBox(10);
        box.setAlignment(Pos.CENTER);
        box.getStyleClass().add("menu-item");

        ImageView imageView = new ImageView();
        try {
            Image image = new Image(getClass().getResourceAsStream(item.getImagePath()));
            if (image.isError()) {
                throw new NullPointerException("Image not found at path: " + item.getImagePath());
            }
            imageView.setImage(image);
        } catch (Exception e) {
            imageView.setImage(new Image("https://placehold.co/100x100/e8e8e8/000000?text=" + item.getName().replace(" ", "%20")));
            System.err.println("Could not load image: " + item.getImagePath() + ". Using placeholder.");
        }
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        imageView.setPreserveRatio(true);

        Label nameLabel = new Label(item.getName());
        nameLabel.getStyleClass().add("item-name");
        Label priceLabel = new Label(String.format("$%.2f", item.getPrice()));
        priceLabel.getStyleClass().add("item-price");

        box.getChildren().addAll(imageView, nameLabel, priceLabel);
        box.setOnMouseClicked(event -> addItemToOrder(item));

        return box;
    }

    /**
     * Adds an item to the order. If it already exists, increments the quantity.
     */
    private void addItemToOrder(MenuItem item) {
        for (OrderItem orderItem : currentOrder) {
            if (orderItem.getMenuItem().getName().equals(item.getName())) {
                orderItem.incrementQuantity();
                updateTotal();
                return;
            }
        }
        currentOrder.add(new OrderItem(item));
        updateTotal();
    }

    /**
     * Public method to be called by the "+" button in the cell.
     */
    public void incrementOrderItem(OrderItem item) {
        item.incrementQuantity();
        updateTotal();
    }

    /**
     * Public method to be called by the "-" button in the cell.
     */
    public void decrementOrderItem(OrderItem item) {
        item.decrementQuantity();
        if (item.getQuantity() <= 0) {
            currentOrder.remove(item);
        }
        updateTotal();
    }

    /**
     * Recalculates and updates the total price display based on items and quantities.
     */
    private void updateTotal() {
        double total = 0.0;
        for (OrderItem item : currentOrder) {
            total += item.getTotalPrice();
        }
        totalLabel.setText(String.format("Total: $%.2f", total));
    }

    @FXML
    private void handleCheckout() {
        if (currentOrder.isEmpty()) {
            showDialog("Order is empty!", false);
            return;
        }
        double total = Double.parseDouble(totalLabel.getText().replace("Total: $", ""));
        showDialog(String.format("Checkout successful!\nTotal: $%.2f", total), true);
        handleClearOrder();
    }

    @FXML
    private void handleClearOrder() {
        currentOrder.clear();
        updateTotal();
    }

    private void showDialog(String message, boolean isSuccess) {
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.initStyle(StageStyle.TRANSPARENT);

        VBox dialogVBox = new VBox(20);
        dialogVBox.setAlignment(Pos.CENTER);
        dialogVBox.setPadding(new Insets(20, 40, 20, 40));
        dialogVBox.getStyleClass().add("dialog-pane");
        if (!isSuccess) {
            dialogVBox.getStyleClass().add("dialog-pane-error");
        }

        Label label = new Label(message);
        label.getStyleClass().add("dialog-label");
        Button okButton = new Button("OK");
        okButton.getStyleClass().add("dialog-button");
        okButton.setOnAction(e -> dialogStage.close());

        dialogVBox.getChildren().addAll(label, okButton);

        Scene dialogScene = new Scene(dialogVBox);
        dialogScene.setFill(Color.TRANSPARENT);

        final String cssPath = "/style.css";
        java.net.URL cssUrl = getClass().getResource(cssPath);
        if (cssUrl != null) {
            dialogScene.getStylesheets().add(cssUrl.toExternalForm());
        } else {
            System.err.println("CRITICAL: Stylesheet not found at " + cssPath);
        }

        dialogStage.setScene(dialogScene);
        dialogStage.showAndWait();
    }
}
