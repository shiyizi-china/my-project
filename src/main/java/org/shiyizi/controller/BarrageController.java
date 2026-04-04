package org.shiyizi.controller;

import org.shiyizi.pojo.Barrage;
import org.shiyizi.pojo.Result;
import org.shiyizi.service.BarrageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/barrage")
@CrossOrigin
public class BarrageController {

    @Autowired
    private BarrageService barrageService;

    // 获取所有弹幕
    @GetMapping
    public Result list() {
        List<Barrage> list = barrageService.getList();
        return Result.success(list);
    }

    // 添加弹幕
    @PostMapping
    public Result add(@RequestBody Barrage barrage) {
        boolean ok = barrageService.addBarrage(barrage);
        return ok ? Result.success() : Result.error("发送失败");
    }
}