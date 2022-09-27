package palma.app.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import palma.app.model.Role;
import palma.app.model.Usuario;
import palma.app.service.IUsuarioService;


@RestController
@RequestMapping("/api")
public class UsuarioRestController {

	@Autowired
	private IUsuarioService usuarioService;

	@GetMapping("/usuarios")
	public ResponseEntity<List<Usuario>> listadoUsuario() {
		return ResponseEntity.ok().body(usuarioService.listadoUsuario());
	}

	@PostMapping("/usuarios/crear")
	public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario) {
		return ResponseEntity.ok().body(usuarioService.guardarUsuario(usuario));
	}

	@PostMapping("/role/crear")
	public ResponseEntity<Role> guardarRole(@RequestBody Role role) {
		return ResponseEntity.ok().body(usuarioService.guardarRole(role));
	}

	@PostMapping("/role/addtouser")
	public ResponseEntity<?> agregarRolAUsuario(@RequestBody RoleAUsuarioFrom from) {
		usuarioService.agregarRoleAUsuario(from.getNombreUsuario(), from.getRoleNombre());
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/token/refresh")
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws StreamWriteException, DatabindException, IOException {
		String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

		if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			try {
				String regreshToken = authorizationHeader.substring("Bearer ".length());
				Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
				JWTVerifier verifier = JWT.require(algorithm).build();
				DecodedJWT decodedJWT = verifier.verify(regreshToken);
				String username =decodedJWT.getSubject();
				Usuario usuario = usuarioService.getUsuario(username);
				
				String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
				Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
				Arrays.stream(roles).forEach(rol -> {
					authorities.add(new SimpleGrantedAuthority(rol));
				});
				
				String accessToken = JWT.create()
						.withSubject(usuario.getNombreUsuario())
						.withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
						.withIssuer(request.getRequestURL().toString())
						.withClaim("roles", usuario.getRoles().stream().map(Role::getNombre).collect(Collectors.toList()))
						.sign(algorithm);
			
				
				Map<String, String> tokens = new HashMap<>();
				tokens.put("access_token", accessToken);
				tokens.put("refresh_token", regreshToken);
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				
				new ObjectMapper().writeValue(response.getOutputStream(), tokens);
				
			} catch (Exception e) {
				//log.error("Error loin en : {}", e.getMessage() );
				response.setHeader("Error", e.getMessage());
				response.setStatus(HttpStatus.FORBIDDEN.value());
				//response.sendError(HttpStatus.FORBIDDEN.value());
				
				Map<String, String> error = new HashMap<>();
				error.put("error_message", e.getMessage());
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				
				new ObjectMapper().writeValue(response.getOutputStream(), error);
			}
		}else {
			throw new RuntimeException("Refresh Token Desconocido ");
		}
	}

}

class RoleAUsuarioFrom {
	private String nombreUsuario;
	private String roleNombre;

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getRoleNombre() {
		return roleNombre;
	}

	public void setRoleNombre(String roleNombre) {
		this.roleNombre = roleNombre;
	}

	@Override
	public String toString() {
		return "RoleAUsuarioFrom [nombreUsuario=" + nombreUsuario + ", roleNombre=" + roleNombre + "]";
	}
}
