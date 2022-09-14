package com.backend.service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

//    @Autowired
//    private UserService userService;
//
//    @GetMapping("/getUser")
//    public ResultDTO<String> getUser() {
//        return BizTemplate.execute(new BizCallBack<String>() {
//            @Override
//            public void paramCheck() {
//
//            }
//
//            @Override
//            public String preCheck() {
//                return null;
//            }
//
//            @Override
//            public String execute() throws Exception {
//                return userService.getUser();
//            }
//        });
//    }
}
