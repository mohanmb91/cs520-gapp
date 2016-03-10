package springmvc.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "applications")
public class Application {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getCitizenship() {
		return citizenship;
	}

	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}

	@Column(name="first_name")
    private String firstName;

	@Column(name = "last_name")
	private String last_name;
    
	@Column(name="user_name")
    private String userName;
	
	@Column(name="email_id")
    private String emailId;
    
	private String cin;
    
	@Column(name="phone_number")
    private String phoneNumber;
    
    private String gender;
    @Column(name="date_of_birth")
    private String dateOfBirth;
    
    private String citizenship;
    
	
	private boolean isSubmitted;
	
	private String term;
	
	@OneToMany(cascade = CascadeType.ALL)
    private List<EducationBackground> educationBackgrounds;
    
    @OneToOne(cascade = CascadeType.ALL)
    private StudentAcademics academics;
    
	

	public boolean getIsSubmitted() {
		return isSubmitted;
	}

	public void setIsSubmitted(boolean isSubmitted) {
		this.isSubmitted = isSubmitted;
	}

	public List<EducationBackground> getEducationBackgrounds() {
		return educationBackgrounds;
	}

	public void setEducationBackgrounds(List<EducationBackground> educationBackgrounds) {
		this.educationBackgrounds = educationBackgrounds;
	}

	public StudentAcademics getAcademics() {
		return academics;
	}

	public void setAcademics(StudentAcademics academics) {
		this.academics = academics;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public List<AdditionalDepartmentfeildvalues> getAdditionalDepartmentFeildValues() {
		return additionalDepartmentFeildValues;
	}

	public void setAdditionalDepartmentFeildValues(List<AdditionalDepartmentfeildvalues> additionalDepartmentFeildValues) {
		this.additionalDepartmentFeildValues = additionalDepartmentFeildValues;
	}

	@OneToMany(cascade = CascadeType.ALL)
	private List<AdditionalDepartmentfeildvalues> additionalDepartmentFeildValues;
	
	@ManyToOne
	private Student student;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Program program;
	
	@OneToMany
	private List<StatusUpdate> statusHistory;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

	public List<StatusUpdate> getStatusHistory() {
		return statusHistory;
	}

	public void setStatusHistory(List<StatusUpdate> statusHistory) {
		this.statusHistory = statusHistory;
	}
	
	
	
}
