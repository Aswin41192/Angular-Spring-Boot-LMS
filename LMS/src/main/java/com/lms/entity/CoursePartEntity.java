package com.lms.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "course_parts")
public class CoursePartEntity extends BaseEntity{

	private String partId;
	private String partName;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "COURSE_FK",referencedColumnName = "ID")
	private CourseEntity course;

	public String getPartId() {
		return partId;
	}

	public void setPartId(String partId) {
		this.partId = partId;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public CourseEntity getCourse() {
		return course;
	}

	public void setCourse(CourseEntity course) {
		this.course = course;
	}

}
