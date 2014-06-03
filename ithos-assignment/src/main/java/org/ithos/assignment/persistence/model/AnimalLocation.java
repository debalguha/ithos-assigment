package org.ithos.assignment.persistence.model;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;


@Entity
@AssociationOverrides({ @AssociationOverride(name = "pk.animal", joinColumns = @JoinColumn(name = "animal_code")), @AssociationOverride(name = "pk.location", joinColumns = @JoinColumn(name = "location_id")) })
public class AnimalLocation extends BaseModel{
	@EmbeddedId
	private AnimalLocationPK pk = new AnimalLocationPK();

	public AnimalLocationPK getPk() {
		return pk;
	}

	public void setPk(AnimalLocationPK pk) {
		this.pk = pk;
	}
	@Transient
	public Animal getAnimal() {
		return pk.getAnimal();
	}

	public void setAnimal(Animal animal) {
		pk.setAnimal(animal);
	}
	@Transient
	public Location getLocation() {
		return pk.getLocation();
	}

	public void setLocation(Location location) {
		pk.setLocation(location);
	}
}
