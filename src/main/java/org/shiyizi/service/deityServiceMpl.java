package org.shiyizi.service;

import org.shiyizi.pojo.Deity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.shiyizi.mapper.deityMapper;

import java.util.List;

@Service
public class deityServiceMpl implements deityService{

    @Autowired
    private deityMapper deityMapper;

    @Override
    public List<Deity> findAll() {
        return deityMapper.findAll();
    }

    @Override
    public Deity findById(Integer id) {
        return deityMapper.findById(id);
    }

    @Override
    public int add(Deity deity) {
        return deityMapper.add(deity);
    }

    @Override
    public void deleteById(Integer id) {
        deityMapper.deleteById(id);
    }

    @Override
    public int update(Deity deity) {
        return deityMapper.update(deity);
    }

    @Override
    public boolean updateAvatar(Integer userId, String avatarUrl) {
        if (userId == null || avatarUrl == null || avatarUrl.isEmpty()) {
            return false;
        }
        // 使用ID更新头像（更安全，避免用户名重复问题）
        Deity deity = new Deity();
        deity.setId(userId);
        deity.setAvatar(avatarUrl);

        // 需要在mapper中添加根据ID更新头像的方法
        int result = deityMapper.updateAvatarById(userId, avatarUrl);
        return result > 0;
    }
}