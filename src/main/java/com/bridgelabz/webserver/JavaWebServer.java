package com.bridgelabz.webserver;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.io.IOException;

public class JavaWebServer {
	public static void main(String[] args) throws IOException {
		int port = 9004; // Port number to listen on

		HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

		// Define context paths and their corresponding handlers
		server.createContext("/", new RootHandler());
		server.createContext("/hello", new HelloHandler());

		server.setExecutor(null); // Use the default executor

		server.start();

		System.out.println("Server started on port " + port);
	}

	static class RootHandler implements HttpHandler {
		@Override
		public void handle(HttpExchange exchange) throws IOException {
			String response = "Welcome to the Java Web Server!";
			sendResponse(exchange, response);
		}
	}

	static class HelloHandler implements HttpHandler {
		@Override
		public void handle(HttpExchange exchange) throws IOException {
			String response = "Hello, World!";
			sendResponse(exchange, response);
		}
	}

	private static void sendResponse(HttpExchange exchange, String response) throws IOException {
		exchange.sendResponseHeaders(200, response.length());
		OutputStream outputStream = exchange.getResponseBody();
		outputStream.write(response.getBytes());
		outputStream.flush();
		outputStream.close();
	}
}
