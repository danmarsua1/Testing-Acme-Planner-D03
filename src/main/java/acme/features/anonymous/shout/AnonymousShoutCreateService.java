package acme.features.anonymous.shout;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisations.Customisation;
import acme.entities.shouts.Shout;
import acme.features.administrator.customisation.AdministratorCustomisationRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousShoutCreateService implements AbstractCreateService<Anonymous, Shout> {

	// Internal state -------------------------------------------------------------

	@Autowired
	AnonymousShoutRepository repository;
	
	@Autowired
	private AdministratorCustomisationRepository	customisationRepository;


	@Override
	public boolean authorise(final Request<Shout> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Shout> request, final Shout entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Shout> request, final Shout entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "text", "info");
	}

	@Override
	public Shout instantiate(final Request<Shout> request) {
		assert request != null;

		final Shout result = new Shout();
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		result.setAuthor("John Doe");
		result.setText("Write here");
		result.setMoment(moment);
		result.setInfo("http://www.example.com");
		return result;
	}

	@Override
	public void validate(final Request<Shout> request, final Shout entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final List<Customisation> customisations = new ArrayList<Customisation>(this.customisationRepository.findMany());
		final Customisation customisation = customisations.get(0);

		final String author = entity.getAuthor();
		final String text = entity.getText();
		final String info = entity.getInfo();

		if (customisation.isSpam(author)) {
			errors.state(request, false, "author", "anonymous.shout.error.spam");
		}
		if (customisation.isSpam(text)) {
			errors.state(request, false, "text", "anonymous.shout.error.spam");
		}
		if (customisation.isSpam(info)) {
			errors.state(request, false, "info", "anonymous.shout.error.spam");
		}
		
	}

	@Override
	public void create(final Request<Shout> request, final Shout entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		
		this.repository.save(entity);
	}

}