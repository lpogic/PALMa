package palma.dealer;

import palma.core.shop.OpenDealer;
import palma.core.shop.Shop;
import palma.core.shop.contract.Contract;
import palma.model.logic.builder.DeviceAdapterCase;
import palma.model.logic.builder.IdProvider;
import palma.model.logic.builder.Validator;
import palma.model.logic.writer.LXMLWriter;

import java.io.FileNotFoundException;

public class XMLDealer extends OpenDealer {

    public static final Contract<Boolean> exportXml = Contract.forService();

    @Override
    public void employ(Shop shop) {

        shop.offer(exportXml,()->{
            try{
                if(shop().order(LogicDesignDealer.getDevices)){
                    DeviceAdapterCase devices = shop().deal(LogicDesignDealer.getDevices);
                    IdProvider.givePinsId(devices);
                    if(Validator.getInvalidInputs(devices).isEmpty() &&
                        Validator.getInvalidOutputs(devices).isEmpty()){
                        LXMLWriter.write(shop().deal(LogicDesignDealer.getDevices),"logic.xml");
                        System.out.println("Exported");
                        return true;
                    }
                }
            }catch(FileNotFoundException fnfe){
                fnfe.printStackTrace();
            }
            return false;
        });
    }
}
