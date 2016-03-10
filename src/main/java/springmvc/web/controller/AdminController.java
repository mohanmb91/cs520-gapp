package springmvc.web.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import springmvc.model.AdditionalDepartmentFeilds;
import springmvc.model.Department;
import springmvc.model.Program;
import springmvc.model.Student;
import springmvc.model.User;
import springmvc.model.UserRole;
import springmvc.model.dao.DepartmentsDao;
import springmvc.model.dao.ProgramDao;
import springmvc.model.dao.StudentDao;
import springmvc.model.dao.UserDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;


@Controller
public class AdminController {
	@Autowired
	private UserDao userDao;
	@Autowired
	private DepartmentsDao departmentsDao;
	@Autowired
	private ProgramDao programDao;
	@Autowired
	private StudentDao studentDao;

	@RequestMapping("/login.html")
	public String index()
	{
		return "login";
	}

	@RequestMapping("/admin.html")
	public String admin()
	{
		return "admin";
	}
	@RequestMapping(value = "/AddNewRequirement.html", method = RequestMethod.GET)
	public String addNewRequirementGet(@RequestParam Integer deptId,HttpServletRequest request, ModelMap models) {

		Department dept = departmentsDao.getDepartmentById(deptId);
		models.put("department", dept);
		return "AddNewRequirement";
	}
	@RequestMapping(value = "/AddNewDepartment.html", method = RequestMethod.GET)
	public String addNewDepartmentGet(HttpServletRequest request, ModelMap models) {

		models.put("department", new Department());
		return "AddNewDepartment";
	}
	@RequestMapping(value = "/AddNewDepartment.html", method = RequestMethod.POST)
	public String addNewDepartmentPost(HttpServletRequest request, ModelMap models) {
		String departmentName = request.getParameter("departmentName");
		//List<Department> departments = departmentsDao.getDepartments();
		Department department = new Department();

		department.setName(departmentName);
		//departments.add(department);
		departmentsDao.saveDepartment(department);
		return "redirect:managedepartments.html";
	}
	@RequestMapping(value = "/AddNewRequirement.html", method = RequestMethod.POST)
	public String addNewRequirementPost(@RequestParam Integer deptId,HttpServletRequest request, ModelMap models) {

		String requirementName = request.getParameter("requirementName");
		String requirementType = request.getParameter("requirementType");
		String isRequirement = request.getParameter("isRequirement");
		boolean isrequired = false;
		if(isRequirement.equals("required")){
			isrequired = true;	
		}
		Department dept = departmentsDao.getDepartmentById(deptId);
		AdditionalDepartmentFeilds additionalDepartmentFeilds = new AdditionalDepartmentFeilds();
		additionalDepartmentFeilds.setRequiredFeild(requirementName);
		additionalDepartmentFeilds.setFeildType(requirementType);
		additionalDepartmentFeilds.setRequired(isrequired);
		additionalDepartmentFeilds.setDepartment(dept);
		additionalDepartmentFeilds = departmentsDao.savedditionalDepartmentFeilds(additionalDepartmentFeilds);

		dept.setRequirements(additionalDepartmentFeilds);
		models.put("deptId", deptId);
		models.put("department", dept);
		dept = departmentsDao.saveDepartment(dept);

		return "redirect:ViewDepartment.html";
	}
	@RequestMapping(value = "/AddNewProgram.html", method = RequestMethod.GET)
	public String addNewProgramGet(@RequestParam Integer deptId,HttpServletRequest request, ModelMap models) {

		Department dept = departmentsDao.getDepartmentById(deptId);
		models.put("department", dept);
		return "AddNewProgram";
	}

	@RequestMapping(value = "/AddNewProgram.html", method = RequestMethod.POST)
	public String addNewProgramPost(@RequestParam Integer deptId,HttpServletRequest request, ModelMap models) {

		String programName = request.getParameter("programName");
		Department dept = departmentsDao.getDepartmentById(deptId);
		Program program = new Program();
		program.setName(programName);
		program.setDepartment(dept);
		program = programDao.saveProgram(program);
		dept.setPrograms(program);
		models.put("deptId", deptId);
		models.put("department", dept);
		dept = departmentsDao.saveDepartment(dept);

		return "redirect:ViewDepartment.html";
	}
	@RequestMapping(value = "/DeleteDepartment.html", method = RequestMethod.GET)
	public String DeleteDepartmentGet(HttpServletRequest request, ModelMap models) {
		int deptId = Integer.parseInt(request.getParameter("deptId"));
		departmentsDao.removeDepartment(deptId);
		return "redirect:managedepartments.html";
	}
	@RequestMapping(value = "/DeleteProgram.html", method = RequestMethod.GET)
	public String deleteProgram(HttpServletRequest request, ModelMap models) {
		int deptId = Integer.parseInt(request.getParameter("deptId"));
		int programId = Integer.parseInt(request.getParameter("programId"));

		Department dept = departmentsDao.getDepartmentById(deptId);
		List<Program> programs = dept.getPrograms();
		programs.remove(programDao.getProgramByID(programId));
		dept.setPrograms(programs);
		departmentsDao.saveDepartment(dept);

		programDao.removeProgram(programId);
		models.put("deptId", deptId);
		return "redirect:ViewDepartment.html";
	}
	@RequestMapping(value = "/deleteFeild.html", method = RequestMethod.GET)
	public String deleteFeild(HttpServletRequest request, ModelMap models) {
		int deptId = Integer.parseInt(request.getParameter("deptId"));
		int requirementId = Integer.parseInt(request.getParameter("requirementId"));

		Department dept = departmentsDao.getDepartmentById(deptId);
		List<AdditionalDepartmentFeilds> requirements = dept.getRequirements();
		requirements.remove(departmentsDao.getAdditionalDepartmentFeilds(requirementId));
		dept.setRequirements(requirements);
		departmentsDao.saveDepartment(dept);

		departmentsDao.removeAdditionalFeild(requirementId);

		models.put("deptId", deptId);
		return "redirect:ViewDepartment.html";
	}

	@RequestMapping(value = "/EditFeild.html", method = RequestMethod.GET)
	public String editFeildGet( HttpServletRequest request, ModelMap models) {
		int deptId = Integer.parseInt(request.getParameter("deptId"));
		int requirementId = Integer.parseInt(request.getParameter("requirementId"));
		Department dept = departmentsDao.getDepartmentById(deptId);
		AdditionalDepartmentFeilds additionalDepartmentFeilds = departmentsDao.getAdditionalDepartmentFeilds(requirementId);
		models.put("department", dept);
		models.put("additionalfeilds", additionalDepartmentFeilds);


		return "EditFeild";
	}
	@RequestMapping(value = "/EditFeild.html", method = RequestMethod.POST)
	public String editFeildPost(HttpServletRequest request, ModelMap models) {
		int deptId = Integer.parseInt(request.getParameter("deptId"));
		int requirementId = Integer.parseInt(request.getParameter("requirementId"));
		String requirementName = request.getParameter("requirementName");
		String requirementType = request.getParameter("requirementType");
		String isRequirement = request.getParameter("isRequirement");
		boolean isrequired = false;
		if(isRequirement.equals("required")){
			isrequired = true;	
		}
		Department dept = departmentsDao.getDepartmentById(deptId);

		AdditionalDepartmentFeilds additionalDepartmentFeilds = departmentsDao.getAdditionalDepartmentFeilds(requirementId); 

		additionalDepartmentFeilds.setRequiredFeild(requirementName);
		additionalDepartmentFeilds.setFeildType(requirementType);
		additionalDepartmentFeilds.setRequired(isrequired);


		additionalDepartmentFeilds = departmentsDao.savedditionalDepartmentFeilds(additionalDepartmentFeilds);

		models.put("deptId", deptId);
		models.put("department", dept);
		models.put("programId", requirementId);
		return "redirect:ViewDepartment.html";
	}
	@RequestMapping(value = "/EditProgram.html", method = RequestMethod.GET)
	public String editProgramGet(HttpServletRequest request, ModelMap models) {
		int deptId = Integer.parseInt(request.getParameter("deptId"));
		int programId = Integer.parseInt(request.getParameter("programId"));
		Department dept = departmentsDao.getDepartmentById(deptId);
		Program program = programDao.getProgramByID(programId);
		models.put("department", dept);
		models.put("programId", programId);
		models.put("programName", program.getName());

		return "EditProgram";
	}
	@RequestMapping(value = "/EditDepartment.html", method = RequestMethod.GET)
	public String editDepartmentGet(HttpServletRequest request, ModelMap models) {
		int deptId = Integer.parseInt(request.getParameter("deptId"));
		String deptName = request.getParameter("departmentName");
		Department dept = departmentsDao.getDepartmentById(deptId);

		models.put("department", dept);

		return "EditDepartment";
	}

	@RequestMapping(value = "/EditDepartment.html", method = RequestMethod.POST)
	public String editDepartmentPost(HttpServletRequest request, ModelMap models) {
		int deptId = Integer.parseInt(request.getParameter("deptId"));
		String deptName = request.getParameter("departmentName");
		Department dept = departmentsDao.getDepartmentById(deptId);

		models.put("department", dept);


		dept.setName(deptName);
		departmentsDao.saveDepartment(dept);
		return "redirect:managedepartments.html";
	}
	@RequestMapping(value = "/EditProgram.html", method = RequestMethod.POST)
	public String editProgramPost(HttpServletRequest request, ModelMap models) {
		int deptId = Integer.parseInt(request.getParameter("deptId"));
		String programName = request.getParameter("programName");
		int programId = Integer.parseInt(request.getParameter("programId"));
		Department dept = departmentsDao.getDepartmentById(deptId);
		Program program = programDao.getProgramByID(programId);
		program.setName(programName);
		program = programDao.saveProgram(program);

		models.put("deptId", deptId);
		models.put("department", dept);
		models.put("programId", programId);
		return "redirect:ViewDepartment.html";
	}
	@RequestMapping(value = "/login.html", method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpSession session) {
		List<User> users =  userDao.getUsers(); 
		User user =null;
		System.out.println(request.getParameter("email"));
		System.out.println(request.getParameter("password"));
		for(User eachUser : users){
			if(eachUser.getEmailId().equalsIgnoreCase(request.getParameter("email")) && eachUser.getPassword().equals(request.getParameter("password"))){
				user = eachUser;
				session.setAttribute("username", user.getFirstName());
				if (user.getUserRole().getId()== 3)
					return "redirect:/admin.html";
				else if (user.getUserRole().getId() == 2)
					return "redirect:/staff.html";
				else
					return "redirect:/student/studentHome.html?userId="+user.getId();	
			}
		}
		return "redirect:/login.html";
	}

	@RequestMapping("/logout.html")
	public String logout(HttpSession session, SessionStatus status) {
		status.setComplete();
		session.invalidate();
		return "redirect:/login.html";
	}
	@RequestMapping(value = "/managedepartments.html", method = RequestMethod.GET)
	public String manageDepartments(ModelMap models) {

		System.out.println("ManageDepartments get");
		List<Department> departments = new ArrayList<Department>();
		departments = departmentsDao.getDepartments();

		models.put("departments", departments);
		models.put("newDepartment", new Department());

		return "managedepartments";
	}
	@RequestMapping(value = "/Register.html", method = RequestMethod.GET)
	public String registerGet(HttpServletRequest request, ModelMap models) {
		models.put("user", new User() );
		
		return "registration";
	}
	@RequestMapping(value = "/Register.html", method = RequestMethod.POST)
	public String registerPost(HttpServletRequest request,@ModelAttribute User user, ModelMap models) {
		
		UserRole userRole = new UserRole();

		List<User> users = userDao.getUsers();
		String error = "no";
		for (User eachUser : users) {
			if(eachUser.getEmailId().equalsIgnoreCase(user.getEmailId())){
				error = "yes";
				models.put("error", error);
				return "registration";
			}
		}
		models.put("error", error);
		userRole = userDao.getUserRoleById(1);
		
		Student student = new Student();
		student.setFirstName(user.getFirstName());
		student.setLast_name(user.getLastName());
		student.setUserName(user.getFirstName());
		student.setEmailId(user.getEmailId());
		
		user.setUserRole(userRole);
		student.setUser(user);
		user.setStudent(student);
		
		user = userDao.saveUser(user);
		models.put("userId", user.getId());
	
		return "redirect:/student/studentHome.html";//?userId="+user.getId();
	}
	
	@RequestMapping(value = "/ViewDepartment.html", method = RequestMethod.GET)
	public String viewDepartment(HttpServletRequest request, ModelMap models) {
		int deptId = Integer.parseInt(request.getParameter("deptId"));
		Department dept = departmentsDao.getDepartmentById(deptId);

		models.put("department", dept);

		return "ViewDepartmentAdmin";
	}
}
