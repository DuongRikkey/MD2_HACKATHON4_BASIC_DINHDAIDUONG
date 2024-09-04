package Exam_Java_Core.ra.business.design.features.implement;

import Exam_Java_Core.ra.business.design.entity.Catalog;
import Exam_Java_Core.ra.business.design.entity.Product;
import Exam_Java_Core.ra.business.design.features.ICatalogFeatures;
import Exam_Java_Core.ra.business.design.features.IGenericService;

import java.util.ArrayList;
import java.util.List;

public class CatalogFeatureImpl implements ICatalogFeatures {
    public static List<Catalog> catalogs=new ArrayList<>();
    static {
        catalogs.add(new Catalog(1, "Catalog 1", "Catalog 1"));
        catalogs.add(new Catalog(2, "Catalog 2", "Catalog 2"));
        catalogs.add(new Catalog(3, "Catalog 4", "Catalog 5"));

        ProductFeatureImpl.products.add(new Product("P1234","dUONG","CCC",12,15,catalogs.get(1), true));
        ProductFeatureImpl.products.add(new Product("P1239","dUONGg","CCC",17,15,catalogs.get(2), true));
        ProductFeatureImpl.products.add(new Product("P1239","dUONGg","CCC",20,15,catalogs.get(2), true));
    }

        @Override
    public void save(Catalog catalog) {
            int indexCheck=findIndexById(catalog.getCatalogId());
            if(indexCheck==-1){
                catalogs.add(catalog);
            }else {
                catalogs.set(indexCheck, catalog);

            }

        }

    @Override
    public void remove(Integer id) {
        catalogs.remove(findIndexById(id));
    }

    @Override
    public int findIndexById(Integer id) {
        return catalogs.stream().
                map(Catalog::getCatalogId).toList().
                indexOf(id);
    }

    @Override
    public List<Catalog> getAll() {
        return catalogs;
    }
}
