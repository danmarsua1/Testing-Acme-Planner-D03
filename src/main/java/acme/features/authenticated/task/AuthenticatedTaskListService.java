
package acme.features.authenticated.task;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tasks.Task;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedTaskListService implements AbstractListService<Authenticated, Task> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedTaskRepository repository;


	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;
		Boolean res = false;
		final Principal principal = request.getPrincipal();
		if (principal.isAuthenticated()) {
			res = true;
		}

		return res;
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "executionStart", "executionEnd", "workLoad", "title");

	}

	@Override
	public Collection<Task> findMany(final Request<Task> request) {
		assert request != null;

		return this.repository.findManyFinished();
	}

}
