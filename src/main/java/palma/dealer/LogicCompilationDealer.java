package palma.dealer;

import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import palma.controller.LogicDesignController;
import palma.core.shop.OpenDealer;
import palma.core.shop.Shop;
import palma.core.shop.contract.Contract;
import palma.model.logic.builder.Input;
import palma.model.logic.builder.Output;
import palma.model.logic.builder.Parameter;
import palma.model.logic.builder.device.DeviceAdapter;
import palma.model.logic.builder.validate.ValidationError;
import palma.model.logic.builder.validate.ValidationException;

import java.util.ArrayList;
import java.util.List;

public class LogicCompilationDealer extends OpenDealer {

    public static final Contract<ValidationException> proceedException = Contract.forObjectOf(ValidationException.class);

    public static final Contract<Boolean> showCompilationErrors = Contract.forService();
    public static final Contract<Boolean> hideCompilationErrors = Contract.forService();
    public static final Contract<List<Button>> getExceptionButtons = Contract.forListOf(Button.class);

    @Override
    public void employ(Shop shop) {
        shop.offer(showCompilationErrors,()->{
            root().openStage("logic-compilation-error").show();
            Stage stage = root().openStage("logic-compilation-error").getStage();
            stage.setAlwaysOnTop(true);
            stage.setTitle("Bledy kompilacji");
            return true;
        });

        shop.offer(hideCompilationErrors,()->{
            root().openStage("logic-compilation-error").close();
            return true;
        });

        shop.offer(getExceptionButtons,this::getExceptionButtons);

    }

    private List<Button> getExceptionButtons(){
        ValidationException exception = shop().deal(proceedException);
        List<Button> buttons = new ArrayList<>();

        for(ValidationError error : exception.getErrors()){
            Button button = new Button(error.getMessage());
            button.setPrefWidth(Region.USE_COMPUTED_SIZE);
            button.setMaxWidth(Double.MAX_VALUE);
            DeviceAdapter device = fetchDevice(error);
            if(device != null) {
                button.setOnAction(e -> {
                    shop().deliver(LogicDesignDealer.selectedDevice, device);
                    shop().deal(LogicDesignController.setCenterEditor);
                });
            } else button.setDisable(true);
            buttons.add(button);
        }

        return buttons;
    }

    private DeviceAdapter fetchDevice(ValidationError error){
        DeviceAdapter device = null;
        if(error.getObject() instanceof Input)device = ((Input) error.getObject()).getOwner();
        else if(error.getObject() instanceof Output)device = ((Output) error.getObject()).getOwner();
        else if(error.getObject() instanceof Parameter)device = ((Parameter) error.getObject()).getOwner();
        return device;
    }
}
