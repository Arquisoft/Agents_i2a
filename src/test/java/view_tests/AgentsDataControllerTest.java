package view_tests;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by Jorge.7
 * Test for the AgentsDataController, mainly focused on REST requests
 * Modified by Marcos on 17/02/2018
 */
import dbmanagement.UsersRepository;
import domain.User;
import main.Application;

@SpringBootTest(classes = { Application.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class AgentsDataControllerTest {

	private static final String QUERY = "{\"login\":\"%s\", \"password\":\"%s\", \"kind\":\"%s\"}";

	@Autowired
	private WebApplicationContext context;

	// MockMvc --> Para realizar peticiones y comprobar resultado, usado para
	// respuestas con informacion json.
	private MockMvc mockMvc;

	@Autowired
	private UsersRepository repo;

	private MockHttpSession session;

	private User javier;
	private String plainPassword;

	@Before
	public void setUp() throws Exception {

		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();

		session = new MockHttpSession();

		// Setting up javier
		plainPassword = "pass14753";
		javier = new User("Javier", "Aviles", "User3@hola.com", plainPassword, "12", "Person", 1);
		repo.insert(javier);
	}

	@After
	public void tearDown() {
		repo.delete(javier);
	}

	@Test
	public void userInsertInformation() throws Exception {
		String payload = String.format(QUERY, javier.getEmail(), plainPassword, javier.getKind());
		// We send a POST request to that URI (from http:localhost...)
		MockHttpServletRequestBuilder request = post("/user").session(session).contentType(MediaType.APPLICATION_JSON)
				.content(payload.getBytes());
		mockMvc.perform(request).andDo(print())
				// The state of the response must be OK. (200);
				.andExpect(status().isOk())
				// We can do jsonpaths in order to check 1996
				.andExpect(jsonPath("$.name", is(javier.getName())))
				.andExpect(jsonPath("$.location", is(javier.getLocation())))
				.andExpect(jsonPath("$.email", is(javier.getEmail())))
				.andExpect(jsonPath("$.id", is(javier.getUserId()))).andExpect(jsonPath("$.kind", is(javier.getKind())))
				.andExpect(jsonPath("$.kindCode", is(javier.getKindCode())));
	}

	@Test
	public void userInsertInformationXML() throws Exception {
		String payload = String.format("<data><login>%s</login><password>%s</password><kind>%s</kind></data>",
				javier.getEmail(), plainPassword, javier.getKind());
		// POST request with XML content
		MockHttpServletRequestBuilder request = post("/user").session(session)
				.contentType(MediaType.APPLICATION_XML_VALUE).content(payload.getBytes());
		mockMvc.perform(request).andDo(print())
				// The state of the response must be OK. (200);
				.andExpect(status().isOk())
				// We can do jsonpaths in order to check
				.andExpect(jsonPath("$.name", is(javier.getName())))
				.andExpect(jsonPath("$.location", is(javier.getLocation())))
				.andExpect(jsonPath("$.email", is(javier.getEmail())))
				.andExpect(jsonPath("$.id", is(javier.getUserId()))).andExpect(jsonPath("$.kind", is(javier.getKind())))
				.andExpect(jsonPath("$.kindCode", is(javier.getKindCode())));
	}

	@Test
	public void userInterfaceInsertInfoCorect() throws Exception {
		MockHttpServletRequestBuilder request = post("/userForm").session(session).param("login", javier.getEmail())
				.param("password", plainPassword).param("kind", javier.getKind());
		mockMvc.perform(request).andExpect(status().isOk());
	}

	@Test
	public void testForNotFound() throws Exception {
		String payload = String.format(QUERY, "Nothing", "Not really", "Nothing");
		MockHttpServletRequestBuilder request = post("/user").session(session).contentType(MediaType.APPLICATION_JSON)
				.content(payload.getBytes());
		mockMvc.perform(request).andDo(print())// AndDoPrint it is very usefull
												// to see the http response and
												// see if something went wrong.
				.andExpect(status().isNotFound()); // The state of the response
													// must be OK. (200);
	}

	/**
	 * Should return a 404 as before
	 */
	@Test
	public void testForIncorrectPassword() throws Exception {
		String payload = String.format(QUERY, javier.getName(), "Not maria's password", javier.getKind());
		MockHttpServletRequestBuilder request = post("/user").session(session).contentType(MediaType.APPLICATION_JSON)
				.content(payload.getBytes());
		mockMvc.perform(request).andDo(print()).andExpect(status().isNotFound());
	}

	@Test
	public void testChangePassword() throws Exception {
		MockHttpSession session = new MockHttpSession();
		// We check we have the proper credentials
		MockHttpServletRequestBuilder request = post("/userForm").session(session).param("login", javier.getEmail())
				.param("password", plainPassword).param("kind", javier.getKind());
		mockMvc.perform(request).andExpect(status().isOk());
		// We change it
		request = post("/userChangePassword").session(session).param("password", plainPassword)
				.param("newPassword", "HOLA").param("newPasswordConfirm", "HOLA");
		mockMvc.perform(request).andExpect(status().isOk());

		String payload = String.format(QUERY, javier.getEmail(), "HOLA", javier.getKind());
		// We check password has changed
		request = post("/user").session(session).contentType(MediaType.APPLICATION_JSON).content(payload.getBytes());
		mockMvc.perform(request).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is(javier.getName())))
				.andExpect(jsonPath("$.location", is(javier.getLocation())))
				.andExpect(jsonPath("$.email", is(javier.getEmail())))
				.andExpect(jsonPath("$.id", is(javier.getUserId()))).andExpect(jsonPath("$.kind", is(javier.getKind())))
				.andExpect(jsonPath("$.kindCode", is(javier.getKindCode())));
	}

}
