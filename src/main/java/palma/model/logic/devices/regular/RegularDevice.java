package palma.model.logic.devices.regular;

import palma.model.logic.devices.Device;

public abstract class RegularDevice implements Device {

    public void dawn(){}

    public void dusk(){}

    public boolean isMorning(){
        return false;
    }

    public boolean isEvening(){
        return false;
    }
}
