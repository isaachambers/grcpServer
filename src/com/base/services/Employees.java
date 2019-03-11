package com.base.services;

import java.util.ArrayList;

public class Employees extends ArrayList<Messages.Employee> {
	private static final long serialVersionUID = 1L;
	public static Employees employees;

	public static Employees getInstance() {
		if (employees == null) {
			employees = new Employees();
		}
		return employees;
	}

	private Employees() {

		this.add(Messages.Employee.newBuilder().setId(1).setBadgeNumber(2080).setFirstName("James")
				.setLastName("Cormey").setVaccationAccrualRate(2).setVaccationAccrued(30).build());

		this.add(Messages.Employee.newBuilder().setId(1).setBadgeNumber(2080).setFirstName("Amity")
				.setLastName("Fuller").setVaccationAccrualRate(2).setVaccationAccrued(30).build());

		this.add(Messages.Employee.newBuilder().setId(1).setBadgeNumber(2080).setFirstName("Ben").setLastName("Cormey")
				.setVaccationAccrualRate(2).setVaccationAccrued(30).build());
	}

}
