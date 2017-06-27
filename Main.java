import javafx.application.Application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {
	private Button btnStart1 = new Button("Start");
	private Button btnStop1 = new Button("Stop");
	private Button btnReset1 = new Button("Reset");

	private Button btnStart2 = new Button("Start");
	private Button btnStop2 = new Button("Stop");
	private Button btnReset2 = new Button("Reset");

	private Button btnStart3 = new Button("Start");
	private Button btnStop3 = new Button("Stop");
	private Button btnReset3 = new Button("Reset");

	private Button btnStart4 = new Button("Start");
	private Button btnStop4 = new Button("Stop");
	private Button btnReset4 = new Button("Reset");

	public void start(Stage primaryStage) throws Exception {
		try {
			// Object for each stopwatch
			ClockPane clockPane = new ClockPane(250);
			ClockPane clockPane1 = new ClockPane(250);
			ClockPane clockPane2 = new ClockPane(250);
			ClockPane clockPane3 = new ClockPane(250);

			HBox hBox = new HBox();
			VBox vBox1 = new VBox();
			VBox vBox2 = new VBox();
			VBox vBox3 = new VBox();
			VBox vBox4 = new VBox();
			HBox hBox1 = new HBox();
			hBox1.getChildren().addAll(btnStart1, btnStop1, btnReset1);
			hBox1.setPadding(new Insets(0, 60, 0, 60));
			hBox1.setSpacing(5);

			HBox hBox2 = new HBox();
			hBox2.getChildren().addAll(btnStart2, btnStop2, btnReset2);
			hBox2.setPadding(new Insets(0, 60, 0, 60));
			hBox2.setSpacing(5);

			HBox hBox3 = new HBox();
			hBox3.getChildren().addAll(btnStart3, btnStop3, btnReset3);
			hBox3.setPadding(new Insets(0, 60, 0, 60));
			hBox3.setSpacing(5);

			HBox hBox4 = new HBox();
			hBox4.getChildren().addAll(btnStart4, btnStop4, btnReset4);
			hBox4.setPadding(new Insets(0, 60, 0, 60));
			hBox4.setSpacing(5);

			vBox1.getChildren().addAll(clockPane, hBox1);
			vBox2.getChildren().addAll(clockPane1, hBox2);
			vBox3.getChildren().addAll(clockPane2, hBox3);
			vBox4.getChildren().addAll(clockPane3, hBox4);
			hBox.getChildren().addAll(vBox1, vBox2, vBox3, vBox4);

			AnchorPane root = new AnchorPane();
			root.getChildren().add(hBox);

			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("1.png")));
			primaryStage.setTitle("Stopwatch");
			primaryStage.setScene(scene);
			primaryStage.show();

			// Button to start the thread
			btnStart1.setOnAction(e1 -> clockPane.start());
			btnStart2.setOnAction(e1 -> clockPane1.start());
			btnStart3.setOnAction(e1 -> clockPane2.start());
			btnStart4.setOnAction(e1 -> clockPane3.start());
			// Button to stop the thread
			btnStop1.setOnAction(e2 -> clockPane.suspend());
			btnStop2.setOnAction(e2 -> clockPane1.suspend());
			btnStop3.setOnAction(e2 -> clockPane2.suspend());
			btnStop4.setOnAction(e2 -> clockPane3.suspend());
			// Button to reset the thread
			btnReset1.setOnAction(e3 -> clockPane.reset());
			btnReset2.setOnAction(e3 -> clockPane1.reset());
			btnReset3.setOnAction(e3 -> clockPane2.reset());
			btnReset4.setOnAction(e3 -> clockPane3.reset());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		launch(args);

	}

}
