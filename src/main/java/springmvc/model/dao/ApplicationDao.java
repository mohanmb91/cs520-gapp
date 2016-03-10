package springmvc.model.dao;
import java.util.List;

import springmvc.model.AdditionalDepartmentfeildvalues;
import springmvc.model.Application;
import springmvc.model.Department;
public interface ApplicationDao {
List<Application> getApplications();
Application getApplicationById(Integer id);

Application saveApplication(Application application);


AdditionalDepartmentfeildvalues saveFieldValues(AdditionalDepartmentfeildvalues feildValue);
}
