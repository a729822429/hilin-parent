package icu.hilin.web.filter;

import cn.hutool.core.util.ObjectUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@WebFilter(urlPatterns = "/*")
@Order(1)
public class HilinFilter implements Filter {

    private final ApplicationContext context;

    private List<IHilinFilter> filterList;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        List<IHilinFilter> filterList = getFilterList();
        boolean isContinue = true;
        if (ObjectUtil.isNotEmpty(filterList)) {
            for (IHilinFilter iHilinFilter : filterList) {
                if (isContinue) {
                    isContinue = iHilinFilter.doFilter(servletRequest, servletResponse);
                } else {
                    break;
                }
            }
        }
        if (isContinue) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    private synchronized List<IHilinFilter> getFilterList() {
        if (filterList == null) {
            Map<String, IHilinFilter> filters = context.getBeansOfType(IHilinFilter.class);
            if (ObjectUtil.isNotEmpty(filters)) {
                filterList = filters.values().stream().sorted((Comparator.comparingInt(IHilinFilter::sort))).toList();
            } else {
                filterList = new ArrayList<>();
            }
        }
        return filterList;
    }

}
