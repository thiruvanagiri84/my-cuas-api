package com.thomsonreuters.cpl.cuasapi.repositories;

import com.thomsonreuters.cpl.cuasapi.beans.OrganizationAdmin;
import com.thomsonreuters.cpl.cuasapi.constants.QueryConstants;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CuasApiRepository {
	
	/*@Autowired
	private JdbcTemplate jdbcTemplate;*/
	
	private JdbcTemplate jdbcTemplate;
	
	public CuasApiRepository(@Autowired JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}

	@Transactional(readOnly = true)
	public int getAdminCount() {
		
		List<OrganizationAdmin> query = jdbcTemplate.query(QueryConstants.ORG_ADMIN_QUERY, new OrganizationAdminRowMapper());
		System.out.println("count in repository: "+query.size());
		return query.size();
		
	}
	
	@Transactional(readOnly = true)
	public int getFirmAdminCount(int orgId, String email) {
		
		List<OrganizationAdmin> query = jdbcTemplate.query(QueryConstants.ORG_ADMIN_COUNT_QUERY,new Object[] { orgId,email },
										new OrganizationAdminRowMapper());
		System.out.println("count in repository: "+query.size());
		return query.size();
		
	}

	public OrganizationAdmin getFirmAdmin(int orgId, String email) {
		
		OrganizationAdmin firmAdmin = jdbcTemplate.queryForObject(
				QueryConstants.ORG_ADMIN_COUNT_QUERY, new Object[] { orgId,email }, new OrganizationAdminRowMapper());
		
		return firmAdmin;
	}

	public List<OrganizationAdmin> getFirmAdmins(int orgId) {
		
		List<OrganizationAdmin> adminList = jdbcTemplate.query(QueryConstants.ORG_FIRM_ADMIN_QUERY,new Object[] { orgId },
				new OrganizationAdminRowMapper());
		return adminList;
	}
	
}

class OrganizationAdminRowMapper implements RowMapper<OrganizationAdmin> {

	@Override
	public OrganizationAdmin mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		OrganizationAdmin orgAdmin = new OrganizationAdmin();
		orgAdmin.setId(rs.getInt("id"));
		orgAdmin.setUsername(rs.getString("username"));
		orgAdmin.setPassword(rs.getString("password"));
		orgAdmin.setOrganization(rs.getInt("organization"));
		orgAdmin.setName(rs.getString("name"));
		orgAdmin.setEmail(rs.getString("email"));
		orgAdmin.setMaster(rs.getInt("master"));
		orgAdmin.setCompliancemanager(rs.getInt("compliance_manager"));
		orgAdmin.setPreference(rs.getString("preference"));
		orgAdmin.setCoach(rs.getInt("coach"));
		orgAdmin.setUniversitymanager(rs.getInt("university_manager"));
		orgAdmin.setEmployeemanager(rs.getInt("employee_manager"));
				
		return orgAdmin;
	}

}
