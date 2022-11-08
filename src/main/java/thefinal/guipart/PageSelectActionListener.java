package thefinal.guipart;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import thefinal.GlobalModel;

public class PageSelectActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        int pageNum = Integer.parseInt(e.getActionCommand());
        GlobalModel.currentStage.setVisible(false);
        GlobalModel.currentStage = GlobalModel.stageList.get(pageNum);
        GlobalModel.currentStage.setVisible(true);
        // TODO:这里需要一个设置模型然后更新视图的功能
        // 部分已经绑定到全局模型上去了。还有什么东西要上去的？
    }
}