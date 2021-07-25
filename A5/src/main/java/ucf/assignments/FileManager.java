/*
 *
 *  *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  *  Copyright 2021 Kevin Kant
 *
 */

package ucf.assignments;

import java.io.File;

//class to serve as a template for load and save file classes
public class FileManager {
    public File filePath;



    public FileManager(String filePath){
        this.filePath = new File(filePath);
    }

    public FileManager(File filePath){
        this.filePath = filePath;
    }

    public File getFilePath(){
        return filePath;
    }
}
