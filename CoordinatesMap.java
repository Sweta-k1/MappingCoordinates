package JavaFXMappingApp;

/**
 *
 * @author Sweta
 */
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CoordinatesMap extends Application {

    ArrayList<Coord> list = new ArrayList();
    VBox root;
    Pane coordfield;

    //Creates  ActionEvent Buttons reloading,disbling and for the contact details,use of panes,timeLine
    @Override
    public void start(Stage primaryStage) {

        root = new VBox();
        HBox hbox = new HBox();
        coordfield = new Pane();

        Button reload = new Button("Reload");
        reload.setOnAction(e -> {
            dothings(list);
        });

        Button disable = new Button("Disable");
        Button about = new Button("About");

        about.setOnAction((ActionEvent event) -> {

            Label Details = new Label("Sweta\n0769762885\nshweta.kollimarla@gmail.com");

            StackPane secondaryLayout = new StackPane();
            secondaryLayout.getChildren().add(Details);
            Scene Scene1 = new Scene(secondaryLayout, 230, 100);
            Stage newWindow = new Stage();
            newWindow.setTitle("Contact Details");
            newWindow.setScene(Scene1);

            newWindow.setX(primaryStage.getX() + 250);
            newWindow.setY(primaryStage.getY() + 100);

            newWindow.show();
        }
        );
        Button exit = new Button("Exit");
        exit.setOnAction(e -> Platform.exit());

        root.getChildren().add(hbox);
        root.getChildren().add(coordfield);

        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("RecruitmentTestDigpro!");
        primaryStage.setScene(scene);
        primaryStage.show();
        Reader r = new Reader(list);
        Thread t1 = new Thread(r);
        t1.setDaemon(true);
        t1.start();

        Timeline timeLine = new Timeline(new KeyFrame(Duration.seconds(1),
                e -> {
                    dothings(list);
                }
        ));
        timeLine.setCycleCount(Timeline.INDEFINITE);
        timeLine.play();
        hbox.getChildren().add(about);
        hbox.getChildren().add(reload);
        hbox.getChildren().add(disable);
        hbox.getChildren().add(exit);

        disable.setOnAction(e -> {
            if (timeLine.statusProperty().get().equals(Animation.Status.PAUSED)) {
                timeLine.play();
                disable.setText("Disable");
            } else {
                timeLine.pause();
                disable.setText("Enable");
            }
        });

    }

    //launches window
    public static void main(String[] args) {
        launch(args);
    }

    //Creates a set of coordinates with the name 
    public void dothings(ArrayList<Coord> list) {
        System.out.println("Creating Coordinates");
        coordfield.getChildren().clear();
        list.stream().map((coord) -> {
            VBox vBox = new VBox();
            vBox.setAlignment(Pos.CENTER);
            Rectangle rectangle = new Rectangle(10, 10);
            Text text = new Text(coord.getName());
            vBox.getChildren().add(rectangle);
            vBox.getChildren().add(text);
            vBox.setLayoutX((1000 + coord.getX()) / 6);
            vBox.setLayoutY((1000 + coord.getY()) / 6);
            return vBox;
        }).forEachOrdered((vBox) -> {
            coordfield.getChildren().add(vBox);
        });
    }

}
