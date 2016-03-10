package springmvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "education_background")
public class EducationBackground {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
	
	private String degree;
	
	private String college;
	
	@Column(name="from_date")
	private String fromDate;
	
	@Column(name="to_date")
	private String toDate;
	
	@Column(name="degree_earned")
	private String degreeEarned;
	
	private String major;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getDegreeEarned() {
		return degreeEarned;
	}

	public void setDegreeEarned(String degreeEarned) {
		this.degreeEarned = degreeEarned;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

}
