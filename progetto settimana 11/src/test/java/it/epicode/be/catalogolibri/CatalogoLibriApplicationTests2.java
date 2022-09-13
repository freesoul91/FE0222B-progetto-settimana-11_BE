package it.epicode.be.catalogolibri;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


//esempio codice di test usando Mock 

// Nota, se uesate ROLE_USER, avrete un errore di JUNIT perché il ruolo non può iniizare con "ROLE_"

// Tutti i metodi associati a "status" - https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/test/web/servlet/result/StatusResultMatchers.html

@SpringBootTest
@AutoConfigureMockMvc
class CatalogoLibriApplicationTests2 {
	
	@Autowired
	private MockMvc mockMvc;


	@Test
	@WithAnonymousUser
	public void listaAutoriWhenUtenteIsAnonymous() throws Exception {
		this.mockMvc.perform(get("/api/autori")).andExpect(status().isUnauthorized());
	}


	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "USER")
	public void listaAutoriWhenUtenteMockIsAuthenticated() throws Exception {
		this.mockMvc.perform(get("/api/autori")).andExpect(status().isOk());
	}



	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "USER")
	public void deleteAutoreByUser() throws Exception {
		this.mockMvc.perform(delete("/api/autore/9"))
			.andExpect(status().isForbidden());
	}
	
	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	public void deleteAutoreByAdmin() throws Exception {
		this.mockMvc.perform(delete("/api/autore/9"))
			.andExpect(status().isOk());
	}


}