package priv.scor.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import priv.scor.utils.DatetimeUtilies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * @createBy Edgar
 * @date 2018/5/30
 * @Package_name priv.scor.config
 */
//@Configuration
//public class Mvc extends WebMvcConfigurerAdapter {
//    @Autowired
//    private SysInterceptor sysInterceptor;
//
////    @Override
////    public void addInterceptors(InterceptorRegistry registry) {
////        registry.addInterceptor(sysInterceptor)
////                .addPathPatterns(
////                        new String[] {
////                                "/user/**",
////                                "/test/**"
////                        });
////    }
//}
