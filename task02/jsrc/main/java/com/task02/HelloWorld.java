package com.task02;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.model.HttpApiV2ProxyRequest;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.syndicate.deployment.annotations.lambda.LambdaHandler;
import com.syndicate.deployment.model.RetentionSetting;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@LambdaHandler(lambdaName = "hello_world",
	roleName = "hello_world-role",
	isPublishVersion = true,
	aliasName = "${lambdas_alias_name}",
	logsExpiration = RetentionSetting.SYNDICATE_ALIASES_SPECIFIED
)
public class HelloWorld implements RequestStreamHandler {
	private static SpringBootLambdaContainerHandler<HttpApiV2ProxyRequest, AwsProxyResponse> handler;

	static {
		try{
			handler = SpringBootLambdaContainerHandler.getHttpApiV2ProxyHandler(Application.class);
		} catch (ContainerInitializationException ex) {
			throw new RuntimeException("Unable to load spring boot application", ex);
		}
	}

	@Override
	public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
		handler.proxyStream(inputStream, outputStream, context);
	}
}
