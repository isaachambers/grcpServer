package com.base.server;

import com.base.services.EmployeeServiceGrpc;
import com.base.services.Employees;
import com.base.services.Messages;
import com.base.services.Messages.EmployeeResponse;
import com.base.services.Messages.GetAllRequest;
import com.base.services.Messages.GetByBadgeNumberRequest;

import io.grpc.stub.StreamObserver;

public class EmployeeService extends EmployeeServiceGrpc.EmployeeServiceImplBase {

	// UNARY CALL
	@Override
	public void getBadgebyNumber(GetByBadgeNumberRequest request, StreamObserver<EmployeeResponse> responseObserver) {
		// Messages and data from clients is retrieved from request as described in
		for (Messages.Employee employee : Employees.getInstance()) {
			if (employee.getBadgeNumber() == request.getBadgeNumber()) {
				Messages.EmployeeResponse res = Messages.EmployeeResponse.newBuilder().setEmployee(employee).build();
				// Send the response via the response observer
				responseObserver.onNext(res);
				// call the onCompleted method on the observer to notify a successful stream of
				// a message
				responseObserver.onCompleted();
				return;
			}
		}
		// For Known Errors, You can call the error method on the response observer. In
		// this case If there are no employees found on the server.
		responseObserver.onError(new Error("No Employee found with Badge Number : " + request.getBadgeNumber()));
	}

	// Server STREAM
	@Override
	public void getAll(GetAllRequest request, StreamObserver<EmployeeResponse> responseObserver) {

		for (Messages.Employee employee : Employees.getInstance()) {
			Messages.EmployeeResponse res = Messages.EmployeeResponse.newBuilder().setEmployee(employee).build();
			// For a stream you send one object at a time until all data has been sent
			// Successfully;
			responseObserver.onNext(res);
		}
		// After all the data has been sent, it is mandatory to call the onCompleted
		// method. It finalizes the request.
		responseObserver.onCompleted();

	}

}
