package org.ithos.assignment.dto;

import java.util.Set;

import org.ithos.assignment.persistence.model.AnimalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Animal {
	private int codeNumber;
	private String name;
	private AnimalType type;
	private Set<String> animalLocations;
	@JsonIgnore
	private Class<?> entityClass;
	public int getCodeNumber() {
		return codeNumber;
	}
	public void setCodeNumber(int codeNumber) {
		this.codeNumber = codeNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public AnimalType getType() {
		return type;
	}
	public void setType(AnimalType type) {
		this.type = type;
	}
	public Set<String> getAnimalLocations() {
		return animalLocations;
	}
	public void setAnimalLocations(Set<String> animalLocations) {
		this.animalLocations = animalLocations;
	}
	public Animal(int codeNumber, String name, AnimalType type, Set<String> animalLocations, Class<?> entityClass) {
		super();
		this.codeNumber = codeNumber;
		this.name = name;
		this.type = type;
		this.animalLocations = animalLocations;
		this.entityClass = entityClass;
	}
	public Animal(){}
	public Class<?> getEntityClass() {
		return entityClass;
	}
	public void setEntityClass(Class<?> entityClass) {
		this.entityClass = entityClass;
	}
}
