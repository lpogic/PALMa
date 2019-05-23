package palma.core.shop.contract.stamp;

import palma.core.shop.Product;

public class WarrantyStamp implements Stamp {

    @Override
    public Object seal(Product product, boolean spend) {
        if(!product.isStored())
            product.set(product.imp());
        return product.get();
    }
}
