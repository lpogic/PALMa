package palma.model.logic.builder;

import palma.model.logic.builder.device.DeviceAdapter;
import palma.model.logic.builder.device.DeviceAdapterCase;

/**
 * Generator unikalnych nazw
 */
public class IdProvider {

    /**
     * Zwraca nazwe bazowa z indeksem zapewniajacym niepowtarzalnosc
     * @param devices
     * @param base
     * @return
     */
    public static String getFreeDeviceName(DeviceAdapterCase devices, String base){
        String tested = base;
        boolean isFree = true;
        int index = 0;
        do {
            if(!isFree)tested = base + ++index;
            isFree = true;
            for (DeviceAdapter it : devices) {
                if(it.getName().equals(tested)){
                    isFree = false;
                    break;
                }
            }
        }while (!isFree);
        return tested;
    }

    /**
     * Ustala id wszystkich polaczen
     * @param devices
     */
    public static void setPinsId(DeviceAdapterCase devices){
        int i = 0;
        for(DeviceAdapter it : devices){
            for(Output output : it.getOutputs()){
                output.setId("" + ++i);
            }
        }
    }
}
