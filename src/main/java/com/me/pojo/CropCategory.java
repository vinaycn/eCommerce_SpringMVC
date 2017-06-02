package com.me.pojo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(
uniqueConstraints={@UniqueConstraint(columnNames={"name"})})
public class CropCategory {

	@Id
	@GeneratedValue
	private int id;
	private String name;
	@OneToMany(mappedBy="cropCategory",fetch=FetchType.LAZY)
	@NotFound(action=NotFoundAction.IGNORE)
	private List<Crop> cropList;
	/**
	 * @return the cropList
	 */
	public List<Crop> getCropList() {
		return cropList;
	}
	/**
	 * @param cropList the cropList to set
	 */
	public void setCropList(List<Crop> cropList) {
		this.cropList = cropList;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
