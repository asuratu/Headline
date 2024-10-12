package com.heima.controller;


import com.heima.pojo.Headline;
import com.heima.pojo.vo.PublishNewsReq;
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

    // 注意: @Valid 默认验证 query 的参数
    // 所以这里需要使用 @RequestBody 接收 body 参数
    @PostMapping("/publish")
    public Result<?> publish(@Valid @RequestBody PublishNewsReq publishNewsReq) {
        return headlineService.publish(publishNewsReq);
    }

    @GetMapping("/item")
    public Result<?> getItem(@RequestParam("id") Integer id) {
        return headlineService.getItem(id);
    }

    @PostMapping("/update")
    public Result<?> update(@RequestBody Headline headline) {
        return headlineService.updateItem(headline);
    }

    @DeleteMapping("/delete")
    public Result<?> delete(@RequestParam("hid") Integer id) {
        return headlineService.deleteItem(id);
    }

}
