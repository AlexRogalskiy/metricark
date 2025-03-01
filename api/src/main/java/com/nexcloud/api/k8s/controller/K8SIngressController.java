package com.nexcloud.api.k8s.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nexcloud.api.domain.ResponseData;
import com.nexcloud.api.k8s.service.K8SIngressService;
import com.nexcloud.util.Const;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Configuration
@RestController
@EnableAutoConfiguration
@ComponentScan
@RequestMapping(value = "/v1")
public class K8SIngressController extends SpringBootServletInitializer implements WebApplicationInitializer {
	
	@Autowired
    private K8SIngressService service;
	
	static final Logger logger = LoggerFactory.getLogger(K8SIngressController.class);
	
	/**
	* Unix creation timestamp
	* @return
	* @throws Exception
	*/
	@ApiOperation(value="Unix creation timestamp", httpMethod="GET", notes="Unix creation timestamp")
	@ApiImplicitParams({
		@ApiImplicitParam(
				name = "cluster_id",
				value = "Cluster ID (ex) 1",
				required = true,
				dataTypeClass = String.class,
				paramType = "path"
		),
		@ApiImplicitParam(
	            name = "ingress", 
	            value = "ingress name (ex) '.*' => (전체 ingress), exporterhub-ingress", 
	            required = true, 
	            dataTypeClass = String.class, 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "namespace", 
	            value = "namespace (ex) nexclipper", 
	            required = false, 
	            dataTypeClass = String.class, 
	            paramType = "query"
	    )
	})
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	
	@RequestMapping(value="/cluster/{cluster_id}/ingress/{ingress}/creation", method=RequestMethod.GET)
	@ResponseBody
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseEntity<ResponseData> getIngressCreation(  @PathVariable("cluster_id") String cluster_id
														,@PathVariable("ingress") String ingress
														,@QueryParam("namespace") String namespace
														) throws Exception {
		
		ResponseEntity<ResponseData> response 			= null;
		try{
			response 									= service.getIngressCreation( cluster_id, ingress, namespace );
		}catch(Exception e){
			
			ResponseData resData	= new ResponseData();
			resData.setResponse_code(Const.INTERNAL_SERVER_ERROR);
			resData.setMessage(Const.FAIL);
			response = new ResponseEntity<ResponseData>(resData, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	/**
	* Information about ingress
	* @return
	* @throws Exception
	*/
	@ApiOperation(value="Information about ingress", httpMethod="GET", notes="Information about ingress")
	@ApiImplicitParams({
		@ApiImplicitParam(
				name = "cluster_id",
				value = "Cluster ID (ex) 1",
				required = true,
				dataTypeClass = String.class,
				paramType = "path"
		),
		@ApiImplicitParam(
	            name = "ingress", 
	            value = "ingress name (ex) '.*' => (전체 ingress), exporterhub-ingress", 
	            required = true, 
	            dataTypeClass = String.class, 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "namespace", 
	            value = "namespace (ex) nexclipper", 
	            required = false, 
	            dataTypeClass = String.class, 
	            paramType = "query"
	    )
	})
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	
	@RequestMapping(value="/cluster/{cluster_id}/ingress/{ingress}/info", method=RequestMethod.GET)
	@ResponseBody
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseEntity<ResponseData> getIngressInfo(  @PathVariable("cluster_id") String cluster_id
														,@PathVariable("ingress") String ingress
														,@QueryParam("namespace") String namespace
														) throws Exception {
		
		ResponseEntity<ResponseData> response 			= null;
		try{
			response 									= service.getIngressInfo( cluster_id, ingress, namespace );
		}catch(Exception e){
			
			ResponseData resData	= new ResponseData();
			resData.setResponse_code(Const.INTERNAL_SERVER_ERROR);
			resData.setMessage(Const.FAIL);
			response = new ResponseEntity<ResponseData>(resData, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	/**
	* Kubernetes labels converted to Prometheus labels
	* @return
	* @throws Exception
	*/
	@ApiOperation(value="Kubernetes labels converted to Prometheus labels", httpMethod="GET", notes="Kubernetes labels converted to Prometheus labels")
	@ApiImplicitParams({
		@ApiImplicitParam(
				name = "cluster_id",
				value = "Cluster ID (ex) 1",
				required = true,
				dataTypeClass = String.class,
				paramType = "path"
		),
		@ApiImplicitParam(
	            name = "ingress", 
	            value = "ingress name (ex) '.*' => (전체 ingress), exporterhub-ingress", 
	            required = true, 
	            dataTypeClass = String.class, 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "namespace", 
	            value = "namespace (ex) nexclipper", 
	            required = false, 
	            dataTypeClass = String.class, 
	            paramType = "query"
	    )
	})
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	
	@RequestMapping(value="/cluster/{cluster_id}/ingress/{ingress}/labels", method=RequestMethod.GET)
	@ResponseBody
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseEntity<ResponseData> getLabelsInfo(  @PathVariable("cluster_id") String cluster_id
														,@PathVariable("ingress") String ingress
														,@QueryParam("namespace") String namespace
														) throws Exception {
		
		ResponseEntity<ResponseData> response 			= null;
		try{
			response 									= service.getLabelsInfo( cluster_id, ingress, namespace );
		}catch(Exception e){
			
			ResponseData resData	= new ResponseData();
			resData.setResponse_code(Const.INTERNAL_SERVER_ERROR);
			resData.setMessage(Const.FAIL);
			response = new ResponseEntity<ResponseData>(resData, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
}
