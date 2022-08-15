import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application{

    //stage in use
    public static Stage currentStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Controller.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("sliderStyle.css");
        currentStage = primaryStage;
        currentStage.initStyle(StageStyle.UNDECORATED); 
        currentStage.setScene(scene);
        currentStage.show();

    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
