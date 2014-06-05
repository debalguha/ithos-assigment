package org.ithos.assignment.persistence;

import java.util.List;

public interface Delegate {
	public org.ithos.assignment.dto.Animal findAnimalByCodeNum(int codeNum);
	public List<org.ithos.assignment.dto.Animal> findAnimalByName(String name);
}
