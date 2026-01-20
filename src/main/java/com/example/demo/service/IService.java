package com.example.demo.service;

import java.util.List;

public interface IService <T> {
    List getAll();

    void insert(T t);

    void update(T t, String id);

    void delete(String id);
}

