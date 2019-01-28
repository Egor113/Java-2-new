import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Main extends Application {
    public Button btnSent;
    public TextField textSender;
    public TextArea textReceiver;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("main.fxml"));

        primaryStage.setTitle("Чат");
        primaryStage.setScene(new Scene(parent));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void onClicked(ActionEvent actionEvent) {
        textReceiver.appendText(textSender.getCharacters().toString());
        textReceiver.appendText("\n");
        textSender.clear();
    }


    public void onPressedEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER)
        {
            textReceiver.appendText(textSender.getCharacters().toString());
            textReceiver.appendText("\n");
            textSender.clear();
        }
    }
}
