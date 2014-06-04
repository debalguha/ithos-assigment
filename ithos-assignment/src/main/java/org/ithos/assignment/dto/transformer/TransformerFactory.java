package org.ithos.assignment.dto.transformer;

import java.util.Map;

import org.ithos.assignment.persistence.model.Dog;

import com.google.common.collect.Maps;

public class TransformerFactory {
	private static Map<Class<?>, Transformer> transformerMap = Maps.newHashMap();
	private static TransformerFactory me = new TransformerFactory();
	public static TransformerFactory getInstance(){
		return me;
	}
	private TransformerFactory(){
		transformerMap.put(Dog.class, new DogTransformer());
	}
	
	public Transformer getTransformer(Class<?> clazz){
		return transformerMap.get(clazz);
	}
}
