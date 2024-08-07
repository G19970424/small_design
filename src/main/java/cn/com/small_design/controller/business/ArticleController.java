package cn.com.small_design.controller.business;

import cn.com.small_design.common.response.RestResponse;
import cn.com.small_design.common.response.ResultApi;
import cn.com.small_design.controller.business.dto.ArticleDto;
import cn.com.small_design.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author gejj
 * @create 2024年04月29日 13:41
 * @version 1.0
 *
 * 文章接口
 */

@RestController("/article")
public class ArticleController {

    @Autowired
    private IArticleService articleService;

    /**
     * 查询文章
     * @return
     */
    @PostMapping("//query")
    public RestResponse query(){
        return ResultApi.ok(articleService.query());
    }


    /**
     * 新增文章
     * @param articleDto 文章实体类
     * @return
     */
    @PostMapping("/insert")
    public RestResponse insert(@RequestBody ArticleDto articleDto){
        articleService.insert(articleDto);
        return ResultApi.ok();
    }

    /**
     * 根据文章id删除文章
     * @param id
     * @return
     */
    @PostMapping("/article/delete")
    public RestResponse delete(String id){
        articleService.delete(id);
        return ResultApi.ok();
    }

}
