/*
 * @Copyright Nagarro Software Pvt. Ltd
 * 
 */
package com.nagarro.testrunner.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nagarro.testrunner.client.helper.HTTPMethodType;
import com.nagarro.testrunner.exception.ApplicationException;
import com.nagarro.testrunner.request.dto.ResponseDO;

/**
 * 
 * @author anujmehra
 *
 */
@Component("httpPutRestClient")
public class HTTPPutRestClientImpl implements HTTPRestClient<ResponseDO>{

	/**
	 * 
	 */
	@Value("${http.proxy.host}")
	private String HTTP_PROXY_HOST;

	/**
	 * 
	 */
	@Value("${http.proxy.port}")
	private int HTTP_PROXY_PORT;

	/**
	 * 
	 */
	@Value("${http.proxy.protocol}")
	private String HTTP_PROXY_PROTOCOL;
	
	
	/**
	 * 
	 * @param restURI String
	 * @param resourceURIPath String
	 * @param consumesMediaType MediaType
	 * @param returnsMediaType MediaType
	 * @param inputJSON String
	 * @param headerParameters Map<String,String>
	 * @return responseDO ResponseDO
	 * @throws ApplicationException
	 */
	@SuppressWarnings("deprecation")
	@Override
	public ResponseDO makeWSCall(String restURI
								, String resourceURIPath
								, MediaType consumesMediaType
								, MediaType returnsMediaType
								, String inputJSON
								,Map<String,String> headerParameters)  throws ApplicationException{

		CloseableHttpClient httpClient = null;
		HttpPut putRequest = null;
		HttpResponse response = null;


		final StringBuilder responseJson = new StringBuilder();
		final ResponseDO reponseDO  = new ResponseDO();

		try {

			//If we want to use proxy then code inside if-loop will get executed.
			if(null != this.HTTP_PROXY_HOST 
					&& this.HTTP_PROXY_HOST.trim().length() >0)
			{
				HttpHost proxy = new HttpHost(this.HTTP_PROXY_HOST, this.HTTP_PROXY_PORT, this.HTTP_PROXY_PROTOCOL);

				DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);

				httpClient = HttpClients.custom().setRoutePlanner(routePlanner).build();

			}else{
				httpClient = HttpClients.createDefault();
			}

			putRequest = new HttpPut(restURI + resourceURIPath);
			
			if(null != inputJSON){
				putRequest.setEntity(new StringEntity(inputJSON, "UTF-8"));
			}
			
			
			putRequest.addHeader("Accept", consumesMediaType.toString());
			putRequest.addHeader("Content-Type", "application/json");


			for(Map.Entry<String,String> entry:headerParameters.entrySet()){
				putRequest.addHeader(entry.getKey(),entry.getValue());
			}

			response = httpClient.execute(putRequest);
			reponseDO.setResponseCode(response.getStatusLine().getStatusCode());


			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent()), "UTF-8"));

			String output = null;
			while ((output = br.readLine()) != null) 
			{
				responseJson.append(output);
			}
			
			if(responseJson.toString().trim().length() >0){
				reponseDO.setJsonReponse(responseJson.toString());
			}else{
				reponseDO.setJsonReponse(null);
			}
			
		
			reponseDO.setHttpMethodType(HTTPMethodType.PUT);

		} catch (ClientProtocolException clientProtocolException) {

			throw new ApplicationException(" HTTPPutRestClientImpl :: makeWSCall :: MalformedURLException Occured : ", clientProtocolException);

		} catch (IOException ioException) {

			throw new ApplicationException(" HTTPPutRestClientImpl :: makeWSCall :: MalformedURLException Occured : ", ioException);
		}finally{
			httpClient.getConnectionManager().shutdown();
		}

		return reponseDO;

	}//end of method makeWSCall
	
}
