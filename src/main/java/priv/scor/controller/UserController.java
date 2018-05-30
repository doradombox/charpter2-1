package priv.scor.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import priv.scor.common.exception.AppException;
import priv.scor.common.exception.Code;
import priv.scor.entity.UserEntity;
import priv.scor.repository.UserRepository;
import priv.scor.services.UserService;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * @createBy huangwenbo
 * @date 2018/5/23
 * @Package_name priv.scor.controller
 */
@Api(value="user",tags = "用户接口相关")
@RestController
public class UserController {
    public static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;


    @ApiOperation(value = "获取用户列表，不分页")
    @RequestMapping(value="/user/list",method= RequestMethod.GET)
    public List<UserEntity> list(){
        return userRepository.findAll();
    }

    @ApiOperation(value = "获取用户列表，分页查询")
    @GetMapping(value = "/user/listByPage")
    public Page<UserEntity> listByPageable(@RequestParam(value = "page") Integer page,@RequestParam(value = "size") Integer size){
        Pageable pageable = new PageRequest(page,size);
        List<UserEntity> userEntitys = userRepository.findAll(pageable).getContent();
        if(true) {
            throw new AppException(Code.NOT_LOGIN, Code.NOT_LOGIN.getText());
        }
        return userService.getUserByPage(pageable);
    }


    @ApiOperation(value="保存用户")
    @PostMapping(value = "/user/save")
    public UserEntity save(UserEntity userEntity){
        return userRepository.save(userEntity);
    }

    @ApiOperation(value="删除用户")
    @DeleteMapping(value="/user/delete")
    public void delete(Long id){
         userRepository.deleteById(id);
    }

}
