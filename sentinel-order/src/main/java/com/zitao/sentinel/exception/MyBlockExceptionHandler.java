package com.zitao.sentinel.exception;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zitao.sentinel.common.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 使用sentinel进行服务流量控制时，服务出现异常后的统一异常处理器
 */
@Component
public class MyBlockExceptionHandler implements BlockExceptionHandler {
    Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * @param httpServletRequest
     * @param httpServletResponse
     * @param e                   根据此异常类型即可判断出属于什么规则下的异常
     * @throws Exception
     */
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws Exception {
        // 打印日志
        log.info("BlockExceptionHandler BlockException ================= {}", e.getRule());
        CommonResult<String> commonResult = new CommonResult<>();
        if (e instanceof FlowException) {
            commonResult = CommonResult.error("接口限流", 100);
        } else if (e instanceof DegradeException) {
            commonResult = CommonResult.error("服务降级", 101);
        } else if (e instanceof ParamFlowException) {
            commonResult = CommonResult.error("热点参数限流", 103);
        } else if (e instanceof SystemBlockException) {
            commonResult = CommonResult.error("触发系统保护规则", 104);
        } else if (e instanceof AuthorityException) {
            commonResult = CommonResult.error("授权校验不通过", 105);
        }

        // 返回json数据
        httpServletResponse.setStatus(500);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(httpServletResponse.getWriter(), commonResult);
    }
}
