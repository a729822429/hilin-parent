package icu.hilin.web.filter;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;

public interface IHilinFilter {

    /**
     * 排序，数字越小优先级越高
     */
    int sort();

    /**
     * 执行过滤
     *
     * @return 是否继续执行，如果返回false，则不继续执行后续filter
     */
    boolean doFilter(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException, ServletException;


}
