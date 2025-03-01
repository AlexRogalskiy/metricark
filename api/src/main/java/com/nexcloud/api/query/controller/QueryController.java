package com.nexcloud.api.query.controller;

import java.io.Serializable;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nexcloud.api.domain.ResponseData;
import com.nexcloud.api.query.service.QueryService;
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
public class QueryController extends SpringBootServletInitializer implements Serializable, WebApplicationInitializer {
	
	@Autowired
    private QueryService service;
	
	static final Logger logger = LoggerFactory.getLogger(QueryController.class);
	

	/**
	* Dynamic Query 
	* @return
	* @throws Exception
	*/
	@ApiOperation(value="Dynamic Query", httpMethod="GET", notes="동적 쿼리 작성")
	@ApiImplicitParams({
		@ApiImplicitParam(
				name = "cluster_id",
				value = "Cluster ID (ex) 1",
				required = true,
				dataTypeClass = String.class,
				paramType = "path"
		),
		@ApiImplicitParam(
				name = "query",
				value = "query (ex) kube_pod_owner{namespace='nexclipperagent'} ",
				required = true,
				dataTypeClass = String.class,
				paramType = "query",
				defaultValue=""
		),
		@ApiImplicitParam(
	            name = "start", 
	            value = "조회 시작 시간( Unix Timestamp ) (ex) 1521577239 ", 
	            required = false, 
	            dataTypeClass = String.class, 
	            paramType = "query",
	            defaultValue=""
	    ),
		@ApiImplicitParam(
	            name = "end", 
	            value = "조회 종료시간( Unix Timestamp ) (ex) 1521577239 ", 
	            required = false, 
	            dataTypeClass = String.class, 
	            paramType = "query",
	            defaultValue=""
	    )
	})
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	
	@RequestMapping(value="/cluster/{cluster_id}/query", method=RequestMethod.GET)
	@ResponseBody
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseEntity<ResponseData> getDynamicQuery( @PathVariable("cluster_id") String cluster_id
														,@RequestParam(value = "query", required = true) String query
														,@QueryParam("start") String start
														,@QueryParam("end") String end
														) throws Exception {
		
		ResponseEntity<ResponseData> response 			= null;
		try{
			response 									= service.getDynamicQuery( cluster_id, query, start, end );
		}catch(Exception e){
			
			ResponseData resData	= new ResponseData();
			resData.setResponse_code(Const.INTERNAL_SERVER_ERROR);
			resData.setMessage(Const.FAIL);
			response = new ResponseEntity<ResponseData>(resData, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}

	/**
	* Direct Query 
	* @return
	* @throws Exception
	*/
	@ApiOperation(value="Direct Query", httpMethod="GET", notes="동적 쿼리 작성")
	@ApiImplicitParams({
		@ApiImplicitParam(
				name = "promql",
				value = "query (ex) kube_pod_owner{namespace='nexclipperagent'} ",
				required = true,
				dataTypeClass = String.class,
				paramType = "query",
				defaultValue=""
		),
		@ApiImplicitParam(
				name = "endpoint",
				value = "promscale endpoint (ex) http://nc-promscale-connector.nexclipper:9201",
				required = false,
				dataTypeClass = String.class,
				paramType = "query",
				defaultValue=""
		)
	})
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	
	@RequestMapping(value="/p8s/query", method=RequestMethod.GET)
	@ResponseBody
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseEntity<ResponseData> getDirectQuery(@RequestParam(value = "promql", required = true) String promql,
			@RequestParam(value = "endpoint", required = false) String endpoint
			) throws Exception {
		
		ResponseEntity<ResponseData> response 			= null;
		try{
			response 									= service.getDirectQuery( promql, endpoint );
		}catch(Exception e){
			
			ResponseData resData	= new ResponseData();
			resData.setResponse_code(Const.INTERNAL_SERVER_ERROR);
			resData.setMessage(Const.FAIL);
			response = new ResponseEntity<ResponseData>(resData, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
}
