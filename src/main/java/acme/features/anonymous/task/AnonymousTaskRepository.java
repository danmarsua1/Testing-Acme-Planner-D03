
package acme.features.anonymous.task;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.tasks.Task;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousTaskRepository extends AbstractRepository {

	@Query("select t from Task t where t.isPrivate=false")
	Collection<Task> findMany();

	@Query("select t from Task t where CURRENT_TIMESTAMP >= t.executionStart and CURRENT_TIMESTAMP < t.executionEnd and t.isPrivate=false order by t.workLoad DESC")
	Collection<Task> findManyActives();
	
	@Query("select t from Task t where t.id = ?1")
	Task findOneById(int id);

}

