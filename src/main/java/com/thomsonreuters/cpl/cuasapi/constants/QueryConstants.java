package com.thomsonreuters.cpl.cuasapi.constants;

public interface QueryConstants {

	String ORG_ADMIN_QUERY = "select id, username, password, organization, name, email, master, compliance_manager, preference, "
			+ "coach, university_manager, employee_manager from organizationadministrator where organization=2787";
	
	String ORG_ADMIN_COUNT_QUERY = "select id, username, password, organization, name, email, master, compliance_manager, preference, "
			+ "coach, university_manager, employee_manager from organizationadministrator where organization=? and upper(email)=?";

	String ORG_FIRM_ADMIN_QUERY = "select id, username, password, organization, name, email, master, compliance_manager, preference, "
			+ "coach, university_manager, employee_manager from organizationadministrator where organization=?";
}
