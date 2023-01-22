package com.zitao.dubboprovider.service.impl;


import com.zitao.dubbocommon.service.UserService;
import org.apache.dubbo.common.stream.StreamObserver;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@DubboService(group ="userServiceImpl1", version = "1.0.0")
public class UserServiceImpl implements UserService {

    // UNARY 普通方式
    @Override
    public String getUser(Integer userId) {
        return "userId:" + userId.toString();
    }

    // SERVER_STREAM，服务端流式调用方式
    @Override
    public void sayHelloServerStream(String name, StreamObserver<String> response) {
        // 传入name，接下来进行你要进行的业务处理操作

        // 操作结束后，将要返回的结果传入response中
        response.onNext("hello:" + name);

        // 与上面的普通方式不同的是，使用流式调用可以响应多次，进行多次处理，分批次响应
        response.onNext("hello:" + name);

        response.onCompleted();
    }
}
