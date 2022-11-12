package thefinal.guipart;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import thefinal.GlobalModel;
import thefinal.StageViewport;

public class PageSelectActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        int pageNum = Integer.parseInt(e.getActionCommand());
        StageViewport.currentStage.setVisible(false);
        StageViewport.currentStage = GlobalModel.stageList.get(pageNum);
        StageViewport.currentStage.setVisible(true);
        // TODO:这里需要一个设置模型然后更新视图的功能
        // 部分已经绑定到全局模型上去了。还有什么东西要上去的？
    }
}