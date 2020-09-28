// Assignment #: 6
//         Name: Vincent Le
//    StudentID: 1214852145
//      Lecture: MWF 10:45-11:35am
//  Description: CreatePane generates a pane where a user can enter
//  a movie information and create a list of available movies.

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
//import all other necessary javafx classes here
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;

public class CreatePane extends HBox
{
    private ArrayList<Movie> movieList;

    //The relationship between CreatePane and ReviewPane is Aggregation
    private ReviewPane reviewPane;

    private TextField titleField,lengthField,yearField;
    private Label errorText;
    private TextArea display;                               //nodes that have to be edited.

    //constructor
    public CreatePane(ArrayList<Movie> list, ReviewPane rePane)
    {
        this.movieList = list;
        this.reviewPane = rePane;

        //Step #1: initialize each instance variable and set up the layout
        VBox createBox = new VBox();
        errorText = new Label("");
        errorText.setTextFill(Color.RED);                           //sets the text as red now, stays red
        GridPane inputArea = new GridPane();
        Button createButton = new Button("Create a Movie");   //create objects

        Label titleLabel=new Label("Title");
        Label lengthLabel=new Label("Length");
        Label yearLabel=new Label("Year");                  //label the textfields

        titleField=new TextField();
        lengthField=new TextField();
        yearField=new TextField();                              //set textfields

        //create a GridPane hold those labels & text fields
        //consider using .setPadding() or setHgap(), setVgap()
        //to control the spacing and gap, etc.
        inputArea.setVgap(5);
        inputArea.setHgap(5);
        inputArea.setMinWidth(250);
        inputArea.setPadding(new Insets(5,10,5,30));
        inputArea.add(titleLabel,0,0);
        inputArea.add(lengthLabel,0,1);
        inputArea.add(yearLabel,0,2);
        inputArea.add(titleField,1,0);
        inputArea.add(lengthField,1,1);
        inputArea.add(yearField,1,2);                   //a lot of formatting

        //You might need to create a sub pane to hold the button
        StackPane buttonPane = new StackPane(createButton);
        buttonPane.setPadding(new Insets(0,0,0,0));
        buttonPane.setAlignment(Pos.TOP_CENTER);                            //aligns the button in the middle

        //Set up the layout for the left half of the CreatePane.
        createBox.getChildren().addAll(errorText,inputArea,buttonPane);
        createBox.setPadding(new Insets(5,10,10,10));
        createBox.setSpacing(5);                                            //sets up the left side of the pane

        //the right half of the InputPane is simply a TextArea object
        //Note: a ScrollPane will be added to it automatically when there are no
        //enough space
        display = new TextArea("No Movie");
        display.setEditable(false);                             //sets up the text area
        //Add the left half and right half to the CreatePane
        //Note: CreatePane extends from HBox
        this.getChildren().addAll(createBox,display);

        //Step #3: register source object with event handler
        ButtonHandler handler = new ButtonHandler();
        createButton.setOnAction(handler);

    } //end of constructor

    //Step 2: Create a ButtonHandler class
    //ButtonHandler listens to see if the button "Create a Movie" is pushed or not,
    //When the event occurs, it get a movie's Title, Year, and Length
    //information from the relevant text fields, then create a new movie and add it inside
    //the movieList. Meanwhile it will display the movie's information inside the text area.
    //It also does error checking in case any of the textfields are empty or non-numeric string is typed
    private class ButtonHandler implements EventHandler<ActionEvent>
    {
        //Override the abstact method handle()
        public void handle(ActionEvent event)
        {
            //declare any necessary local variables here
            String newTitle=titleField.getText();
            String newLength=lengthField.getText();
            String newYear=yearField.getText();         //gets values from the textfields

            //when a text field is empty and the button is pushed
            if (newTitle.isEmpty() || newLength.isEmpty() || newYear.isEmpty())     //checks if all fields are filled
            {
                errorText.setText("Please fill all fields");        //change error text
            }
            else	//for all other cases
            {
                try
                {
                    Movie newMovie = new Movie();
                    newMovie.setMovieTitle(newTitle);
                    newMovie.setLength(Integer.parseInt(newLength));
                    newMovie.setYear(Integer.parseInt(newYear));                //parses text from the text fields

                    if (movieList.size()==0)
                    {
                        display.setText("");                //removes the "No movie" text when adding a new movie for the first time
                    }

                    movieList.add(newMovie);                            //makes a new movie and adds it
                    errorText.setText("Movie Added");       //error text change
                    reviewPane.updateMovieList();           //updates review pane
                    display.appendText(newMovie.toString());//updates the area text
                    titleField.setText("");
                    lengthField.setText("");
                    yearField.setText("");                  //resets the fields
                }
                catch(NumberFormatException e)
                {
                    errorText.setText("Incorrect data format");     //error text change
                }

                    //----
                    //at the end, don't forget to update the new arrayList
                    //information on the ListView of the ReviewPane
                    //----

                    //Also somewhere you will need to use try & catch block to catch
                    //the NumberFormatException

            }
        } //end of handle() method
    } //end of ButtonHandler class
}
