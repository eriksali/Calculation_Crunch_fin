package csi2300;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class App extends Application {

    @Override 

    public void start(Stage primaryStage) {

        Pane Pane = new Pane();
        Label question = new Label("");
        Label answer = new Label();
        TextField result = new TextField();
        Loader loader = new Loader(result, answer, question);
   
        Scene scene = new Scene(Pane, 600, 600);

        primaryStage.setTitle("Space game!");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        loader.loadText(Pane);
        loader.submitBtn(Pane);
        loader.renewBtn(Pane);
        loader.exitBtn(Pane, primaryStage);
  
    }

    public static void main(String[] args){
        launch(args);
    }

}


