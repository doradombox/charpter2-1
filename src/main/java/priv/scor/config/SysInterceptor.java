//package priv.scor.config;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//import priv.scor.common.exception.AppException;
//import priv.scor.common.exception.Code;
//import priv.scor.entity.UserEntity;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * @createBy Edgar
// * @date 2018/5/29
// * @Package_name priv.scor.config
// */
//@Component
//public class SysInterceptor extends HandlerInterceptorAdapter{
//
//    public static final Logger logger = LoggerFactory.getLogger(SysInterceptor.class);
//
//    public static final String SESSION_ATTR_USER = "user";
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
//                             Object handle)throws Exception{
//        if(handle instanceof HandlerMethod){
//            UserEntity userEntity = (UserEntity) request.getSession().getAttribute(SESSION_ATTR_USER);
//            if(null == userEntity){
//                throw new AppException(Code.NOT_LOGIN,Code.NOT_LOGIN.getText());
//            }
//        }
//        return true;
//    }
//}
//
