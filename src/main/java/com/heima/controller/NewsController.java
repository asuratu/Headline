package com.heima.controller;


import com.heima.dto.TypeNewsReq;
import com.heima.service.HeadlineService;
import com.heima.utils.Result;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author asura
 */
@RestController
@RequestMapping("/news")
@CrossOrigin
public class NewsController {

    private final HeadlineService headlineService;

    public NewsController(HeadlineService headlineService) {
        this.headlineService = headlineService;
    }

    @GetMapping("/type")
    public Result<?> getTypeNews(@Valid TypeNewsReq typeNewsReq) {
        return headlineService.getTypeNews(typeNewsReq);
    }
}
