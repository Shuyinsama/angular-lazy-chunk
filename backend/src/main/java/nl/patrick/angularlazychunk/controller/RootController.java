package nl.patrick.angularlazychunk.controller;

import jakarta.servlet.http.HttpServletRequest;
import nl.patrick.angularlazychunk.service.EnvironmentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static nl.patrick.angularlazychunk.controller.RootController.LANG_REQUEST_MAPPING_PATH_VARIABLE;

@Controller
@RequestMapping(value = LANG_REQUEST_MAPPING_PATH_VARIABLE)
public class RootController {

	public static final String LANG_REQUEST_MAPPING_PATH_VARIABLE = "/{lang:en|sv|da|cs|de|fi|fr|nl|nb|pl|ru|sk}";

	private final EnvironmentService environmentService;

	public RootController(EnvironmentService environmentService) {
		this.environmentService = environmentService;
	}

	@GetMapping(value = {"", "/", "/*", "/products/**", "/blogs/**"})
	public ModelAndView index(@PathVariable(name = "lang", required = false) String urlLanguage,
							  HttpServletRequest request) {
		var mav = new ModelAndView();

		var isDevelopment = environmentService.isDevelopment();
		mav.addObject("isDevelopment", isDevelopment);
		mav.addObject("webpackDevServerBaseUrl", request.getScheme() + "://" + request.getServerName() + ":4200/js/dist");

		mav.addObject("appLanguageCode", urlLanguage);

		mav.setViewName("index");

		return mav;
	}
}
