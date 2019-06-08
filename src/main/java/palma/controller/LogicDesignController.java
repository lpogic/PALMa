package palma.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.*;
import palma.core.pane.OpenController;
import palma.core.shop.contract.Contract;
import palma.dealer.LogicDesignDealer;

public class LogicDesignController extends OpenController {

    public static final Contract<Object> updateLeftPane = Contract.forObject();
    public static final Contract<Object> setCenterConnections = Contract.forObject();
    public static final Contract<Object> setCenterFunctions = Contract.forObject();

    @FXML
    private BorderPane border;

    @FXML
    private VBox devices;

    @FXML
    private VBox functions;


    @Override
    public void employ(){
        shop().offer(updateLeftPane, ()->{
            devices.getChildren().setAll(shop().deal(LogicDesignDealer.getDeviceButtons));
            functions.getChildren().setAll(shop().deal(LogicDesignDealer.getFunctionButtons));
            return null;
        });

        shop().offer(setCenterConnections, ()->{
            border.setCenter(scene().openPane("logic-connection-design").getParent());
            return null;
        });

        shop().offer(setCenterFunctions, this::setCenterFunctions);
    }

    @Override
    protected void dress() {
        shop().deal(updateLeftPane);
    }

    @FXML
    public Object setCenterFunctions(){
        border.setCenter(scene().openPane("logic-function-design").getParent());
        return null;
    }
}
