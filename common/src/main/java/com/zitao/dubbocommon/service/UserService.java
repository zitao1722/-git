package com.zitao.dubbocommon.service;

import org.apache.dubbo.common.stream.StreamObserver;

public interface UserService {
    // UNARY，普通的方式
    String getUser(Integer userId);

    // SERVER_STREAM，服务端流式调用方式
    default void sayHelloServerStream(String name, StreamObserver<String> response){

    }

    // CLIENT_STREAM，客户端流方式
    default StreamObserver<String> sayHelloStream(StreamObserver<String> response) {
        return response;
    }
}
