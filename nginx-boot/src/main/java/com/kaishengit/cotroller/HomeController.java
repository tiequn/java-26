package com.kaishengit.cotroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 * @author guojiabang
 * @date 2018/8/27 0027
 */
@RestController
public class HomeController {

    @GetMapping("/")
    public String index(HttpServletRequest req, HttpSession session){

        StringBuilder stringBuilder = new StringBuilder();

        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String headerName = headerNames.nextElement();
            String value = req.getHeader(headerName);
            stringBuilder.append(headerName + "-->" + value).append("<br>");

        }
        stringBuilder.append("--------------------------------------------");

        String ip = req.getRemoteAddr();
        String sessionId = session.getId();

        stringBuilder.append("ip:" + ip + "sessionId" + sessionId);
        return stringBuilder.toString();

    }

}
