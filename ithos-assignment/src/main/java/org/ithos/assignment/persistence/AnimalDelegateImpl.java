package org.ithos.assignment.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;

import org.ithos.assignment.persistence.model.Animal;
import org.ithos.assignment.persistence.model.AnimalLocation;
import org.ithos.assignment.persistence.model.BaseModel;
import org.ithos.assignment.persistence.model.Location;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class AnimalDelegateImpl implements AnimalDelegate{
	@PersistenceContext
	private EntityManager entityManager;
	
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
		entityManager.remove(model);
	}
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor=Throwable.class, readOnly = true)
	public Animal findAnimalByCodeNumUsingJPA(long codeNum) {
		return entityManager.find(Animal.class, codeNum);
	}
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor=Throwable.class, readOnly = true)
	public List<Animal> findAnimalByNameUsingJPA(String name) {
		Query query = entityManager.createQuery("select a from Animal a where name=:name");
		query.setParameter("name", name);
		return query.getResultList();
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public Animal findAnimalByCodeNum(long codeNum) {
		return null;
	}
	
	public List<Animal> findAnimalByName(String name) {
		return null;
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

}
