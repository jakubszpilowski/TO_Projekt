package com.projekt.manager;
import com.projekt.entity.Train;

import java.util.TreeMap;

public abstract class Manager<T> {
    private TreeMap<Integer, T> collection = new TreeMap<Integer, T>();

    public TreeMap<Integer, T> getCollection() {
        return collection;
    }

    public void setCollection(TreeMap<Integer, T> collection) {
        this.collection = collection;
    }

    //CRUD
    public abstract void add(int id, T object);
    public abstract void print();
    public abstract void update(int id, T object);
    public abstract void delete(int id);

}
