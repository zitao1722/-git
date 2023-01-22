package com.zitao.dubboprovider.service.impl;


import com.zitao.dubbocommon.service.UserService;
import org.apache.dubbo.common.stream.StreamObserver;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@DubboService(group ="userServiceImpl2", version = "2.0.0", interfaceClass = UserService.class)
public class UserServiceImpl2 implements UserService {

    @Override
    public String getUser(Integer userId) {
        return "userId:" + userId.toString();
    }

    // 使用客户端流CLIENT_STREAM
    @Override
    public StreamObserver<String> sayHelloStream(StreamObserver<String> response) {
        return new StreamObserver<String>() {
            @Override
            public void onNext(String data) {
                System.out.println("服务端接收到的数据：" + data);

                // 处理data，进行业务操作

                response.onNext("响应结果：" + data);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                System.out.println("发送完成");
            }
        };
    }
}
