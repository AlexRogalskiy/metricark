package com.nexcloud.api.node.controller;

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
import com.nexcloud.api.node.service.NodeCPUService;
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
public class NodeCPUController extends SpringBootServletInitializer implements WebApplicationInitializer {
	
	@Autowired
    private NodeCPUService cpuService;
	
	static final Logger logger = LoggerFactory.getLogger(NodeCPUController.class);
	
	
	/**
	* Node별 CPU core 정보
	* @return
	* @throws Exception
	*/
	@ApiOperation(value="node별 capacity cpu core", httpMethod="GET", notes="core")
	@ApiImplicitParams({
		@ApiImplicitParam(
				name = "cluster_id",
				value = "Cluster ID (ex) 1",
				required = true,
				dataTypeClass = String.class,
				paramType = "path"
		),
		@ApiImplicitParam(
	            name = "start", 
	            value = "조회 시작 시간( Unix Timestamp ) (ex) 1521577239 ", 
	            required = false, 
	            dataTypeClass = String.class, 
	            paramType = "query"
	    ),
		@ApiImplicitParam(
	            name = "end", 
	            value = "조회 종료시간( Unix Timestamp ) (ex) 1521577239 ", 
	            required = false, 
	            dataTypeClass = String.class, 
	            paramType = "query"
	    )
	})
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	
	@RequestMapping(value="/cluster/{cluster_id}/nodes/resources/cpu/core", method=RequestMethod.GET)
	@ResponseBody
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseEntity<ResponseData> getNodeCpuCoreResource(  @PathVariable("cluster_id") String cluster_id
																,@QueryParam("start") String start
																,@QueryParam("end") String end
															  ) throws Exception {
		
		ResponseEntity<ResponseData> response 			= null;
		try{
			response 									= cpuService.getNodeCpuCoreResource( cluster_id, start, end );
		}catch(Exception e){
			
			ResponseData resData	= new ResponseData();
			resData.setResponse_code(Const.INTERNAL_SERVER_ERROR);
			resData.setMessage(Const.FAIL);
			response = new ResponseEntity<ResponseData>(resData, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	/**
	* 특정 Node CPU core 정보
	* @return
	* @throws Exception
	*/
	@ApiOperation(value="특정 Node CPU core", httpMethod="GET", notes="core")
	@ApiImplicitParams({
		@ApiImplicitParam(
				name = "cluster_id",
				value = "Cluster ID (ex) 1",
				required = true,
				dataTypeClass = String.class,
				paramType = "path"
		),
		@ApiImplicitParam(
	            name = "node_name", 
	            value = " Node Name(ex) node1", 
	            required = true, 
	            dataTypeClass = String.class, 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "start", 
	            value = "조회 시작 시간( Unix Timestamp ) (ex) 1521577239 ", 
	            required = false, 
	            dataTypeClass = String.class, 
	            paramType = "query"
	    ),
		@ApiImplicitParam(
	            name = "end", 
	            value = "조회 종료시간( Unix Timestamp ) (ex) 1521577239 ", 
	            required = false, 
	            dataTypeClass = String.class, 
	            paramType = "query"
	    )
	})
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	
	@RequestMapping(value="/cluster/{cluster_id}/nodes/{node_name}/resources/cpu/core", method=RequestMethod.GET)
	@ResponseBody
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseEntity<ResponseData> getNodeCpuCoreResource(  @PathVariable("cluster_id") String cluster_id
																,@PathVariable("node_name") String node_name
																,@QueryParam("start") String start
																,@QueryParam("end") String end
															  ) throws Exception {
		
		ResponseEntity<ResponseData> response 			= null;
		try{
			response 									= cpuService.getNodeCpuCoreResource( cluster_id, node_name, start, end );
		}catch(Exception e){
			
			ResponseData resData	= new ResponseData();
			resData.setResponse_code(Const.INTERNAL_SERVER_ERROR);
			resData.setMessage(Const.FAIL);
			response = new ResponseEntity<ResponseData>(resData, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}

	/**
	*  특정Node CPU Usage (%)
	* @return
	* @throws Exception
	*/
	@ApiOperation(value="특정Node CPU Usage (%)", httpMethod="GET", notes="core")
	@ApiImplicitParams({
		@ApiImplicitParam(
				name = "cluster_id",
				value = "Cluster ID (ex) 1",
				required = true,
				dataTypeClass = String.class,
				paramType = "path"
		),
		@ApiImplicitParam(
	            name = "node_name", 
	            value = " Node Name(ex) node1", 
	            required = true, 
	            dataTypeClass = String.class, 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "start", 
	            value = "조회 시작 시간( Unix Timestamp ) (ex) 1521577239 ", 
	            required = false, 
	            dataTypeClass = String.class, 
	            paramType = "query"
	    ),
		@ApiImplicitParam(
	            name = "end", 
	            value = "조회 종료시간( Unix Timestamp ) (ex) 1521577239 ", 
	            required = false, 
	            dataTypeClass = String.class, 
	            paramType = "query"
	    )
	})
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	
	@RequestMapping(value="/cluster/{cluster_id}/nodes/{node_name}/resources/cpu/used/usage", method=RequestMethod.GET)
	@ResponseBody
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseEntity<ResponseData> getNodeCpuUsageResource(  @PathVariable("cluster_id") String cluster_id
																 ,@PathVariable("node_name") String node_name
																 ,@QueryParam("start") String start
																 ,@QueryParam("end") String end
															  ) throws Exception {
		
		ResponseEntity<ResponseData> response 			= null;
		try{
			response 									= cpuService.getNodeCpuUsageResource( cluster_id, node_name, start, end );
		}catch(Exception e){
			
			ResponseData resData	= new ResponseData();
			resData.setResponse_code(Const.INTERNAL_SERVER_ERROR);
			resData.setMessage(Const.FAIL);
			response = new ResponseEntity<ResponseData>(resData, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	/**
	*  특정Node CPU Used core
	* @return
	* @throws Exception
	*/
	@ApiOperation(value="특정Node CPU Used core", httpMethod="GET", notes="core")
	@ApiImplicitParams({
		@ApiImplicitParam(
				name = "cluster_id",
				value = "Cluster ID (ex) 1",
				required = true,
				dataTypeClass = String.class,
				paramType = "path"
		),
		@ApiImplicitParam(
	            name = "node_name", 
	            value = " Node Name(ex) node1", 
	            required = true, 
	            dataTypeClass = String.class, 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "start", 
	            value = "조회 시작 시간( Unix Timestamp ) (ex) 1521577239 ", 
	            required = false, 
	            dataTypeClass = String.class, 
	            paramType = "query"
	    ),
		@ApiImplicitParam(
	            name = "end", 
	            value = "조회 종료시간( Unix Timestamp ) (ex) 1521577239 ", 
	            required = false, 
	            dataTypeClass = String.class, 
	            paramType = "query"
	    )
	})
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	
	@RequestMapping(value="/cluster/{cluster_id}/nodes/{node_name}/resources/cpu/used/core", method=RequestMethod.GET)
	@ResponseBody
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseEntity<ResponseData> getNodeCpuUsedCoreResource(  @PathVariable("cluster_id") String cluster_id
																	,@PathVariable("node_name") String node_name
																	,@QueryParam("start") String start
																	,@QueryParam("end") String end
															  ) throws Exception {
		
		ResponseEntity<ResponseData> response 			= null;
		try{
			response 									= cpuService.getNodeCpuUsedCoreResource( cluster_id, node_name, start, end );
		}catch(Exception e){
			
			ResponseData resData	= new ResponseData();
			resData.setResponse_code(Const.INTERNAL_SERVER_ERROR);
			resData.setMessage(Const.FAIL);
			response = new ResponseEntity<ResponseData>(resData, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	/**
	*  특정Node mode별 CPU Usage
	* @return
	* @throws Exception
	*/
	@ApiOperation(value="특정Node mode별 CPU Usage", httpMethod="GET", notes="core")
	@ApiImplicitParams({
		@ApiImplicitParam(
				name = "cluster_id",
				value = "Cluster ID (ex) 1",
				required = true,
				dataTypeClass = String.class,
				paramType = "path"
		),
		@ApiImplicitParam(
	            name = "node_name", 
	            value = " Node Name(ex) node1", 
	            required = true, 
	            dataTypeClass = String.class, 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "mode", 
	            value = " mode(ex) idle, irq, nice, softirq, steal, system, user, iowait", 
	            required = true, 
	            dataTypeClass = String.class, 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "start", 
	            value = "조회 시작 시간( Unix Timestamp ) (ex) 1521577239 ", 
	            required = false, 
	            dataTypeClass = String.class, 
	            paramType = "query"
	    ),
		@ApiImplicitParam(
	            name = "end", 
	            value = "조회 종료시간( Unix Timestamp ) (ex) 1521577239 ", 
	            required = false, 
	            dataTypeClass = String.class, 
	            paramType = "query"
	    )
	})
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	
	@RequestMapping(value="/cluster/{cluster_id}/nodes/{node_name}/resources/cpu/mode/{mode}/used/usage", method=RequestMethod.GET)
	@ResponseBody
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseEntity<ResponseData> getNodeCpuModeUsageResource(  @PathVariable("cluster_id") String cluster_id
																	 ,@PathVariable("node_name") String node_name
																	 ,@PathVariable("mode") String mode
																	 ,@QueryParam("start") String start
																	 ,@QueryParam("end") String end
															  ) throws Exception {
		
		ResponseEntity<ResponseData> response 			= null;
		try{
			response 									= cpuService.getNodeCpuModeUsageResource( cluster_id, node_name, mode, start, end );
		}catch(Exception e){
			
			ResponseData resData	= new ResponseData();
			resData.setResponse_code(Const.INTERNAL_SERVER_ERROR);
			resData.setMessage(Const.FAIL);
			response = new ResponseEntity<ResponseData>(resData, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	/**
	*  특정Node Capacity CPU
	* @return
	* @throws Exception
	*/
	@ApiOperation(value="특정Node Capacity CPU", httpMethod="GET", notes="core")
	@ApiImplicitParams({
		@ApiImplicitParam(
				name = "cluster_id",
				value = "Cluster ID (ex) 1",
				required = true,
				dataTypeClass = String.class,
				paramType = "path"
		),
		@ApiImplicitParam(
	            name = "node_name", 
	            value = " Node Name(ex) node1", 
	            required = true, 
	            dataTypeClass = String.class, 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "start", 
	            value = "조회 시작 시간( Unix Timestamp ) (ex) 1521577239 ", 
	            required = false, 
	            dataTypeClass = String.class, 
	            paramType = "query"
	    ),
		@ApiImplicitParam(
	            name = "end", 
	            value = "조회 종료시간( Unix Timestamp ) (ex) 1521577239 ", 
	            required = false, 
	            dataTypeClass = String.class, 
	            paramType = "query"
	    )
	})
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	
	@RequestMapping(value="/cluster/{cluster_id}/nodes/{node_name}/resources/cpu/capacity", method=RequestMethod.GET)
	@ResponseBody
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseEntity<ResponseData> getNodeCpuCapacityResource(  @PathVariable("cluster_id") String cluster_id
																	,@PathVariable("node_name") String node_name
																	,@QueryParam("start") String start
																	,@QueryParam("end") String end
															  ) throws Exception {
		
		ResponseEntity<ResponseData> response 			= null;
		try{
			response 									= cpuService.getNodeCpuCapacityResource( cluster_id, node_name, start, end );
		}catch(Exception e){
			
			ResponseData resData	= new ResponseData();
			resData.setResponse_code(Const.INTERNAL_SERVER_ERROR);
			resData.setMessage(Const.FAIL);
			response = new ResponseEntity<ResponseData>(resData, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	/**
	*  특정Node Allocatable CPU
	* @return
	* @throws Exception
	*/
	@ApiOperation(value="특정Node Allocatable CPU", httpMethod="GET", notes="core")
	@ApiImplicitParams({
		@ApiImplicitParam(
				name = "cluster_id",
				value = "Cluster ID (ex) 1",
				required = true,
				dataTypeClass = String.class,
				paramType = "path"
		),
		@ApiImplicitParam(
	            name = "node_name", 
	            value = " Node Name(ex) node1", 
	            required = true, 
	            dataTypeClass = String.class, 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "start", 
	            value = "조회 시작 시간( Unix Timestamp ) (ex) 1521577239 ", 
	            required = false, 
	            dataTypeClass = String.class, 
	            paramType = "query"
	    ),
		@ApiImplicitParam(
	            name = "end", 
	            value = "조회 종료시간( Unix Timestamp ) (ex) 1521577239 ", 
	            required = false, 
	            dataTypeClass = String.class, 
	            paramType = "query"
	    )
	})
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	
	@RequestMapping(value="/cluster/{cluster_id}/nodes/{node_name}/resources/cpu/allocatable", method=RequestMethod.GET)
	@ResponseBody
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseEntity<ResponseData> getNodeCpuAllocatableResource(  @PathVariable("cluster_id") String cluster_id
																	,@PathVariable("node_name") String node_name
																	,@QueryParam("start") String start
																	,@QueryParam("end") String end
															  ) throws Exception {
		
		ResponseEntity<ResponseData> response 			= null;
		try{
			response 									= cpuService.getNodeCpuAllocatableResource( cluster_id, node_name, start, end );
		}catch(Exception e){
			
			ResponseData resData	= new ResponseData();
			resData.setResponse_code(Const.INTERNAL_SERVER_ERROR);
			resData.setMessage(Const.FAIL);
			response = new ResponseEntity<ResponseData>(resData, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	/**
	*  특정Node Request CPU
	* @return
	* @throws Exception
	*/
	@ApiOperation(value="특정Node Request CPU", httpMethod="GET", notes="core")
	@ApiImplicitParams({
		@ApiImplicitParam(
				name = "cluster_id",
				value = "Cluster ID (ex) 1",
				required = true,
				dataTypeClass = String.class,
				paramType = "path"
		),
		@ApiImplicitParam(
	            name = "node_name", 
	            value = " Node Name(ex) node1", 
	            required = true, 
	            dataTypeClass = String.class, 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "start", 
	            value = "조회 시작 시간( Unix Timestamp ) (ex) 1521577239 ", 
	            required = false, 
	            dataTypeClass = String.class, 
	            paramType = "query"
	    ),
		@ApiImplicitParam(
	            name = "end", 
	            value = "조회 종료시간( Unix Timestamp ) (ex) 1521577239 ", 
	            required = false, 
	            dataTypeClass = String.class, 
	            paramType = "query"
	    )
	})
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	
	@RequestMapping(value="/cluster/{cluster_id}/nodes/{node_name}/resources/cpu/request", method=RequestMethod.GET)
	@ResponseBody
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseEntity<ResponseData> getNodeCpuRequestResource(   @PathVariable("cluster_id") String cluster_id
																	,@PathVariable("node_name") String node_name
																	,@QueryParam("start") String start
																	,@QueryParam("end") String end
															  ) throws Exception {
		
		ResponseEntity<ResponseData> response 			= null;
		try{
			response 									= cpuService.getNodeCpuRequestResource( cluster_id, node_name, start, end );
		}catch(Exception e){
			
			ResponseData resData	= new ResponseData();
			resData.setResponse_code(Const.INTERNAL_SERVER_ERROR);
			resData.setMessage(Const.FAIL);
			response = new ResponseEntity<ResponseData>(resData, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	/**
	*  특정Node CPU Load1
	* @return
	* @throws Exception
	*/
	@ApiOperation(value="특정Node CPU Load1", httpMethod="GET", notes="core")
	@ApiImplicitParams({
		@ApiImplicitParam(
				name = "cluster_id",
				value = "Cluster ID (ex) 1",
				required = true,
				dataTypeClass = String.class,
				paramType = "path"
		),
		@ApiImplicitParam(
	            name = "node_name", 
	            value = " Node Name(ex) node1", 
	            required = true, 
	            dataTypeClass = String.class, 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "start", 
	            value = "조회 시작 시간( Unix Timestamp ) (ex) 1521577239 ", 
	            required = false, 
	            dataTypeClass = String.class, 
	            paramType = "query"
	    ),
		@ApiImplicitParam(
	            name = "end", 
	            value = "조회 종료시간( Unix Timestamp ) (ex) 1521577239 ", 
	            required = false, 
	            dataTypeClass = String.class, 
	            paramType = "query"
	    )
	})
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	
	@RequestMapping(value="/cluster/{cluster_id}/nodes/{node_name}/resources/cpu/load1", method=RequestMethod.GET)
	@ResponseBody
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseEntity<ResponseData> getNodeCpuLoad1Resource(    @PathVariable("cluster_id") String cluster_id
																	,@PathVariable("node_name") String node_name
																	,@QueryParam("start") String start
																	,@QueryParam("end") String end
															  ) throws Exception {
		
		ResponseEntity<ResponseData> response 			= null;
		try{
			response 									= cpuService.getNodeCpuLoad1Resource( cluster_id, node_name, start, end );
		}catch(Exception e){
			
			ResponseData resData	= new ResponseData();
			resData.setResponse_code(Const.INTERNAL_SERVER_ERROR);
			resData.setMessage(Const.FAIL);
			response = new ResponseEntity<ResponseData>(resData, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	/**
	*  특정Node CPU Load5
	* @return
	* @throws Exception
	*/
	@ApiOperation(value="특정Node CPU Load5", httpMethod="GET", notes="core")
	@ApiImplicitParams({
		@ApiImplicitParam(
				name = "cluster_id",
				value = "Cluster ID (ex) 1",
				required = true,
				dataTypeClass = String.class,
				paramType = "path"
		),
		@ApiImplicitParam(
	            name = "node_name", 
	            value = " Node Name(ex) node1", 
	            required = true, 
	            dataTypeClass = String.class, 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "start", 
	            value = "조회 시작 시간( Unix Timestamp ) (ex) 1521577239 ", 
	            required = false, 
	            dataTypeClass = String.class, 
	            paramType = "query"
	    ),
		@ApiImplicitParam(
	            name = "end", 
	            value = "조회 종료시간( Unix Timestamp ) (ex) 1521577239 ", 
	            required = false, 
	            dataTypeClass = String.class, 
	            paramType = "query"
	    )
	})
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	
	@RequestMapping(value="/cluster/{cluster_id}/nodes/{node_name}/resources/cpu/load5", method=RequestMethod.GET)
	@ResponseBody
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseEntity<ResponseData> getNodeCpuLoad5Resource(    @PathVariable("cluster_id") String cluster_id
																	,@PathVariable("node_name") String node_name
																	,@QueryParam("start") String start
																	,@QueryParam("end") String end
															  ) throws Exception {
		
		ResponseEntity<ResponseData> response 			= null;
		try{
			response 									= cpuService.getNodeCpuLoad5Resource( cluster_id, node_name, start, end );
		}catch(Exception e){
			
			ResponseData resData	= new ResponseData();
			resData.setResponse_code(Const.INTERNAL_SERVER_ERROR);
			resData.setMessage(Const.FAIL);
			response = new ResponseEntity<ResponseData>(resData, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	/**
	*  특정Node CPU Load15
	* @return
	* @throws Exception
	*/
	@ApiOperation(value="특정Node CPU Load15", httpMethod="GET", notes="core")
	@ApiImplicitParams({
		@ApiImplicitParam(
				name = "cluster_id",
				value = "Cluster ID (ex) 1",
				required = true,
				dataTypeClass = String.class,
				paramType = "path"
		),
		@ApiImplicitParam(
	            name = "node_name", 
	            value = " Node Name(ex) node1", 
	            required = true, 
	            dataTypeClass = String.class, 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "start", 
	            value = "조회 시작 시간( Unix Timestamp ) (ex) 1521577239 ", 
	            required = false, 
	            dataTypeClass = String.class, 
	            paramType = "query"
	    ),
		@ApiImplicitParam(
	            name = "end", 
	            value = "조회 종료시간( Unix Timestamp ) (ex) 1521577239 ", 
	            required = false, 
	            dataTypeClass = String.class, 
	            paramType = "query"
	    )
	})
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	
	@RequestMapping(value="/cluster/{cluster_id}/nodes/{node_name}/resources/cpu/load15", method=RequestMethod.GET)
	@ResponseBody
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseEntity<ResponseData> getNodeCpuLoad15Resource(    @PathVariable("cluster_id") String cluster_id
																	,@PathVariable("node_name") String node_name
																	,@QueryParam("start") String start
																	,@QueryParam("end") String end
															  ) throws Exception {
		
		ResponseEntity<ResponseData> response 			= null;
		try{
			response 									= cpuService.getNodeCpuLoad15Resource( cluster_id, node_name, start, end );
		}catch(Exception e){
			
			ResponseData resData	= new ResponseData();
			resData.setResponse_code(Const.INTERNAL_SERVER_ERROR);
			resData.setMessage(Const.FAIL);
			response = new ResponseEntity<ResponseData>(resData, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
}
