//package io.haicheng.dashu.handle;
//
///**
// * <p>Title: ResultWarpReturnValueHandler</p>
// * <p>Description: </p>
// * ----------------------------
// *
// * @author haicheng
// * @date 2022/1/13 18:45
// */
//
//import io.haicheng.dashu.domain.dto.Result;
//import org.springframework.core.MethodParameter;
//import org.springframework.lang.Nullable;
//import org.springframework.web.context.request.NativeWebRequest;
//import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
//import org.springframework.web.method.support.ModelAndViewContainer;
//
///**
// * 统一处理ResponseBody数据格式
// *
// * @Author: Autumn、
// * @Date: 2019/4/24 23:59
// * @Description:
// **/
//public class ResultWarpReturnValueHandler implements HandlerMethodReturnValueHandler {
//
//    private final HandlerMethodReturnValueHandler delegate;
//
//    /**
//     * 委托
//     */
//    public ResultWarpReturnValueHandler(HandlerMethodReturnValueHandler delegate) {
//        this.delegate = delegate;
//    }
//
//    /**
//     * 判断返回类型是否需要转成字符串返回
//     *
//     * @param returnType 方法返回类型
//     * @return 需要转换返回true，否则返回false
//     */
//    @Override
//    public boolean supportsReturnType(MethodParameter returnType) {
//        return delegate.supportsReturnType(returnType);
//    }
//
//    /**
//     * 返回值转换
//     */
//    @Override
//    public void handleReturnValue(@Nullable Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
//        // 委托SpringMVC默认的RequestResponseBodyMethodProcessor进行序列化
//        delegate.handleReturnValue(returnValue instanceof Result ? returnValue : Result.succeed(returnValue), returnType, mavContainer, webRequest);
//    }
//}