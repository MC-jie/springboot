package com.atguigu.user.controller;

import com.atguigu.user.config.PatternProperties;
import com.atguigu.user.mapper.UserService;
import com.atguigu.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Mr jie
 * @create 2022-50-26-20:50
 */
@RestController
@RequestMapping("/user")
@RefreshScope           //配置热更新
public class UserController {
    @Autowired
    private UserService userService;

    //配置热更新:方式二@ConfigurationProperties注解代替@Value注解。
    @Autowired
    public PatternProperties patternProperties;

    //配置热更新:方式一 加@RefreshScope注解
    @Value("${info.version}")
    private String version;
    @GetMapping("getInfo")
    public PatternProperties version(){
        return patternProperties;
    }

    @GetMapping("/prop")
    public PatternProperties prop(){return patternProperties;}


    //从微服务拉取配置
  /*  @Value("${pattern.dateformat}")
    private String dateformat;
    @GetMapping("now")
    public String now(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(dateformat));
    }*/

    /**
     * 路径： /user/110
     *
     * @param id 用户id
     * @return 用户
     */
    @GetMapping("/{id}")
    public User queryById(@PathVariable Long id) throws Exception{
//        if (id==3){
//            throw new RuntimeException("aaa");
//        }
        if (id==1){
            //id为1时,触发慢调用
            Thread.sleep(60);
        }else if(id==2){
            throw new RuntimeException("故意抛出异常,触发异常比例熔断");
        }
        if (id==2){

        }
        User user = userService.queryById(id);
        user.setUsername(user.getUsername() + ":" + "user-service");
        return user;
    }
}
