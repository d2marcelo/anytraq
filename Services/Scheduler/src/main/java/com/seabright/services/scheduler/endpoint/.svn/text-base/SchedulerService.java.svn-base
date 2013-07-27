package com.seabright.services.scheduler.endpoint;

import java.text.ParseException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.quartz.SchedulerException;

import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.logging.MyLogger;


@Path("rest")
public class SchedulerService{

	private MyLogger logger = new MyLogger(SchedulerService.class);
	
	@GET
	@Path("listJobs")
	@Produces(MediaType.APPLICATION_JSON)
	public String listJobs () {
		logger.logInfo("Listing Cats Jobs");
		return "";
	}
	
	@POST
	@Path("scheduleJob")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String scheduleJob(String payload) {
		return "";
	}
	
	@GET
	@Path("isJobScheduled") 
	@Produces(MediaType.APPLICATION_JSON)
	public String isJobScheduled (@QueryParam("jobId") String jobId) {
		return "";
	}
	
	@GET
	@Path("unscheduleJob") 
	@Produces(MediaType.APPLICATION_JSON)
	public String unscheduleJob(@QueryParam("scheduleName") String scheduleName, @QueryParam("userId") String userId) {
		return "";
	}
	
	@GET
	@Path("suspendJobs")
	@Produces(MediaType.APPLICATION_JSON)
	public String suspendJob() {
		return "";
	}
	
	@GET
	@Path("resumeJobs")
	@Produces(MediaType.APPLICATION_JSON)
	public String resumeJob() {
		return "";
	}
	
	@POST
	@Path("updateJob")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String udpateJob(String payload) {
		return "";
			
	}
}
