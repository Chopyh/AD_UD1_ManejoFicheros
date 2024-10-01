package jv.chopy.crud.dao;

public interface I_DAO<T> {
    void create(T t);
    T read(int id);
    void update(T t);
    void delete(int id);
}
