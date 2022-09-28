package palma.app.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.crypto.password.PasswordEncoder;
import palma.app.model.Escuela;
import palma.app.model.Perfil;
import palma.app.model.Usuario;
import palma.app.service.EscuelaConsumerService;
import palma.app.service.IUsuariosService;
import springfox.documentation.annotations.ApiIgnore;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
@ApiIgnore
public class EscuelaController {
	
	private static final Logger log = LoggerFactory.getLogger(EscuelaController.class);
	
	@Autowired
	private EscuelaConsumerService consumerService;

	@Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
   	private IUsuariosService serviceUsuarios;

		
	@GetMapping({"/", "/escuelas"})
	public String listadoEscuelas(Model model) {
		log.info("Listado Escuelas" + consumerService.listadoEScuelas().toString());
		model.addAttribute("escuelas", consumerService.listadoEScuelas());
		return "index";
	}
		
	@GetMapping("/escuelas/{id}")
	public String buscarPorEscuela(@PathVariable Long id, Model model) {
		model.addAttribute("escuelas", consumerService.buscarPorId(id));
		return "index";
	}
	
	@GetMapping("/escuelas/crear")
	public String crear(@ModelAttribute  Escuela escuela, BindingResult result, Model model) {
		log.info("Mostrando Form Crear");
		if(result.hasErrors()) {
			return "formEscuela";
		}
		log.info(consumerService.listadoFacultades().toString());
		return "formEscuela";
	}
	
	@PostMapping("/escuelas/guardar")
	public String guardarEscuela(@Valid @ModelAttribute Escuela escuela, BindingResult bindResult, Model model) {
		log.info("Guardar Escuela");
		
		if(bindResult.hasErrors()) {
			return "formEscuela";
		}
		
		log.info("Crear: " + escuela.toString());
		consumerService.agregarEscuela(escuela);
		
		return "redirect:/escuelas";
	}
	
	@GetMapping("/escuelas/editar/{id}")
	public String editarEscuela(@PathVariable Long id, @ModelAttribute Escuela escuela, Model model) {
		log.info("Mostrando Form Editar");
		
		List<Escuela> list = consumerService.buscarPorId(id);
		model.addAttribute("escuela", list.get(0));
		model.addAttribute("titulo", "Editar Escuela");
		model.addAttribute("action", "editar");
		
		return "formEscuela";
	}
	
	@PostMapping("/escuelas/actualizar")
	public String actualizarEscuela(@ModelAttribute Escuela escuela, BindingResult bindingResult) {
		log.info("Actualizar Escuela");
		
		if(bindingResult.hasErrors()) {
			return "formEscuela";
		}
		consumerService.actualizarEscuela(escuela.getIdEscuela(), escuela);
		log.info("Actualizar: " + escuela.toString());

		return "redirect:/escuelas";
	}
	
	@GetMapping("/escuelas/eliminar/{id}")
	public String eliminarEscuela(@PathVariable Long id) {
		consumerService.eliminarEscuela(id);
		return "redirect:/escuelas";
	} 

	@GetMapping("/index")
	public String mostrarIndex(Authentication authentication, HttpSession session) {		
		
		String username = authentication.getName();		
		
		for(GrantedAuthority rol: authentication.getAuthorities()) {
			System.out.println("ROL: " + rol.getAuthority());
		}
		
		if (session.getAttribute("usuario") == null){
			Usuario usuario = serviceUsuarios.buscarPorUsername(username);	
			System.out.println("Usuario: " + usuario);
			 session.setAttribute("usuario", usuario);
		}
		
		return "redirect:/";
	}

	@GetMapping("/signup")
	public String registrarse(Usuario usuario) {
		return "formRegistro";
	}

	@PostMapping("/signup")
	public String guardarRegistro(Usuario usuario, RedirectAttributes attributes) {
		String pwdPlano = usuario.getPassword();
		String pwdEncriptado = passwordEncoder.encode(pwdPlano); 
		usuario.setPassword(pwdEncriptado);	
		Perfil perfil = new Perfil();
		usuario.setEstatus(1);
    	Date date = new Date();  
		usuario.setFecharegistro(date);
		perfil.setIdperfil(2); 
		usuario.agregar(perfil);
		serviceUsuarios.guardar(usuario);	
		attributes.addFlashAttribute("msg", "Has sido registrado. ¡Ahora puedes ingresar al sistema!");
		
		return "redirect:/login";
	}

	@GetMapping("/about")
	public String mostrarAcerca() {			
		return "acerca";
	}

	@GetMapping("/login")
	public String mostrarLogin() {
		return "formLogin";
	}

	@GetMapping("/logout")
	public String logout2(HttpServletRequest request) {
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.logout(request, null, null);
		return "redirect:/login";
	}

	@GetMapping("/logout2")
	public String logout(HttpServletRequest request) {
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.logout(request, null, null);
		return "redirect:/";
	}
	
	@ModelAttribute
	public void getModelGenerico(Model model) {
		model.addAttribute("facultades", consumerService.listadoFacultades());
	}
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyy-MM-dd");
		dateformat.setTimeZone(TimeZone.getTimeZone("GMT-5"));
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateformat, false));
	}
}
