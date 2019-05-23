package palma.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.*;
import palma.core.pane.OpenController;
import palma.model.logic.LogicModel;
import palma.model.logic.loader.DeviceFactory;
import palma.model.logic.loader.LXMLLoader;

import java.util.Timer;
import java.util.TimerTask;

public class LogicDesignController extends OpenController {

    private LogicModel model;

    @FXML
    private BorderPane border;

    @Override
    public void employ(){}

    @Override
    protected void dress() {}

    @FXML
    public void action(){
        if(model == null){
            LXMLLoader loader = new LXMLLoader(root().getResource("/palma/lxml/logic.xml"));
            loader.setDeviceFactory(new DeviceFactory(scene().getScene()));
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
