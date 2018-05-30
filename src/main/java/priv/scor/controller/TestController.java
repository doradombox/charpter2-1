package priv.scor.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.google.gson.Gson;

import priv.scor.entity.CategoryEntity;
import priv.scor.services.UserService;

/**
 * 
 * @author huangwenbo
 * 
 */
@RequestMapping(value="/test")
@RestController
public class TestController extends BaseController{
    
    @GetMapping(value = "/test/getCategory")
    @ResponseBody
    public String getCategory() {
        List<CategoryEntity> categoryEntities = Lists.newArrayList();
        // ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
        // [5, 20, 40, 10, 10, 20]
        categoryEntities.add(new CategoryEntity("衬衫",5L));
        categoryEntities.add(new CategoryEntity("羊毛衫",20L));
        categoryEntities.add(new CategoryEntity("雪纺衫",40L));
        categoryEntities.add(new CategoryEntity("裤子", 10L));
        categoryEntities.add(new CategoryEntity("高跟鞋",10L));
        categoryEntities.add(new CategoryEntity("袜子", 100L));
        Gson gson = new Gson();
        return gson.toJson(categoryEntities);
    }
    
    /**
     * @RequestMapping 类或者方法级别注解,表示请求路径/test/hello
     * 2>.method属性
• 指定请求的method类型， GET、POST、PUT、DELETE等:
例：@RequestMapping(value = "/login", method = RequestMethod.POST) 那么只有发送POST请求才会触发这个方法
• 它的值既可以是字符串也可以是数组:
例：@RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})

3>.consumes属性
• 指定请求的提交内容类型（Content-Type），例如application/json, text/html
例：@RequestMapping(value = "/login", consumes = "application/json") 
• 它的值既可以是字符串也可以是数组
例：@RequestMapping(value = "/login", consumes = {"application/json", "text/html"})

4>.produces属性
• 指定返回的内容的类型（Content-Type），例如application/json, text/html
例：@RequestMapping(value = "/login", produces = "application/json") 
• 它的值既可以是字符串也可以是数组
例：@RequestMapping(value = "/login", produces = {"application/json", "text/html"})

5>.params属性
• 指定请求中必须包含某些参数值，才会触发这个处理方法
例：@RequestMapping(value = "/login", params = "id=1") 
• 参数中除了使用=等号外，还可以使用!=号，表示在参数的值不等于的情况下触发这个方法
例：@RequestMapping(value = "/login", params = {"id=1","age!=18"}) 
• 也可以不指定具体的值，直接使用 "paramName" 的格式，代表请求中必须包含参数名为 paramName 的参数
• 直接使用 “!paramName”格式表示请求不能包含名为paramName的请求参数

6>.headers属性
• 请求头Header中必须包含某些指定的参数值，才能让该方法处理请求，可以利用这个特性拒绝非指定来源的客户端的访问，以加强站点的安全。
例：@RequestMapping(value = "/login", headers = "content-type=text/*")
例：@RequestMapping(value = "/login", headers = {"content-type=text/*","Referer=http://www.buyinplay.com"})
     * @return
     */
    @RequestMapping(value="/hello",method= {RequestMethod.GET,RequestMethod.POST}
    ,consumes={"application/json", "text/html"},produces= {"application/json", "text/html"},params= {"id=1","name=2"})
    public String sayHello() {
        return "Hello";
    }
    
    @Resource
    private UserService userService;
    
}
