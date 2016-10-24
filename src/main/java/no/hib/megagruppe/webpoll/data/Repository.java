package no.hib.megagruppe.webpoll.data;

import java.util.List;

interface Repository<T> {
    T add(T entity);
    T findById(int id);
    List<T> findAll();
    T update(T entity);
    void remove(T entity);
}
