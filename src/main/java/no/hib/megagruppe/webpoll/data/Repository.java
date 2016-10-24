package no.hib.megagruppe.webpoll.data;

import java.util.List;

/**
 * Base interface for repositories. All repositories should know how to do
 * these things
 * @param <T> The entity type for the repository
 */
interface Repository<T> {
    T add(T entity);
    T findById(int id);
    List<T> findAll();
    T update(T entity);
    void remove(T entity);
}
