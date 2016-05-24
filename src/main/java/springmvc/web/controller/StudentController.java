package springmvc.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.json.JSONObject;

import springmvc.model.AdditionalDepartmentFeilds;
import springmvc.model.AdditionalDepartmentfeildvalues;
import springmvc.model.Application;
import springmvc.model.Department;
import springmvc.model.EducationBackground;
import springmvc.model.Program;
import springmvc.model.Student;
import springmvc.model.StudentAcademics;
import springmvc.model.User;
import springmvc.model.dao.ApplicationDao;
import springmvc.model.dao.DepartmentsDao;
import springmvc.model.dao.ProgramDao;
import springmvc.model.dao.StudentDao;
import springmvc.model.dao.UserDao;



@Controller
@SessionAttributes({"application","educationBackground","studentAcademics"})
public class StudentController {
	@Autowired
	private UserDao userDao;	
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private DepartmentsDao departmentDao;
	@Autowired
	private ProgramDao programDao;
	@Autowired
	private ApplicationDao applicationDao;
	@Autowired
	private ServletContext context;
	
	private File getFileDirectory(){
		String path = context.getRealPath("/WEB-INF/files");
		return new File(path);
	}
	
	@RequestMapping(value = "/student/studentHome.html", method = RequestMethod.GET)
	public String studentHomeGet(@RequestParam Integer userId,ModelMap models) {
		
		User user = userDao.getUser(userId);
		models.put("user", user);
		
		return "/student/studentHome";
	}
	@RequestMapping(value = "/student/downloadFile.html", method = RequestMethod.GET)
	public String downloadFile(@RequestParam String fileName,ModelMap models,HttpServletResponse response) throws IOException {
		response.setContentType("");
		response.setHeader("Content-Disposition", "inline; filename=" + fileName);
		FileInputStream in = new FileInputStream(new File(getFileDirectory(), fileName));
		OutputStream out = response.getOutputStream();

		byte buffer[] = new byte[2048];
		int byteRead;

		while ((byteRead = in.read(buffer)) > 0) {
			out.write(buffer, 0, byteRead);
		}
		in.close();
		return null;
		
	}
	
	@RequestMapping(value = "/student/editApplication.html", method = RequestMethod.GET)
	public String editApplication(@RequestParam Integer applicationId,HttpServletRequest request,SessionStatus status,ModelMap models) {
		Application application = applicationDao.getApplicationById(applicationId);
		Department department = departmentDao.getDepartmentById(application.getProgram().getDepartment().getId());
		models.put("department",department );
		models.put("programs", department.getPrograms());
		models.put("applicationId", application.getId());
		models.put("application", application);

		models.put("selectedProgram", application.getProgram().getName());
		models.put("term", application.getTerm());
		return "student/editApplication";
	}
	@RequestMapping(value = "/student/editApplication.html", method = RequestMethod.POST)
	public String editApplicationPost(@RequestParam Integer applicationId,HttpServletRequest request,SessionStatus status,ModelMap models) throws IllegalStateException, IOException {
		int programId = Integer.parseInt(request.getParameter("program"));
		String term = (request.getParameter("term"));
		Application application = applicationDao.getApplicationById(applicationId);
		application.setProgram(programDao.getProgramByID(programId));
		application.setTerm(term);
		
		if(application.getProgram().getDepartment().getRequirements().size() != 0){
			List<AdditionalDepartmentfeildvalues> feildValues = application.getAdditionalDepartmentFeildValues();
		
			List<MultipartFile> filess = ((DefaultMultipartHttpServletRequest) request).getFiles("files[]");
		
			int count = 0;
			for (AdditionalDepartmentfeildvalues eachFeild : feildValues) {
				if(eachFeild.getFeildDetails().getFeildType().equals("Text") || eachFeild.getFeildDetails().getFeildType().equals("Number")){
						eachFeild.setFeildValues(request.getParameter(eachFeild.getFeildDetails().getRequiredFeild()));
				}
				else if(eachFeild.getFeildDetails().getFeildType().equals("File")){
					MultipartFile eachFile = filess.get(count);
					if(eachFile.getSize() != 0){
						eachFile.transferTo(new File(getFileDirectory(),application.getId()+"_"+eachFile.getOriginalFilename()));
						eachFeild.setFeildValues(eachFile.getOriginalFilename());
					}
					count += 1;
				}
			}
			
			
			application.setAdditionalDepartmentFeildValues(feildValues);
		}
		applicationDao.saveApplication(application);
		status.setComplete();
		
		return "redirect: addNewApplication2.html?applicationId="+applicationId;
	}
	@RequestMapping(value = "/student/editBasicInfo.html", method = RequestMethod.GET)
	public String editBasicInfo(@RequestParam Integer applicationId,ModelMap models, SessionStatus status) {
		
		Application application = applicationDao.getApplicationById(applicationId);
		
		models.put("application", application);
		
		return "/student/editBasicInfo";
	}
	@RequestMapping(value = "/student/editBasicInfo.html", method = RequestMethod.POST)
	public String editBasicInfoPost(@ModelAttribute Application application, SessionStatus status,ModelMap models) {
		applicationDao.saveApplication(application);
		status.setComplete();		
		return "redirect: addNewApplication2.html?applicationId="+application.getId();
	}
	
	@RequestMapping(value = "/student/removeEducationBackground.html", method = RequestMethod.GET)
	public String removeEducationBackground(@RequestParam Integer educationalBG,@RequestParam Integer applicationId,SessionStatus status,ModelMap models) 
	{
		Application application = applicationDao.getApplicationById(applicationId);
		EducationBackground educationBackground = studentDao.getEducationBackgroundById(educationalBG);
		List<EducationBackground> educationalBGs = application.getEducationBackgrounds();
		educationalBGs.remove(educationBackground);
		application.setEducationBackgrounds(educationalBGs);
		applicationDao.saveApplication(application);
		
		studentDao.removeEducationBackground(educationalBG);
		return "redirect: addNewApplication2.html?applicationId="+application.getId();
	}
	@RequestMapping(value = "/student/addNewApplication1.html", method = RequestMethod.GET)
	public String addNewApplication1(@RequestParam Integer studentId,HttpServletRequest request,SessionStatus status,ModelMap models) {
		List<Department> department = departmentDao.getDepartments();
		models.put("studentId", studentId);
		models.put("department",department );
		return "student/addNewApplication1";
	}
	@RequestMapping(value = "/student/addNewApplication1.html", method = RequestMethod.POST)
	public String addNewApplication1Post(@RequestParam Integer studentId,@RequestParam(name="files") MultipartFile[] files,HttpServletRequest request,SessionStatus status,ModelMap models) throws IllegalStateException, IOException {
		int programId = Integer.parseInt(request.getParameter("program"));
		String term = (request.getParameter("term"));
		Program program = programDao.getProgramByID(programId);
		Student student = studentDao.getStudentByUserId(studentId);
		Application application = new Application();
		application.setUserName(student.getUserName());
		application.setEmailId(student.getEmailId());
		application.setFirstName(student.getFirstName());
		application.setLast_name(student.getLast_name());
		application.setTerm(term);
		application.setStudent(student);
		application.setIsSubmitted(false);
		application.setProgram(program);
		application=applicationDao.saveApplication(application);
		if(application.getProgram().getDepartment().getRequirements().size() != 0){
			List<AdditionalDepartmentfeildvalues> feildValues = application.getAdditionalDepartmentFeildValues();
			//if(feildValues == null){
				feildValues = new ArrayList<AdditionalDepartmentfeildvalues>();
			//}
			List<AdditionalDepartmentFeilds> requirements =  application.getProgram().getDepartment().getRequirements();
			List<MultipartFile> filess = ((DefaultMultipartHttpServletRequest) request).getFiles("files[]");
			int count =0;
			for (AdditionalDepartmentFeilds eachRequirement : requirements) {
				AdditionalDepartmentfeildvalues newfieldValues = new AdditionalDepartmentfeildvalues();
				if(eachRequirement.getFeildType().equals("Text") || eachRequirement.getFeildType().equals("Number")){
					newfieldValues.setApplication(application);
					newfieldValues.setFeildDetails(eachRequirement);
					String text;
					if(request.getParameter(eachRequirement.getRequiredFeild()) == null){
					text = "";
					}
					text = request.getParameter(eachRequirement.getRequiredFeild());
					newfieldValues.setFeildValues(text);
				}
				else if(eachRequirement.getFeildType().equals("File")){
					newfieldValues.setApplication(application);
					newfieldValues.setFeildDetails(eachRequirement);
					MultipartFile eachFile = filess.get(count);
					if(eachFile.getSize() != 0){
						eachFile.transferTo(new File(getFileDirectory(),application.getId()+"_"+eachFile.getOriginalFilename()));
						newfieldValues.setFeildValues(eachFile.getOriginalFilename());
					}
					
					
					count += 1;
				}
				feildValues.add(newfieldValues);
			}
			application.setAdditionalDepartmentFeildValues(feildValues);
		}
		application = applicationDao.saveApplication(application);
		List<Application> applications = student.getApplications();
		applications.add(application);
		student.setApplications(applications);
		studentDao.saveStudent(student);
		return "redirect: addNewApplication2.html?applicationId="+application.getId();
	}
	@RequestMapping(value = "/student/addNewApplication2.html", method = RequestMethod.GET)
	public String addNewApplication2(@RequestParam Integer applicationId,HttpServletRequest request,SessionStatus status,ModelMap models) {
		Application application = applicationDao.getApplicationById(applicationId);
		
		models.put("application", application);
		
		return "student/addNewApplication2";
	}


	@RequestMapping(value = "/student/viewApplication.html", method = RequestMethod.GET)
	public String viewApplication(@RequestParam Integer applicationId,ModelMap models) {
		Application application = applicationDao.getApplicationById(applicationId);
		models.put("application", application);
		return "student/viewApplication";
	}
	
	@RequestMapping(value = "/student/viewApplication.html", method = RequestMethod.POST)
	public String viewApplicationPost(@RequestParam Integer applicationId,ModelMap models) {
		Application application = applicationDao.getApplicationById(applicationId);
		models.put("userId", application.getStudent().getUser().getId());
		
		return "redirect: studentHome.html?userId="+application.getStudent().getUser().getId();
	}
	
	@RequestMapping(value = "/student/submitApplication.html", method = RequestMethod.GET)
	public String submitApplication(@RequestParam Integer applicationId,ModelMap models) {
		Application application = applicationDao.getApplicationById(applicationId);
		application.setIsSubmitted(true);
		application = applicationDao.saveApplication(application);
		
		return "redirect: studentHome.html?userId="+application.getStudent().getUser().getId();
		
	}
	@RequestMapping(value = "/student/addNewAcademics.html", method = RequestMethod.GET)
	public String addNewAcademics(@RequestParam Integer applicationId,ModelMap models) {
		Application application = applicationDao.getApplicationById(applicationId);
		
		StudentAcademics academics = application.getAcademics();
		if(application.getAcademics() == null){
			academics= new StudentAcademics();
		}
		models.put("studentAcademics", academics);
		models.put("applicationId", applicationId);
		return "student/addNewAcademics";
	}
	@RequestMapping(value = "/student/addNewAcademics.html", method = RequestMethod.POST)
	public String addNewAcademicsPost(@RequestParam Integer applicationId,@RequestParam(required=false) MultipartFile file0,@ModelAttribute StudentAcademics studentAcademics,ModelMap models,SessionStatus status) throws IllegalStateException, IOException {
		
		if(file0.getSize() != 0){
			file0.transferTo(new File(getFileDirectory(),applicationId+"_"+file0.getOriginalFilename()));
			studentAcademics.setFileLocationTranscript(file0.getOriginalFilename());
		}
		Application application = applicationDao.getApplicationById(applicationId);
		application.setAcademics(studentAcademics);
		applicationDao.saveApplication(application);
		status.setComplete();
		return "redirect: addNewApplication2.html?applicationId="+application.getId();
	}
	@ResponseBody
	@RequestMapping(value = "/student/GetProgram.html")
	public String getProgramsByDepartment(@RequestParam(value = "departmentID") Integer departmentID)
    {
		List<Program> programs = programDao.getPrograms(departmentID);
		String html = "";
		int count = 0;
			html += "<option selected disabled></option>";
			for (Program program : programs)
			{
				html += "<option value = '"+program.getId()+"'>"+program.getName()+"</option>";
			}
			html += "*****";
			Department department = departmentDao.getDepartmentById(departmentID);
			List<AdditionalDepartmentFeilds> additionalDepartmentFeilds = department.getDeptRequirements();
			for(AdditionalDepartmentFeilds eachADF : additionalDepartmentFeilds){
					count = 1;
				html += "<div class='form-group'>";
				html +="<label for='"+ eachADF.getRequiredFeild()+"'>"+eachADF.getRequiredFeild()+"</label>";
				if(eachADF.getFeildType().equals("Text")){
				if(eachADF.isRequired()){
					html +="<input type='text' class='form-control' name='"+ eachADF.getRequiredFeild()+"'  placeholder='"+ eachADF.getRequiredFeild()+"' required='required'/>";
					}
					else{
					html +="<input type='text' class='form-control' name='"+ eachADF.getRequiredFeild()+"'  placeholder='"+ eachADF.getRequiredFeild()+"'/>";
					}
				}
				else if(eachADF.getFeildType().equals("Number")){
					
					if(eachADF.isRequired()){
						html +="<input type='number' class='form-control' name='"+ eachADF.getRequiredFeild()+"'  placeholder='"+ eachADF.getRequiredFeild()+"' required='required' />";
						}
						else{
						html +="<input type='number' class='form-control' name='"+ eachADF.getRequiredFeild()+"'  placeholder='"+ eachADF.getRequiredFeild()+"' />";
						}
				}
				else{
					if(eachADF.isRequired()){
					html += "<input type='file' name = 'files[]' required='required'/>";
					}
					else{
						html += "<input type='file' name = 'files[]' />";	
					}
				}
				html+="</div>";
			}
			if(count == 0)
			{
				html+= "<h1>No additional requirement for the selcted department</h1>";
			}			
			return   html;
    }
	@RequestMapping(value = "/student/addNewEducationalBackground.html", method = RequestMethod.GET)
	public String addNewEducationalBackground(@RequestParam Integer applicationId,SessionStatus status,ModelMap models) {
		models.put("applicationId", applicationId);
		EducationBackground educationBackground = new EducationBackground();
		models.put("educationBackground", educationBackground);
		return "/student/addNewEducationalBackground";
	}
	@RequestMapping(value = "/student/addNewEducationalBackground.html", method = RequestMethod.POST)
	public String addNewEducationalBackgroundPost(@RequestParam Integer applicationId,@ModelAttribute EducationBackground educationBackground,SessionStatus status,ModelMap models) {
		Application application = applicationDao.getApplicationById(applicationId);
		educationBackground = studentDao.saveEducationBackground(educationBackground);
		List<EducationBackground> educationalBackgrounds = application.getEducationBackgrounds();
		if(educationalBackgrounds == null){
			educationalBackgrounds = new ArrayList<EducationBackground>();
		}
		educationalBackgrounds.add(educationBackground);
		application.setEducationBackgrounds(educationalBackgrounds);
		
		applicationDao.saveApplication(application);
		status.setComplete();
		return "redirect: addNewApplication2.html?applicationId="+application.getId();
	}
}

