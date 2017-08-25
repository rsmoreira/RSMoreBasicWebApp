package com.rsmore.rest.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/")
public class WelcomeController {

	private final Logger logger = LoggerFactory.getLogger(WelcomeController.class);

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index() {
		
		logger.debug("Index is being executed");
		 
		ModelAndView model = new ModelAndView();
		model.setViewName("index");
 
		model.addObject("title", "Eu removi o titulo da JSP e coloquei fixo la... ¬¬");
		model.addObject("msg", "Calculator! Show TIIIIIME!");
 
		return model;
	}

	/*@RequestMapping(value="/hello/{name:.+}", method = RequestMethod.GET)
	public ModelAndView hello(@PathVariable("name") String name) {
		
		logger.debug("hello() is executed - $name {}", name);
		 
		ModelAndView model = new ModelAndView();
		model.setViewName("index");
 
		model.addObject("title", helloWorldService.getTitle(name));
		model.addObject("msg", helloWorldService.getDesc());
 
		return model;
	}*/

	
	
	// muito canela seca esse view resolver!!! esse mapeamento!!! prefiro o que possui o modelandview
//	@RequestMapping(value="/", method = RequestMethod.GET)
//	public String index(Map<String, Object> model) {
//		logger.debug("index() is executed!");
//
//		model.put("title", helloWorldService.getTitle(""));
//		model.put("msg", helloWorldService.getDesc());
//
//		return "index";
//	}

}
