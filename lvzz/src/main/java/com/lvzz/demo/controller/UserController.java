package com.lvzz.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.lvzz.demo.entity.Result;
import com.lvzz.demo.entity.User;
import com.lvzz.demo.service.UserService;
import com.lvzz.demo.utils.VerificationCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController {

        @Autowired
        private UserService userService;

        @RequestMapping("/queryUser")
        public Result queryUser(HttpServletRequest req, @RequestParam String userName, @RequestParam String password,@RequestParam String code){
            String verify_code = (String) req.getServletContext().getAttribute("verify_code");
            if(code == null || verify_code == null || "".equals(code) || !verify_code.toLowerCase().equals(code.toLowerCase())){
                return Result.othereError(3,"验证码错误");
            }else {
                User u = userService.queryUser(userName,password);
                String userInfo = JSONObject.toJSONString(u);
                if (u != null) {
                    return Result.success(userInfo);
                } else return Result.error();
            }
        }

        @GetMapping("/verifyCode")
        public void verifyCode(HttpServletRequest request, HttpServletResponse resp) throws IOException {
            VerificationCode code = new VerificationCode();
            BufferedImage image = code.getImage();
            String text = code.getText();
            HttpSession session = request.getSession(true);
            request.getServletContext().setAttribute("verify_code", text);
            //这个req是HttpServletRequest

            session.setAttribute("verify_code", text);
//        String verify_code = (String) request.getSession().getAttribute("verify_code");
            VerificationCode.output(image,resp.getOutputStream());
        }
}

