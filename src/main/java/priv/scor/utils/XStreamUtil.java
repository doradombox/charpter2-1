/**  
 * @Title:  XStreamUtil.java   
 * @Package priv.scor.utils   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: huangwenbo    
 * @date:   2018年5月30日 下午11:08:13   
 * @version V1.0 
 * @Copyright: 
 */
package priv.scor.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import priv.scor.entity.Address;
import priv.scor.entity.Person;

/**  
 * @Title:  XStreamUtil.java   
 * @Package priv.scor.utils   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: huangwenbo    
 * @date:   2018年5月30日 下午11:08:13   
 * @version V1.0 
 * @Copyright: 
 */
public class XStreamUtil {
    private static XStream xStream = new XStream();
    // JVM加载类时会执行这些静态的代码块，如果static代码块有多个，JVM将按照它们在类中出现的先后顺序依次执行它们，每个代码块只会被执行一次。
    static {
        XStream.setupDefaultSecurity(xStream);//设置安全级别
        xStream.allowTypes(new Class[] {Address.class,Person.class});//允许访问的类
        xStream = new XStream(new DomDriver());
        /*
         * 使用xStream.alias(String name, Class Type)为任何一个自定义类创建到类到元素的别名
         * 如果不使用别名，则生成的标签名为类全名
         */
        xStream.alias("person", Person.class);
        xStream.alias("address", Address.class);
        // 将某一个类的属性，作为xml头信息的属性，而不是子节点
        xStream.useAttributeFor(Address.class, "country");
        // 对属性取别名
        xStream.aliasField("省", Address.class, "province");
    }

    // xml转java对象
    public static Object xmlToBean(String xml) {
        return xStream.fromXML(xml);
    }

    // java对象转xml
    public static String beanToXml(Object obj) {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + xStream.toXML(obj);
    }

}
