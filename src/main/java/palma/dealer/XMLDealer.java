package palma.dealer;

import javafx.scene.control.Alert;
import javafx.stage.Modality;
import palma.core.shop.OpenDealer;
import palma.core.shop.Shop;
import palma.core.shop.contract.Contract;
import palma.model.logic.builder.Input;
import palma.model.logic.builder.device.DeviceAdapterCase;
import palma.model.logic.builder.IdProvider;
import palma.model.logic.builder.validate.ValidationException;
import palma.model.logic.builder.validate.Validator;
import palma.model.logic.writer.LXMLWriter;

import java.io.FileNotFoundException;

public class XMLDealer extends OpenDealer {

    public static final Contract<Boolean> exportXml = Contract.forService();

    @Override
    public void employ(Shop shop) {

        shop.offer(exportXml,()->{
            shop().deal(LogicCompilationDealer.hideCompilationErrors);
            try{
                if(shop().order(LogicDesignDealer.getDevices)){
                    DeviceAdapterCase devices = shop().deal(LogicDesignDealer.getDevices);
                    IdProvider.setPinsId(devices);
                    if(exportXml(devices)){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Zakończono eksport");
                        alert.setHeaderText(null);
                        alert.setContentText("Pomyślnie wyeksportowano plik: logic.xml");
                        alert.show();
                    }
                }
            }catch (ValidationException ve){
                shop().deliver(LogicCompilationDealer.proceedException,ve);
                shop().deal(LogicCompilationDealer.showCompilationErrors);
            }catch(FileNotFoundException fnfe){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Eksport nie powódł się");
                alert.setHeaderText("Problem z plikiem logic.xml:");
                alert.setContentText(fnfe.getMessage());
                alert.showAndWait();
            }
            return false;
        });
    }

    private boolean exportXml(DeviceAdapterCase devices)throws ValidationException, FileNotFoundException {
        Validator.validateAll(devices);
        LXMLWriter.write(shop().deal(LogicDesignDealer.getDevices),"logic.xml");
        return true;
    }
}
