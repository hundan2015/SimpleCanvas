package thefinal.SceneSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import thefinal.GlobalModel;
import thefinal.StageViewport;

public class CanvasFileSystem {
    static File currentFile = new File("./shit.canvas");

    public static void saveFile() {
        saveFilePanel();
        if (currentFile == null)
            return;
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
        openFilePanel();
        if (currentFile == null)
            return;
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

    static void openFilePanel() {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jFileChooser.setCurrentDirectory(new File("."));
        jFileChooser.setFileFilter(new FileNameExtensionFilter("Canvas File", "canvas"));
        int result = jFileChooser.showOpenDialog(jFileChooser);

        if (result == JFileChooser.APPROVE_OPTION) {
            currentFile = jFileChooser.getSelectedFile();
        } else {
            currentFile = null;
        }
    }

    static void saveFilePanel() {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jFileChooser.setCurrentDirectory(new File("."));
        jFileChooser.setFileFilter(new FileNameExtensionFilter("Canvas File", "canvas"));
        int result = jFileChooser.showSaveDialog(jFileChooser);

        if (result == JFileChooser.APPROVE_OPTION) {
            currentFile = jFileChooser.getSelectedFile();
            // tried to make it canvas but failed!
            if (currentFile.getName().indexOf(".canvas") == -1) {
                String path = currentFile.getPath();
                if (currentFile.renameTo(new File(path + ".canvas"))) {
                    System.out.println(path);
                }
            }
        } else {
            currentFile = null;
        }
    }
}
