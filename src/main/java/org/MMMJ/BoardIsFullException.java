/******************************************
 *CSCI 205 - Software Engineering and Design
 *Spring 2024
 *Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 *Name: Miguel Romero
 *Section: 01
 *Date: 4/25/24
 *Time: 10:28 PM
 *
 *Project: csci205_final_project
 *Package: org.MMMJ
 *Class: BoardIsFullException
 *Description:
 * **************************************** */
package org.MMMJ;

/**
 * Exception class that gets thrown when trying to add a tile to an already full board
 */
public class BoardIsFullException extends Exception{
    public BoardIsFullException(String msg){
        super(msg);
    }
}