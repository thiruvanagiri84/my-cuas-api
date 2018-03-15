package com.thomsonreuters.cpl.cuasapi.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class OrganizationAdminOutput {

	private List<OrganizationAdmin> orgAdmins;
	
	public OrganizationAdminOutput(List<OrganizationAdmin> admins) {
		this.orgAdmins = admins;
	}
	
	public OrganizationAdminOutput() {
		
	}

	public List<OrganizationAdmin> getOrgAdmins() {
		return orgAdmins;
	}

	public void setOrgAdmins(List<OrganizationAdmin> orgAdmins) {
		this.orgAdmins = orgAdmins;
	}
	
	
	
}
