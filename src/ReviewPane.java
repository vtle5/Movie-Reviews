// Assignment #: 6
//         Name: Vincent Le
//    StudentID: 1214852145
//      Lecture: MWF 10:45-11:35am
//  Description: ReviewPane displays a list of available movies
//  from which a user can select to reviw.

import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;	//**Need to import to handle event
import javafx.event.EventHandler;	//**Need to import to handle event

import java.util.ArrayList;
import javafx.scene.layout.HBox;

//import all other necessary javafx classes here
import javafx.scene.control.*;

public class ReviewPane extends VBox
{
    private ArrayList<Movie> movieList;

    //A ListView to display movies created
    private ListView<Movie> movieListView;

    //declare all other necessary GUI variables here
    private ObservableList obsList;
    private HBox rateBox;
    private ToggleGroup group;
    private RadioButton rate1,rate2,rate3,rate4,rate5;
    private Button rateButton;

    //constructor
    public ReviewPane(ArrayList<Movie> list)
    {
        //initialize instance variables
        this.movieList = list;

        //set up the layout
        Label infoText = new Label("Choose a movie to give a review, and select a rating:");        //top text

        obsList= FXCollections.observableArrayList(movieList);      //setting up the list
        movieListView= new ListView(obsList);
        movieListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        rate1 = new RadioButton("1 Poor");
        rate2 = new RadioButton("2 Fair");
        rate3 = new RadioButton("3 Average");
        rate4 = new RadioButton("4 Good");
        rate5 = new RadioButton("5 Excellent");                 //create all the buttons

        group = new ToggleGroup();
        rate1.setToggleGroup(group);
        rate2.setToggleGroup(group);
        rate3.setToggleGroup(group);
        rate4.setToggleGroup(group);
        rate5.setToggleGroup(group);                                //set all the buttons to one group

        rateBox= new HBox();
        rateBox.setSpacing(10);
        rateBox.getChildren().addAll(rate1,rate2,rate3,rate4,rate5);    //set up layout for the buttons

        rateButton = new Button("Submit Review");

        //ReviewPane is a VBox - add the components here
        this.getChildren().addAll(infoText,movieListView,rateBox,rateButton);

        //Step #3: Register the button with its handler class
        RatingHandler handler = new RatingHandler();
        rateButton.setOnAction(handler);

    } //end of constructor

    //This method refresh the ListView whenever there's new movie added in CreatePane
    //you will need to update the underline ObservableList object in order for ListView
    //object to show the updated movie list
    public void updateMovieList()       //doesnt need a movie object, is handled in create pane
    {
        obsList.setAll(movieList);      //updates the observerlist with the movielist
    }

    //Step 2: Create a RatingHandler class
    private class RatingHandler implements EventHandler<ActionEvent>
    {
        //Override the abstact method handle()
        public void handle(ActionEvent event)
        {
            //When "Submit Review" button is pressed and a movie is selected from
            //the list view's average rating is updated by adding a additional
            //rating specified by a selected radio button
            if (!movieListView.getSelectionModel().isEmpty() && group.getSelectedToggle()!=null)
            {
                Movie select = (Movie) movieListView.getSelectionModel().getSelectedItem(); //gets the movie selected
                if (rate1.isSelected())                                       //rates based on radio selected
                {
                    select.addRating(1.0);
                }
                if (rate2.isSelected())
                {
                    select.addRating(2.0);
                }
                if (rate3.isSelected())
                {
                    select.addRating(3.0);
                }
                if (rate4.isSelected())
                {
                    select.addRating(4.0);
                }
                if (rate5.isSelected())
                {
                    select.addRating(5.0);
                }
                updateMovieList();
                movieListView.getSelectionModel().clearSelection();
                group.getSelectedToggle().setSelected(false);           //deselects everything
            }
        }
    } //end of RatingHandler
} //end of ReviewPane class
