package cucumber.steps;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import controllers.AgentsController;
import cucumber.AbstractSteps;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class ChangePasswordSteps extends AbstractSteps{

	@Mock
	private AgentsController aController;
	private MockMvc mockMvc;
	private MockHttpServletResponse result;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		 InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	        viewResolver.setPrefix("/WEB-INF/jsp/view/");
	        viewResolver.setSuffix(".jsp");
	 
		mockMvc = MockMvcBuilders.standaloneSetup(aController).setViewResolvers(viewResolver).build();
	}

	@And("^I click on change password$")
	public void i_receive_a_welcome_message() throws Throwable {
		executeGet("http://localhost:8080/passMenu");
		MatcherAssert.assertThat(latestResponse.getBody(),containsString("Confirmation")) ;
	}
	
	@And("^I introduce new password \"([^\"]*)\"$")
	public void i_introduce_new_password(String newPassword) throws Throwable {
		MockitoAnnotations.initMocks(this);
		 InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	        viewResolver.setPrefix("/WEB-INF/jsp/view/");
	        viewResolver.setSuffix(".jsp");
	 
		mockMvc = MockMvcBuilders.standaloneSetup(aController).setViewResolvers(viewResolver).build();
		MockHttpServletRequestBuilder request = post("/userChangePassword").param("password", "123456").param("newPassword", newPassword)
				.param("newPasswordConfirm", newPassword);

		result = mockMvc.perform(request).andReturn().getResponse();
		MatcherAssert.assertThat(result.getStatus(), equalTo(200));
	}

	@Then("^My password is changed$")
	public void my_password_is_changed() throws Throwable {
		MockitoAnnotations.initMocks(this);
		 InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	        viewResolver.setPrefix("/WEB-INF/jsp/view/");
	        viewResolver.setSuffix(".jsp");
	 
		mockMvc = MockMvcBuilders.standaloneSetup(aController).setViewResolvers(viewResolver).build();

		mockMvc.perform(get("http://localhost:8080"));
		MockHttpServletRequestBuilder request = post("/userForm").param("login", "Prueba07").param("password", "newpass")
				.param("kind", "1");
		
		result = mockMvc.perform(request).andReturn().getResponse();	
		MatcherAssert.assertThat(result.getStatus(), equalTo(200));
		
	}
}
