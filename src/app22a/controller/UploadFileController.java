package app22a.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
public class UploadFileController {
    @RequestMapping(value = "/upload_file")
    public String uploadFile(){
        return "uploadFile";
    }

    @RequestMapping(value = "/save_file")
    /**
     * 多个文件上传，对应需要MultipartFile[]数组，否则不需要数组
     */
    public String saveFile(@RequestParam("files")MultipartFile[] files,
                           @RequestParam("name") String name,
                           HttpServletRequest request,Model model){
        //保存文件
        if(files != null && files.length > 0){
            for(MultipartFile file : files){
                String fileName = file.getOriginalFilename();
                String dir = "E://test";
                File saveFile = new File(dir,fileName);
                try {
                    //文件保存到本地
                    file.transferTo(saveFile);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        //显示添加请求对象，在fileLists.jsp重可以使用EL表达式访问对象，即${files}
        request.setAttribute("files",files);
        request.setAttribute("name",name);
        //其实也可以不添加对象，在fileLists.jsp总可以直接使用EL隐式对象param来访问，即${param.email}来访问
        //request.setAttribute("email",email);

        //使用Model添加，为啥在jsp中访问不到？
        //model.addAttribute(files);
        //model.addAttribute(name);
        return "fileLists";
    }
}
