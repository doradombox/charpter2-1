package priv.scor.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.spi.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.jdbc.log.Log;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import priv.scor.common.Result;
import priv.scor.common.exception.AppException;
import priv.scor.common.exception.Code;
import priv.scor.entity.UserEntity;
import priv.scor.repository.UserRepository;

@Api(value="/login",tags = "用户登录接口")
@RestController
public class LoginController extends BaseController{
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(LoginController.class);
    
    @Autowired
    private UserRepository userRepository;
    
    @ApiOperation(value="登录")
    @PostMapping(value = "/login")
    public Result<UserEntity> loginByNameAndPassword(HttpServletRequest request, String userName, String password){
        HttpSession session = request.getSession();
        log.info(session.getServletContext().getRealPath("/"));
        UserEntity userEntity = userRepository.findByNameAndPassword(userName,password);
        if(null != userEntity) {
            session.setAttribute("user",userEntity);
        }else {
            throw new AppException("用户名或密码错误");
        }
        Result<UserEntity> result = new Result<>();
        result.setData(userEntity);
        return result;
    }
    
    @ApiOperation(value="退出登录")
    @GetMapping(value="/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        session.invalidate();
        return Code.SUCCESS.getText();
    }

}
