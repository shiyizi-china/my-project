package org.shiyizi.service;


import org.shiyizi.pojo.Barrage;

import java.util.List;

public interface BarrageService {
    List<Barrage> getList();
    boolean addBarrage(Barrage barrage);
}