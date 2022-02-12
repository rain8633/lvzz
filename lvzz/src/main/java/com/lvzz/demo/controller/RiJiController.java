package com.lvzz.demo.controller;

import com.lvzz.demo.entity.Page;
import com.lvzz.demo.entity.Result;
import com.lvzz.demo.entity.RiJi;
import com.lvzz.demo.pojo.RiJiPoJo;
import com.lvzz.demo.service.RiJiService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
        String date= DateFormatUtils.format(new Date(),"yyyy-MM-dd");
        riJi.setCreateTime(date);
//        riJi.setCreateTime(new Date());
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
        if(riJi.getTitle()==null || riJi.getTitle().equals("")){
            riJi.setTitle("无");
        }
        int i=riJiService.AddRiJi(riJi);
        if(i>0){
            return Result.success();
        }
        else return Result.error();
    }

//    @InitBinder
//    public void  initBinder(WebDataBinder binder){
//        SimpleDateFormat dateFormat =  new SimpleDateFormat("yyyy-MM-dd");
//        dateFormat.setLenient(false);
//        CustomDateEditor editor = new CustomDateEditor(dateFormat,true);
//        binder.registerCustomEditor(Date.class,editor);
//    }

   @PostMapping("/queryRiJiDetail/{id}")
   public Result queryRiJiDetail(@PathVariable("id") Integer id){
        RiJiPoJo riJi = riJiService.queryRiJiDetail(id);
        if(riJi!=null){
            return Result.success(riJi);
        }
        else return Result.error();
   }

   @PostMapping("/updateRiJi")
   public Result updateRiJi(RiJi riJi){

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
       if(riJi.getTitle()==null || riJi.getTitle().equals("")){
           riJi.setTitle("无");
       }

        int i = riJiService.updateRiJi(riJi);
        if(i>0){
            return Result.success();
        }
        else return Result.error();
   }

   //根据条件查询单个用户游记
    @PostMapping("/queryAllRiJiByInfo")
    public Result queryAllRiJiByInfo(@RequestParam String content,RiJi riJi,@RequestParam(required = false,defaultValue = "4") Integer pageSize, @RequestParam(required = false,defaultValue = "1") Integer pageNum) throws ParseException {
        Page<RiJi> riJiPageList = null;
//        String date= DateFormatUtils.format( riJi.getCreateTime(),"yyyy-MM-dd");
        riJiPageList = riJiService.queryAllRiJi(riJi.getUserId(), content, riJi.getCreateTime(), pageSize, pageNum);

        if(riJiPageList == null){
            return Result.error();
        }
        return Result.success(riJiPageList);
    }


    @GetMapping("/deleterijiById")
    public Result deleterijiByUserId(@RequestParam Integer id,@RequestParam Integer userId){
        int i = riJiService.deleterijiById(id,userId);
        if(i>0){
            return Result.success();
        }else {
            return Result.error();
        }
    }

    //查询所有用户游记
    @PostMapping("/queryAllUserRiJi")
    public Result queryAllUserRiJi(@RequestParam(required = false,defaultValue = "4") Integer pageSize, @RequestParam(required = false,defaultValue = "1") Integer pageNum){
        Page<RiJiPoJo> riJiPoJoList = riJiService.queryAllUserRiJi(pageSize,pageNum);
        if(riJiPoJoList != null){
            return Result.success(riJiPoJoList);
        }
        else return Result.error();
    }
}
