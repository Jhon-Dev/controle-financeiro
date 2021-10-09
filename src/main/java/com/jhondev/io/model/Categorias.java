package com.jhondev.io.model;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Categorias  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String categoria;
	

}
