package thefinal.SceneSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import thefinal.GlobalModel;
import thefinal.StageViewport;

public class CanvasFileSystem {
    static File currentFile = new File("./shit.txt");

    public static void saveFile() {
        FileWriter fileWriter;
        if (currentFile == null) {
            // TODO:Open a select file menu.
        }
        try {
            fileWriter = new FileWriter(currentFile);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fileWriter = null;
        }
        try {
            fileWriter.write(SequenceGenerator.getFileJSON().toJSONString());
            fileWriter.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("File write failed!");
            e.printStackTrace();
        }
    }

    public static void loadFile() {
        /*
         * FileReader fileReader;
         * if (currentFile == null) {
         * // TODO:Open a select file menu.
         * }
         * try {
         * fileReader = new FileReader(currentFile);
         * } catch (FileNotFoundException e) {
         * e.printStackTrace();
         * fileReader = null;
         * }
         */
        Scanner sc;
        try {
            sc = new Scanner(currentFile);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            sc = null;
            e.printStackTrace();
        }
        String fileString = sc.nextLine();
        StageViewport.currentStage.setVisible(false);
        StageViewport.currentStage = null;
        for (StageObject stageObject : GlobalModel.stageList) {
            GlobalModel.mainFrame.remove(stageObject);
        }
        ArrayList<StageObject> stageList = SequenceGenerator.getStageList(fileString);
        GlobalModel.clearStageList();
        for (StageObject stageObject : stageList) {
            GlobalModel.addStageObject(stageObject);
        }

        sc.close();
    }
}
