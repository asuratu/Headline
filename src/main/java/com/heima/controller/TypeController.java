package com.heima.controller;


import com.heima.service.TypeService;
import com.heima.utils.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author asura
 */
@RestController
@RequestMapping("/types")
@CrossOrigin
public class TypeController {

    private final TypeService typeService;

    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping("/index")
    public Result<?> index() {
        return typeService.getIndex();
    }
}
