package com.lvzz.demo.controller;

import com.lvzz.demo.entity.Result;
import com.lvzz.demo.entity.RiJi;
import com.lvzz.demo.service.RiJiService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/RiJi")
public class RiJiController {

    @Autowired
    private RiJiService riJiService;

    @PostMapping("/upload")
    public Result upload(@RequestParam("files") MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
//        String extension = FilenameUtils.getExtension();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String prefix = UUID.randomUUID().toString().replaceAll("-", "");
        String newName=prefix+suffix;
        String path="H:/upload";

        file.transferTo(new File(path,newName));
        return Result.success("/pics/"+newName);
    }

    @PostMapping("/AddRiJi")
    public Result AddRiJi(RiJi riJi){
        String date= DateFormatUtils.format(new Date(),"yyyy-MM-dd HH-mm-ss");
        riJi.setCreateTime(date);
        String content1=riJi.getContent();
        int length=content1.length();

        /*
         * <.*?>为正则表达式，其中的.表示任意字符，*?表示出现0次或0次以上，此方法可以去掉    双头标签(双头针对于残缺的标签)
         * "<.*?"表示<尖括号后的所有字符，此方法可以去掉残缺的标签，及后面的内容
         * " "，若有多种此种字符，可用同一方法去除
         */
        content1 = content1.replaceAll("<.*?>", " ").replaceAll("", "");
        content1 = content1.replaceAll("<.*?", "");
        int length1=content1.length();
        riJi.setContent(content1.substring(0, length1));

        int i=riJiService.AddRiJi(riJi);
        if(i>0){
            return Result.success();
        }
        else return Result.error();
    }
}
