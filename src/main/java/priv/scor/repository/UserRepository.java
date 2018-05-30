package priv.scor.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import priv.scor.entity.UserEntity;

import java.io.Serializable;

/**
 * @createBy huangwenbo
 * @date 2018/5/23
 * @Package_name priv.scor.repository
 */

public interface UserRepository extends
        JpaRepository<UserEntity, Long>,
        JpaSpecificationExecutor<UserEntity>,
        Serializable{

    
    public UserEntity findByNameAndPassword(String userName,String password);

}
