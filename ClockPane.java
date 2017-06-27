import javafx.application.Platform;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.*;

public class ClockPane extends Pane {
	// Clock pane's width and height
	private int size;

	double time;
	private int miliseconds = 0;
	private int seconds = 0;
	private int minutes = 0;
	private int hours = 0;

	private int sleepTime = 100;

	int center;
	int radius;
	double clockRadius;
	private Label lbl1 = new Label("00:00:00");
	private Circle circle;
	private Circle spindle;
	private Text t1;
	private Text t2;
	private Text t3;
	private Text t4;

	Group groupLayout = new Group();
	Group group;

	// Constructor
	public ClockPane(int size) {
		this.size = size;
		center = size / 2;
		radius = (size - 80);
		clockRadius = Math.min(size, size) * 0.8 * 0.5;
		init();
		this.setFocusTraversable(true);
		thread.start();

		// start clock from 0
		if (thread.isAlive()) {
			thread.suspend();
			reset();
		}

		//Increase and Decrease the clock
		this.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.UP) {
				this.faster();
			} 
			else if (e.getCode() == KeyCode.DOWN) {
				this.slower();
			}
			e.consume();
		});

	}

	// Initialize design
	private void init() {
		// Draw Circle
		circle = new Circle(center, center, clockRadius);
		circle.setId("circle");
		circle.setFill(Color.WHITE);
		circle.setStroke(Color.BLACK);
		circle.setStrokeWidth(2);

		// text
		t1 = new Text(center - 5, center - clockRadius + 12, "0");
		t2 = new Text(center - clockRadius + 3, center + 5, "45");
		t3 = new Text(center + clockRadius - 10, center + 3, "15");
		t4 = new Text(center - 3, center + clockRadius - 3, "30");

		// Center
		spindle = new Circle(center, center, 5);
		spindle.setId("spindle");
		spindle.setFill(Color.RED);
		// Label
		lbl1.setId("lbl1");
		lbl1.layoutXProperty().bind(circle.centerXProperty().subtract(lbl1.widthProperty().divide(2)));
		lbl1.layoutYProperty().bind(circle.centerYProperty().add(circle.radiusProperty()).divide(3));
		groupLayout.getChildren().addAll(circle, spindle, lbl1, t1, t2, t3, t4);

		setTimer();
	}

	// Digital stop watch
	private void setTimer() {
		miliseconds++;
		if (miliseconds > 99) {
			miliseconds = 0;
			seconds++;
			if (seconds > 60) {
				seconds = 0;
				minutes++;
				if (minutes > 60) {
					hours++;
				}
			}
		}
		lbl1.setText(String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":"
				+ String.format("%02d", seconds) + "." + String.format("%02d", miliseconds));

		paintClock();
	}

	private void paintClock() {
		// Initialize clock parameters
		group = new Group();
		int anim;
		int anim2;
		int anim3;
		int anim4;

		double sLength = clockRadius * 0.8;
		double secondX = center + sLength * Math.sin((miliseconds) * (2 * Math.PI / 100));
		double secondY = center - sLength * Math.cos((miliseconds) * (2 * Math.PI / 100));
		Line sLine = new Line(center, center, secondX, secondY);
		sLine.setStroke(Color.RED);

		Group ticks = new Group();
		double t;

		// Draws a tick line inside the circle
		for (int i = 0; i < 60; i++) {
			radius = size - 80;
			t = (i + 1) % 60.0 / 60 * Math.PI * 2;
			anim = (int) ((center) + (Math.sin(t) * radius / 2));
			anim2 = (int) ((center) - (Math.cos(t) * radius / 2));
			radius = size - 70;
			anim3 = (int) ((center) + (Math.sin(t) * radius / 2));
			anim4 = (int) ((center) - (Math.cos(t) * radius / 2));

			Line tick = new Line(anim, anim2, anim3, anim4);
			tick.getStyleClass().add("tick");
			ticks.getChildren().add(tick);
		}
		
		group.getChildren().addAll(sLine, ticks);

		this.getChildren().clear();
		this.getChildren().addAll(groupLayout, group);

	}

	public Thread thread = new Thread(() -> {
		try {
			while (true) {
				Platform.runLater(() -> setTimer());
				Thread.sleep(sleepTime);
			}
		} catch (InterruptedException ex) {
		}
	});

	// Reset the digital clock
	public void reset() {
		miliseconds = 0;
		seconds = 0;
		minutes = 0;
		hours = 0;
		lbl1.setText(String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":"
				+ String.format("%02d", seconds) + "." + String.format("%02d", miliseconds));
		paintClock();
	}

	// Make the stopwatch timer faster
	public void faster() {
		if (sleepTime > 1)
			sleepTime--;		
	}

	// Make the stopwatch timer slower
	public void slower() {
		sleepTime ++;	
	}

	public void start() {
		thread.resume();
	}

	public void suspend() {
		thread.suspend();
	}

}
