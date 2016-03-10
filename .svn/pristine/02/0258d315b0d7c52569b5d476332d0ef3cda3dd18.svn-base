package springmvc.model.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import springmvc.model.EducationBackground;
import springmvc.model.Student;
import springmvc.model.User;
import springmvc.model.dao.StudentDao;

@Repository
public class StudentDaoImpl implements StudentDao {
	 @PersistenceContext
	    private EntityManager entityManager;

	@Override
	public Student getStudentByUserId(Integer id) {
		// TODO Auto-generated method stub
		 return entityManager.find( Student.class, id );
	}

	@Override
	@Transactional
	public Student saveStudent(Student student) {
		return entityManager.merge(student);		
	}

	@Override
	@Transactional
	public EducationBackground saveEducationBackground(EducationBackground educationBackground) {
		// TODO Auto-generated method stub
		return entityManager.merge(educationBackground);
	}

	@Override
	public EducationBackground getEducationBackgroundById(Integer id) {
		// TODO Auto-generated method stub
		 return entityManager.find( EducationBackground.class, id );
	}

	@Override
	@Transactional
	public void removeEducationBackground(Integer educationalBG) {
		// TODO Auto-generated method stub
		entityManager.remove(entityManager.find(EducationBackground.class, educationalBG));
	}
	 
	 
}
