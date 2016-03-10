package springmvc.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "additional_department_feilds")
public class AdditionalDepartmentFeilds {

@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

	public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public String getRequiredFeild() {
	return requiredFeild;
}

public void setRequiredFeild(String requiredFeild) {
	this.requiredFeild = requiredFeild;
}

public String getFeildType() {
	return feildType;
}

public void setFeildType(String feildType) {
	this.feildType = feildType;
}

public boolean isRequired() {
	return required;
}

public void setRequired(boolean required) {
	this.required = required;
}

public Department getDepartment() {
	return department;
}

public void setDepartment(Department department) {
	this.department = department;
}

	@Column(name = "required_feild")
	private String requiredFeild;
	
	@Column(name = "feild_type")
	private String feildType;
	
	private boolean required;
	
	@ManyToOne
	private Department department;

}
