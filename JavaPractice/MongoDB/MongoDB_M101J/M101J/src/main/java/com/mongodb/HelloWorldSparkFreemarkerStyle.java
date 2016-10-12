package com.mongodb;

import static spark.Spark.get;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.BasicConfigurator;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class HelloWorldSparkFreemarkerStyle {
	public static void main(String args[])
	{
		Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(
				HelloWorldSparkFreemarkerStyle.class, "/");
		
		BasicConfigurator.configure();
		
        Spark.get("/", new Route() {
            public Object handle(final Request request, final Response response){
            	StringWriter strWriter = new StringWriter();
        		try {
        			Template helloTemplate = configuration.getTemplate("hello.ftl");
        			Map<String, Object> helloMap = new HashMap<String, Object>();
        			helloMap.put("name", "Prag Thiru");
        			
        			try {
        				helloTemplate.process(helloMap, strWriter);
        			} catch (TemplateException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
        			
        		} catch (TemplateNotFoundException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		} catch (MalformedTemplateNameException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		} catch (ParseException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		} catch (IOException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
				return strWriter;
            }
        });
	}
}
