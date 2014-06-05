package org.ithos.assignment.dto;

import java.util.Set;

import org.ithos.assignment.persistence.model.AnimalType;

public class Dog extends Animal{
	private String breed;
	private double pawSize;
	public Dog(){}
	public Dog(int codeNumber, String name, AnimalType type, Set<String> animalLocations, String breed, double pawSize, Class<?> entityClass) {
		super(codeNumber, name, type, animalLocations, entityClass);
		this.breed = breed;
		this.pawSize = pawSize;
	}
	public String getBreed() {
		return breed;
	}
	public void setBreed(String breed) {
		this.breed = breed;
	}
	public double getPawSize() {
		return pawSize;
	}
	public void setPawSize(double pawSize) {
		this.pawSize = pawSize;
	}
	
}
