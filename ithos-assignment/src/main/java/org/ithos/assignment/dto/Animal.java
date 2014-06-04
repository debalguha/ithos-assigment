package org.ithos.assignment.dto;

import java.util.Set;

import org.ithos.assignment.persistence.model.AnimalType;

public class Animal {
	private long codeNumber;
	private String name;
	private AnimalType type;
	private Set<String> animalLocations;
	public long getCodeNumber() {
		return codeNumber;
	}
	public void setCodeNumber(long codeNumber) {
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
	public Animal(long codeNumber, String name, AnimalType type, Set<String> animalLocations) {
		super();
		this.codeNumber = codeNumber;
		this.name = name;
		this.type = type;
		this.animalLocations = animalLocations;
	}
	public Animal(){}
}
