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
import sample.Part;
/**
 * Supplied class ConfirmBox.java
 */

/**
 *
 * @author Robert Bradbury
 */


public class ConfirmBox
{
    static boolean answer;
    public static boolean display(String title, String message)
    {

        Stage window1 = new Stage();
        window1.initModality(Modality.APPLICATION_MODAL);
        window1.setTitle(title);
        window1.setMinWidth(250);
        Label label1 = new Label();
        label1.setText(message);

        //Creating 2 buttons
        Button yesButton = new Button("yes");
        Button noButton  = new Button("no");

        yesButton.setOnAction(event -> { answer=true; window1.close(); });
        noButton.setOnAction(event -> { answer=false; window1.close(); });


        VBox myLayout = new VBox(10);
        myLayout.getChildren().addAll(label1,yesButton,noButton);
        myLayout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(myLayout);
        window1.setScene(scene);
        window1.showAndWait();
        return answer;

        //Part newPart;
        //newPart = new InhousePart();
    }




    public static boolean errorChecking(String title, String message, Scene newSceen)
    {

        Stage window1 = new Stage();
        window1.initModality(Modality.APPLICATION_MODAL);
        window1.setTitle(title);
        window1.setMinWidth(250);
        Label label1 = new Label();
        label1.setText(message);

        //Creating 2 buttons
        Button yesButton = new Button("OK");
        //Button noButton  = new Button("no");

        yesButton.setOnAction(event -> { answer=true; window1.close(); });
        //noButton.setOnAction(event -> { answer=false; window1.close(); });


        VBox myLayout = new VBox(10);
        myLayout.getChildren().addAll(label1,yesButton);
        myLayout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(myLayout);
        newSceen = scene;
        window1.setScene(scene);
        window1.showAndWait();
        return answer;

    }

}
