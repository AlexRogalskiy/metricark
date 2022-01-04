package com.nexcloud.api.openstack.service;

import com.nexcloud.api.client.OpenstackClient;
import com.nexcloud.api.domain.ResponseData;
import com.nexcloud.util.Const;
import com.nexcloud.util.Util;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class OpenstackService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpenstackService.class);
    private static final JSONParser PARSER = new JSONParser();

    private final OpenstackClient openstackClient;

    @Autowired
    public OpenstackService(OpenstackClient openstackClient) {
        this.openstackClient = openstackClient;
    }

    @Value("${openstack.endpoint}")
    private String ENDPOINT;

    public ResponseEntity<ResponseData> accessOpenstack(String port, String uri, String projectName, String domainId, String endpoint) {

        if (StringUtils.isEmpty(endpoint)) {
            endpoint = ENDPOINT;
        }

        String targetUrl = endpoint + ":" + port + uri;
        return executeAccessOpenstack(targetUrl, projectName, domainId, endpoint);
    }

    public ResponseEntity<ResponseData> accessOpenstack(String uri, String projectName, String domainId, String endpoint) {
        String targetUrl = ENDPOINT + uri;
        return executeAccessOpenstack(targetUrl, projectName, domainId, endpoint);
    }

    public ResponseEntity<ResponseData> parseOpenstackNetworks(ResponseEntity<ResponseData> rawResponse) {

        ResponseEntity<ResponseData> response;
        ResponseData resData = new ResponseData();

        try {

            String rawResponseBody = (String) rawResponse.getBody().getData();
            JSONObject jsonObject = (JSONObject) PARSER.parse(rawResponseBody);
            JSONArray jsonArray = (JSONArray) jsonObject.get("networks");

            resData.setData(jsonArray);
            resData.setStatus("success");
            resData.setResponse_code(200);
            resData.setMessage(Const.SUCCESS);

            response = new ResponseEntity<>(resData, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.warn("Parsing failed", e);
            resData.setResponse_code(Const.INTERNAL_SERVER_ERROR);
            resData.setMessage(Const.FAIL);
            resData.setMessage(Util.makeStackTrace(e));
            response = new ResponseEntity<>(resData, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    public ResponseEntity<ResponseData> getErrorResponse() {
        ResponseData resData = new ResponseData();
        resData.setResponse_code(Const.INTERNAL_SERVER_ERROR);
        resData.setMessage(Const.FAIL);

        return new ResponseEntity<>(resData, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ResponseData> executeAccessOpenstack(String targetUrl, String projectName, String domainId, String endpoint) {
        ResponseEntity<ResponseData> response;
        ResponseData resData = new ResponseData();
        ResponseEntity<String> entityData;

        try {
            entityData = openstackClient.executeHttpRequest(targetUrl, projectName, domainId, endpoint);

            resData.setData(entityData.getBody());
            resData.setStatus("success");

            resData.setResponse_code(entityData.getStatusCodeValue());
            resData.setMessage(Const.SUCCESS);

            response = new ResponseEntity<>(resData, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            resData.setResponse_code(Const.INTERNAL_SERVER_ERROR);
            resData.setMessage(Const.FAIL);
            resData.setMessage(Util.makeStackTrace(e));
            response = new ResponseEntity<>(resData, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    public String getAccessOpenstackPayload(String targetUrl, String projectName, String domainId, String endpoint) {
        String networksTarget = "http://192.168.1.14:9696/v2.0/networks";
        String portsTarget = "http://192.168.1.14:9696/v2.0/ports";
        String routersTarget = "http://192.168.1.14:9696/v2.0/routers";
        ResponseEntity<String> networksResponse = openstackClient.executeHttpRequest(networksTarget, projectName, domainId, endpoint);
        ResponseEntity<String> portsResponse = openstackClient.executeHttpRequest(portsTarget, projectName, domainId, endpoint);
        ResponseEntity<String> routersResponse = openstackClient.executeHttpRequest(routersTarget, projectName, domainId, endpoint);

        try {
            JSONObject parsedNetworks = (JSONObject) PARSER.parse(networksResponse.getBody());
            JSONArray networksJson = (JSONArray) parsedNetworks.get("networks");

            JSONObject parsedPorts = (JSONObject) PARSER.parse(portsResponse.getBody());
            JSONArray portsJson = (JSONArray) parsedPorts.get("ports");

            JSONObject parsedRouters = (JSONObject) PARSER.parse(routersResponse.getBody());
            JSONArray routersJson = (JSONArray) parsedRouters.get("routers");

            JSONObject result = new JSONObject();
            result.put("networks", networksJson);
            result.put("ports", portsJson);
            result.put("routers", routersJson);

            return result.toJSONString();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return networksResponse.getBody();
    }
}
