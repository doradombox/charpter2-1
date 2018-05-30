package priv.scor.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import priv.scor.entity.UserEntity;

import java.util.List;

/**
 * @createBy Edgar
 * @date 2018/5/24
 * @Package_name priv.scor.services
 */
public interface UserService {

    public List<UserEntity> getUsers();

    public Page<UserEntity> getUserByPage(Pageable pageable);

    public UserEntity findByNameAndPwd(String userName,String password);
}
