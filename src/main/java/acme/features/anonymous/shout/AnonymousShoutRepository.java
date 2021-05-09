
package acme.features.anonymous.shout;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.shouts.Shout;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousShoutRepository extends AbstractRepository {

	@Query("Select s from Shout s")
	Collection<Shout> findMany();

	@Query("select s from Shout s where ABS(FUNCTION('DATEDIFF', s.moment, CURRENT_TIMESTAMP)) < 31 order by s.moment DESC")
	Collection<Shout> findManyNews();

}

