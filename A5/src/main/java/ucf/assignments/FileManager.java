package ucf.assignments;

import java.io.File;

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
