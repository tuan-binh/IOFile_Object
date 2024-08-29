package business.feature.impl;

import business.entity.Product;
import business.feature.IProductFeature;
import business.utils.IOFile;

import java.util.ArrayList;
import java.util.List;

public class ProductFeatureImpl implements IProductFeature {

    public static List<Product> products = IOFile.readObjectFromFile(IOFile.PATH_PRODUCTS);

    @Override
    public void addOrUpdate(Product product) {
        int index = findIndexByID(product.getProductId());
        if (index != -1) {
            products.set(index, product);
        }else {
            products.add(product);
        }
        IOFile.writeObjectToFile(products, IOFile.PATH_PRODUCTS);
    }

    @Override
    public void delete(String id) {
        products.remove(findIndexByID(id));
        IOFile.writeObjectToFile(products, IOFile.PATH_PRODUCTS);
    }

    @Override
    public int findIndexByID(String id) {
        return products.stream().map(Product::getProductId).toList().indexOf(id);
    }
}
