package priv.scor.entity;

import java.util.List;

/**
 * 
 * @Title:  Person.java   
 * @Package priv.scor.entity   
 * @Description:  xml测试类
 * @author: huangwenbo    
 * @date:   2018年5月30日 下午11:03:03   
 * @version V1.0 
 * @Copyright:
 */
public class Person {
    //姓名  
    private String name;  
    //性别  
    private String sex;  
    //年龄  
    private int age;  
    //地址
    private List<Address> Address;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public List<Address> getAddress() {
        return Address;
    }
    public void setAddress(List<Address> address) {
        Address = address;
    }
}
