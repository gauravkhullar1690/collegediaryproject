/**
 * 
 */
package com.collegediary.model.user;

/**
 * @author gaurav.khullar This class contains the list of UserTypes that we
 *         maintain. Initially we have Student Faculty Alumni
 */
public class UserType {
	private Long typeId;
	private String typeDescription;

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public String getTypeDescription() {
		return typeDescription;
	}

	public void setTypeDescription(String typeDescription) {
		this.typeDescription = typeDescription;
	}
}
