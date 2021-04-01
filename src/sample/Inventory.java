package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HorizontalDirection;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import sample.Part;
import sample.AlertBox;
import sample.Outsourced;
import sample.InHouse;
import sample.ConfirmBox;
import java.awt.*;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.function.Predicate;
/**
 * This is the File where the Main Method resides. Variables are at the top and Methods can be found at the bottom
 * Supplied class Inventory.java
 */

/**
 *
 * @author Robert Bradbury
 */


/**
 *RUNTIME ERROR
 * Had difficulty setting up multiple scenes/windows. I Wanted to reuse many of the class members I had already created but when I did the window would not display recycled/re-used items. So in the interest of getting this
 * PA done, I went ahead and created a lot of redundant members. Clearly not optimal, but I was able to keep moving
 *
 * FUTURE ENHANCEMENT
 *
 * Need to find a way to reduce the amount of redundant class items/members such as buttons, labels and such
 * @author Robert Bradbury
 */
public class Inventory extends Application {
    Stage window = new Stage();
    ToggleGroup partTypeChoice;
    GridPane topPartsControls;
    Scene mainScene, modifyPartScene, ProductScene, addPartScene;
    TableView<Product> ProductsTable;
    TableView<Part> PartsTableMainView, PartsTableProductView, PartsTableAssociated,PartsModifyTable;
    TableColumn nameColumn, PartIdColumn, PartNameColumn, PartPriceColumn, associatedPartProductViewPartOutSourcedNameColumn,associatedPartProductViewPartMachineIdColumn,PartQuantityColumn,associatedPartProductViewPartIdColumn,associatedPartProductViewPartNameColumn,associatedPartProductViewPartPriceColumn,associatedPartProductViewPartQuantityColumn;
    TextField namePartInput, pricePartInput, maxQuantityPartInput, minQuantityPartInput, searchPartInput, machineIdOrCompanyNamePartInput, IDPartInput, invLevelPartInput, searchProductInput, searchPartInput2, IDProductInput, nameProductInput, invLevelProductInput, priceProductInput, maxQuantityProductInput, minQuantityProductInput, machineProductIDPartInput, companyNamePartInput;
    Button modifyButton, removeAssociatedPart2ProductButton,searchPartButton,saveProductButton, saveOutSourcedPartButton, saveInHousePartButton, saveModifyPartButton, saveAddPartButton, addAssociatedPart2ProductButton;
    Label AddProductLabel, idLabel, invLevelLabel, idProductLabel, nameAddProductLabel, invLevelProductLabel, priceProductAddLabel, maxQuantityProductAddLabel, minQuantityProductAddLabel, machineProductIDLabel, macIdOrCompanyNameLabel;
    Part newOutSourcedPart = new Outsourced(1, "SUPER_TRUCK", 10000, 1, 1, 1, "MissionFirst");
    Part newOutSourcedPart2 = new InHouse(1, "SUPER_Fusion", 10000, 1, 1, 1, 96);
    ObservableList<Product> products;
    RadioButton partTypeRadioButton1 = new RadioButton("In-House");
    RadioButton partTypeRadioButton2 = new RadioButton("Outsourced");
    String observable, olValue, newValue;
    ObservableList<Part> Parts = FXCollections.observableArrayList();
    ObservableList<Part> searchParts = FXCollections.observableArrayList();
    ObservableList<Part> partsAssociatedToProductsList = FXCollections.observableArrayList();
    ObservableList<Product> Products = FXCollections.observableArrayList();
    ObservableList<Part> PartsToRemove = FXCollections.observableArrayList();

    public static ObservableList<Part> allParts;
    public static ObservableList<Product> allProducts;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window.setTitle("Inventory Management System");
////////////////////////////////buttons, labels and other controls
        /**
         *Labels are Initialized here
         */
        //Product Label
        Label productsViewLabel = new Label("PRODUCTS");
        productsViewLabel.setFont(Font.font(null, FontWeight.BOLD, 20));
        productsViewLabel.setTextFill(Color.WHITE);
        //Parts Label
        Label partsViewLabel = new Label("PARTS");
        partsViewLabel.setFont(Font.font(null, FontWeight.BOLD, 20));
        partsViewLabel.setTextFill(Color.WHITE);
        //Add Part Label
        Label AddPartLabel = new Label("ADD Part");
        AddPartLabel.setTextFill(Color.YELLOW);
        AddPartLabel.setFont(Font.font(null, FontWeight.BOLD, 26));
        // A Label to determine if we need to use a machine ID or a Company Name
        Label macIdOrCompanyNameLabel = new Label("Machine ID or Company:");
        macIdOrCompanyNameLabel.setTextFill(Color.WHITE);
        //Add Product Label
        Label AddProductLabel = new Label("ADD Product");
        AddProductLabel.setTextFill(Color.YELLOW);
        AddProductLabel.setFont(Font.font(null, FontWeight.BOLD, 26));
        ///Name Label
        Label nameAddLabel = new Label("Name:");
        nameAddLabel.setTextFill(Color.WHITE);
        ///ID Label
        Label idLabel = new Label("ID");
        idLabel.setTextFill(Color.WHITE);
        ///Inventory  Label
        Label invLevelLabel = new Label("Inv:");
        invLevelLabel.setTextFill(Color.WHITE);
        ///Price Label
        Label priceAddLabel = new Label("Price/Cost::");
        priceAddLabel.setTextFill(Color.WHITE);
        ///Max Quantity Label
        Label maxQuantityAddLabel = new Label("Max Quantity:");
        maxQuantityAddLabel.setTextFill(Color.WHITE);
        ///Min Quantity Label
        Label minQuantityAddLabel = new Label("Min Quantity:");
        minQuantityAddLabel.setTextFill(Color.WHITE);
        ///Parts Label
        Label productViewLabel = new Label("PARTS");
        productViewLabel.setFont(Font.font(null, FontWeight.BOLD, 20));
        productViewLabel.setTextFill(Color.WHITE);
        ///Add Product Label
        Label nameAddProductLabel = new Label("Name:");
        nameAddProductLabel.setTextFill(Color.WHITE);
        ///ID Product Label
        Label idProductLabel = new Label("ID:");
        idProductLabel.setTextFill(Color.WHITE);
        ///Inventory Product Label
        Label invLevelProductLabel = new Label("Inv:");
        invLevelProductLabel.setTextFill(Color.WHITE);
        ///Product  Prince Label
        Label priceProductAddLabel = new Label("Price/Cost::");
        priceProductAddLabel.setTextFill(Color.WHITE);
        ///Product  Max Quantity Label
        Label maxQuantityProductAddLabel = new Label("Max Quantity:");
        maxQuantityProductAddLabel.setTextFill(Color.WHITE);
        ///Product  Min Quantity Label
        Label minQuantityProductAddLabel = new Label("Min Quantity:");
        minQuantityProductAddLabel.setTextFill(Color.WHITE);

        /**
         *Buttons are Initialized here
         */

        //Buttons Section - Tons of Buttons
        Button ViewButton = new Button("VIEW");
        ViewButton.setOnAction(event -> window.setScene(mainScene));
        ///Exit Button
        Button exitButton = new Button("Exit");
        exitButton.setOnAction(event -> window.close());
        ///Save Product Button - Calls various methods via lamdba expressions based on values of the AddProductLabel
        Button saveProductButton = new Button("Save");
        saveProductButton.setOnAction(event -> { try {
            if (AddProductLabel.getText() == "Add Product") {
                if (Integer.parseInt(minQuantityProductInput.getText()) < 0 || Integer.parseInt(maxQuantityProductInput.getText()) < Integer.parseInt(minQuantityProductInput.getText()) || Integer.parseInt(minQuantityProductInput.getText()) > Integer.parseInt(maxQuantityProductInput.getText()) || Integer.parseInt(invLevelProductInput.getText()) > Integer.parseInt(maxQuantityProductInput.getText()) || Integer.parseInt(invLevelProductInput.getText()) < Integer.parseInt(minQuantityProductInput.getText()))
                {
                    System.out.println("Inventory is greater then max, no cant do!");
                    Alert newAlert2 = new Alert(Alert.AlertType.WARNING);
                    newAlert2.setTitle("ERROR");
                    newAlert2.setContentText("Please enter Numeric values for\n Inventory between the Min and Max");
                    newAlert2.showAndWait();
                }

                else if (Integer.parseInt(invLevelProductInput.getText()) < Integer.parseInt(maxQuantityProductInput.getText()))
                {
                    addProductButtonOnClick();
                    window.setScene(mainScene);
                    nameProductInput.clear();
                    IDProductInput.clear();
                    invLevelProductInput.clear();
                    priceProductInput.clear();
                    maxQuantityProductInput.clear();
                    minQuantityProductInput.clear();
                    PartsTableAssociated.refresh();
                    partsAssociatedToProductsList.clear();
                }

                }
                else if (AddProductLabel.getText() == "Modify Product")
                {
                    if (Integer.parseInt(minQuantityProductInput.getText()) < 0 || Integer.parseInt(maxQuantityProductInput.getText()) < Integer.parseInt(minQuantityProductInput.getText()) || Integer.parseInt(minQuantityProductInput.getText()) > Integer.parseInt(maxQuantityProductInput.getText()) || Integer.parseInt(invLevelProductInput.getText()) > Integer.parseInt(maxQuantityProductInput.getText()) || Integer.parseInt(invLevelProductInput.getText()) < Integer.parseInt(minQuantityProductInput.getText()))
                    {
                        System.out.println("Inventory is greater then max, no cant do!");
                        Alert newAlert2 = new Alert(Alert.AlertType.WARNING);
                        newAlert2.setTitle("ERROR");
                        newAlert2.setContentText("Please enter Numeric values for\n Inventory between the Min and Max");
                        newAlert2.showAndWait();
                    }

                    else if (Integer.parseInt(invLevelProductInput.getText()) < Integer.parseInt(maxQuantityProductInput.getText()))
                    {
                        addModifiedProductButtonOnClick();
                        deleteModifiedProductButtonOnClick();
                        window.setScene(mainScene);
                        System.out.println("Hitting the Modify Product Save section");
                    }

                }

        }
        catch(NumberFormatException e){
                System.out.println("These are not numeric values!");
                Alert newAlert2 = new Alert(Alert.AlertType.WARNING);
                newAlert2.setTitle("ERROR");
                newAlert2.setContentText("Please enter Numeric values for\n Inventory between the Min and Max");
                newAlert2.showAndWait();
            }
        });///end of Lambda



        ///Save Part Button
        Button saveAddPartButton = new Button("Save");
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///addAssociatedPart2ProductButton Product Button
        Button addAssociatedPart2ProductButton = new Button("Add");
        addAssociatedPart2ProductButton.setOnAction(event -> {
            addPartToProduct();
        });
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///removeAssociatedPart2ProductButton Product Button
        Button removeAssociatedPart2ProductButton = new Button("Remove Associated Part");
        removeAssociatedPart2ProductButton.setOnAction(event -> {
            System.out.println("This is from the Delete button");
            if(ConfirmBox.errorChecking("Associated Part Removal","Are you sure you want to remove this part?" , ProductScene) == true)
            removePartFromProductButtonOnClick();
        });
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///saveModifyPartButton Part Button
        Button saveModifyPartButton = new Button("Save");
        ///Cancel Part Button  - Sets the scene to the main-scene and clears the input boxes
        Button cancelPartButton = new Button("Cancel");
        cancelPartButton.setOnAction(event -> {
            window.setScene(mainScene);
            IDPartInput.clear();
            namePartInput.clear();
            invLevelPartInput.clear();
            pricePartInput.clear();
            maxQuantityPartInput.clear();
            minQuantityPartInput.clear();
            machineIdOrCompanyNamePartInput.clear();
        });
        ///Cancel Product Button  - Sets the scene to the main-scene and clears the input boxes
        Button cancelProductButton = new Button("Cancel");
        cancelProductButton.setOnAction(event -> {window.setScene(mainScene);
        nameProductInput.clear();IDProductInput.clear();invLevelProductInput.clear();priceProductInput.clear();maxQuantityProductInput.clear();minQuantityProductInput.clear();
        PartsTableAssociated.refresh();
        partsAssociatedToProductsList.clear();
        });
        ///Add  Part Button  - Sets the AddProductLabel.setText to "Add Product"
        Button addProductButton = new Button("Add");
        addProductButton.setOnAction(event -> {
            window.setScene(ProductScene);
            AddProductLabel.setText("Add Product");
            PartsTableAssociated.refresh();
        });
        ///Modify  Part Button  - Sets the AddProductLabel.setText to "Modify"
        Button modifyProductButton = new Button("Modify");
        modifyProductButton.setOnAction(event -> {
            window.setScene(ProductScene);
            AddProductLabel.setText("Modify Product");modifyProductButtonOnClick();
        });
        ///Delete  Product Button  - Sets the AddProductLabel.setText to "Delete"
        Button deleteProductButton = new Button("Delete");
        deleteProductButton.setOnAction(event -> {
            ConfirmBox.display("Product Listings", "Do you want to delete this record?");
            if(ConfirmBox.answer == true )
                deleteProductButtonOnClick();
            else {
                return ;
            }
        });

        ///Add  Part Button  - Sets the AddProductLabel.setText to "Add"
        Button addPartButton = new Button("Add");
        addPartButton.setOnAction(event -> {
            window.setScene(modifyPartScene);
            AddPartLabel.setText("Add Part");
        });
        ///Modify  Part Button  - Sets the AddProductLabel.setText to "Modify"
        Button modifyPartButton = new Button("Modify");
        modifyPartButton.setOnAction(event -> {
            window.setScene(modifyPartScene);
            AddPartLabel.setText("Modify Part");
            modifyPartButtonOnClick();
        });
        ///Delete  Part Button
        Button deletePartButton = new Button("Delete");
        deletePartButton.setOnAction(event -> {
            ConfirmBox.display("Part Listings", "Do you want to delete this record?");
            if(ConfirmBox.answer == true)
            deletePartButtonOnClick();
            else if (ConfirmBox.answer == false){
                return ;
            }


        });
        ///Delete  Part Button
        Button AlertButton = new Button("ALERT");
        AlertButton.setOnAction(event -> AlertBox.display("BS Software Development", "CCIE Wireless"));
        Button confirmButton = new Button("CONFIRM");
        confirmButton.setOnAction(event -> {
            boolean answer = ConfirmBox.display("This is the Confirmation Box", "Will you finish doing the goals you have set forth?");
            System.out.println(answer);
        });

        /**
         *Radio Buttons are Initialized here
         */

/////////////////////////Add Button Logic
        ToggleGroup partTypeChoice = new ToggleGroup();
        partTypeRadioButton1.setToggleGroup(partTypeChoice);
        partTypeRadioButton2.setToggleGroup(partTypeChoice);
        partTypeRadioButton1.setOnAction(event -> {
            macIdOrCompanyNameLabel.setText("Machine ID");
            machineIdOrCompanyNamePartInput.setPromptText("Machine Id");
            if (partTypeRadioButton1.isSelected()) {
                macIdOrCompanyNameLabel.setText("Machine ID");
                machineIdOrCompanyNamePartInput.setPromptText("Machine Id");
                System.out.println("InHouse is selected");
                saveAddPartButton.setOnAction(i -> {            try {
                    if (AddPartLabel.getText() == "Add Part") {
                        if(Integer.parseInt(minQuantityPartInput.getText()) < 0 || Integer.parseInt(maxQuantityPartInput.getText()) < Integer.parseInt(minQuantityPartInput.getText()) || Integer.parseInt(minQuantityPartInput.getText()) > Integer.parseInt(maxQuantityPartInput.getText()) || Integer.parseInt(invLevelPartInput.getText()) > Integer.parseInt(maxQuantityPartInput.getText()) || Integer.parseInt(invLevelPartInput.getText()) < Integer.parseInt(minQuantityPartInput.getText()) ){
                            System.out.println("Inventory is greater then max, no cant do!");
                            Alert newAlert2 =  new Alert(Alert.AlertType.WARNING);
                            newAlert2.setTitle("ERROR");
                            newAlert2.setContentText("Please enter Numeric values for\n Inventory between the Min and Max");
                            newAlert2.showAndWait();
                        }
                        else if(Integer.parseInt(invLevelPartInput.getText()) < Integer.parseInt(maxQuantityPartInput.getText())){
                        addPartButtonOnClickInHouse();
                        window.setScene(mainScene);
                        System.out.println("Add Part InHouse");
                        namePartInput.clear();IDPartInput.clear();invLevelPartInput.clear();pricePartInput.clear();maxQuantityPartInput.clear();minQuantityPartInput.clear();machineIdOrCompanyNamePartInput.clear();
                        }


                    } else {
                        if(Integer.parseInt(minQuantityPartInput.getText()) < 0 || Integer.parseInt(maxQuantityPartInput.getText()) < Integer.parseInt(minQuantityPartInput.getText()) || Integer.parseInt(minQuantityPartInput.getText()) > Integer.parseInt(maxQuantityPartInput.getText()) || Integer.parseInt(invLevelPartInput.getText()) > Integer.parseInt(maxQuantityPartInput.getText()) || Integer.parseInt(invLevelPartInput.getText()) < Integer.parseInt(minQuantityPartInput.getText()) ){
                            System.out.println("Inventory is greater then max, no cant do!");
                            Alert newAlert2 =  new Alert(Alert.AlertType.WARNING);
                            newAlert2.setTitle("ERROR");
                            newAlert2.setContentText("Please enter Numeric values for\n Inventory between the Min and Max");
                            newAlert2.showAndWait();
                        }
                        else if(Integer.parseInt(invLevelPartInput.getText()) < Integer.parseInt(maxQuantityPartInput.getText())){
                            modifyPartButtonOnClickInHouse();
                            window.setScene(mainScene);
                            deletePartButtonOnClick();
                            System.out.println("Modify Part InHouse");
                            namePartInput.clear();IDPartInput.clear();invLevelPartInput.clear();pricePartInput.clear();maxQuantityPartInput.clear();minQuantityPartInput.clear();machineIdOrCompanyNamePartInput.clear();
                        }

                    }


                }
                catch (NumberFormatException e){
                    System.out.println("These are not numeric values!");
                    Alert newAlert2 = new Alert(Alert.AlertType.WARNING);
                    newAlert2.setTitle("ERROR");
                    newAlert2.setContentText("Please enter Numeric values for\n Inventory between the Min and Max");
                    newAlert2.showAndWait();
                }
            });///end of Lambda
            }
        });

        partTypeRadioButton2.setOnAction(event -> {
            macIdOrCompanyNameLabel.setText("Company Name");
            machineIdOrCompanyNamePartInput.setPromptText("Enter Company");
            if (partTypeRadioButton2.isSelected()) {
                macIdOrCompanyNameLabel.setText("Company Name");
                machineIdOrCompanyNamePartInput.setPromptText("Enter Company");
                System.out.println("Outsourced is selected");
                saveAddPartButton.setOnAction(o -> {                try {
                    if (AddPartLabel.getText() == "Add Part") {


                        if (Integer.parseInt(minQuantityPartInput.getText()) < 0 || Integer.parseInt(maxQuantityPartInput.getText()) < Integer.parseInt(minQuantityPartInput.getText()) || Integer.parseInt(minQuantityPartInput.getText()) > Integer.parseInt(maxQuantityPartInput.getText()) || Integer.parseInt(invLevelPartInput.getText()) > Integer.parseInt(maxQuantityPartInput.getText()) || Integer.parseInt(invLevelPartInput.getText()) < Integer.parseInt(minQuantityPartInput.getText())) {
                            //if (Integer.parseInt(invLevelPartInput.getText()) > Integer.parseInt(maxQuantityPartInput.getText())){
                            System.out.println("Inventory is greater then max, no cant do!");
                            Alert newAlert = new Alert(Alert.AlertType.WARNING);
                            newAlert.setTitle("ERROR");
                            newAlert.setContentText("Please enter Numeric values for\n Inventory between the Min and Max");
                            newAlert.showAndWait();

                        } else if (Integer.parseInt(invLevelPartInput.getText()) < Integer.parseInt(maxQuantityPartInput.getText())) {
                            addPartButtonOnClickOutsourced();
                            window.setScene(mainScene);
                            System.out.println("Add Part Outsource");
                            namePartInput.clear();
                            IDPartInput.clear();
                            invLevelPartInput.clear();
                            pricePartInput.clear();
                            maxQuantityPartInput.clear();
                            minQuantityPartInput.clear();
                            machineIdOrCompanyNamePartInput.clear();
                        }


                    } else {
                        if (Integer.parseInt(minQuantityPartInput.getText()) < 0 || Integer.parseInt(maxQuantityPartInput.getText()) < Integer.parseInt(minQuantityPartInput.getText()) || Integer.parseInt(minQuantityPartInput.getText()) > Integer.parseInt(maxQuantityPartInput.getText()) || Integer.parseInt(invLevelPartInput.getText()) > Integer.parseInt(maxQuantityPartInput.getText()) || Integer.parseInt(invLevelPartInput.getText()) < Integer.parseInt(minQuantityPartInput.getText())) {
                            System.out.println("Inventory is greater then max, no cant do!");
                            Alert newAlert2 = new Alert(Alert.AlertType.WARNING);
                            newAlert2.setTitle("ERROR");
                            newAlert2.setContentText("Please enter Numeric values for\n Inventory between the Min and Max");
                            newAlert2.showAndWait();
                        } else if (Integer.parseInt(invLevelPartInput.getText()) < Integer.parseInt(maxQuantityPartInput.getText())) {
                            modifyPartButtonOnClickOutsourced();
                            window.setScene(mainScene);
                            deletePartButtonOnClick();
                            System.out.println("Modify Part InHouse");
                            namePartInput.clear();
                            IDPartInput.clear();
                            invLevelPartInput.clear();
                            pricePartInput.clear();
                            maxQuantityPartInput.clear();
                            minQuantityPartInput.clear();
                            machineIdOrCompanyNamePartInput.clear();
                        }

                    }
                }
                catch (NumberFormatException e){
                    System.out.println("These are not numeric values!");
                    Alert newAlert2 = new Alert(Alert.AlertType.WARNING);
                    newAlert2.setTitle("ERROR");
                    newAlert2.setContentText("Please enter Numeric values for\n Inventory between the Min and Max");
                    newAlert2.showAndWait();
                }
                });///end of Lambda
            }
        });


        partTypeRadioButton2.setTextFill(Color.WHITE);
        partTypeRadioButton1.setTextFill(Color.WHITE);

        /**
         *TableViews  are Initialized here
         */
        //Name of the Columns for the ProductsTable - TableView
        TableColumn<Product, Integer> idColumn = new TableColumn<>("Product ID");
        idColumn.setMinWidth(80);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));

        TableColumn<Product, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Product, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setMinWidth(100);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Product, Integer> quantityColumn = new TableColumn<>("Stock");
        quantityColumn.setMinWidth(200);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));

        TableColumn<Product, Integer> maxQuantityColumn = new TableColumn<>("Max Quantity");
        maxQuantityColumn.setMinWidth(200);
        maxQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("max"));

        TableColumn<Product, Integer> minQuantityColumn = new TableColumn<>("Min Quantity");
        minQuantityColumn.setMinWidth(200);
        minQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("min"));

        ProductsTable = new TableView<>();
        ProductsTable.setItems(getProduct());
        ProductsTable.getColumns().addAll(idColumn, nameColumn, priceColumn, quantityColumn);


        //Name of the Columns for the PartsTableMainView - TableView
        TableColumn<Part, Integer> PartIdColumn = new TableColumn<>("Part ID");
        PartIdColumn.setMinWidth(80);
        PartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Part, String> PartNameColumn = new TableColumn<>("Name");
        PartNameColumn.setMinWidth(200);
        PartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Part, Double> PartPriceColumn = new TableColumn<>("Price");
        PartPriceColumn.setMinWidth(100);
        PartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Part, Integer> PartQuantityColumn = new TableColumn<>("Stock");
        PartQuantityColumn.setMinWidth(200);
        PartQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));

        TableColumn<Part, String> PartOutSourcedNameColumn = new TableColumn<>("Company");
        PartOutSourcedNameColumn.setMinWidth(200);
        PartOutSourcedNameColumn.setCellValueFactory(new PropertyValueFactory<>("companyName"));

        TableColumn<Part, Integer> PartMachineIdColumn = new TableColumn<>("Machine ID");
        PartMachineIdColumn.setMinWidth(80);
        PartMachineIdColumn.setCellValueFactory(new PropertyValueFactory<>("machineId"));
        //Parts Table Main view
        PartsTableMainView = new TableView<>();
        PartsTableMainView.setItems(getPart());
        PartsTableMainView.getColumns().addAll(PartIdColumn, PartNameColumn, PartPriceColumn, PartQuantityColumn);


        //Name of the Columns for the PartsTableMainView - TableView
        TableColumn<Part, Integer> ProductViewPartIdColumn = new TableColumn<>("Part ID");
        ProductViewPartIdColumn.setMinWidth(80);
        ProductViewPartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Part, String> ProductViewPartNameColumn = new TableColumn<>("Name");
        ProductViewPartNameColumn.setMinWidth(200);
        ProductViewPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Part, Double> ProductViewPartPriceColumn = new TableColumn<>("Price");
        ProductViewPartPriceColumn.setMinWidth(100);
        ProductViewPartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Part, Integer> ProductViewPartQuantityColumn = new TableColumn<>("Stock");
        ProductViewPartQuantityColumn.setMinWidth(200);
        ProductViewPartQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));

        TableColumn<Part, String> ProductViewPartOutSourcedNameColumn = new TableColumn<>("Company");
        ProductViewPartOutSourcedNameColumn.setMinWidth(200);
        ProductViewPartOutSourcedNameColumn.setCellValueFactory(new PropertyValueFactory<>("companyName"));

        TableColumn<Part, Integer> ProductViewPartMachineIdColumn = new TableColumn<>("Machine ID");
        ProductViewPartMachineIdColumn.setMinWidth(80);
        ProductViewPartMachineIdColumn.setCellValueFactory(new PropertyValueFactory<>("machineId"));

        //Product Table Main view
        PartsTableProductView = new TableView<>();
        PartsTableProductView.setItems(PartsTableMainView.getItems());
        PartsTableProductView.getColumns().addAll(ProductViewPartIdColumn, ProductViewPartNameColumn, ProductViewPartPriceColumn, ProductViewPartQuantityColumn);


        TableColumn<Part, Integer> associatedPartProductViewPartIdColumn = new TableColumn<>("Part ID");
        associatedPartProductViewPartIdColumn.setMinWidth(80);
        associatedPartProductViewPartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Part, String>  associatedPartProductViewPartNameColumn = new TableColumn<>("Name");
        associatedPartProductViewPartNameColumn.setMinWidth(200);
        associatedPartProductViewPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Part, Double> associatedPartProductViewPartPriceColumn = new TableColumn<>("Price");
        associatedPartProductViewPartPriceColumn.setMinWidth(100);
        associatedPartProductViewPartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Part, Integer> associatedPartProductViewPartQuantityColumn = new TableColumn<>("Stock");
        associatedPartProductViewPartQuantityColumn.setMinWidth(200);
        associatedPartProductViewPartQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));

        TableColumn<Part, String> associatedPartProductViewPartOutSourcedNameColumn = new TableColumn<>("Company");
        associatedPartProductViewPartOutSourcedNameColumn.setMinWidth(200);
        associatedPartProductViewPartOutSourcedNameColumn.setCellValueFactory(new PropertyValueFactory<>("companyName"));

        TableColumn<Part, Integer> associatedPartProductViewPartMachineIdColumn = new TableColumn<>("Machine ID");
        associatedPartProductViewPartMachineIdColumn.setMinWidth(80);
        associatedPartProductViewPartMachineIdColumn.setCellValueFactory(new PropertyValueFactory<>("machineId"));

        //Associated Table  view
        PartsTableAssociated = new TableView<>();
        PartsTableAssociated.getColumns().addAll(associatedPartProductViewPartIdColumn, associatedPartProductViewPartNameColumn, associatedPartProductViewPartPriceColumn, associatedPartProductViewPartQuantityColumn);

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        /**
         *Text Input Boxes are Initialized here
         */
        //Name Input
        namePartInput = new TextField();
        namePartInput.setPromptText("Name");
        namePartInput.setMinWidth(100);
        //Price Input
        pricePartInput = new TextField();
        pricePartInput.setPromptText("Price");
        //Max Quantity Input
        maxQuantityPartInput = new TextField();
        maxQuantityPartInput.setPromptText("Max Quantity");
        //Min Quantity Input
        minQuantityPartInput = new TextField();
        minQuantityPartInput.setPromptText("Min Quantity");
        //Search Input
        searchPartInput = new TextField();
        searchPartInput.setPromptText("Search by Part ID or Name");
        searchPartInput.setMinWidth(100);

        //Search Input
        searchProductInput = new TextField();
        searchProductInput.setPromptText("Search by Product ID or Name");
        searchProductInput.setMinWidth(100);
        //Search Input
        searchPartInput2 = new TextField();
        searchPartInput2.setPromptText("Search by Part ID or Name");
        searchPartInput2.setMinWidth(100);
        //Machine  Input
        machineIdOrCompanyNamePartInput = new TextField();
        machineIdOrCompanyNamePartInput.setPromptText("Enter the Machine ID");
        machineIdOrCompanyNamePartInput.setMinWidth(100);
        //ID  Input
        IDPartInput = new TextField();
        IDPartInput.setPromptText("Auto Gen - Disabled");
        IDPartInput.setDisable(true);
        IDPartInput.setMinWidth(100);

        //Inv  Input
        invLevelPartInput = new TextField();
        invLevelPartInput.setPromptText(" Inv");
        invLevelPartInput.setMinWidth(100);
        //Name Input
        nameProductInput = new TextField();
        nameProductInput.setPromptText("Name");
        nameProductInput.setMinWidth(100);
        //Price Input
        priceProductInput = new TextField();
        priceProductInput.setPromptText("Price");
        //Max Quantity Input
        maxQuantityProductInput = new TextField();
        maxQuantityProductInput.setPromptText("Max Quantity");
        //Min Quantity Input
        minQuantityProductInput = new TextField();
        minQuantityProductInput.setPromptText("Min Quantity");
        //Search Input
        searchProductInput = new TextField();
        searchProductInput.setPromptText("Search by Product ID or Name");
        searchProductInput.setMinWidth(100);
        //Search Input
        searchPartInput2 = new TextField();
        searchPartInput2.setPromptText("Search by Product ID or Name");
        searchPartInput2.setMinWidth(100);
        //Machine  Input
        machineProductIDPartInput = new TextField();
        machineProductIDPartInput.setPromptText("Enter the Machine ID");
        machineProductIDPartInput.setMinWidth(100);
        //ID  Input
        IDProductInput = new TextField();
        IDProductInput.setPromptText("Auto Gen - Disabled");
        IDProductInput.setDisable(true);
        IDProductInput.setMinWidth(100);
        //Inv  Input
        invLevelProductInput = new TextField();
        invLevelProductInput.setPromptText(" Inv");
        invLevelProductInput.setMinWidth(100);

        /**
         *Table Filtering code that is running outside more specific methods
         */
///////////////////////////////////////Table Filtering ////////////////////////////////////////
        //Observable list for Parts
        ObservableList Parts =  PartsTableMainView.getItems();
        searchPartInput.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (oldValue != null && (newValue.length() < oldValue.length())) {
                PartsTableMainView.setItems(Parts);
            }
            String value = newValue.toLowerCase();
            ObservableList<Part> tempParts = FXCollections.observableArrayList();

            long count = PartsTableMainView.getColumns().stream().count();
            for (int i = 0; i < PartsTableMainView.getItems().size(); i++) {
                for (int j = 0; j < count; j++) {
                    String entry = "" + PartsTableMainView.getColumns().get(j).getCellData(i);
                    if (entry.toLowerCase().contains(value)) {
                        tempParts.add(PartsTableMainView.getItems().get(i));
                        break;
                    }


                }
            }
            PartsTableMainView.setItems(tempParts);
            if(tempParts == null || tempParts.isEmpty()){
                ConfirmBox.errorChecking("Parts Table", "No records match your query", mainScene);
                searchPartInput.clear();
            }
        });
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //ObservableList<Product> products = FXCollections.observableArrayList();
        ObservableList Products =  ProductsTable.getItems();
        searchProductInput.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (oldValue != null && (newValue.length() < oldValue.length())) {
                ProductsTable.setItems(Products);
            }
            String value = newValue.toLowerCase();
            ObservableList<Product> tempProducts = FXCollections.observableArrayList();

            long count = ProductsTable.getColumns().stream().count();
            for (int i = 0; i < ProductsTable.getItems().size(); i++) {
                for (int j = 0; j < count; j++) {
                    String entry = "" + ProductsTable.getColumns().get(j).getCellData(i);
                    if (entry.toLowerCase().contains(value)) {
                        tempProducts.add(ProductsTable.getItems().get(i));
                        break;
                    }
                }
            }
            ProductsTable.setItems(tempProducts);
            if(tempProducts == null || tempProducts.isEmpty()){
                ConfirmBox.errorChecking("Products Table", "No records match your query", mainScene);
                searchProductInput.clear();
            }
        });

///////////////////////////////////////Table Filtering ////////////////////////////////////////

        //ObservableList Parts
        searchPartInput2.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (oldValue != null && (newValue.length() < oldValue.length())) {
                PartsTableProductView.setItems(Parts);
            }
            String value = newValue.toLowerCase();
            ObservableList<Part> tempParts = FXCollections.observableArrayList();

            long count = PartsTableProductView.getColumns().stream().count();
            for (int i = 0; i < PartsTableProductView.getItems().size(); i++) {
                for (int j = 0; j < count; j++) {
                    String entry = "" + PartsTableProductView.getColumns().get(j).getCellData(i);
                    if (entry.toLowerCase().contains(value)) {
                        tempParts.add(PartsTableProductView.getItems().get(i));
                        break;
                    }
                }
            }
            PartsTableProductView.setItems(tempParts);
            if(tempParts == null || tempParts.isEmpty()){
                ConfirmBox.errorChecking("Parts Table", "No records match your query", mainScene);
                searchPartInput2.clear();
            }
        });

/////////////////////////////////////////////////////////////////////////////////////////////////////
        /**
         *Panels  are Initialized here
         */
        GridPane topPartsControls = new GridPane();
        topPartsControls.setPadding(new Insets(15, 15, 15, 15));
        topPartsControls.add(partsViewLabel, 0, 0);
        topPartsControls.add(PartsTableMainView, 5, 2);
        topPartsControls.setAlignment(Pos.TOP_RIGHT);
        topPartsControls.add(searchPartInput, 6, 1);
        //Buttons for the bottom part of the Parts section
        HBox bottomPartControls = new HBox();
        bottomPartControls.setPadding(new Insets(15, 15, 15, 15));
        bottomPartControls.setSpacing(5);
        bottomPartControls.getChildren().addAll(addPartButton, modifyPartButton, deletePartButton);
        bottomPartControls.setAlignment(Pos.BOTTOM_RIGHT);
        //Buttons for the bottom part of the Product section
        HBox bottomProductControls = new HBox();
        bottomProductControls.setPadding(new Insets(15, 15, 15, 15));
        bottomProductControls.setSpacing(5);
        bottomProductControls.getChildren().addAll(addProductButton, modifyProductButton, deleteProductButton);
        bottomProductControls.setAlignment(Pos.BOTTOM_RIGHT);
        //Buttons for the top part of the Product section
        GridPane topProductControls = new GridPane();
        topProductControls.setPadding(new Insets(15, 15, 15, 15));
        topProductControls.add(productsViewLabel, 0, 0);
        //Buttons for the top part of the Product section
        topProductControls.setAlignment(Pos.TOP_RIGHT);
        topProductControls.add(searchProductInput, 6, 1);
        //Buttons for the other sections of the layout. It gets hard to track I know
        VBox partsLayout = new VBox();
        partsLayout.getChildren().addAll(topPartsControls, PartsTableMainView, bottomPartControls);
        //Buttons for the top  products of the Product section
        HBox topControlsModifyProductLEFT = new HBox();
        topControlsModifyProductLEFT.getChildren().addAll(AddProductLabel);

        VBox productsLayout = new VBox();
        productsLayout.getChildren().addAll(topProductControls, ProductsTable, bottomProductControls);

        HBox topControlsModifyProduct = new HBox();
        topControlsModifyProduct.setAlignment(Pos.TOP_RIGHT);
        topControlsModifyProduct.getChildren().addAll( searchPartInput2);

        VBox productsModifyLayout = new VBox();
        productsModifyLayout.setSpacing(10);
        productsModifyLayout.setAlignment(Pos.TOP_RIGHT);
        productsModifyLayout.getChildren().addAll(topControlsModifyProduct,PartsTableProductView,addAssociatedPart2ProductButton,PartsTableAssociated,removeAssociatedPart2ProductButton);

        //Product Modify/Add Scene
        GridPane ProductModifyLayout = new GridPane();
        ProductModifyLayout.add(topControlsModifyProductLEFT, 3, 0);
        ProductModifyLayout.add(idProductLabel, 0, 3);
        ProductModifyLayout.add(IDProductInput, 1, 3);
        ProductModifyLayout.add(productsModifyLayout, 5, 1);
        ProductModifyLayout.add(nameAddProductLabel, 0, 4);
        ProductModifyLayout.add(nameProductInput, 1, 4);
        ProductModifyLayout.add(invLevelProductLabel, 0, 5);
        ProductModifyLayout.add(invLevelProductInput, 1, 5);
        ProductModifyLayout.add(priceProductAddLabel, 0, 6);
        ProductModifyLayout.add(priceProductInput, 1, 6);
        //ProductModifyLayout.add(PartsTableProductView, 4,5 );
        ProductModifyLayout.add(maxQuantityProductAddLabel, 0, 7);
        ProductModifyLayout.add(maxQuantityProductInput, 1, 7);
        ProductModifyLayout.add(minQuantityProductAddLabel, 2, 7);
        ProductModifyLayout.add(minQuantityProductInput, 3, 7);
        ProductModifyLayout.add(saveProductButton, 2, 8);
        ProductModifyLayout.add(cancelProductButton, 3, 8);
        ProductModifyLayout.setVgap(5);
        ProductModifyLayout.setHgap(5);
        ProductModifyLayout.setAlignment(Pos.CENTER);
        //ProductModifyLayout.setGridLinesVisible(true);
        ProductModifyLayout.setHgap(100);
        ProductModifyLayout.setVgap(30);
        ProductModifyLayout.setStyle("-fx-background-color: #336699;");
        ProductScene = new Scene(ProductModifyLayout, 1750, 800);
        //Main Scene
        GridPane mainDisplay = new GridPane();
        mainDisplay.add(partsLayout, 0, 0);
        mainDisplay.add(productsLayout, 2, 0);
        mainDisplay.add(exitButton, 3, 1);
        mainDisplay.setHgap(15);
        mainDisplay.setStyle("-fx-background-color: #336699;");
        mainDisplay.setPadding(new Insets(20, 20, 20, 20));
        mainScene = new Scene(mainDisplay);
        //Part Add Scene

        //Part Modify
        GridPane modifyPartLayout = new GridPane();
        modifyPartLayout.add(AddPartLabel, 0, 0);
        modifyPartLayout.add(partTypeRadioButton1, 0, 1);
        modifyPartLayout.add(partTypeRadioButton2, 1, 1);
        modifyPartLayout.add(idLabel, 0, 3);
        modifyPartLayout.add(IDPartInput, 1, 3);
        modifyPartLayout.add(nameAddLabel, 0, 4);
        modifyPartLayout.add(namePartInput, 1, 4);
        modifyPartLayout.add(invLevelLabel, 0, 5);
        modifyPartLayout.add(invLevelPartInput, 1, 5);
        modifyPartLayout.add(priceAddLabel, 0, 6);
        modifyPartLayout.add(pricePartInput, 1, 6);
        modifyPartLayout.add(maxQuantityAddLabel, 0, 7);
        modifyPartLayout.add(maxQuantityPartInput, 1, 7);
        modifyPartLayout.add(minQuantityAddLabel, 3, 7);
        modifyPartLayout.add(minQuantityPartInput, 4, 7);
        modifyPartLayout.add(macIdOrCompanyNameLabel, 0, 8);
        modifyPartLayout.add(machineIdOrCompanyNamePartInput, 1, 8);
        modifyPartLayout.add(saveAddPartButton, 0, 10);
        modifyPartLayout.add(cancelPartButton, 1, 10);
        modifyPartLayout.setVgap(25);
        modifyPartLayout.setHgap(5);
        modifyPartLayout.setAlignment(Pos.CENTER);
        modifyPartLayout.setStyle("-fx-background-color: #336699;");
        modifyPartScene = new Scene(modifyPartLayout, 700, 700);
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        window.setScene(mainScene);
        window.show();
    }
    /**
     * This is where the new product is created after clicking the Add button
     * @param
     */
    ///// Add Button
    public void addProductButtonOnClick() {

        ObservableList<Product> allProductsToModify;
        allProductsToModify = ProductsTable.getItems();
        int productIdIncrement = ProductsTable.getItems().toArray().length + 1;

        Product newManuallyAddedProduct = new Product();
        newManuallyAddedProduct.setID(productIdIncrement);
        newManuallyAddedProduct.setName(nameProductInput.getText());
        newManuallyAddedProduct.setPrice(Double.parseDouble(priceProductInput.getText()));
        newManuallyAddedProduct.setStock(Integer.parseInt(invLevelProductInput.getText()));
        newManuallyAddedProduct.setMin(Integer.parseInt(minQuantityProductInput.getText()));
        newManuallyAddedProduct.setMax(Integer.parseInt(maxQuantityProductInput.getText()));
        ProductsTable.getItems().add(newManuallyAddedProduct);
        IDProductInput.clear();
        nameProductInput.clear();
        priceProductInput.clear();
        maxQuantityProductInput.clear();
        System.out.println("I made it this far - Product Addition");

        for (int y=0;y<partsAssociatedToProductsList.size();y++){
            newManuallyAddedProduct.addAssociatedPart(partsAssociatedToProductsList.get(y));
            System.out.println(partsAssociatedToProductsList.get(y).getName());

            System.out.println( newManuallyAddedProduct.getName() + "   " + newManuallyAddedProduct.getAllAssociatedParts().get(y).getName());

        }
        PartsTableAssociated.setItems(partsAssociatedToProductsList);
      //PartsTableAssociated.setItems(newManuallyAddedProduct.getAllAssociatedParts());


    }



    /**
     * This is where a Product is modified after clicking the modify button
     * @param
     */
    public void modifyProductButtonOnClick() {

        int ProductIdSelectedToModify;
        String ProductNameSelectedToModify;
        double ProductPriceSelectedToModify;
        int ProductStockSelectedToModify;
        int ProductMinSelectedToModify;
        int ProductMaxSelectedToModify;
        String ProductCompanySelectedToModify;
        ObservableList<Part> setValuesToModifyList;
        Product test;

        //ObservableList<Product> allProductsToModify;
        //allProductsToModify = ProductsTable.getItems();
        ProductIdSelectedToModify = ProductsTable.getSelectionModel().getSelectedItem().getID();
        ProductNameSelectedToModify = ProductsTable.getSelectionModel().getSelectedItem().getName();
        ProductPriceSelectedToModify = ProductsTable.getSelectionModel().getSelectedItem().getPrice();
        ProductStockSelectedToModify = ProductsTable.getSelectionModel().getSelectedItem().getStock();
        ProductMinSelectedToModify = ProductsTable.getSelectionModel().getSelectedItem().getMin();
        ProductMaxSelectedToModify = ProductsTable.getSelectionModel().getSelectedItem().getMax();
        setValuesToModifyList = ProductsTable.getSelectionModel().getSelectedItem().getAllAssociatedParts();

        System.out.println("I hope I can print this out " + ProductIdSelectedToModify + " " + ProductNameSelectedToModify + " " + " " + ProductPriceSelectedToModify + " " + " " + ProductStockSelectedToModify + " " + ProductMinSelectedToModify + " " + ProductMaxSelectedToModify + " " + ProductsTable.getSelectionModel().getSelectedItems().toString() );

        IDProductInput.setText(String.valueOf(ProductIdSelectedToModify));
        nameProductInput.setText(ProductNameSelectedToModify);
        invLevelProductInput.setText(String.valueOf(ProductStockSelectedToModify));
        priceProductInput.setText(String.valueOf(ProductPriceSelectedToModify));
        minQuantityProductInput.setText(String.valueOf(ProductMinSelectedToModify));
        maxQuantityProductInput.setText(String.valueOf(ProductMaxSelectedToModify));
        PartsTableAssociated.setItems(setValuesToModifyList);
        //partsAssociatedToProductsList

        //PartsTableAssociated.setItems(newManuallyAddedProduct.getAllAssociatedParts());

    }

    /**
     * This is where a Product is modified after clicking the modify button
     * @param
     */
    public void addModifiedProductButtonOnClick() {

        Product newManuallyAddedProduct = new Product(ProductsTable.getSelectionModel().getSelectedItem().getID(),nameProductInput.getText(),Double.parseDouble(priceProductInput.getText()),Integer.parseInt(invLevelProductInput.getText()),Integer.parseInt(minQuantityProductInput.getText()),Integer.parseInt(maxQuantityProductInput.getText()));
        newManuallyAddedProduct.setID(ProductsTable.getSelectionModel().getSelectedItem().getID());
        newManuallyAddedProduct.setName(nameProductInput.getText());
        newManuallyAddedProduct.setPrice(Double.parseDouble(priceProductInput.getText()));
        newManuallyAddedProduct.setStock(Integer.parseInt(invLevelProductInput.getText()));
        newManuallyAddedProduct.setStock(Integer.parseInt(invLevelProductInput.getText()));
        newManuallyAddedProduct.setMin(Integer.parseInt(minQuantityProductInput.getText()));
        newManuallyAddedProduct.setMax(Integer.parseInt(maxQuantityProductInput.getText()));
        for(int r=0; r<partsAssociatedToProductsList.size();r++) {
            newManuallyAddedProduct.addAssociatedPart(partsAssociatedToProductsList.get(r));
        }

        ProductsTable.getItems().add(newManuallyAddedProduct);
        System.out.println("I made it this far - Product Modification");

        PartsTableAssociated.setItems(newManuallyAddedProduct.getAllAssociatedParts());

    }

    /**
     * This is where a Product is deleted after clicking the delete button
     * @param
     */
    public void deleteProductButtonOnClick() {
        ObservableList<Product> productSelected, allProducts;
        allProducts = ProductsTable.getItems();
        productSelected = ProductsTable.getSelectionModel().getSelectedItems();
        if (ProductsTable.getSelectionModel().getSelectedItem().getAllAssociatedParts().size()  == 0) {
            productSelected.forEach(allProducts::remove);
        } else if (allProducts.size() != 0) {
            System.out.println();
            Alert cannotDeleteProductWithParts = new Alert(Alert.AlertType.WARNING);
            cannotDeleteProductWithParts.setTitle("ERROR");
            cannotDeleteProductWithParts.setContentText("Warning, we cannot DELETE a Product with Parts Associated\n Please try again");
            cannotDeleteProductWithParts.showAndWait();
        }
    }

    /**
     * This is where the new Part from the InHouse subClass is created after clicking the Add button
     * @param
     */

    public void deleteModifiedProductButtonOnClick() {
        ObservableList<Product> productSelected, allProducts;
        allProducts = ProductsTable.getItems();
        productSelected = ProductsTable.getSelectionModel().getSelectedItems();
        productSelected.forEach(allProducts::remove);
        }

    /**
     * This is where the new Part from the InHouse subClass is created after clicking the Add button
     * @param
     */
    ///// Add Button
    public void addPartButtonOnClickInHouse() {
        ObservableList<Part> allPartsToModify;
        allPartsToModify = PartsTableMainView.getItems();
        int partIdIncrement = PartsTableMainView.getItems().toArray().length + 1;

                InHouse InHousePart = new InHouse(partIdIncrement, namePartInput.getText(), Double.parseDouble(pricePartInput.getText()), Integer.parseInt(invLevelPartInput.getText()), Integer.parseInt(minQuantityPartInput.getText()), Integer.parseInt(maxQuantityPartInput.getText()), Integer.parseInt(machineIdOrCompanyNamePartInput.getText()));
                InHousePart.setId(partIdIncrement);
                InHousePart.setName(namePartInput.getText());
                InHousePart.setPrice(Double.parseDouble(pricePartInput.getText()));
                InHousePart.setStock(Integer.parseInt(invLevelPartInput.getText()));
                InHousePart.setMax(Integer.parseInt(maxQuantityPartInput.getText()));
                InHousePart.setMin(Integer.parseInt(minQuantityPartInput.getText()));
                InHousePart.setMachineId(Integer.parseInt(machineIdOrCompanyNamePartInput.getText()));
                PartsTableMainView.getItems().add(InHousePart);

        IDPartInput.clear();
        namePartInput.clear();
        pricePartInput.clear();
        maxQuantityPartInput.clear();


    }
    /**
     * This is where the new Part from the Outsourced subClass is created after clicking the Add button
     * @param
     */
    ///// Add Button
    public void addPartButtonOnClickOutsourced() {
        ObservableList<Part> allPartsToModify;
        allPartsToModify = PartsTableMainView.getItems();
        int partIdIncrement = PartsTableMainView.getItems().toArray().length + 1;

        Outsourced OutsourcedPart = new Outsourced(partIdIncrement, namePartInput.getText(), Double.parseDouble(pricePartInput.getText()), Integer.parseInt(invLevelPartInput.getText()), Integer.parseInt(maxQuantityPartInput.getText()), Integer.parseInt(minQuantityPartInput.getText()), machineIdOrCompanyNamePartInput.getText());
        OutsourcedPart.setId(partIdIncrement);
        OutsourcedPart.setName(namePartInput.getText());
        OutsourcedPart.setPrice(Double.parseDouble(pricePartInput.getText()));

        //NumberFormatException e
        OutsourcedPart.setStock(Integer.parseInt(invLevelPartInput.getText()));
        OutsourcedPart.setMax(Integer.parseInt(maxQuantityPartInput.getText()));
        OutsourcedPart.setMin(Integer.parseInt(minQuantityPartInput.getText()));

        OutsourcedPart.setCompanyName(machineIdOrCompanyNamePartInput.getText());
        PartsTableMainView.getItems().add(OutsourcedPart);
        IDPartInput.clear();
        namePartInput.clear();
        pricePartInput.clear();
        maxQuantityPartInput.clear();

        System.out.println("This should be the length of the list : " + partIdIncrement);

    }
    /////////////////////////////////////

    /**
     * This is where a Part from the InHouse subClass is modified after clicking the modify button
     * @param
     */
    public void modifyPartButtonOnClickInHouse() {

        ObservableList<Part> allPartsToModify;
        allPartsToModify = PartsTableMainView.getItems();
        int partIdSelectedToModify = PartsTableMainView.getSelectionModel().getSelectedItem().getId();

        InHouse InHousePart = new InHouse(partIdSelectedToModify, namePartInput.getText(), Double.parseDouble(pricePartInput.getText()), Integer.parseInt(invLevelPartInput.getText()), Integer.parseInt(maxQuantityPartInput.getText()), Integer.parseInt(minQuantityPartInput.getText()), Integer.parseInt(machineIdOrCompanyNamePartInput.getText()));
        InHousePart.setId(partIdSelectedToModify);
        InHousePart.setName(namePartInput.getText());
        InHousePart.setPrice(Double.parseDouble(pricePartInput.getText()));
        InHousePart.setStock(Integer.parseInt(invLevelPartInput.getText()));
        InHousePart.setMax(Integer.parseInt(maxQuantityPartInput.getText()));
        InHousePart.setMin(Integer.parseInt(minQuantityPartInput.getText()));
        InHousePart.setMachineId(Integer.parseInt(machineIdOrCompanyNamePartInput.getText()));
        PartsTableMainView.getItems().add(InHousePart);
        IDPartInput.clear();
        namePartInput.clear();
        pricePartInput.clear();
        maxQuantityPartInput.clear();

    }

    /**
     * This is where a Part from the Outsourced subClass is modified after clicking the modify button
     * @param
     */
    public void modifyPartButtonOnClickOutsourced() {

        ObservableList<Part> allPartsToModify;
        allPartsToModify = PartsTableMainView.getItems();
        int partIdSelectedToModify = PartsTableMainView.getSelectionModel().getSelectedItem().getId();

        Outsourced OutsourcedPart = new Outsourced(partIdSelectedToModify, namePartInput.getText(), Double.parseDouble(pricePartInput.getText()), Integer.parseInt(invLevelPartInput.getText()), Integer.parseInt(maxQuantityPartInput.getText()), Integer.parseInt(minQuantityPartInput.getText()), machineIdOrCompanyNamePartInput.getText());
        OutsourcedPart.setId(OutsourcedPart.getId());
        OutsourcedPart.setName(namePartInput.getText());
        OutsourcedPart.setPrice(Double.parseDouble(pricePartInput.getText()));
        OutsourcedPart.setStock(Integer.parseInt(invLevelPartInput.getText()));
        OutsourcedPart.setMax(Integer.parseInt(maxQuantityPartInput.getText()));
        OutsourcedPart.setMin(Integer.parseInt(minQuantityPartInput.getText()));
        OutsourcedPart.setCompanyName(machineIdOrCompanyNamePartInput.getText());
        PartsTableMainView.getItems().add(OutsourcedPart);
        IDPartInput.clear();
        namePartInput.clear();
        pricePartInput.clear();
        maxQuantityPartInput.clear();

    }
    ///////////////////////////////////////////////////////////////////
    /**
     * This is where a Part is deleted after clicking the delete button
     * @param
     */
    public void deletePartButtonOnClick() {
        ObservableList<Part> partSelected, allParts;
        allParts = PartsTableMainView.getItems();
        partSelected = PartsTableMainView.getSelectionModel().getSelectedItems();
        partSelected.forEach(allParts::remove);
    }
    /**
     * This is where a Part is removed from the associated list  after clicking the remove button
     * @param
     */
    public void removePartFromProductButtonOnClick() {
        ObservableList<Part> partSelectedAssociated;
        partsAssociatedToProductsList =  PartsTableAssociated.getItems();
        partSelectedAssociated =  PartsTableAssociated.getSelectionModel().getSelectedItems();
        partSelectedAssociated.forEach(partsAssociatedToProductsList::remove);
    }
    /**
     * This is where a Parts are added to a list related to a particular product
     * @param
     */

    /**
     * @return the partsAssociatedToProductsList
     */
        public ObservableList<Part>     addPartToProduct() {
        Part addPartToProduct = PartsTableProductView.getSelectionModel().getSelectedItem();
        partsAssociatedToProductsList.addAll(addPartToProduct);
        PartsTableAssociated.setItems(partsAssociatedToProductsList);
        System.out.println(addPartToProduct.getName());
        System.out.println(partsAssociatedToProductsList);
        return partsAssociatedToProductsList;
    }

    /**
     * This is where a Parts is modified
     * @param
     */
    public void modifyPartButtonOnClick() {

        int partIdSelectedToModify;
        String partNameSelectedToModify;
        double partPriceSelectedToModify;
        int partStockSelectedToModify;
        int partMinSelectedToModify;
        int partMaxSelectedToModify;
        String partCompanySelectedToModify;
        Part test;

        ObservableList<Part> allPartsToModify;
        allPartsToModify = PartsTableMainView.getItems();
        partIdSelectedToModify = PartsTableMainView.getSelectionModel().getSelectedItem().getId();
        partNameSelectedToModify = PartsTableMainView.getSelectionModel().getSelectedItem().getName();
        partPriceSelectedToModify = PartsTableMainView.getSelectionModel().getSelectedItem().getPrice();
        partStockSelectedToModify = PartsTableMainView.getSelectionModel().getSelectedItem().getStock();
        partMinSelectedToModify = PartsTableMainView.getSelectionModel().getSelectedItem().getMin();
        partMaxSelectedToModify = PartsTableMainView.getSelectionModel().getSelectedItem().getMax();

        test = PartsTableMainView.getSelectionModel().getSelectedItem();
        if (test instanceof InHouse) {
            System.out.println("I am from the Ihhouse class");
            machineIdOrCompanyNamePartInput.setText(String.valueOf(((InHouse) PartsTableMainView.getSelectionModel().getSelectedItem()).getMachineId()));
            System.out.println(((InHouse) PartsTableMainView.getSelectionModel().getSelectedItem()).getMachineId());
            partTypeRadioButton1.setSelected(true);
        } else if (test instanceof Outsourced) {
            System.out.println("I am from the Outsourced class");
            machineIdOrCompanyNamePartInput.setText(((Outsourced) PartsTableMainView.getSelectionModel().getSelectedItem()).getCompanyName());
            System.out.println(((Outsourced) PartsTableMainView.getSelectionModel().getSelectedItem()).getCompanyName());
            partTypeRadioButton2.setSelected(true);
        }
        System.out.println("I hope I can print this out " + partIdSelectedToModify + " " + partNameSelectedToModify + " " + " " + partPriceSelectedToModify + " " + " " + partStockSelectedToModify + " " + partMinSelectedToModify + " " + partMaxSelectedToModify + " " + PartsTableMainView.getSelectionModel().getSelectedItems().toString() + " " + machineIdOrCompanyNamePartInput);

        IDPartInput.setText(String.valueOf(partIdSelectedToModify));
        namePartInput.setText(partNameSelectedToModify);
        invLevelPartInput.setText(String.valueOf(partStockSelectedToModify));
        pricePartInput.setText(String.valueOf(partPriceSelectedToModify));
        maxQuantityPartInput.setText(String.valueOf(partMaxSelectedToModify));
        minQuantityPartInput.setText(String.valueOf(partMinSelectedToModify));

        }


    /**
     * This is where product objects are added to an observable list
     * @param
     * @return the products
     */

    public ObservableList<Product>  getProduct()
    {
        ObservableList<Product> products = FXCollections.observableArrayList();
        products.add(new Product(1,"Desktop",1.00,50,1,33));
        products.add(new Product(2,"Laptop",.50,35,434,56565));
        products.add(new Product(3,"Android Phone",11000,2,23,76765));
        products.add(new Product(4,"Wireless Mouse",3.5,100,23,565));
        products.add(new Product(5,"F150-Truck",11.5,58,87,454));
        products.add(new Product(6,"ParaMotor",91.00,50,3456,7643));
        products.add(new Product(7,"Bicycle",5.50,35,455,763));
        products.add(new Product(8,"Old Projector",31000,2,678,23));
        products.add(new Product(9,"Coffee Maker",300.5,100,238,98));
        products.add(new Product(10,"Wireless Router",10000.5,58,346,342));
        return products;
    }
    /**
     * This is where Parts objects are added to an observable list
     * @param
     * @return the Parts
     */
    public ObservableList<Part>  getPart()
    {
        ObservableList<Part> Parts = FXCollections.observableArrayList();
        Parts.add(new Outsourced(1,"Intel Processor",1.00,50,1,1,"InterFuze"));
        Parts.add(new Outsourced(2,"8 GIG DIM",.50,35,1,1,"AT&T"));
        Parts.add(new Outsourced(3,"VGA Video Card",11000,2,1,1,"Lockheed"));
        Parts.add(new Outsourced(4,"Sound Card",3.5,100,1,1,"DataDoctors"));
        Parts.add(new InHouse(5,"Tires",21.5,58,1,1,34));
        Parts.add(new InHouse(6,"Windshield",43.00,50,1,1,43));
        Parts.add(new InHouse(7,"Car Seat",9.50,35,1,1,78));
        Parts.add(new InHouse(8,"Transmission",4140,2,1,1,90));
        Parts.add(new InHouse(9,"V8 Engine",333.5,100,1,1,21));
        Parts.add(new InHouse(10,"Wireless Adaptor",985.26,58,1,1,45));
        return Parts;
    }


}
