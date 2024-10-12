package com.heima.controller;


import com.heima.pojo.vo.TypeNewsReq;
import com.heima.service.HeadlineService;
import com.heima.utils.Result;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/detail")
    public Result<?> getDetail(@RequestParam("id") Integer id) {
        return headlineService.getNewsDetail(id);
    }
}
