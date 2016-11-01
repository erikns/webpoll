package no.hib.megagruppe.webpoll.data;

import java.util.List;

/**
 * Base interface for repositories. All repositories should know how to do
 * these things
 * @param <T> The entity type for the repository
 */
interface Repository<T> {
    /**
     * Add a new entity. Should only be called when the entity does not currently
     * exist. When the entity is returned, the primary key field will be set to the
     * automatically generated value.
     * @param entity The entity to add
     * @return The added entity with generated key field set, or null on error
     */
    T add(T entity);

    /**
     * Finds a single entity by its primary key field.
     * @param id The id of the entity to find
     * @return The entity if it exists, null otherwise
     */
    T findById(int id);

    /**
     * Finds and returns a List of all entities of T
     * @return The list of all T entities
     */
    List<T> findAll();

    /**
     * Updates an entity T already in the system.
     * @param entity The entity to update
     * @return The updated entity
     */
    T update(T entity);

    /**
     * Remove an entity T from the ssytem
     * @param entity The entity to remove
     */
    void remove(T entity);
}
