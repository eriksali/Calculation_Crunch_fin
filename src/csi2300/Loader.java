package csi2300;

import java.util.Random;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.stage.Window;


class Loader {

    private Label question;
    private Label answer;
    //private Label flag;
    private Text flag;
    private TextField result;

    int sum1;
    int sum2 = -1;

    Random rand;
    int number1;
    int number2;

    int counter = 1;

    Loader(TextField result, Label answer, Label question, Text flag) {
        this.question = question;
        this.answer = answer;
        this.result = result;
        this.flag = flag;
    }


    public int loadQuestion(int number1, int number2) {
        question.setText(number1 + " x " + number2 + " = ?");
        return number1 * number2;
    }


    public int loadAnswer(TextField result, Label answer) {
        int res = Integer.parseInt(result.getText());
        answer.setText(result.getText());
        return res;     
    }


    public void loadText(Pane gp) {

        Text questiontxt = new Text("Question: ");
        Text answertxt = new Text("Answer: ");
        

        gp.getChildren().add(questiontxt);
        gp.getChildren().add(answertxt);
        gp.getChildren().add(question);
        gp.getChildren().add(result);
        gp.getChildren().add(answer);
        gp.getChildren().add(flag);

        questiontxt.setLayoutX(50);
        questiontxt.setLayoutY(200);
        question.setLayoutX(110);
        question.setLayoutY(200);

        answertxt.setLayoutX(50);
        answertxt.setLayoutY(230);
        answer.setLayoutX(110);
        answer.setLayoutY(230);
        flag.setLayoutX(260);
        flag.setLayoutY(245);

        result.setLayoutX(50);
        result.setLayoutY(255);

        rand = new Random(); 
        number1 = rand.nextInt(10); 
        number2 = rand.nextInt(10);

        question.setText(number1 + " x " + number2);

        // AnswerPrompt prompt = new AnswerPrompt(
        //     gp.getScene().getWindow()
        // );

        // String res = prompt.getResult();

        // result.setText(res);

    }



    public void submitBtn(Pane gp) {

        Button submit = new Button("Summit");
        TranslateTransition transition = new TranslateTransition();
        Circle cir = new Circle();

        gp.getChildren().add(submit);
        gp.getChildren().add(cir);

        submit.setLayoutX(260);
        submit.setLayoutY(270);

        cir.setFill(Color.DARKSALMON);
        cir.setRadius(30);
        cir.setLayoutX(500);
        cir.setLayoutY(550);
        
        submit.setDefaultButton(true);
        submit.setOnAction(e -> {

            if (counter < 3) { 

                try {
                    sum1 = loadQuestion(number1, number2);
                    if (result.getText().isEmpty()) {
                        Alert alert = new Alert(AlertType.ERROR, "Please enter a number");
                        alert.showAndWait();
                        sum2 = -1;
                        answer.setText("");
                    } else {
                        sum2 = loadAnswer(result, answer);
                    }
                    
                } catch(NumberFormatException ex){ 
                    ;
                }

                
                if (!(result.getText().isEmpty())) {

                    if (sum1 == sum2) {
                                
                        transition.setAutoReverse(false);
                        transition.setNode(cir);
                        
                        cir.setLayoutX(500);
                        cir.setLayoutY(550 - 50 * counter);

                        transition.play();

                        counter++;
                        
                        answer.setText(number1 + " x " + number2 + " = " + result.getText());
                        flag.setText("Correct");
                        //flag.setStyle("-fx-color: green");
                        //flag = new Text("Correct!");
                        flag.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16));
                        flag.setFill(Color.GREEN);
                        result.clear();
                    }
                    else {
                        counter++;
                        answer.setText(number1 + " x " + number2 + " = " + result.getText());
                        flag.setText("Incorrect");
                        //flag.setStyle("-fx-color: red");
                        //flag = new Text("Incorrect!");
                        flag.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16));
                        flag.setFill(Color.RED);
                        result.clear();
                    } 
                    //result.requestFocus();
                    //continuebtn.setDefaultButton(true);
                    //Platform.runLater(() -> result.requestFocus());
                }
                
            } else {
                    transition.setAutoReverse(false);
                    transition.setNode(cir);
                    transition.play();
                    cir.setLayoutY(50);
                    // Alert alert = new Alert(AlertType.INFORMATION, "EXCELLENT!");
                    // alert.showAndWait();
                    display();
                    cir.setLayoutY(550);
                    counter = 1;
                    result.clear();
                    question.setText("");
                    answer.setText("");
                    flag.setText("");
            }

            //Platform.runLater(() -> result.requestFocus());
        });
    }

    public void renewBtn(Pane gp) {

        Button continuebtn = new Button("Continue");
        
        continuebtn.setOnAction(e -> {
            number1 = rand.nextInt(10); 
            number2 = rand.nextInt(10);

            question.setText(number1 + " x " + number2);
            result.clear();
            answer.setText("");
            sum1 = 0;
            sum2 = 0;
            Platform.runLater(() -> result.requestFocus());
        });

        gp.getChildren().add(continuebtn);
        continuebtn.setLayoutX(260);
        continuebtn.setLayoutY(300);
        //result.requestFocus();
        //Platform.runLater(() -> result.requestFocus());
        
    }


    public void exitBtn(Pane gp, Stage stage) {

        Button btnExit = new Button("Exit");
        btnExit.setOnAction(e -> {
            stage.close();
        });

        gp.getChildren().add(btnExit);
        btnExit.setLayoutX(260);
        btnExit.setLayoutY(330);
    }

    public static void display()
    {
        Stage popupwindow=new Stage();
            
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Finish Line");      
            
        Label label= new Label(""); 
        //label.setStyle("-fx-color: blue");
        label.setFont(new Font("Arial", 30)); 
        label.setTextFill(Color.color(0, 0, 1));    
        label.setText("Excellent!");   
            
        Button button= new Button("OK");   
        button.setOnAction(e -> popupwindow.close());
        VBox layout= new VBox(10);
        layout.getChildren().addAll(label, button);
        layout.setAlignment(Pos.CENTER);
        Scene scene1= new Scene(layout, 250, 160);
            
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();
        
    }
    
}
