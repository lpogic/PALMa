package palma.model.logic;

import palma.model.logic.devices.regular.RegularDevice;
import palma.model.logic.functions.Function;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class LogicModel implements Runnable{
    private Collection<RegularDevice> mornings;
    private Collection<RegularDevice> evenings;
    private Collection<Signal> modelDrivenSignals;
    private Collection<Function> functions;

    public LogicModel(Collection<RegularDevice> mornings, Collection<RegularDevice> evenings,
                      Collection<Signal> modelDrivenSignals, Collection<Function> functions) {
        this.mornings = mornings;
        this.evenings = evenings;
        this.modelDrivenSignals = modelDrivenSignals;
        this.functions = functions;
    }

    @Override
    public void run()throws RuntimeException{
        modelDrivenSignals.forEach(Signal::reset);
        mornings.forEach(RegularDevice::dawn);
        List<Function> campers = new ArrayList<>(functions);
        List<Function> pioneers;
        while(campers.size() > 0){
            pioneers = new ArrayList<>(campers.size());
            for(Function it : campers){
                if(!it.execute())pioneers.add(it);
            }
            if(pioneers.size() > 0 && campers.size() == pioneers.size())
                throw new RuntimeException("Run method stuck in interminable cycle");
            campers = pioneers;
        }
        evenings.forEach(RegularDevice::dusk);
    }
}
