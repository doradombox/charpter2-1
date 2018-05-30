package priv.scor.entity;

/**
 * 
 * @Title:  Address.java   
 * @Package priv.scor.entity   
 * @Description:    xml测试类
 * @author: huangwenbo    
 * @date:   2018年5月30日 下午11:03:54   
 * @version V1.0 
 * @Copyright:
 */
public class Address {
    // 国
    private String country;
    // 省
    private String province;
    // 市
    private String city;
    // 县
    private String county;
    // 镇
    private String town;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
