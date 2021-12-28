import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        root.setStyle("-fx-background-image: url('https://i.pinimg.com/originals/42/cb/01/42cb01cdb985dbfd59e84ffe8ae46047.jpg'); -fx-background-repeat: no-repeat;  -fx-background-size: 150 150; ");
        primaryStage.setTitle("Weather Forecasting App");
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.show();

        


    }

    public static void main(String[] args) {
        launch(args);
    }
}
