package org.shiyizi.service;

import org.shiyizi.pojo.Deity;

import java.util.List;

public interface deityService {
    List<Deity> findAll();

    Deity  findById(Integer id);

    int add(Deity deity);

    void deleteById(Integer id);

    int update(Deity deity);
}
