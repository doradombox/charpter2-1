package priv.scor.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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


