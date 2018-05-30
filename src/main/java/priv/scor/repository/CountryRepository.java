package priv.scor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import priv.scor.entity.Country;

import java.io.Serializable;

/**
 * @createBy Edgar
 * @date 2018/5/30
 * @Package_name priv.scor.repository
 */
public interface CountryRepository extends JpaRepository<Country,Long>,JpaSpecificationExecutor<Country>,
        Serializable {
    public Country findCountry(String name);
}
