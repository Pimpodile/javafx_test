package _main;


//https://stackoverflow.com/questions/33840436/javafx-highlight-vbox-on-mouse-click-and-change-color-when-unfocused

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		System.out.println("Starting");
		launch(args);
	}
	
	private int counter = 0;
	private StringProperty scounter = new SimpleStringProperty(String.valueOf(counter));
	

	private VBox Box1 = new VBox(new Label("Box1"));
	private VBox Box2 = new VBox(new Label("Box2222222"));
	private VBox Box3 = new VBox(new Label("Box3"));
	private Label lcount = new Label(scounter.toString());
	private VBox box4 = new VBox(lcount);
	private Button Button1 = new Button("Add+1");
	private VBox parentBox = new VBox(Box1, Box2, Box3, box4);
	
	
	
	

	private final Background focusBackground = new Background(
			new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY, Insets.EMPTY));
	private final Background unfocusBackground = new Background(
			new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY));
	private final Border border = new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, null, null));

	private final Border bordergrid = new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, null));

	@Override
	public void start(Stage stage) throws Exception {

		GridPane grid = new GridPane();
//		grid.setPadding(new Insets(10, 10, 10, 10));
		
//		Button
		GridPane.setConstraints(Button1, 1, 0);
		Button1.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler1);
		lcount.textProperty().bind(scounter);
		lcount.setTextFill(Color.WHITE);

		GridPane.setConstraints(parentBox, 0, 0);
		grid.getChildren().addAll(parentBox, Button1);

		grid.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, null, null)));
		grid.setBorder(bordergrid);

		boxify();

		Scene scene = new Scene(grid, 800, 480);
		stage.setScene(scene);
		stage.show();

	}

	private void boxify() {
		for (Node child : parentBox.getChildren()) {
			VBox vb = (VBox) child;
			vb.setPadding(new Insets(10));
			vb.setBorder(border);

			vb.setOnMouseClicked((e) -> {
				vb.requestFocus();
			});

			// use different backgrounds for focused and unfocused states
			vb.backgroundProperty()
					.bind(Bindings.when(vb.focusedProperty()).then(focusBackground).otherwise(unfocusBackground));
		}
	}
	
	
	EventHandler<MouseEvent> eventHandler1 = new EventHandler<MouseEvent>() {
		public void handle(MouseEvent e) {
			counter++;
			scounter.set(String.valueOf(counter));
		}
	};

	/**
	 * GRID
	 * 
	 * GridPane grid = new GridPane(); grid.setPadding(new Insets(10, 10, 10, 10));
	 * 
	 * Label l1 = new Label("Label"); GridPane.setConstraints(l1, 0, 0);
	 * 
	 * Label l2 = new Label("Kurz"); GridPane.setConstraints(l2, 0, 1);
	 * 
	 * 
	 * 
	 * grid.getChildren().addAll(l1, l2);
	 * 
	 * grid.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, null,
	 * null)));
	 * 
	 * Scene scene = new Scene(grid, 640, 480); stage.setScene(scene); stage.show();
	 *
	 */

}
