package com.thomsonreuters.cpl.cuasapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thomsonreuters.cpl.cuasapi.beans.OrganizationAdmin;
import com.thomsonreuters.cpl.cuasapi.repositories.CuasApiRepository;

@Service
public class CuasApiService {

	@Autowired
	CuasApiRepository cuasRepository;
	
	public int getAdminCount() {
		int count = cuasRepository.getAdminCount();
		System.out.println("count in service: "+count);
		return count;
	}
	
	public int getFirmAdminCount(int orgId, String email) {
		int count = cuasRepository.getFirmAdminCount(orgId, email);
		System.out.println("count in service: "+count);
		return count;
	}

	public OrganizationAdmin getFirmAdmin(int orgId, String email) {
		return cuasRepository.getFirmAdmin(orgId, email);
	}

	public List<OrganizationAdmin> getFirmAdmins(int orgId) {
		return cuasRepository.getFirmAdmins(orgId);
	}
	
}
