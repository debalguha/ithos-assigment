package org.ithos.assignment.web;

import java.util.List;

import org.ithos.assignment.dto.Animal;


public class ControllerResponse {
	private ResponseType responseType;
	private List<Animal> animals;
	private String erroReason;
	public ResponseType getResponseType() {
		return responseType;
	}
	public void setResponseType(ResponseType responseType) {
		this.responseType = responseType;
	}
	public List<Animal> getAnimals() {
		return animals;
	}
	public void setAnimals(List<Animal> animals) {
		this.animals = animals;
	}
	public String getErroReason() {
		return erroReason;
	}
	public void setErroReason(String erroReason) {
		this.erroReason = erroReason;
	}
}
