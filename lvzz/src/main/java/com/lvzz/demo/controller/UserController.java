package com.lvzz.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.lvzz.demo.entity.Result;
import com.lvzz.demo.entity.User;
import com.lvzz.demo.service.UserService;
import com.lvzz.demo.utils.MD5Util;
import com.lvzz.demo.utils.VerificationCode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

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
                //        System.out.println(username+"  "+password);
                //登录验证
                UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
                Subject subject = SecurityUtils.getSubject();
                User user=userService.findByUsername(userName);
                String userInfo = JSONObject.toJSONString(user);
                try {
                    subject.login(token);
                } catch (AuthenticationException e) {
                    return Result.othereError(4,"用户名或密码错误");
                }
                return Result.success(userInfo);
            }
//                User u = userService.queryUser(userName,password);
//                String userInfo = JSONObject.toJSONString(u);
//                if (u != null) {
//                    return Result.success(userInfo);
//                } else return Result.error();
        }

        @GetMapping("/queryAllUser")
        public Result queryAllUser(){
            List<User> userList = userService.queryAllUser();
            if(userList!=null){
                return Result.success(userList);
            }else return Result.error();
        }
    @DeleteMapping("/deleteUser/{id}")
    public Result deleteUser(@PathVariable("id") Integer id){
            int i = userService.deleteUserById(id);
            if(i>0){
                return Result.success();
            }
            else return Result.error();
    }

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
//        String extension = FilenameUtils.getExtension();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String prefix = UUID.randomUUID().toString().replaceAll("-", "");
        String newName=prefix+suffix;
        String path="H:/upload";

        file.transferTo(new File(path,newName));
        return Result.success("/pics/"+newName);
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

    @RequestMapping("/addUser")
    public Result addUser(@RequestBody User user){
        String salt = MD5Util.md5Encrypt32Lower(user.getPassword());
        // 使用SimpleHash类对原始密码进行加密
        String Pw = new SimpleHash("MD5", user.getPassword(),salt, 1024).toHex();
        user.setPassword(Pw);
        user.setSalt(salt);
        int i = userService.addUser(user);
        if(i>0){
            return Result.success();
        }
        else return Result.error();
    }

    @GetMapping("/cheakUsername/{username}")
    public Result cheakUsername(@PathVariable("username") String userName){
        User user=userService.findByUsername(userName);
        if(user!=null){
            return Result.error();
        }else return Result.success();
    }

    @PostMapping("/queryUserById/{id}")
    public Result queryUserById(@PathVariable("id") Integer id){
        User user = userService.queryUserById(id);
        return Result.success(user);
    }

    @GetMapping("/updateUserImg")
    public Result updateUserImg(@RequestParam String pic,@RequestParam Integer id){
         int i=userService.updateUserImg(pic,id);
         if(i>0){
             User user=userService.queryUserById(id);
             return Result.success(user);
         }
         else return Result.error();
    }

    @PostMapping("/updateUserMsg")
    public Result updateUserMsg(@RequestParam Integer id,@RequestParam String userName,@RequestParam String password,@RequestParam String phone){
           String pass=userService.queryPasswordById(id);
           if(pass == password){
               int i=userService.updateUserMsg(id,userName,password,phone);
               User user1=userService.queryUserById(id);
               if(i>0){
                   return Result.success(user1);
               } else return Result.error();
           }else {
               String salt = MD5Util.md5Encrypt32Lower(password);
               // 使用SimpleHash类对原始密码进行加密
               String Pw = new SimpleHash("MD5", password, salt, 1024).toHex();
               int i1 = userService.updateUserMsg(id, userName, Pw, phone);
               User user1 = userService.queryUserById(id);
               if (i1 > 0) {
                   return Result.success(user1);
               } else return Result.error();
           }
    }

}

