/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2024
 * Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 * Name: Mike Merola
 * Section: 01
 * Date: 4/23/24
 * Time: 4:50 PM
 *
 * Project: csci205_final_project
 * Package: org.MMMJ
 * Class: OutOfBoardException
 *
 * Description:
 *
 * ****************************************
 */
package org.MMMJ;

/**
 * Exception class that gets thrown when a tile tries to get added outside the board
 */
public class OutOfBoardException extends Exception{
    public OutOfBoardException(String msg){
        super(msg);
    }
}