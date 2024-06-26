package cn.com.small_design.controller.base;

import cn.com.small_design.common.response.RestResponse;
import cn.com.small_design.common.response.ResultApi;
import cn.com.small_design.controller.base.dto.RegisterDto;
import cn.com.small_design.service.IRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gejj
 * @create 2024年03月25日 14点59分
 * @version 1.0
 *
 * 注册接口
 */
@RestController
public class RegisterController {

    @Autowired
    private IRegisterService registerService;

    /**
     * 注册接口实现
     * @param registerDto
     * @return
     */
    @PostMapping("/register")
    public RestResponse<?> register(@RequestBody RegisterDto registerDto){
        registerService.register(registerDto);
        return ResultApi.ok("用户注册成功");
    }
}
