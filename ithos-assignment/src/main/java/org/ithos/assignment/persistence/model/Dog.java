package org.ithos.assignment.persistence.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="dog")
@DiscriminatorValue("DOG")
public class Dog extends Animal {
	@Column(name="breed", nullable=false)
	private String breed;
	@Column(name="paw_size", nullable=true)
	private double pawSize;
	public Dog() {}
	public Dog(long codeNumber, String name, AnimalType type, Set<AnimalLocation> animalLocations, String breed, double pawSize) {
		super(codeNumber, name, type, animalLocations);
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
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	@Override
	public Object getPk() {
		return getCodeNumber();
	}
	
	
}
