
package org.shiyizi.service;

import org.shiyizi.pojo.Deity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.shiyizi.mapper.deityMapper;

import java.util.List;
import com.aliyun.oss.*;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.common.comm.SignVersion;
import org.springframework.stereotype.Component;
import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

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
}
