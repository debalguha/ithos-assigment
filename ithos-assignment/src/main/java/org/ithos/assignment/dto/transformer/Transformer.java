package org.ithos.assignment.dto.transformer;

import org.ithos.assignment.dto.Animal;

public interface Transformer {
	public Animal transform(org.ithos.assignment.persistence.model.Animal animal);
}
