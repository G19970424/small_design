package cn.com.small_design.controller.manager;

import cn.com.small_design.common.response.RestResponse;
import cn.com.small_design.common.response.ResultApi;
import cn.com.small_design.controller.manager.dto.UserManagerDto;
import cn.com.small_design.service.IUserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gejj
 * @create 2024年04月25日 15:48
 * @version 1.0
 *
 * 用户管理接口
 */
@RestController("/userManager")
public class UserManagerController {

    @Autowired
    private IUserManagerService userManagerService;

    /**
     * 查询所有用户
     * @return
     */
    @PostMapping("/queryAll")
    public RestResponse<?> queryAll(){
        return ResultApi.ok(userManagerService.query());
    }

    /**
     * 新增用户
     * @param dto
     * @return
     */
    @PostMapping("/insert")
    public RestResponse<?> insert(@RequestBody UserManagerDto dto){
        userManagerService.insert(dto);
        return ResultApi.ok("新增成功");
    }

    /**
     * 更新用户
     * @param dto
     * @return
     */
    @PostMapping("/userManager/update")
    public RestResponse<?> update(@RequestBody UserManagerDto dto){
        userManagerService.update(dto);
        return ResultApi.ok("更新成功");
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @PostMapping("/userManager/delete")
    public RestResponse<?> delete(@RequestBody String id){
        userManagerService.delete(id);
        return ResultApi.ok("删除成功");
    }

}
