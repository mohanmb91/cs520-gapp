package springmvc.model.dao.jpa;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import springmvc.model.*;
import springmvc.model.dao.ProgramDao;;

@Repository
public class ProgramDaoImpl implements ProgramDao{
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public Program saveProgram(Program program) {
		// TODO Auto-generated method stub
		return entityManager.merge(program);
	}

	@Override
	public Program getProgramByID(Integer id) {
		// TODO Auto-generated method stub
		return entityManager.find(Program.class,id );
	}
	
	@Override
	@Transactional
	public void removeProgram(Integer id) {
		entityManager.remove(entityManager.find(Program.class, id));
		
	}

	@Override
	public List<Program> getPrograms(Integer deptId) {
		return entityManager.createQuery( "select p from Program p where p.department.id = "+deptId+" order by p.id", Program.class )
	            .getResultList();
	}
	
}
