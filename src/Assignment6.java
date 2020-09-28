// Assignment #: 6
//         Name: Vincent Le
//    StudentID: 1214852145
//      Lecture: MWF 10:45-11:35am
//  Description: The Assignment6 class creates a Tab Pane with
//               two tabs, one for Movie Creation and one for
//               Movie Review.

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import java.util.ArrayList;

public class Assignment6 extends Application
{
    private TabPane tabPane;
    private CreatePane createPane;
    private ReviewPane reviewPane;
    private ArrayList<Movie> movieList;

    public void start(Stage stage)
    {
        StackPane root = new StackPane();

        //movieList to be used in both createPane & reviewPane
        movieList = new ArrayList<Movie>();

        reviewPane = new ReviewPane(movieList);
        createPane = new CreatePane(movieList, reviewPane);

        tabPane = new TabPane();

        Tab tab1 = new Tab();
        tab1.setText("Movie Creation");
        tab1.setContent(createPane);

        Tab tab2 = new Tab();
        tab2.setText("Movie Review");
        tab2.setContent(reviewPane);

        tabPane.getSelectionModel().select(0);
        tabPane.getTabs().addAll(tab1, tab2);

        root.getChildren().add(tabPane);

        Scene scene = new Scene(root, 700, 400);
        stage.setTitle("Movie Review Apps");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
