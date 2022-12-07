package org.university.db.project.tinytwitter.service;

import java.util.List;

public interface IService<S> {
    boolean add(S service);
    boolean update(S service);
    boolean delete(S service);
    List<S> find(String pattern);
    List<S> getAll();
}
