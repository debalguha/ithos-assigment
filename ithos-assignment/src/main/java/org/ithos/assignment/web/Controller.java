package org.ithos.assignment.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.ithos.assignment.dto.Animal;
import org.ithos.assignment.dto.transformer.TransformerFactory;
import org.ithos.assignment.persistence.JPAAnimalDelegate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;

@SuppressWarnings("serial")
public class Controller extends HttpServlet{
	
	private ClassPathXmlApplicationContext ctx;
	private JPAAnimalDelegate delegate;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		delegate = ctx.getBean(JPAAnimalDelegate.class);
		TransformerFactory.getInstance();
		super.init(config);
	}
	
	@Override
	public void destroy() {
		ctx.close();
		super.destroy();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("nameParam");
		String code = request.getParameter("codeParam");
		System.out.println("name: "+name+", code: "+code);
		List<Animal> animals = Lists.newArrayList();
		ControllerResponse controllerResponse = new ControllerResponse();
		if(!StringUtils.isEmpty(code)){
			Animal animal = null;
			try {
				animal = delegate.findAnimalByCodeNum(Integer.parseInt(code));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				controllerResponse.setResponseType(ResponseType.ERROR);
				controllerResponse.setErroReason("Invalid Code Number");
			}
			if(animal!=null){
				controllerResponse.setResponseType(ResponseType.SUCCESS);
				animals.add(animal);
			}else{
				controllerResponse.setResponseType(ResponseType.ERROR);
				controllerResponse.setErroReason("Unable to find an animal with the given code.");
			}
		}else{
			if(!StringUtils.isEmpty(name)){
				try {
					animals = delegate.findAnimalByName(name);
					controllerResponse.setResponseType(ResponseType.SUCCESS);
				} catch (Exception e) {
					e.printStackTrace();
					controllerResponse.setResponseType(ResponseType.ERROR);
					controllerResponse.setErroReason("no animal found with the given name!!");
				}
			}
		}
		controllerResponse.setAnimals(animals);
		response.setContentType("application/json");
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(response.getOutputStream(), controllerResponse);
	}

}
