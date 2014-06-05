package org.ithos.assignment.dto.transformer;

import java.util.Set;

import org.ithos.assignment.dto.Animal;
import org.ithos.assignment.persistence.model.AnimalLocation;
import org.ithos.assignment.persistence.model.Dog;
import org.springframework.stereotype.Component;

import com.google.common.collect.Sets;

@Component
public class DogTransformer implements Transformer {

	public Animal transform(org.ithos.assignment.persistence.model.Animal animal) {
		if(animal == null)
			return null;
		Dog dog = (Dog)animal;
		Set<String> locations = Sets.newHashSet();
		for(AnimalLocation animalLocation : dog.getAnimalLocations())
			locations.add(animalLocation.getLocation().getPlace());
		return new org.ithos.assignment.dto.Dog(dog.getCodeNumber(), dog.getName(), dog.getType(), locations, dog.getBreed(), dog.getPawSize(), animal.getClass());
	}

}
