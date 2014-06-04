package org.ithos.assignment.persistence.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang.math.RandomUtils;
import org.ithos.assignment.persistence.AnimalDelegate;
import org.ithos.assignment.persistence.model.Animal;
import org.ithos.assignment.persistence.model.AnimalLocation;
import org.ithos.assignment.persistence.model.AnimalType;
import org.ithos.assignment.persistence.model.Dog;
import org.ithos.assignment.persistence.model.Location;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Sets;

public class AnimalDelegateImplTest extends BaseTestCase{
	private Animal currentAnimal;
	private AnimalDelegate delegate;
	@Before
	public void setup(){
		delegate = (AnimalDelegate)rootCtx.getBean(AnimalDelegate.class);
	}
	@Test
	public void shouldBeAbleToInsertAnimal() throws Exception{
		currentAnimal = createAnAnimal(delegate, RandomUtils.nextInt());
		assertNotNull(currentAnimal);
		assertNotNull(delegate.findAnimalByCodeNumUsingJPA(currentAnimal.getCodeNumber()));
	}
	@Test
	public void shouldBeAbleToInsert10Animals() throws Exception{
		for(int i=0;i<=10;i++)
			currentAnimal = createAnAnimal(delegate, i);
		assertNotNull(currentAnimal);
		assertNotNull(delegate.findAnimalByCodeNumUsingJPA(currentAnimal.getCodeNumber()));
	}
	@After
	public void tearDown(){
		if(currentAnimal!=null)
			delegate.deleteModel(currentAnimal);
		currentAnimal = null;
	}
	public Animal createAnAnimal(AnimalDelegate delegate, int diff){
		Set<AnimalLocation> animalLocations = Sets.newHashSet();
		Animal animal = new Dog(RandomUtils.nextLong(), "Google-"+diff, AnimalType.MAMMAL, null, "Labrador", RandomUtils.nextDouble());
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
		return animal;
	}
	@Test
	public void shouldBeAbleTofindAnimalByName() throws Exception{
		currentAnimal = createAnAnimal(delegate, RandomUtils.nextInt());
		List<Animal> animals = delegate.findAnimalByNameUsingJPA(currentAnimal.getName());
		assertNotNull(animals);
		assertFalse(animals.isEmpty());
	}
}
