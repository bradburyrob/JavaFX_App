package sample;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Supplied class AlertBox.java
 */

/**
 *
 * @author Robert Bradbury
 */
public class AlertBox
{
public static void display(String title, String message)
    {
    Stage window1 = new Stage();
    window1.initModality(Modality.APPLICATION_MODAL);
    window1.setTitle(title);
    window1.setMinWidth(250);

    Label label1 = new Label();
    label1.setText(message);
    Button closeButton = new Button("OK");
    closeButton.setOnAction(event -> window1.close());

    VBox myLayout = new VBox(10);
    myLayout.getChildren().addAll(label1, closeButton);
    myLayout.setAlignment(Pos.CENTER);
    Scene scene = new Scene(myLayout,600,300);
    window1.setScene(scene);
    window1.showAndWait();

    }



}
