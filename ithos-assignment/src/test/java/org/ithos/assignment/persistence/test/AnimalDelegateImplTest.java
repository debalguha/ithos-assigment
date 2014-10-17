package org.ithos.assignment.persistence.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang.math.RandomUtils;
import org.ithos.assignment.dto.transformer.TransformerFactory;
import org.ithos.assignment.persistence.JPAAnimalDelegate;
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
	private org.ithos.assignment.dto.Animal currentAnimal;
	private JPAAnimalDelegate delegate;
	@Before
	public void setup(){
		delegate = (JPAAnimalDelegate)rootCtx.getBean(JPAAnimalDelegate.class);
	}
	@After
	public void tearDown(){
		if(currentAnimal!=null)
			delegate.deleteModel(currentAnimal.getEntityClass(), currentAnimal.getCodeNumber());
		currentAnimal = null;
	}	
	@Test
	public void shouldBeAbleToInsertAnimal() throws Exception{
		Animal anAnimal = createAnAnimal(delegate, RandomUtils.nextInt());
		currentAnimal = TransformerFactory.getInstance().getTransformer(anAnimal.getClass()).transform(anAnimal);
		assertNotNull(currentAnimal);
		assertNotNull(delegate.findAnimalByCodeNum(currentAnimal.getCodeNumber()));
	}
	@Test
	public void shouldBeAbleToInsert10Animals() throws Exception{
		for(int i=0;i<=100;i++){
			Animal anAnimal = createAnAnimal(delegate, RandomUtils.nextInt());
			currentAnimal = TransformerFactory.getInstance().getTransformer(anAnimal.getClass()).transform(anAnimal);
		}
		assertNotNull(currentAnimal);
		assertNotNull(delegate.findAnimalByCodeNum(currentAnimal.getCodeNumber()));
	}
	public Animal createAnAnimal(JPAAnimalDelegate delegate, int diff){
		Set<AnimalLocation> animalLocations = Sets.newHashSet();
		Animal animal = new Dog(RandomUtils.nextInt(), "Google-"+diff, AnimalType.MAMMAL, null, "Labrador", RandomUtils.nextDouble());
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
		Animal anAnimal = createAnAnimal(delegate, RandomUtils.nextInt());
		currentAnimal = TransformerFactory.getInstance().getTransformer(anAnimal.getClass()).transform(anAnimal);
		List<org.ithos.assignment.dto.Animal> animals = delegate.findAnimalByName(currentAnimal.getName().substring(2));
		assertNotNull(animals);
		assertFalse(animals.isEmpty());
	}
}
