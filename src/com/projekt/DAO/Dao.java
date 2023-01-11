package com.projekt.DAO;

import java.util.List;
import java.util.TreeMap;

public interface Dao<T> {
    T get(int id);
    List<T> getAll();
    void add(T t);
    void update(T t, TreeMap<String, Object> params);
    void delete(T t);
}
