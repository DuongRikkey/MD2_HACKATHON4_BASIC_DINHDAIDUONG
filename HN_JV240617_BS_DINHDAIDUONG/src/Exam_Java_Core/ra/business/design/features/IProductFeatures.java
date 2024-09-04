package Exam_Java_Core.ra.business.design.features;

import Exam_Java_Core.ra.business.design.entity.Catalog;
import Exam_Java_Core.ra.business.design.entity.Product;

public interface IProductFeatures extends IGenericService<Product,String> {
    void sortProductByPrice();
    void searchProductByName(String nameProduct);
}
