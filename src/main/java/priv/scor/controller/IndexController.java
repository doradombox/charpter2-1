package priv.scor.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.google.gson.Gson;

import priv.scor.entity.CategoryEntity;

/**
 * @createBy Edgar
 * @date 2018/5/25
 * @Package_name priv.scor.controller
 */
@Controller
public class IndexController extends BaseController{

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/echart1")
    public String echart1() {
        return "echarts1";
    }

    
}


