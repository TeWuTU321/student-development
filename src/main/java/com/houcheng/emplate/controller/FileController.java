package com.houcheng.emplate.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.houcheng.emplate.common.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

/**
 * author:tutu
 * version:1.0
 */
@RestController
@RequestMapping("/file")
public class FileController {
    @Value("${server.port}")
    private String port;
    @Value("${file.ip}")
    private String ip = "";

    /**
     * 富文本中的文件上传
      */

    @PostMapping("/editor/upload")
    public JSON upload(@RequestParam MultipartFile file, HttpServletRequest request) throws IOException {
        String flag = UUID.randomUUID().toString();
        String originalFilename = file.getOriginalFilename() ;
        String rootFilePath = System.getProperty("user.dir") + "\\src\\main\\resources\\file\\" + "_" + flag + "_" + originalFilename;
        FileUtil.writeBytes(file.getBytes(), rootFilePath);
        String url =  "http://" + ip + ":" + port  + request.getContextPath() + "/file/" + flag;
        JSONObject json = new JSONObject();
        json.set("errno",0);
        JSONArray arr = new JSONArray();
        JSONObject data = new JSONObject();
        data.set("url",url);
        arr.add(data);
        json.set("data",arr);
        return json;
    }

    /**
     * 普通接口文件上传
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public Result<?> editorUpload(@RequestParam MultipartFile file,HttpServletRequest request) throws IOException {
        String flag = UUID.randomUUID().toString();
        String originalFilename = file.getOriginalFilename() ;
        String rootFilePath = System.getProperty("user.dir") + "\\src\\main\\resources\\file\\" + "_" + flag + "_" + originalFilename;
        FileUtil.writeBytes(file.getBytes(), rootFilePath);

        return Result.success("http://" + ip  + ":" + port + request.getContextPath()  +"/file/" + flag);
    }
    /**
     * 下载接口
     * @param flag
     * @param response
     */
    @GetMapping("/{flag}")
    public void getFiles(@PathVariable String flag, HttpServletResponse response) {
        OutputStream os;  // 新建一个输出流对象
        String basePath = System.getProperty("user.dir") + "\\src\\main\\resources\\file\\";  // 定于文件上传的根路径
        List<String> fileNames = FileUtil.listFileNames(basePath);  // 获取所有的文件名称
        String fileName = fileNames.stream().filter(name -> name.contains(flag)).findAny().orElse("");  // 找到跟参数一致的文件
        System.out.println(fileName);
        try {
            if (StrUtil.isNotEmpty(fileName)) {
                response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
                response.setContentType("application/octet-stream");
                byte[] bytes = FileUtil.readBytes(basePath + fileName);  // 通过文件的路径读取文件字节流
                os = response.getOutputStream();   // 通过输出流返回文件
                os.write(bytes);
                os.flush();
                os.close();
            }
        } catch (Exception e) {
            System.out.println("文件下载失败");
        }
    }

}
