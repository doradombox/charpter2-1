package priv.scor.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import priv.scor.entity.UserEntity;
import priv.scor.repository.UserRepository;
import priv.scor.services.UserService;

import java.util.List;

/**
 * @createBy Edgar
 * @date 2018/5/24
 * @Package_name priv.scor.services.impl
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRepository UserRepository;

    @Override
    public List<UserEntity> getUsers() {
        return UserRepository.findAll();
    }

    @Override
    public Page<UserEntity> getUserByPage(Pageable pageable) {
        return UserRepository.findAll(pageable);
    }

    @Override
    public UserEntity findByNameAndPwd(String userName, String password) {
        return userService.findByNameAndPwd(userName, password);
    }
}
