package palma.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.*;
import palma.model.logic.LogicModel;
import palma.core.PaneController;
import palma.core.PaneModel;
import palma.model.logic.loader.DeviceFactory;
import palma.model.logic.loader.LXMLLoader;

import java.util.Timer;
import java.util.TimerTask;

public class LogicDesignController extends PaneController {

    private LogicModel model;

    @FXML
    private BorderPane border;

    @Override
    public void init(PaneModel model){
        if(model instanceof LogicModel) {
            this.model = (LogicModel)model;
        }
    }

    @FXML
    public void action(){
        if(model == null){
            LXMLLoader loader = new LXMLLoader(getStageHost().getResource("/palma/lxml/logic.xml"));
            loader.setDeviceFactory(new DeviceFactory(getPaneHost().getStage().getScene()));
            try{
                model = loader.load();
            }catch (Exception e){
                e.printStackTrace();
            }
            Timer timer = new Timer();
            TimerTask logicTask = new TimerTask() {
                @Override
                public void run() {
                    model.run();
                }
            };
            timer.scheduleAtFixedRate(logicTask,0,100);
        }
    }
}
