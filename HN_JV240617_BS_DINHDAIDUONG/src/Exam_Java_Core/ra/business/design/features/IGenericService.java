package Exam_Java_Core.ra.business.design.features;

import java.util.List;

public interface IGenericService <T,E>{
    void save(T t);

    void remove(E id);

    int findIndexById(E id);

    List<T> getAll();

}
