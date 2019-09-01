package com.example.demo.controller;

import com.example.demo.model.LogRecord;
import com.example.demo.model.Login;
import com.example.demo.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private UserDetailServiceImpl userDetailService;


    @GetMapping(value="/login")
    public String loginpage(@ModelAttribute("login") Login login)
    {
        return "login.jsp";
    }

    @GetMapping("/logout-sucess")
    public String logoutpage()
    {
//        Map map=userDetailService.getUserTempData();
//        userDetailService.deleteIntoUserTemp((String) map.get("username"));
        Date logoutTime = new Date(System.currentTimeMillis());
        Map<String, Object> map1=new HashMap<>();
        map1.put("logoutTime", logoutTime);
        List<LogRecord> logRecords=userDetailService.getListLogRecord((Date) map1.get("logoutTime"));
        System.out.println(logRecords);
        for(LogRecord logRecord:logRecords)
        {
            map1.put("userId", logRecord.getUserId());
            map1.put("username", logRecord.getUserName());
            map1.put("loginTime", logRecord.getLoginTime());
            map1.put("loginId", logRecord.getLoginId());
            userDetailService.updateIntoLogRecord(map1);
        }
        return "logout.jsp";
    }


}
