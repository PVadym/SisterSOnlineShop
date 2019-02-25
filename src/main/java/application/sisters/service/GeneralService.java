package application.sisters.service;

import java.util.Collection;

public interface GeneralService<T> {

    T findById(Long id);

    Collection<T> findByName(String name);

    Collection<T> getAll();

    void delete (T t);

    T createOrUpdate (T t);

}
