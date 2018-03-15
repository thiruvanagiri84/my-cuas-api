package com.thomsonreuters.cpl.cuasapi.beans;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class OrganizationAdmin {

	int id;
	String username;
	String password;
	int organization;
	String name;
	String email;
	int master;
	int compliancemanager;
	String preference;
	int coach;
	int universitymanager;
	int employeemanager;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getOrganization() {
		return organization;
	}
	public void setOrganization(int organization) {
		this.organization = organization;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getMaster() {
		return master;
	}
	public void setMaster(int master) {
		this.master = master;
	}
	public int getCompliancemanager() {
		return compliancemanager;
	}
	public void setCompliancemanager(int compliancemanager) {
		this.compliancemanager = compliancemanager;
	}
	public String getPreference() {
		return preference;
	}
	public void setPreference(String preference) {
		this.preference = preference;
	}
	public int getCoach() {
		return coach;
	}
	public void setCoach(int coach) {
		this.coach = coach;
	}
	public int getUniversitymanager() {
		return universitymanager;
	}
	public void setUniversitymanager(int universitymanager) {
		this.universitymanager = universitymanager;
	}
	public int getEmployeemanager() {
		return employeemanager;
	}
	public void setEmployeemanager(int employeemanager) {
		this.employeemanager = employeemanager;
	}
	
}
