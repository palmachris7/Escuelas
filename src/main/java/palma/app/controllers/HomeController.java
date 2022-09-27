package palma.app.controllers;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import palma.app.model.Perfil;
import palma.app.model.Usuario;
import palma.app.service.IUsuariosService;

@Controller
public class HomeController {

	

	@Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
   	private IUsuariosService serviceUsuarios;

	

    @GetMapping("/home")
	public String mostrarHome() {
		return "home";
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



	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	    binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
}
