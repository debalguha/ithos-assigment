package org.ithos.assignment.persistence.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.google.common.collect.Sets;

@Entity
@Table(name="animal", uniqueConstraints={
		@UniqueConstraint(columnNames={"name"})
 })
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="type", discriminatorType=DiscriminatorType.STRING)
public abstract class Animal extends BaseModel{
	@Id
	@Column(name="code_number")
	private long codeNumber;
	@Column(name="name")
	private String name;
	@Column(name="type", nullable = false)
	@Enumerated(EnumType.STRING)
	private AnimalType type;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="pk.animal")
	@Cascade(CascadeType.ALL)
	private Set<AnimalLocation> animalLocations;

	public Animal() {
		animalLocations = Sets.newHashSet();
	}

	public Animal(long codeNumber, String name, AnimalType type, Set<AnimalLocation> animalLocations) {
		super();
		this.codeNumber = codeNumber;
		this.name = name;
		this.type = type;
		this.animalLocations = animalLocations;
		if(this.animalLocations == null)
			animalLocations = Sets.newHashSet();
	}
	
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

	public Set<AnimalLocation> getAnimalLocations() {
		return animalLocations;
	}

	public void setAnimalLocations(Set<AnimalLocation> animalLocations) {
		this.animalLocations = animalLocations;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (codeNumber ^ (codeNumber >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animal other = (Animal) obj;
		if (codeNumber != other.codeNumber)
			return false;
		return true;
	}
	
}
