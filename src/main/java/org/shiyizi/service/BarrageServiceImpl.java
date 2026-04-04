package org.shiyizi.service;

import org.shiyizi.mapper.BarrageMapper;
import org.shiyizi.pojo.Barrage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BarrageServiceImpl implements BarrageService {

    @Autowired
    private BarrageMapper barrageMapper;

    @Override
    public List<Barrage> getList() {
        return barrageMapper.list();
    }

    @Override
    public boolean addBarrage(Barrage barrage) {
        barrage.setCreateTime(LocalDateTime.now());
        return barrageMapper.insert(barrage) > 0;
    }
}