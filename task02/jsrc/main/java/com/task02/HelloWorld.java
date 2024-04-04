package com.task02;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.syndicate.deployment.annotations.lambda.LambdaHandler;
import com.syndicate.deployment.model.RetentionSetting;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.Map;

//@LambdaHandler(lambdaName = "hello_world",
//	roleName = "hello_world-role",
//	isPublishVersion = true,
//	aliasName = "${lambdas_alias_name}",
//	logsExpiration = RetentionSetting.SYNDICATE_ALIASES_SPECIFIED
//)
public class HelloWorld implements RequestHandler<Object, Map<String, Object>> {
	public Map<String, Object> handleRequest(Object request, Context context) {
		System.out.println("Hello from lambda");
		Map<String, Object> resultMap = new LinkedHashMap<>();
		resultMap.put("statusCode", 200);
		resultMap.put("message", "Hello from Lambda");
		return resultMap;
	}
}
