package springmvc.model.dao.jpa;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import springmvc.model.*;
import springmvc.model.dao.DepartmentsDao;;

@Repository
public class DepartmentsDaoImpl implements DepartmentsDao  {
	  @PersistenceContext
	    private EntityManager entityManager;

	  @Override
	    public List<Department> getDepartments()
	    {
		  return entityManager.createQuery( "from Department order by id", Department.class )
		            .getResultList();
	    }
	  
		@Override
		public Department getDepartmentById(Integer id) {
			return entityManager.find(Department.class,id );
		}
		
		@Override
		@Transactional
		public void removeDepartment(Integer id) {
			entityManager.remove(entityManager.find(Department.class, id));	
		}
		
		@Override
		@Transactional
		public Department saveDepartment(Department department) {
			// TODO Auto-generated method stub
			return entityManager.merge(department);
		}
		@Override
		@Transactional
		 public AdditionalDepartmentFeilds savedditionalDepartmentFeilds(AdditionalDepartmentFeilds additionalDepartmentFeilds){
			return entityManager.merge(additionalDepartmentFeilds);
			
		}
		
		@Override
		public AdditionalDepartmentFeilds getAdditionalDepartmentFeilds(Integer id) {
			// TODO Auto-generated method stub
			return entityManager.find(AdditionalDepartmentFeilds.class,id );
		}


		@Override
		@Transactional
		public void removeAdditionalFeild(Integer id) {
			// TODO Auto-generated method stub
			entityManager.remove(entityManager.find(AdditionalDepartmentFeilds.class, id));	
		}


}
