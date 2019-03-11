package com.base.server;

import java.io.File;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptors;
import io.grpc.ServerServiceDefinition;

public class EmployeeServiceServer {

	private Server server;

	public static void main(String[] args) {
		try {
			EmployeeServiceServer service = new EmployeeServiceServer();
			service.start();
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	private void start() throws InterruptedException {
		File certificate = new File("/Users/isaack/certificates/cert.pem");
		File key = new File("/Users/isaack/certificates/key.pem");
		final int port = 9000;
		// Create the Employee Service
		EmployeeService employeeService = new EmployeeService();
		// Use Server Defination to wrap the interceptor and the service.

		ServerServiceDefinition serverServiceDefinition = ServerInterceptors.interceptForward(employeeService,
				new HeaderServerInterceptor());
		server = ServerBuilder.forPort(port).useTransportSecurity(certificate, key).addService(serverServiceDefinition)
				.build();

		System.out.println("Listening on Port " + port);
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				System.out.println("Shuttin Down Server");
				EmployeeServiceServer.this.stop();
			}

		});

		server.awaitTermination();

	}

	private void stop() {
		if (server != null) {
			server.isShutdown();
		}
	}

}
