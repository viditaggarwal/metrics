package com.challenge.service;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.challenge.delegator.StatisticsDelegator;
import com.challenge.model.Statistics;
import com.challenge.model.Transaction;
import com.google.gson.Gson;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/")
public class StatisticsService {

	@GET
    @Path("statistics")
	public Response getStatistics() {
		return Response.ok(new Gson().toJson(Statistics.getInstance()), 
				MediaType.APPLICATION_JSON).build(); 
	}

	@POST
	@Path("transactions")
	@Consumes("application/json")
    public Response postTransaction(Transaction transaction) {
		Date date = new Date();
		if(date.getTime() - transaction.getTimestamp() <= StatisticsDelegator.TIME_LIMIT) {
			StatisticsDelegator.compute(transaction);
			return Response.status(Status.CREATED).build();
		}else {
    			return Response.status(Status.NO_CONTENT).build();
		}
    }
}
