package palma.core.shop.contract.stamp;

import palma.core.shop.Product;

public class ServiceStamp implements Stamp {

    @Override
    public Object seal(Product product, boolean spend) {
        return product.imp();
    }
}
