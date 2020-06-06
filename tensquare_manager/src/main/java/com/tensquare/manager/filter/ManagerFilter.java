package com.tensquare.manager.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

@Component
public class ManagerFilter extends ZuulFilter {
    /**
     * 表示过滤器什么时候执行
     *
     * pre ：可以在请求被路由之前调用
     * route ：在路由请求时候被调用
     * post ：在route和error过滤器之后被调用
     * error ：处理请求时发生错误时被调用
     *
     * @return "pre"-表示之前运行，"post"-表示之后运行
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 表示优先级，数字越小表示优先级越高
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 表示是否开启过滤器
     * @return  true-开启，false-不开启
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 表示过滤内容
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {

        return null;
    }
}
