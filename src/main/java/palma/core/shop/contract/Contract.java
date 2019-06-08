package palma.core.shop.contract;

import palma.core.shop.Product;
import palma.core.shop.contract.complex.ListContract;
import palma.core.shop.contract.stamp.Stamp;

import java.util.List;

public interface Contract<T> {
    T fetch(Product product, boolean spend);

    static SimpleContract forObject(){
        return new SimpleContract(Stamp.SUPPLY);
    }

    static<T> ClassicContract<T> forObjectOf(Class<T> brand){
        return new ClassicContract<>(brand, Stamp.SUPPLY);
    }

    static<T> ExclusiveContract<T> forClass(Class<T> brand){
        return new ExclusiveContract<>(brand, Stamp.SUPPLY);
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

    static<T> ExclusiveContract<T> forClass(Class<T> brand, Stamp stamp){
        return new ExclusiveContract<>(brand, stamp);
    }

    static<T> Contract<List<T>> forListOf(Class<T> brand, Stamp stamp){
        return new ListContract<>(brand, stamp);
    }
}
