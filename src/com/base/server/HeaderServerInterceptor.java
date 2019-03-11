package com.base.server;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCall.Listener;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;

public class HeaderServerInterceptor implements ServerInterceptor {

	@Override
	public <ReqT, RespT> Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> serverCall, Metadata meta,
			ServerCallHandler<ReqT, RespT> nextCallHandler) {
		if (serverCall.getMethodDescriptor().getFullMethodName().equalsIgnoreCase("EmployeeService/GetByBadgeNumber")) {
			for (String key : meta.keys()) {
				System.out.println(key + " : " + Metadata.Key.of(key, Metadata.ASCII_STRING_MARSHALLER));
			}
		}
		return nextCallHandler.startCall(serverCall, meta);
	}

}
