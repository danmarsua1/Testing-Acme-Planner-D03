
package acme.features.usermanager.task;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.roles.Usermanager;
import acme.entities.tasks.Task;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface UsermanagerTaskRepository extends AbstractRepository {

	@Query("select t from Task t where t.id =?1")
	Task findOneById(int id);

	@Query("select m from Usermanager m where m.id =?1")
	Usermanager findManagerById(int activeRoleId);

	@Query("select m.tasks from Usermanager m where m.id=?1")
	Collection<Task> findManyByManagerId(int managerId);

}
