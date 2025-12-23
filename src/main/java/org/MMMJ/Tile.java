/******************************************
 *CSCI 205 - Software Engineering and Design
 *Spring 2024
 *Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 *Name: Miguel Romero
 *Section: 01
 *Date: 4/11/24
 *Time: 3:34 PM
 *
 *Project: csci205_final_project
 *Package: org.MMMJ
 *Class: Tile
 *Description:
 * **************************************** */
package org.MMMJ;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Color;

public class Tile {
    private int currNum;

    private int xPos;

    private int yPos;

    /**
     * Constructor for the tile class
     */
    public Tile(){
        this.currNum = 0;
    }

    /**
     * Constructor for the tile class that allows for tiles with numbers other than 0
     */
    public Tile(int tileNumber){
        this.currNum = tileNumber;
    }


    /**
     * Equals method which comes to true if the two Tile objects have the same current number
     * @param tile2 the tile its being compared to
     * @return whether it comes out to true
     * @Author Miguel Romero
     */
    public boolean equals(Tile tile2) {
        if (this.currNum == tile2.getCurrNum()){
            return true;
        }else {
            return false;
        }
    }

    /**
     * Returns the string representation of the tile
     */
    @Override
    public String toString() {
        if(this.currNum == 0){
            return "    ";
        }else{
            return  String.format("%4d",currNum);
        }
    }

    /**
     * Getter and Setter methods to get and set the properties of the tile's position and value
     */
    public int getCurrNum() {
        return currNum;
    }

    /**
     * sets the current number of the tile
     * @param newNum - new current number
     */
    public void setCurrNum(int newNum){
        this.currNum = newNum;
    }

    /**
     * gets the x position of the tile
     * @return - xPos
     */
    public int getXPos(){return this.xPos;}

    /**
     * gets the y position of the tile
     * @return - yPos
     */
    public int getYPos(){return this.yPos;}

    /**
     * sets the x position of the tile
     * @param xPos - x position
     */
    public void setXPos(int xPos){
        this.xPos = xPos;
    }

    /**
     * sets the y position of the tile
     * @param yPos - y position
     */
    public void setYPos(int yPos){
        this.yPos = yPos;
    }
}