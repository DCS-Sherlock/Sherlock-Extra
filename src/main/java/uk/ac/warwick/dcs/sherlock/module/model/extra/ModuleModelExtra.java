package uk.ac.warwick.dcs.sherlock.module.model.extra;

import uk.ac.warwick.dcs.sherlock.api.SherlockRegistry;
import uk.ac.warwick.dcs.sherlock.api.annotation.EventHandler;
import uk.ac.warwick.dcs.sherlock.api.annotation.SherlockModule;
import uk.ac.warwick.dcs.sherlock.api.event.EventPreInitialisation;
import uk.ac.warwick.dcs.sherlock.module.model.base.lang.HaskellLexer;

@SherlockModule
public class ModuleModelExtra {

	@EventHandler
	public void preInitialisation(EventPreInitialisation event) {
		SherlockRegistry.registerLanguage("Haskell", HaskellLexer.class); //Testing, will be moved to a separate module for testing purposes
	}

}
