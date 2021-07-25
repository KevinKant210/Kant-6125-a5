package ucf.assignments;

import java.io.File;
import java.io.IOException;

public class SaveFile extends FileManager{

    private String fileName;

    public SaveFile(String filePath,String fileName) {
        super(filePath);
        this.fileName = fileName;
    }

    public SaveFile(File filePath,String fileName) {
        super(filePath);
        this.fileName = fileName;
    }


    public void makeNewFile() throws IOException {
        filePath = new File(filePath.toString()+fileName);

        filePath.createNewFile();
    }

}
