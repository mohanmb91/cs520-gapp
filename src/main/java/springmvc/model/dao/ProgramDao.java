package springmvc.model.dao;
import java.util.List;

import springmvc.model.Program;
public interface ProgramDao {
	Program saveProgram(Program program);
	
	Program getProgramByID(Integer id);
	
	void removeProgram(Integer id);
	
	List<Program> getPrograms(Integer deptId);
}
