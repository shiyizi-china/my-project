package org.shiyizi.controller;

import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.annotations.Delete;
import org.shiyizi.pojo.Result;
import org.shiyizi.pojo.Deity;
import org.shiyizi.service.deityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/deitys")
public class deityController {
    @Autowired
    private deityService deityService;

    @GetMapping
    public Result findALL(){
        log.info("查询所有");
        List<Deity> deitylist = deityService.findAll();
        return Result.success(deitylist);
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id){
        log.info("查询id为{}",id);
        Deity deity = deityService.findById(id);
        return Result.success(deity);
    }

    @PostMapping
    public Result add(@RequestBody Deity deity){
        log.info("添加{}",deity);
        deityService.add(deity);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("删除id为{}",id);
        deityService.deleteById( id);
        return Result.success();
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable Integer id, @RequestBody Deity deity){
        log.info("修改{}, ID: {}", deity, id);
        deityService.update(deity);
        return Result.success();
    }
}
