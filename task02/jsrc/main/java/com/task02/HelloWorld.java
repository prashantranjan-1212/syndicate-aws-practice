package com.task02;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.syndicate.deployment.annotations.lambda.LambdaHandler;
import com.syndicate.deployment.model.RetentionSetting;

@LambdaHandler(lambdaName = "hello_world",
	roleName = "hello_world-role",
	isPublishVersion = true,
	aliasName = "${lambdas_alias_name}",
	methodName = "handleRequest",
	logsExpiration = RetentionSetting.SYNDICATE_ALIASES_SPECIFIED
)
public class HelloWorld implements RequestHandler<AwsProxyRequest, AwsProxyResponse> {
	private static SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;

	static {
		try{
			handler = SpringBootLambdaContainerHandler.getAwsProxyHandler(Application.class);
		} catch (ContainerInitializationException ex) {
			throw new RuntimeException("Unable to load spring boot application", ex);
		}
	}

	@Override
	public AwsProxyResponse handleRequest(AwsProxyRequest awsProxyRequest, Context context) {
		return handler.proxy(awsProxyRequest, context);
	}
}
