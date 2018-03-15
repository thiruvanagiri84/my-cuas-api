package com.thomsonreuters.cpl.cuasapi.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thomsonreuters.cpl.cuasapi.beans.OrgIdInput;
import com.thomsonreuters.cpl.cuasapi.beans.OrganizationAdmin;
import com.thomsonreuters.cpl.cuasapi.beans.OrganizationAdminOutput;
import com.thomsonreuters.cpl.cuasapi.beans.OrganizationInput;
import com.thomsonreuters.cpl.cuasapi.exceptions.CuasApiApplicationException;
import com.thomsonreuters.cpl.cuasapi.exceptions.InvalidInputException;
import com.thomsonreuters.cpl.cuasapi.services.CuasApiService;


@RestController
public class CuasApiController {

	private static final Logger logger = LoggerFactory.getLogger(CuasApiController.class);
	
	@Autowired
	CuasApiService cuasService;
	
	@RequestMapping(value="/admincount",method=RequestMethod.GET)
	//Request would be like this: http://localhost:8070/admincount?orgId=2787&email=prodtestfirm3@tr.com
	public int getCountOfAdmins(@RequestParam int orgId, @RequestParam String email) {
		
		System.out.println("orgid: "+orgId+"  email: "+email);
		logger.info("Logback log.... orgid: "+orgId+"  email: "+email);
		logger.error("Logback log.... orgid: "+orgId+"  email: "+email);
		System.out.println("after logging");
		
		int adminCount = cuasService.getAdminCount();
		return adminCount;
	}
	
	@RequestMapping(value="/admincount/{orgId}/{email}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	//Request would be like this: http://localhost:8070/admincount/2787/prodtestfirm3@tr.com
	public int getCountOfFirmAdmins(@PathVariable int orgId, @PathVariable String email) {
		System.out.println("orgid: "+orgId+"  email: "+email);
		int adminCount = cuasService.getFirmAdminCount(orgId,email);
		return adminCount;
	}
	
	
	//Request would be like this: http://localhost:8070/admincount with json input like below:
	/*{
		"orgId":2787,
		"email":"prodtestfirm3@tr.com"
	}*/
	@RequestMapping(value="/admincount",consumes=MediaType.APPLICATION_JSON_VALUE,method=RequestMethod.POST)
	public int getCountOfFirmAdmins2(@RequestBody OrganizationInput orgInput) {
		System.out.println("orgid: "+orgInput.getOrgId()+"  email: "+orgInput.getEmail());
		int adminCount = cuasService.getFirmAdminCount(orgInput.getOrgId(),orgInput.getEmail());
		return adminCount;
	}
	
	//Fetch particular FA with input as json(OrganizationInput)
	@RequestMapping(value="/admin",consumes=MediaType.APPLICATION_JSON_VALUE,method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public OrganizationAdmin getFirmAdmin(@RequestBody OrganizationInput orgInput) {
		System.out.println("orgid: "+orgInput.getOrgId()+"  email: "+orgInput.getEmail());
		OrganizationAdmin firmAdmin = cuasService.getFirmAdmin(orgInput.getOrgId(),orgInput.getEmail());
		return firmAdmin;
	}
	
	//Fetch particular FA with input path variables
	@RequestMapping(value="/admin/{orgId}/{email}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public OrganizationAdmin getOrgAdmin(@PathVariable int orgId, @PathVariable String email) {
		System.out.println("orgid: "+orgId+"  email: "+email);
		OrganizationAdmin firmAdmin = cuasService.getFirmAdmin(orgId,email);
		return firmAdmin;
	}
	
	//Fetching List of FA's with input as json(OrgIdInput).
	@RequestMapping(value="/admins",consumes=MediaType.APPLICATION_JSON_VALUE,method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<OrganizationAdmin> getFirmAdmins(@RequestBody OrgIdInput orgIdJson) {
		System.out.println("orgid: "+orgIdJson.getOrgId());
		List<OrganizationAdmin> firmAdminList = cuasService.getFirmAdmins(orgIdJson.getOrgId());
		return firmAdminList;
	}
	
	//Fetching List of FA's with input as pathvariable 
	@RequestMapping(value="/admins/{organizationId}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE )
	public List<OrganizationAdmin> getFirmAdmins2(@PathVariable int organizationId) {
		logger.info("orgid: "+organizationId);
		List<OrganizationAdmin> firmAdminList = cuasService.getFirmAdmins(organizationId);
		return firmAdminList;
	}
	
	//Fetching List of FA's with input as pathvariable. This will return json object containing key value pairs. 
	@RequestMapping(value="/firmAdmins/{organizationId}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE )
	public OrganizationAdminOutput getAdmins(@PathVariable int organizationId) throws Exception {
		OrganizationAdminOutput output = null;
		logger.info("orgid: "+organizationId);
		
		if (!(organizationId > 0)) {
			logger.error("Input Organization Id should be greater than 0: "+organizationId);
			throw new InvalidInputException("Input Organization Id should be greater than 0");
		}
		
		/*String x = null;
		if(x==null) {
			throw new CuasApiApplicationException(" orey jaffa");
		}
		int[] y = {1,2};
		System.out.println(y[4]);
		//System.out.println(x.toString());
*/		
		List<OrganizationAdmin> firmAdminList = cuasService.getFirmAdmins(organizationId);
		logger.info("No.Of Firm Admins retreived: "+firmAdminList);
		output = new OrganizationAdminOutput(firmAdminList);
		return output;
	}
	
}
