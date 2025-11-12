import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SmartHomeAutomation extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Smart Home Automation System");

        // ====== MAIN GRID SETUP ======
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(15);
        grid.setHgap(25);

        // ====== HEADER ======
        Label header = new Label("🏠 Smart Home Dashboard");
        header.setFont(Font.font("Arial", 22));
        header.setTextFill(Color.DARKBLUE);
