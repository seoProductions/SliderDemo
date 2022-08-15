import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.CubicCurve;
import javafx.stage.Stage;

public class Controller implements Initializable {

    @FXML private Slider mySlider;
    @FXML private Button mybutton;  
    @FXML private AnchorPane topAnchorPane;
    @FXML private Label myLabel;
    @FXML private CubicCurve myCurve;

    //coords of currentStage
    double x = 0, y = 0;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        topAnchorPane.setId("topbar");
        //sets coords
        topAnchorPane.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        }); 
        
        //dragging
        topAnchorPane.setOnMouseDragged(mouseEvent -> {
            App.currentStage.setX(mouseEvent.getScreenX() - x);
            App.currentStage.setY(mouseEvent.getScreenY() - y);

        });

        mySlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                myLabel.setText("Slider value: " + (int) mySlider.getValue());
                updateCurve();
            }

        });
    }
    @FXML void buttonAction(ActionEvent event) {
        //Type cast event source as button. Get scene then window into stage
        Stage stage = (Stage)((Button) event.getSource()).getScene().getWindow(); 
        stage.close();
    }
    private void updateCurve()
    {
        //length across curve <--->
        double distance = myCurve.getControlX1() - myCurve.getStartX();

        //intervals of 90 taken from distance
        double interval = distance/90;
        //get slider value
        double value = mySlider.getValue() % 90;

        myCurve.setEndX(myCurve.getControlX2() - (interval * value)); //grow into x^2
        //set to front if slider all the way
        if (mySlider.getValue() == 180)
            myCurve.setEndX(myCurve.getStartX());
        
    }

}
