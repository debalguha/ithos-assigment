package org.ithos.assignment.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;

import org.ithos.assignment.dto.Animal;
import org.ithos.assignment.dto.transformer.TransformerFactory;
import org.ithos.assignment.persistence.model.BaseModel;
import org.ithos.assignment.persistence.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

@Repository
@Transactional
public class JPAAnimalDelegateImpl implements JPAAnimalDelegate{
	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private DataSource dataSource;
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor=Throwable.class)
	public void insertModel(BaseModel model) {
		entityManager.persist(model);
	}
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor=Throwable.class)
	public void updateModel(BaseModel model) {
		entityManager.merge(model);
	}
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor=Throwable.class)
	public void deleteModel(BaseModel model) {
		entityManager.remove(entityManager.find(model.getClass(), model.getPk()));
	}
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor=Throwable.class, readOnly = true)
	public Animal findAnimalByCodeNum(int codeNum) {
		org.ithos.assignment.persistence.model.Animal animal = entityManager.find(org.ithos.assignment.persistence.model.Animal.class, codeNum);
		return TransformerFactory.getInstance().getTransformer(animal.getClass()).transform(animal);
	}
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor=Throwable.class, readOnly = true)
	public List<Animal> findAnimalByName(String name) {
		Query query = entityManager.createQuery("select a from Animal a where name like :name");
		query.setParameter("name", "%".concat(name).concat("%"));
		List<org.ithos.assignment.persistence.model.Animal> resultList = query.getResultList();
		List<Animal> animals = Lists.newArrayList();
		for(org.ithos.assignment.persistence.model.Animal animal : resultList)
			animals.add(TransformerFactory.getInstance().getTransformer(animal.getClass()).transform(animal));
		return animals;
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	@Transactional(propagation = Propagation.REQUIRED)
	public Location findLocationByName(String location) {
		Query query = entityManager.createQuery("select l from Location l where place= :location");
		query.setParameter("location", location);
		return (Location)query.getSingleResult();
	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteModel(Class<?> clazz, int pk) {
		entityManager.remove(entityManager.find(clazz, pk));
	}

}
