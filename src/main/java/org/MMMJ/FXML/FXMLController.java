/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2024
 * Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 * Name: Mike Merola
 * Section: 01
 * Date: 4/15/24
 * Time: 3:52 PM
 *
 * Project: csci205_final_project
 * Package: org.MMMJ.FXML
 * Class: FXMLController
 *
 * Description:
 *
 * ****************************************
 */
package org.MMMJ.FXML;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import org.MMMJ.*;


public class FXMLController{
    /**  make an instance of board */
    public  Board theBoard = new Board(4);

    /**An instance of the {@link Movement} class**/
    public   Movement movement = new Movement(theBoard);
    /** Make an instance of game manager */
    private GameManager manager = new GameManager(theBoard, movement, 2048);

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnNewGame;

    @FXML
    private Label labelScore;

    @FXML
    private GridPane tileGrid;

    @FXML
    public Button btnUp;

    @FXML
    private Button btnDown;

    @FXML
    private Button btnLeft;

    @FXML
    private Button btnRight;

    @FXML
    private Label labelTitle1;
    @FXML
    private Label lblScore;
    /** Used to find out if the game is over */
    private boolean gameOver;
    /** keeps track of the current score */
    private int score;

    private Scene mainScene;



    @FXML
    void initialize() throws TileOccupiedException, OutOfBoardException, BoardIsFullException {
        assert btnNewGame != null : "fx:id=\"btnNewGame\" was not injected: check your FXML file 'FinalFXML.fxml'.";
        assert labelScore != null : "fx:id=\"labelScore\" was not injected: check your FXML file 'FinalFXML.fxml'.";
        assert tileGrid != null : "fx:id=\"tileGrid\" was not injected: check your FXML file 'FinalFXML.fxml'.";
        gameOver = false;
        initBindings();
        movement.placeGenTile();

    }


    /**
     * sets up a binding between the board and the labels in tileGrid
     * used information from @see </https://stackoverflow.com/questions/26838183/how-to-monitor-changes-on-objects-contained-in-an-observablelist-javafx>
     * to correctly bind 2D Observable list of tiles with the labels in the grid pane
     */
    public void initBindings(){

        for (int row = 0; row < theBoard.getBoard().size(); row++) {
            theBoard.getBoard().get(row).addListener(new ListChangeListener<Tile>() {
                @Override
                public void onChanged(Change<? extends Tile> change) {
                    updateLabelInGridPane(theBoard.getBoard());
                }
            });
        }

    }

    public void setScene(Scene scene){
        this.mainScene = scene;
    }

    /**
     * A helper method used to set the text of the label to the value in the array given
     * @param array - an array of tile objects
     */
    private void updateLabelInGridPane(ObservableList<ObservableList<Tile>> array){
        for (int row = 0; row < array.size(); row++){
            for (int col = 0; col < array.get(row).size(); col++){
                Tile tile = array.get(row).get(col);
                Label label = (Label) tileGrid.lookup("#label" +row + col);
                label.setText(String.valueOf(tile.getCurrNum()));
                // check whether to make the current tile visible or not
                if (tile.getCurrNum() != 0) {
                    label.setText(String.valueOf(tile.getCurrNum()));
                    label.setVisible(true);
                } else {
                    label.setVisible(false);
                }
                this.score = manager.calculateScore();
                lblScore.setText(String.valueOf(this.score));

            }
        }
    }



    /**
     * A method used to update the board when specific ques are given
     */
    public void initEventHandlers(){
        this.mainScene.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode() == KeyCode.W ){
                btnUp.fire();
            } else if (keyEvent.getCode() == KeyCode.D ) {
                btnRight.fire();
            } else if (keyEvent.getCode() == KeyCode.S) {
                btnDown.fire();
            }else if(keyEvent.getCode() == KeyCode.A){
                btnLeft.fire();
            }
        });
        tileGrid.setOnKeyPressed(keyEvent -> {
            System.out.println("Key pressed ");
        });
        btnUp.setOnAction(keyEvent -> {
            changeBoard("w");
            checkWinLoss();
        });
        btnDown.setOnAction(keyEvent -> {
            changeBoard("s");
            checkWinLoss();
        });
        btnLeft.setOnAction(keyEvent -> {
            changeBoard("a");
            checkWinLoss();
        });
        btnRight.setOnAction(keyEvent -> {
            changeBoard("d");
            checkWinLoss();
        });

        btnNewGame.setOnAction(actionEvent -> {
            theBoard.setBoard(new Board(4).getBoard());
            updateLabelInGridPane(theBoard.getBoard());
            try {
                this.initialize();
            } catch (TileOccupiedException | OutOfBoardException | BoardIsFullException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * A method that checks if the player wins or loses
     * if they have reached 2048 once and decide to continue playing the winner
     * pop up will not appear again
     */
    private void checkWinLoss() {
        if(manager.didPlayerWin() && !gameOver){
            winnerPopup();
            gameOver = true;
        }
        try {
            if(manager.didPlayerLose()){
                loserPopup();
                gameOver = true;
            }
        } catch (TileOccupiedException | BoardIsFullException | OutOfBoardException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * A helper method that takes in a direction, then uses the movement class to
     * move the tiles and finally update the array
     * @param direction - direction the user wants the tiles to move
     */
    public void changeBoard(String direction) {
        try {
            movement.moveTile(direction);
        } catch (TileOccupiedException | BoardIsFullException | OutOfBoardException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * An alert that appears when the player isn't able to move any more tiles
     */
    public void loserPopup(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("You Lose!");
        alert.setHeaderText("");
        alert.setContentText("You did not reach "  + manager.getGameEndNumber() +"\n"+"Press new game to play again!");
        alert.setGraphic(labelTitle1);
        alert.showAndWait();
    }

    /**
     * An alert that pops up when the player reaches the 2048 tile
     */
    public void winnerPopup(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("You Won!");
        alert.setHeaderText("");
        alert.setContentText("You reached "  + manager.getGameEndNumber() + "!"+"\n"+"You may continue playing or start a new game");
        alert.setGraphic(labelTitle1);
        alert.showAndWait();
    }
}
