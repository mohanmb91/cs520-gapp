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
@Table(name = "additional_department_feild_values")
public class AdditionalDepartmentfeildvalues {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
	
	@Column(name = "feild_values")
	private String feildValues;
	
	@OneToOne(cascade = CascadeType.ALL)
	private AdditionalDepartmentFeilds feildDetails;
	
	@ManyToOne
	private Application application;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFeildValues() {
		return feildValues;
	}

	public void setFeildValues(String feildValues) {
		this.feildValues = feildValues;
	}

	public AdditionalDepartmentFeilds getFeildDetails() {
		return feildDetails;
	}

	public void setFeildDetails(AdditionalDepartmentFeilds feildDetails) {
		this.feildDetails = feildDetails;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}
	
}
