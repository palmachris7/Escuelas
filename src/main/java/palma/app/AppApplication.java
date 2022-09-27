package palma.app;

import java.util.ArrayList;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

import palma.app.model.Role;
import palma.app.model.Usuario;
import palma.app.service.IUsuarioService;

import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class AppApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	@Bean
	CommandLineRunner run(IUsuarioService usuarioService) {
		return args -> {
			usuarioService.delete();

			usuarioService.guardarRole(new Role(null, "ROLE_USER"));
			usuarioService.guardarRole(new Role(null, "ROLE_MANAGER"));
			usuarioService.guardarRole(new Role(null, "ROLE_ADMIN"));
			usuarioService.guardarRole(new Role(null, "ROLE_SUPER_ADMIN"));
			
			usuarioService.guardarUsuario(new Usuario(null, "UserTest1", "test1", "1234", true, new ArrayList<>()));
			usuarioService.guardarUsuario(new Usuario(null, "UserTest2", "test2", "1234", true, new ArrayList<>()));
			usuarioService.guardarUsuario(new Usuario(null, "UserTest3", "test3", "1234", true, new ArrayList<>()));
			usuarioService.guardarUsuario(new Usuario(null, "UserTest4", "test4", "1234", true, new ArrayList<>()));
			
			usuarioService.agregarRoleAUsuario("test1", "ROLE_USER");
			usuarioService.agregarRoleAUsuario("test1", "ROLE_MANAGER");
			usuarioService.agregarRoleAUsuario("test2", "ROLE_MANAGER");
			usuarioService.agregarRoleAUsuario("test3", "ROLE_ADMIN");
			usuarioService.agregarRoleAUsuario("test4", "ROLE_SUPER_ADMIN");
			usuarioService.agregarRoleAUsuario("test4", "ROLE_MANAGER");
			usuarioService.agregarRoleAUsuario("test4", "ROLE_USER");
		};
	}

}
