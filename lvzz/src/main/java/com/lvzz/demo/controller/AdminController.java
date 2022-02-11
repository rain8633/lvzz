package com.lvzz.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.lvzz.demo.entity.Admin;
import com.lvzz.demo.entity.Result;
import com.lvzz.demo.entity.User;
import com.lvzz.demo.service.AdminService;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    public AdminService adminService;

    @RequestMapping("/addAdmin")
    public Result addAdmin(@RequestBody Admin admin){

        int i = adminService.addAdmin(admin);
        if(i>0){
            return Result.success();
        }
        else return Result.error();
    }


    @RequestMapping("/queryAdmin")
    public Result queryAdmin(HttpServletRequest req, @RequestParam String name, @RequestParam String password,@RequestParam String code){
        String verify_code = (String) req.getServletContext().getAttribute("verify_code");
        if(code == null || verify_code == null || "".equals(code) || !verify_code.toLowerCase().equals(code.toLowerCase())){
            return Result.othereError(3,"验证码错误");
        }else {
           Admin admin = adminService.queryAdmin(name,password);
                String userInfo = JSONObject.toJSONString(admin);
                if (admin != null) {
                    return Result.success(userInfo);
                } else return Result.error();
        }
    }

    @GetMapping("/cheakAdminName/{name}")
    public Result cheakAdminName(@PathVariable("name") String name){
        Admin admin =adminService.findByAdminName(name);
        if(admin!=null){
            return Result.error();
        }else return Result.success();
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
}
