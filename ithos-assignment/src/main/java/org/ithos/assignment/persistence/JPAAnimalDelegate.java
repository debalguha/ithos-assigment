package org.ithos.assignment.persistence;

import org.ithos.assignment.persistence.model.BaseModel;
import org.ithos.assignment.persistence.model.Location;

/**
 * 
 * @author debal
 * An interface is required as there could be generic implementation
 * which deals with Animal attribute. Whereas there could be specific implementation
 * for Dog or Cat which may deal with specific attributes. Like searching by
 * breed or Dog.
 *
 */
public interface JPAAnimalDelegate extends Delegate {
	public void insertModel(BaseModel model);
	public void updateModel(BaseModel model);
	public void deleteModel(BaseModel model);
	public void deleteModel(Class<?> clazz, int pk);
	public Location findLocationByName(String location);
}
