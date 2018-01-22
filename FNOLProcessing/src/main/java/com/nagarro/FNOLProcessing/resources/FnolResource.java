package com.nagarro.FNOLProcessing.resources;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataParam;

import com.nagarro.FNOLProcessing.data.FNOLdata;
import com.nagarro.FNOLProcessing.data.MaintainSessions;
import com.nagarro.FNOLProcessing.data.SSN;
import com.nagarro.FNOLProcessing.data.SocialMediaData;
import com.nagarro.FNOLProcessing.data.Status;
import com.nagarro.FNOLProcessing.services.AppendToFile;
import com.nagarro.FNOLProcessing.services.FNOLDao;
import com.nagarro.FNOLProcessing.services.InputStreamToFileApp;
import com.nagarro.FNOLProcessing.services.ScoreCalculator;

@Path("FNOL")
public class FnolResource {
	
	@POST
	@Path("sentimentAnalysisScore")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public int sentiments(final SocialMediaData socialmediadata) {

		ScoreCalculator scorecalculator = new ScoreCalculator();
		return scorecalculator.sentimentsScore(socialmediadata.getText());
	}

	@POST
	@Path("creditScoreVariation")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public double creditScoreVariation(final SSN ssn) {

		ScoreCalculator scorecalculator = new ScoreCalculator();

		return scorecalculator.getTotalScore(Long.parseLong(ssn.getSsn()));
	}

	@POST
	@Path("addfnol")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public boolean addFnol(final FNOLdata fnolData) {
		boolean status = false;

		if (FNOLDao.getFnolDao().addFnolData(fnolData) ) {
			status = true;
		}
		return status;
	}

	@Path("fileupload")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addFile(@FormDataParam("file") InputStream file) {
		Response response = null;
		
		InputStreamToFileApp.getInstance().convert(file,++MaintainSessions.id);
		System.out.println(response);
		System.out.println("File adding:::::" + file);

		return response;
	}
//	@Path("fileupload")
//	@POST
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response addFile(@Context HttpServletRequest request) {
//		Response response = null;
//		InputStreamToFileApp filedata = new InputStreamToFileApp();
//		try {
//			for (Part part : request.getParts()){
//				filedata.convert(part.getInputStream());
//				System.out.println(response);
//				System.out.println("File adding:::::" + part.getName());
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ServletException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//
//		return response;
//	}

	@Path("addSocialMediaData")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addSocialMediaStatus(final SocialMediaData data) {
		Response response = null;
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
		data.setDate(df.format(date));
		//data.setTime(new SimpleDateFormat("HH").format(Calendar.getInstance().getTime()));
		String text = System.lineSeparator() + data.getDate() + "|" + data.getTime() + " hrs|" + data.getEmail() + "|"
				+ data.getText() + " ..";
		AppendToFile appendText=new AppendToFile();
		appendText.addSocialMediaStatus(text);
		
		return Response.ok().entity(data).build();
	}
	@POST
	@Path("status")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public int status(final Status status) {

		if(MaintainSessions.session!=null){
		try {
			MaintainSessions.session.getBasicRemote().sendText(status.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return 0;
	}
	
	@POST
	@Path("fnols")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFnols(String sDate){

		
		Date date = null;
		if(sDate!=null && sDate!=""){
			try {
				date = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Response response;

		List<FNOLdata> fnols = FNOLDao.getFnolDao().getFnolsByDate(date);

		response = Response.ok().entity(fnols.toArray(new FNOLdata[fnols.size()])).build();

		return response;
	}
	
	@GET
	@Path("fnols")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllFnols(){

		
		
		Response response;

		List<FNOLdata> fnols = FNOLDao.getFnolDao().getFnols();

		response = Response.ok().entity(fnols.toArray(new FNOLdata[fnols.size()])).build();

		return response;
	}
	@POST
	@Path("update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public boolean updateFnol(final FNOLdata fnolData) {
		return FNOLDao.getFnolDao().updateFnol(fnolData);
	}
	
	@PUT
	@Path("update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateFnolPutRequest(final FNOLdata fnolData) {
		Response response=null;
		if( FNOLDao.getFnolDao().updateFnol(fnolData)){
			response= Response.ok().build();
		}
		return response;
	}

}
