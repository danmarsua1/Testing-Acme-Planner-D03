
package acme.features.usermanager.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisations.Customisation;
import acme.entities.roles.Usermanager;
import acme.entities.tasks.Task;
import acme.features.administrator.customisation.AdministratorCustomisationRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class UsermanagerTaskUpdateService implements AbstractUpdateService<Usermanager, Task> {

	// Internal state ------------------------------------------------------------------
	@Autowired
	UsermanagerTaskRepository							repository;

	@Autowired
	private AdministratorCustomisationRepository	customisationRepository;


	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "executionStart", "executionEnd", "workLoad", "description", "link");

	}

	@Override
	public Task findOne(final Request<Task> request) {
		assert request != null;

		Task result;

		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		//SPAM AND DATES INTEGRITY CHECK
		final List<Customisation> customisations = new ArrayList<Customisation>(this.customisationRepository.findMany());
		final Customisation customisation = customisations.get(0);

		final String title = entity.getTitle();
		final String description = entity.getDescription();
		final String link = entity.getLink();
		
		final Date startDate = entity.getExecutionStart();
		final Date endDate = entity.getExecutionEnd();
		final Date present = new Date(System.currentTimeMillis());

		if (customisation.isSpam(title)) {
			errors.state(request, false, "title", "manager.task.error.spam");
		}
		if (customisation.isSpam(description)) {
			errors.state(request, false, "description", "manager.task.error.spam");
		}
		if (customisation.isSpam(link)) {
			errors.state(request, false, "link", "manager.task.error.spam");
		}
		if (startDate != null && endDate != null && startDate.after(endDate)) {
			errors.state(request, false, "executionStart", "task.error.executionDate");
			errors.state(request, false, "executionEnd", "task.error.executionDate");
		}
		if (startDate != null && present.after(startDate)) {
			errors.state(request, false, "executionStart", "task.error.executionStart");
		}
		if (endDate != null && present.after(endDate)) {
			errors.state(request, false, "executionEnd", "task.error.executionEnd");
		}

	}

	@Override
	public void update(final Request<Task> request, final Task entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
