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
 * Class: TileOccupiedException
 *
 * Description:
 *
 * ****************************************
 */
package org.MMMJ;

/**
 *  Exception class that gets throw when trying to add a tile to an occupied space
 */
public class TileOccupiedException extends  Exception{
    public TileOccupiedException(String msg){
        super(msg);
    }
}