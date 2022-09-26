package palma.app.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import palma.app.service.EscuelaConsumerService;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@RequestMapping("/")
@ApiIgnore
public class EscuelaController {
    private static final Logger log = LoggerFactory.getLogger(EscuelaController.class);
	
	@Autowired
	private EscuelaConsumerService consumerService;

    @GetMapping({"/", "/escuelas"})
	public String listadoEscuelas(Model model) {
		log.info("Listado Escuelas" + consumerService.listadoEScuelas().toString());
		model.addAttribute("escuelas", consumerService.listadoEScuelas());
		return "index";
	}

	@ModelAttribute
	public void getModelGenerico(Model model) {
		model.addAttribute("facultades", consumerService.listadoFacultades());
	}
}
