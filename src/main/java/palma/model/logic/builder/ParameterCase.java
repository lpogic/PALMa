package palma.model.logic.builder;

import java.util.Collection;
import java.util.HashSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Kolekcja parametrow
 */
public class ParameterCase extends HashSet<Parameter> {

    public ParameterCase(int initialCapacity) {
        super(initialCapacity);
    }

    public ParameterCase() {
    }

    public ParameterCase(Collection<? extends Parameter> c) {
        super(c);
    }


    public Parameter getFirst(Predicate<Parameter> predicate){
        for (Parameter it : this){
            if(predicate.test(it))return it;
        }
        return null;
    }

    public Parameter getFirst(){
        return getFirst(e->true);
    }

    public ParameterCase getBy(Predicate<Parameter> predicate){
        return stream().filter(predicate).collect(Collectors.toCollection(ParameterCase::new));
    }

    public Parameter getFirstByName(String name){
        return getFirst(p->p.getName().equals(name));
    }
}
