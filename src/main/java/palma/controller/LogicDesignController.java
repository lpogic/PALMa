package palma.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.*;
import palma.core.pane.OpenController;
import palma.core.shop.contract.Contract;
import palma.dealer.LogicDesignDealer;

public class LogicDesignController extends OpenController {

    public static final Contract<Boolean> updateLeftPane = Contract.forService();
    public static final Contract<Boolean> setCenterConnections = Contract.forService();
    public static final Contract<Boolean> setCenterFunctions = Contract.forService();

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
            return true;
        });

        shop().offer(setCenterConnections, ()->{
            border.setCenter(scene().openPane("logic-connection-design").getParent());
            return true;
        });

        shop().offer(setCenterFunctions, this::setCenterFunctions);
    }

    @Override
    protected void dress() {
        shop().deal(updateLeftPane);
    }

    @FXML
    public boolean setCenterFunctions(){
        border.setCenter(scene().openPane("logic-function-design").getParent());
        return true;
    }
}
