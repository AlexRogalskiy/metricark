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
import com.nexcloud.api.node.service.NodeNetworkService;
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
public class NodeNetworkController extends SpringBootServletInitializer implements WebApplicationInitializer {
	
	@Autowired
    private NodeNetworkService netService;
	
	static final Logger logger = LoggerFactory.getLogger(NodeNetworkController.class);
	
	/**
	*  Node별 Network Device id
	* @return
	* @throws Exception
	*/
	@ApiOperation(value="Node별 Network Device id", httpMethod="GET", notes="node별 network device id조회")
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
	    )
	})
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	
	@RequestMapping(value="/cluster/{cluster_id}/nodes/{node_name}/resources/network/device", method=RequestMethod.GET)
	@ResponseBody
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseEntity<ResponseData> getNodeNetworkDevice(  @PathVariable("cluster_id") String cluster_id
																	,@PathVariable("node_name") String node_name
															  ) throws Exception {
		
		ResponseEntity<ResponseData> response 			= null;
		try{
			response 									= netService.getNodeNetworkDevice( cluster_id, node_name );
		}catch(Exception e){
			
			ResponseData resData	= new ResponseData();
			resData.setResponse_code(Const.INTERNAL_SERVER_ERROR);
			resData.setMessage(Const.FAIL);
			response = new ResponseEntity<ResponseData>(resData, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	/**
	* Node별 Receive Network Traffic
	* @return
	* @throws Exception
	*/
	@ApiOperation(value="Node별 Receive Network Traffic", httpMethod="GET", notes="core")
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
	            required = true, 
	            dataTypeClass = String.class, 
	            paramType = "query"
	    ),
		@ApiImplicitParam(
	            name = "end", 
	            value = "조회 종료시간( Unix Timestamp ) (ex) 1521577239 ", 
	            required = true, 
	            dataTypeClass = String.class, 
	            paramType = "query"
	    )
	})
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	
	@RequestMapping(value="/cluster/{cluster_id}/nodes/{node_name}/resources/network/rx", method=RequestMethod.GET)
	@ResponseBody
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseEntity<ResponseData> getNodeNetworkRxResource(  	 @PathVariable("cluster_id") String cluster_id
																   	,@PathVariable("node_name") String node_name
																	,@QueryParam("start") String start
																	,@QueryParam("end") String end
																	) throws Exception {
		
		ResponseEntity<ResponseData> response 			= null;
		try{
			response 									= netService.getNodeNetworkRxResource( cluster_id, node_name, start, end );
		}catch(Exception e){
			
			ResponseData resData	= new ResponseData();
			resData.setResponse_code(Const.INTERNAL_SERVER_ERROR);
			resData.setMessage(Const.FAIL);
			response = new ResponseEntity<ResponseData>(resData, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	/**
	* Node별 Receive Compressed Network Traffic
	* @return
	* @throws Exception
	*/
	@ApiOperation(value="Node별 Receive Compressed Network Traffic", httpMethod="GET", notes="core")
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
	            required = true, 
	            dataTypeClass = String.class, 
	            paramType = "query"
	    ),
		@ApiImplicitParam(
	            name = "end", 
	            value = "조회 종료시간( Unix Timestamp ) (ex) 1521577239 ", 
	            required = true, 
	            dataTypeClass = String.class, 
	            paramType = "query"
	    )
	})
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	
	@RequestMapping(value="/cluster/{cluster_id}/nodes/{node_name}/resources/network/rx/compressed", method=RequestMethod.GET)
	@ResponseBody
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseEntity<ResponseData> getNodeNetworkRxCompressedResource(  	 @PathVariable("cluster_id") String cluster_id
																			   	,@PathVariable("node_name") String node_name
																				,@QueryParam("start") String start
																				,@QueryParam("end") String end
																				) throws Exception {
					
		ResponseEntity<ResponseData> response 			= null;
		try{
			response 									= netService.getNodeNetworkRxCompressedResource( cluster_id, node_name, start, end );
		}catch(Exception e){
			
			ResponseData resData	= new ResponseData();
			resData.setResponse_code(Const.INTERNAL_SERVER_ERROR);
			resData.setMessage(Const.FAIL);
			response = new ResponseEntity<ResponseData>(resData, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	/**
	* Node별 Receive drop Network Traffic
	* @return
	* @throws Exception
	*/
	@ApiOperation(value="Node별 Receive drop Network Traffic", httpMethod="GET", notes="core")
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
	            required = true, 
	            dataTypeClass = String.class, 
	            paramType = "query"
	    ),
		@ApiImplicitParam(
	            name = "end", 
	            value = "조회 종료시간( Unix Timestamp ) (ex) 1521577239 ", 
	            required = true, 
	            dataTypeClass = String.class, 
	            paramType = "query"
	    )
	})
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	
	@RequestMapping(value="/cluster/{cluster_id}/nodes/{node_name}/resources/network/rx/drop", method=RequestMethod.GET)
	@ResponseBody
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseEntity<ResponseData> getNodeNetworkRxDropResource(  	 @PathVariable("cluster_id") String cluster_id
																	   	,@PathVariable("node_name") String node_name
																		,@QueryParam("start") String start
																		,@QueryParam("end") String end
																		) throws Exception {
					
		ResponseEntity<ResponseData> response 			= null;
		try{
			response 									= netService.getNodeNetworkRxDropResource( cluster_id, node_name, start, end );
		}catch(Exception e){
			
			ResponseData resData	= new ResponseData();
			resData.setResponse_code(Const.INTERNAL_SERVER_ERROR);
			resData.setMessage(Const.FAIL);
			response = new ResponseEntity<ResponseData>(resData, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	/**
	* Node별 Receive error Network Traffic
	* @return
	* @throws Exception
	*/
	@ApiOperation(value="Node별 Receive error Network Traffic", httpMethod="GET", notes="core")
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
	            required = true, 
	            dataTypeClass = String.class, 
	            paramType = "query"
	    ),
		@ApiImplicitParam(
	            name = "end", 
	            value = "조회 종료시간( Unix Timestamp ) (ex) 1521577239 ", 
	            required = true, 
	            dataTypeClass = String.class, 
	            paramType = "query"
	    )
	})
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	
	@RequestMapping(value="/cluster/{cluster_id}/nodes/{node_name}/resources/network/rx/error", method=RequestMethod.GET)
	@ResponseBody
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseEntity<ResponseData> getNodeNetworkRxErrorResource( 	 @PathVariable("cluster_id") String cluster_id
																	   	,@PathVariable("node_name") String node_name
																		,@QueryParam("start") String start
																		,@QueryParam("end") String end
																		) throws Exception {
					
		ResponseEntity<ResponseData> response 			= null;
		try{
			response 									= netService.getNodeNetworkRxErrorResource( cluster_id, node_name, start, end );
		}catch(Exception e){
			
			ResponseData resData	= new ResponseData();
			resData.setResponse_code(Const.INTERNAL_SERVER_ERROR);
			resData.setMessage(Const.FAIL);
			response = new ResponseEntity<ResponseData>(resData, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	/**
	* Node별 Receive multicast Network Traffic
	* @return
	* @throws Exception
	*/
	@ApiOperation(value="Node별 Receive multicast Network Traffic", httpMethod="GET", notes="core")
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
	            required = true, 
	            dataTypeClass = String.class, 
	            paramType = "query"
	    ),
		@ApiImplicitParam(
	            name = "end", 
	            value = "조회 종료시간( Unix Timestamp ) (ex) 1521577239 ", 
	            required = true, 
	            dataTypeClass = String.class, 
	            paramType = "query"
	    )
	})
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	
	@RequestMapping(value="/cluster/{cluster_id}/nodes/{node_name}/resources/network/rx/multicast", method=RequestMethod.GET)
	@ResponseBody
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseEntity<ResponseData> getNodeNetworkRxMulticastResource( 	 @PathVariable("cluster_id") String cluster_id
																		   	,@PathVariable("node_name") String node_name
																			,@QueryParam("start") String start
																			,@QueryParam("end") String end
																			) throws Exception {
					
		ResponseEntity<ResponseData> response 			= null;
		try{
			response 									= netService.getNodeNetworkRxMulticastResource( cluster_id, node_name, start, end );
		}catch(Exception e){
			
			ResponseData resData	= new ResponseData();
			resData.setResponse_code(Const.INTERNAL_SERVER_ERROR);
			resData.setMessage(Const.FAIL);
			response = new ResponseEntity<ResponseData>(resData, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	/**
	* Node별 Receive Network Packets
	* @return
	* @throws Exception
	*/
	@ApiOperation(value="Node별 Receive Network Packets", httpMethod="GET", notes="core")
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
	            required = true, 
	            dataTypeClass = String.class, 
	            paramType = "query"
	    ),
		@ApiImplicitParam(
	            name = "end", 
	            value = "조회 종료시간( Unix Timestamp ) (ex) 1521577239 ", 
	            required = true, 
	            dataTypeClass = String.class, 
	            paramType = "query"
	    )
	})
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	
	@RequestMapping(value="/cluster/{cluster_id}/nodes/{node_name}/resources/network/rx/packets", method=RequestMethod.GET)
	@ResponseBody
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseEntity<ResponseData> getNodeNetworkRxPacketsResource( 	 @PathVariable("cluster_id") String cluster_id
																		   	,@PathVariable("node_name") String node_name
																			,@QueryParam("start") String start
																			,@QueryParam("end") String end
																			) throws Exception {
					
		ResponseEntity<ResponseData> response 			= null;
		try{
			response 									= netService.getNodeNetworkRxPacketsResource( cluster_id, node_name, start, end );
		}catch(Exception e){
			
			ResponseData resData	= new ResponseData();
			resData.setResponse_code(Const.INTERNAL_SERVER_ERROR);
			resData.setMessage(Const.FAIL);
			response = new ResponseEntity<ResponseData>(resData, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}

	/**
	*  Node별 Send Network Traffic
	* @return
	* @throws Exception
	*/
	@ApiOperation(value="Node별 Send Network Traffic", httpMethod="GET", notes="core")
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
	            required = true, 
	            dataTypeClass = String.class, 
	            paramType = "query"
	    ),
		@ApiImplicitParam(
	            name = "end", 
	            value = "조회 종료시간( Unix Timestamp ) (ex) 1521577239 ", 
	            required = true, 
	            dataTypeClass = String.class, 
	            paramType = "query"
	    )
	})
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	
	@RequestMapping(value="/cluster/{cluster_id}/nodes/{node_name}/resources/network/tx", method=RequestMethod.GET)
	@ResponseBody
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseEntity<ResponseData> getNodeNetworkTxResource(  @PathVariable("cluster_id") String cluster_id
																	,@PathVariable("node_name") String node_name
																	,@QueryParam("start") String start
																	,@QueryParam("end") String end
															  ) throws Exception {
		
		ResponseEntity<ResponseData> response 			= null;
		try{
			response 									= netService.getNodeNetworkTxResource( cluster_id, node_name, start, end );
		}catch(Exception e){
			
			ResponseData resData	= new ResponseData();
			resData.setResponse_code(Const.INTERNAL_SERVER_ERROR);
			resData.setMessage(Const.FAIL);
			response = new ResponseEntity<ResponseData>(resData, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	/**
	* Node별 Send Compressed Network Traffic
	* @return
	* @throws Exception
	*/
	@ApiOperation(value="Node별 Send Compressed Network Traffic", httpMethod="GET", notes="core")
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
	            required = true, 
	            dataTypeClass = String.class, 
	            paramType = "query"
	    ),
		@ApiImplicitParam(
	            name = "end", 
	            value = "조회 종료시간( Unix Timestamp ) (ex) 1521577239 ", 
	            required = true, 
	            dataTypeClass = String.class, 
	            paramType = "query"
	    )
	})
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	
	@RequestMapping(value="/cluster/{cluster_id}/nodes/{node_name}/resources/network/tx/compressed", method=RequestMethod.GET)
	@ResponseBody
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseEntity<ResponseData> getNodeNetworkTxCompressedResource(  	 @PathVariable("cluster_id") String cluster_id
																			   	,@PathVariable("node_name") String node_name
																				,@QueryParam("start") String start
																				,@QueryParam("end") String end
																				) throws Exception {
					
		ResponseEntity<ResponseData> response 			= null;
		try{
			response 									= netService.getNodeNetworkTxCompressedResource( cluster_id, node_name, start, end );
		}catch(Exception e){
			
			ResponseData resData	= new ResponseData();
			resData.setResponse_code(Const.INTERNAL_SERVER_ERROR);
			resData.setMessage(Const.FAIL);
			response = new ResponseEntity<ResponseData>(resData, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	/**
	* Node별 Send drop Network Traffic
	* @return
	* @throws Exception
	*/
	@ApiOperation(value="Node별 Send drop Network Traffic", httpMethod="GET", notes="core")
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
	            required = true, 
	            dataTypeClass = String.class, 
	            paramType = "query"
	    ),
		@ApiImplicitParam(
	            name = "end", 
	            value = "조회 종료시간( Unix Timestamp ) (ex) 1521577239 ", 
	            required = true, 
	            dataTypeClass = String.class, 
	            paramType = "query"
	    )
	})
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	
	@RequestMapping(value="/cluster/{cluster_id}/nodes/{node_name}/resources/network/tx/drop", method=RequestMethod.GET)
	@ResponseBody
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseEntity<ResponseData> getNodeNetworkTxDropResource(  	 @PathVariable("cluster_id") String cluster_id
																	   	,@PathVariable("node_name") String node_name
																		,@QueryParam("start") String start
																		,@QueryParam("end") String end
																		) throws Exception {
					
		ResponseEntity<ResponseData> response 			= null;
		try{
			response 									= netService.getNodeNetworkTxDropResource( cluster_id, node_name, start, end );
		}catch(Exception e){
			
			ResponseData resData	= new ResponseData();
			resData.setResponse_code(Const.INTERNAL_SERVER_ERROR);
			resData.setMessage(Const.FAIL);
			response = new ResponseEntity<ResponseData>(resData, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	/**
	* Node별 Send error Network Traffic
	* @return
	* @throws Exception
	*/
	@ApiOperation(value="Node별 Send error Network Traffic", httpMethod="GET", notes="core")
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
	            required = true, 
	            dataTypeClass = String.class, 
	            paramType = "query"
	    ),
		@ApiImplicitParam(
	            name = "end", 
	            value = "조회 종료시간( Unix Timestamp ) (ex) 1521577239 ", 
	            required = true, 
	            dataTypeClass = String.class, 
	            paramType = "query"
	    )
	})
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	
	@RequestMapping(value="/cluster/{cluster_id}/nodes/{node_name}/resources/network/tx/error", method=RequestMethod.GET)
	@ResponseBody
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseEntity<ResponseData> getNodeNetworkTxErrorResource( 	 @PathVariable("cluster_id") String cluster_id
																	   	,@PathVariable("node_name") String node_name
																		,@QueryParam("start") String start
																		,@QueryParam("end") String end
																		) throws Exception {
					
		ResponseEntity<ResponseData> response 			= null;
		try{
			response 									= netService.getNodeNetworkTxErrorResource( cluster_id, node_name, start, end );
		}catch(Exception e){
			
			ResponseData resData	= new ResponseData();
			resData.setResponse_code(Const.INTERNAL_SERVER_ERROR);
			resData.setMessage(Const.FAIL);
			response = new ResponseEntity<ResponseData>(resData, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	
	/**
	* Node별 Send Network Packets
	* @return
	* @throws Exception
	*/
	@ApiOperation(value="Node별 Send Network Packets", httpMethod="GET", notes="core")
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
	            required = true, 
	            dataTypeClass = String.class, 
	            paramType = "query"
	    ),
		@ApiImplicitParam(
	            name = "end", 
	            value = "조회 종료시간( Unix Timestamp ) (ex) 1521577239 ", 
	            required = true, 
	            dataTypeClass = String.class, 
	            paramType = "query"
	    )
	})
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	
	@RequestMapping(value="/cluster/{cluster_id}/nodes/{node_name}/resources/network/tx/packets", method=RequestMethod.GET)
	@ResponseBody
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseEntity<ResponseData> getNodeNetworkTxPacketsResource( 	 @PathVariable("cluster_id") String cluster_id
																		   	,@PathVariable("node_name") String node_name
																			,@QueryParam("start") String start
																			,@QueryParam("end") String end
																			) throws Exception {
					
		ResponseEntity<ResponseData> response 			= null;
		try{
			response 									= netService.getNodeNetworkTxPacketsResource( cluster_id, node_name, start, end );
		}catch(Exception e){
			
			ResponseData resData	= new ResponseData();
			resData.setResponse_code(Const.INTERNAL_SERVER_ERROR);
			resData.setMessage(Const.FAIL);
			response = new ResponseEntity<ResponseData>(resData, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
}
