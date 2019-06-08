package palma.model.logic.builder;

public class IdProvider {

    public static String getFreeDeviceId(DeviceAdapterCase devices, String base){
        String tested = base;
        boolean isFree = true;
        int index = 0;
        do {
            if(!isFree)tested = base + ++index;
            isFree = true;
            for (DeviceAdapter it : devices) {
                if(it.getId().equals(tested)){
                    isFree = false;
                    break;
                }
            }
        }while (!isFree);
        return tested;
    }

    public static void givePinsId(DeviceAdapterCase devices){
        int i = 0;
        for(DeviceAdapter it : devices){
            for(Output output : it.getOutputs()){
                output.setId("" + ++i);
            }
        }
    }
}
