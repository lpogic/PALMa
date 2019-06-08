package palma.model.logic.builder;

import java.util.Collection;
import java.util.HashSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DeviceAdapterCase extends HashSet<DeviceAdapter> {

    public DeviceAdapterCase(int initialCapacity) {
        super(initialCapacity);
    }

    public DeviceAdapterCase() {
    }

    public DeviceAdapterCase(Collection<? extends DeviceAdapter> c) {
        super(c);
    }


    public DeviceAdapter getFirst(Predicate<DeviceAdapter> predicate){
        for (DeviceAdapter it : this){
            if(predicate.test(it))return it;
        }
        return null;
    }

    public DeviceAdapter getFirst(){
        return getFirst(e->true);
    }

    public DeviceAdapterCase getBy(Predicate<DeviceAdapter> predicate){
        return stream().filter(predicate).collect(Collectors.toCollection(DeviceAdapterCase::new));
    }
}
