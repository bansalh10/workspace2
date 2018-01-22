package com.nagarro.RetailManagement.resources;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.media.multipart.FormDataParam;

import com.nagarro.RetailManagement.data.Bansal;
import com.nagarro.RetailManagement.data.Image;
import com.nagarro.RetailManagement.data.Product;
import com.nagarro.RetailManagement.services.InputStreamToFileApp;
import com.nagarro.RetailManagement.services.ProductDao;

/**
 * Root resource (exposed at "employees" path)
 */
@Path("products")
public class ProductResource {

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to
	 * the client as "json" media type.
	 *
	 * @return Response that will be returned as a json response.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProducts() {
		// System.out.println(userId + password);
		Response response;

		List<Product> products = ProductDao.getOperation().getProducts();

		response = Response.ok().entity(products.toArray(new Product[products.size()])).build();

		return response;
	}

	@Path("offers")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOffers() {
		// System.out.println(userId + password);
		Response response;
		List<Product> products = ProductDao.getOperation().getProductsByOffer();
		response = Response.ok().entity(products.toArray(new Product[products.size()])).build();
		return response;
	}
	@Path("x")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String check(Bansal bansal) {
		
		
		//System.out.println(product);
		System.out.println("swdf");
		
		//return Response.ok().entity(bansal).build();
		return bansal.getBansal();
	}

	@Path("product")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addProduct(Product product) {
		Response response;
		boolean added = ProductDao.getOperation().addEmployee(product);
		if (added) {
			response = Response.ok().build();
		} else {
			response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return response;
	}

	@Path("image")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addImage(@FormDataParam("images") InputStream file)
			 {
		Response response = null;
		boolean added = ProductDao.getOperation().saveImg(file);
		 if(file != null){
		 response = Response.ok().build();
		 }
		System.out.println(response);
		System.out.println("File adding:::::" + file);

		return response;
	}
	
	@GET
	@Path("image/{Id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getImgById(@PathParam("Id") int id) {
		// System.out.println("fdass");
		Response response;
		if (id <= 0) {
			response = Response.status(Status.BAD_REQUEST).build();
		} else {
			Blob img = ProductDao.getOperation().getImg(id);
			Image image=new Image();
			 byte[] ba = null;
			try {
				ba = img.getBytes(1, (int)img.length());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
             // b.length() throws exception, no message, no cause
             byte[] img64 = Base64.getEncoder().encode(ba);
             String photo64 = new String(img64);
             System.out.println(photo64);
             System.out.println("success");
			image.setImg(photo64);
			
				response = Response.ok().entity(image).build();
			
		}
		return response;
	}
//	@Path("image")
//	@POST
//	@Consumes(MediaType.MULTIPART_FORM_DATA)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response addImage(FormDataMultiPart formData)
//			 {
//		Response response = null;
//		 FormDataBodyPart filePart = formData.getField("images");
//	        ContentDisposition fileHeader = filePart.getContentDisposition();
//	        
//	        String fileName = fileHeader.getFileName();
//		System.out.println(response);
//		System.out.println("File adding:::::" + fileName);
//
//		return response;
//	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("search/{name}")
	public Response getProductsByName(@PathParam("name") String name) {
		// System.out.println("fdass");
		Response response;
		if (name == null) {
			response = Response.status(Status.BAD_REQUEST).build();
		} else {
			List<Product> products = ProductDao.getOperation().getProductsByName(name);
			response = Response.ok().entity(products.toArray(new Product[products.size()])).build();
		}
		return response;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{Id}")
	public Response getEmployeeById(@PathParam("Id") int id) {
		// System.out.println("fdass");
		Response response;
		if (id <= 0) {
			response = Response.status(Status.BAD_REQUEST).build();
		} else {
			Product product = ProductDao.getOperation().getProductById(id);
			if (product == null) {
				response = Response.status(Status.NOT_FOUND).build();
			} else {
				response = Response.ok().entity(product).build();
			}
		}
		return response;
	}

	@POST
	@Path("update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateEmployee(Product product) {
		Response response;
		boolean updated = ProductDao.getOperation().updateProduct(product);
		if (updated) {
			response = Response.ok().build();
		} else {
			response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		// redirect to second page
		return response;

	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{Id}")
	public Response deleteProduct(@PathParam("Id") int id) {
		Response response;
		boolean deleted = ProductDao.getOperation().deleteProduct(id);
		if (deleted) {
			response = Response.ok().build();
		} else {
			response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return response;
	}

}
