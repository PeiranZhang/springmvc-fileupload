package app22a.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import app22a.domain.Login;

@Controller

public class ResourceController {
	
	private static final Log logger = LogFactory.getLog(ResourceController.class);

    /**
     * @param login @ModelAttribute 注解接受表单中的login对象
     * @param session
     * @param model
     * @return
     */
	@RequestMapping(value="/login")
	public String login(@ModelAttribute Login login, HttpSession session, Model model) {
	    System.out.println(login);
	    //与jsp中的标签绑定
	    model.addAttribute("login", new Login());
	    if ("paul".equals(login.getUserName()) &&
	            "secret".equals(login.getPassword())) {
	        //设置session
	        session.setAttribute("loggedIn", Boolean.TRUE);
            return "Main";
	    } else {
	        return "LoginForm";
	    }
	}

	@RequestMapping(value="/resource_download")
	public String downloadResource(HttpSession session, HttpServletRequest request,
	        HttpServletResponse response) {
        if (session == null || 
                session.getAttribute("loggedIn") == null) {
            return "LoginForm";
        }
        String dataDirectory = request.
                getServletContext().getRealPath("/WEB-INF/data");
        System.out.println(dataDirectory);
        File file = new File(dataDirectory, "secret.pdf");
        if (file.exists()) {
            //设置响应类型，这里是下载pdf文件
            response.setContentType("application/pdf");
            //设置Content-Disposition，设置attachment，浏览器会激活文件下载框；filename指定下载后默认保存的文件名
            //不设置Content-Disposition的话，文件会在浏览器内打卡，比如txt、img文件
            response.addHeader("Content-Disposition",
                    "attachment; filename=secret.pdf");
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            // if using Java 7, use try-with-resources
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (IOException ex) {
                // do something, 
                // probably forward to an Error page
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                    }
                }
            }
        }
        return null;
	}
	
}
