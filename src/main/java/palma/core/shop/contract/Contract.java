package palma.core.shop.contract;

import palma.core.shop.Product;
import palma.core.shop.contract.complex.ListContract;
import palma.core.shop.contract.stamp.Stamp;

import java.util.List;

public interface Contract<T> {
    T fetch(Product product, boolean spend);

    static ClassicContract<Boolean> forService(){
        return new ClassicContract<>(Boolean.class, Stamp.SERVICE);
    }

    static<T> ClassicContract<T> forServiceOf(Class<T> brand){
        return new ClassicContract<>(brand, Stamp.SERVICE);
    }

    static SimpleContract forObject(){
        return new SimpleContract(Stamp.WARRANTY);
    }

    static<T> ClassicContract<T> forObjectOf(Class<T> brand){
        return new ClassicContract<>(brand, Stamp.WARRANTY);
    }

    static<T> ExclusiveContract<T> forInstanceOf(Class<T> brand){
        return new ExclusiveContract<>(brand, Stamp.WARRANTY);
    }

    static<T> Contract<List<T>> forListOf(Class<T> brand){
        return new ListContract<>(brand, Stamp.SUPPLY);
    }

    static SimpleContract forObject(Stamp stamp){
        return new SimpleContract(stamp);
    }

    static<T> ClassicContract<T> forObjectOf(Class<T> brand, Stamp stamp){
        return new ClassicContract<>(brand, stamp);
    }

    static<T> ExclusiveContract<T> forInstanceOf(Class<T> brand, Stamp stamp){
        return new ExclusiveContract<>(brand, stamp);
    }

    static<T> Contract<List<T>> forListOf(Class<T> brand, Stamp stamp){
        return new ListContract<>(brand, stamp);
    }
}
