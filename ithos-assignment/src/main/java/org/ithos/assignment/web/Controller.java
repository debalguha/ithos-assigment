package org.ithos.assignment.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.ithos.assignment.dto.transformer.TransformerFactory;
import org.ithos.assignment.persistence.AnimalDelegate;
import org.ithos.assignment.persistence.model.Animal;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;

@SuppressWarnings("serial")
public class Controller extends HttpServlet{
	
	private ClassPathXmlApplicationContext ctx;
	private AnimalDelegate delegate;
	private TransformerFactory factory;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		delegate = ctx.getBean(AnimalDelegate.class);
		factory = TransformerFactory.getInstance();
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
				animal = delegate.findAnimalByCodeNumUsingJPA(Long.parseLong(code));
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
					animals = delegate.findAnimalByNameUsingJPA(name);
					controllerResponse.setResponseType(ResponseType.SUCCESS);
				} catch (Exception e) {
					e.printStackTrace();
					controllerResponse.setResponseType(ResponseType.ERROR);
					controllerResponse.setErroReason("no animal found with the given name!!");
				}
			}
		}
		controllerResponse.setAnimals(convertToDTO(animals));
		response.setContentType("application/json");
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(response.getOutputStream(), controllerResponse);
	}

	private List<org.ithos.assignment.dto.Animal> convertToDTO(List<Animal> animals) {
		if(animals == null)
			return null;
		List<org.ithos.assignment.dto.Animal> animalDtos = Lists.newArrayList();
		/*for(Animal animal : animals){
			Set<String> locations = Sets.newHashSet();
			for(AnimalLocation animalLocation : animal.getAnimalLocations())
				locations.add(animalLocation.getLocation().getPlace());
			org.ithos.assignment.dto.Animal animalDto = new org.ithos.assignment.dto.Animal(animal.getCodeNumber(), animal.getName(), animal.getType(), locations);
			animalDtos.add(animalDto);
		}*/
		for(Animal animal : animals)
			animalDtos.add(factory.getTransformer(animal.getClass()).transform(animal));
		return animalDtos;
	}
}
