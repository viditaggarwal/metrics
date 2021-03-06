package com.challenge.service;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.ValidationException;

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
    public Response postTransaction(Transaction transaction)  {
		try {
			if(transaction == null) throw new ValidationException("NullPointerException", "10001");
			if(transaction.getTimestamp() == 0l) throw new ValidationException("TimeStamp should not be null", "10002");
			Date date = new Date();
			if(date.getTime() - transaction.getTimestamp() <= StatisticsDelegator.TIME_LIMIT) {
				StatisticsDelegator.compute(transaction);
				return Response.status(Status.CREATED).build();
			}else {
	    			return Response.status(Status.NO_CONTENT).build();
			}
		}catch (ValidationException e) {
			throw new WebApplicationException(Response.status(Status.BAD_REQUEST)// Or another Status
	                .entity(e.getMessage()).build());
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).build();
		}
    }
}
