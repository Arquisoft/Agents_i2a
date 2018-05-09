package cucumber.steps;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.List;

import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.util.JSON;

import controllers.AgentsController;
import cucumber.AbstractSteps;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginSteps extends AbstractSteps{

	@Mock
	private AgentsController aController;
	private MockMvc mockMvc;
	private MockHttpServletResponse result;

	@Before
	public void init() {
		
	}

	@Given("a list of users:$")
	public void a_list_of_users(DataTable arg) {
		List<List<String>> data = arg.raw();
		
		  MongoClientURI uri = new
		  MongoClientURI("mongodb://admin:aswadmin2018@ds159489.mlab.com:59489/aswdb");
		  
		  MongoClient mongoClient = new MongoClient(uri); 
		  @SuppressWarnings("deprecation")
		  DB db = mongoClient.getDB("aswdb"); 
		  DBCollection agents = db.getCollection("users");
		  
		  //Insert agents 
		  String json ="{ 'name' : '"+data.get(1).get(0)+"'," +
		  "'location' : 'GeoCords [lat=43.3619, lng=5.85]'," +
		  " 'email' : 'prueba07@prueba.es'," +
		  " 'password' : 'LK/remD0dMwk2Zh35Cjp4KOgQagGX5yP2MGRE8FaSQTiV8qfQbNuTCms4AhdlkXi'," +
		  "'userId' : '04462911Z'," +" 'kind' : '"+data.get(1).get(2)+"'," +
		  "'kindCode' : '1' }"; 
		  DBObject dbObject = (DBObject)JSON.parse(json);
		  
		  BasicDBObject query = new BasicDBObject();
		  query.put("userId", "04462911Z");
		  if(!(agents.find(query).size()>0)) {
			  agents.insert(dbObject);
		  }
		  
		 mongoClient.close();
		 
	}

	@When("I login with name \"([^\"]*)\" and password \"([^\"]*)\" and kind \"([^\"]*)\"$")
	public void login_with_name_and_password_and_kindcode(String name, String password, String kind) throws Exception {
		MockitoAnnotations.initMocks(this);
		 InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	        viewResolver.setPrefix("/WEB-INF/jsp/view/");
	        viewResolver.setSuffix(".jsp");
	 
		mockMvc = MockMvcBuilders.standaloneSetup(aController).setViewResolvers(viewResolver).build();

		mockMvc.perform(get("http://localhost:8080"));
		MockHttpServletRequestBuilder request = post("/userForm").param("login", name).param("password", password)
				.param("kind", kind);

		result = mockMvc.perform(request).andReturn().getResponse();
		
	}
	@Then("^I reach the home page with success$")
	public void i_receive_a_welcome_message() throws Throwable {
		MatcherAssert.assertThat(result.getStatus(), equalTo(200));
	}
}
