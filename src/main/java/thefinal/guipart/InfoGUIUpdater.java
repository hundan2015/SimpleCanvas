package thefinal.guipart;

public class InfoGUIUpdater {
    static InfoGUI infoGUI;

    static void setInfoGUI(InfoGUI infoGUI) {
        InfoGUIUpdater.infoGUI = infoGUI;
    }

    public static void updateModel() {
        infoGUI.updateModel();
    }

    public static void updateGUI() {
        infoGUI.updateGUI();
    }
}
