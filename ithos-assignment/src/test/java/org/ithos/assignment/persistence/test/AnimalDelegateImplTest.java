package org.ithos.assignment.persistence.test;

import java.util.Set;

import org.apache.commons.lang.math.RandomUtils;
import org.ithos.assignment.persistence.AnimalDelegate;
import org.ithos.assignment.persistence.model.Animal;
import org.ithos.assignment.persistence.model.AnimalLocation;
import org.ithos.assignment.persistence.model.AnimalType;
import org.ithos.assignment.persistence.model.Dog;
import org.ithos.assignment.persistence.model.Location;
import org.junit.Test;

import com.google.common.collect.Sets;

public class AnimalDelegateImplTest extends BaseTestCase{
	@Test
	public void shouldBeAbleToInsertAnimal() throws Exception{
		AnimalDelegate delegate = (AnimalDelegate)rootCtx.getBean(AnimalDelegate.class);
		Set<AnimalLocation> animalLocations = Sets.newHashSet();
		Animal animal = new Dog(RandomUtils.nextLong(), "Google", AnimalType.MAMMAL, null, "Labrador", 2.44d);
		for(String location : locationNames){
			Location locatonModel = delegate.findLocationByName(location);
			AnimalLocation animalLocation = new AnimalLocation();
			animalLocation.setAnimal(animal);
			animalLocation.setLocation(locatonModel);
			animalLocations.add(animalLocation);
		}
		animal.setAnimalLocations(animalLocations);
		//delegate.createAnimalsAndAssignLocationsToThem(animal);
		delegate.insertModel(animal);
	}
}
