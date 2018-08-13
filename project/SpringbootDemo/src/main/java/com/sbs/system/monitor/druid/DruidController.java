package com.sbs.system.monitor.druid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * druid 监控
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/monitor/data")
public class DruidController
{
    private String prefix = "/monitor/druid";

//    @RequiresPermissions("monitor:data:view")
    @GetMapping()
    public String index()
    {
        return "redirect:" + prefix + "/index";
    }
}
