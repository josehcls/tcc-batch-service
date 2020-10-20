package br.com.fermentis.tccrecipeservice;

import br.com.fermentis.tccrecipeservice.controller.v1.RecipeController;
import br.com.fermentis.tccrecipeservice.model.dto.BatchDTO;
import br.com.fermentis.tccrecipeservice.model.dto.ControlProfileDTO;
import br.com.fermentis.tccrecipeservice.model.dto.ControlProfileStepDTO;
import br.com.fermentis.tccrecipeservice.model.dto.RecipeDTO;
import br.com.fermentis.tccrecipeservice.model.entity.Batch;
import br.com.fermentis.tccrecipeservice.model.enumerator.ControlVariable;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = TccRecipeServiceApplication.class, loader = SpringBootContextLoader.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class TccRecipeServiceApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private RecipeController recipeController;

	@Autowired
	private ObjectMapper objectMapper;

	private MockMvc mockMvc;

	protected MockMvc getMockMvc() {
		if (this.mockMvc == null) this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
		return this.mockMvc;
	}

	@Test
	void contextLoads() {
	}

	@Test
	void testListRecipes() {
		try {
			getMockMvc().perform(MockMvcRequestBuilders.get("/v1/recipes"))
					.andDo(print()).andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.jsonPath("$").exists());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Test
	void testListBatches() {
		try {
			getMockMvc().perform(MockMvcRequestBuilders.get("/v1/recipes/1/batches"))
					.andDo(print()).andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.jsonPath("$").exists());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Test
	void testListControlProfile() {
		try {
			getMockMvc().perform(MockMvcRequestBuilders.get("/v1/control-profiles"))
					.andDo(print()).andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.jsonPath("$").exists());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Test
	void testCreateRecipe() {
		try {
			RecipeDTO recipeDTO = getDummyRecipe();
			getMockMvc().perform(MockMvcRequestBuilders.post("/v1/recipes")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(recipeDTO)))
					.andDo(print()).andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.jsonPath("$").exists());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Test
	void testCreateControlProfile() {
		try {
			ControlProfileDTO controlProfileDTO = getDummyControlProfile();
			getMockMvc().perform(MockMvcRequestBuilders.post("/v1/control-profiles")
						.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(controlProfileDTO)))
					.andDo(print()).andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.jsonPath("$").exists());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Test
	void testCreateBatch() {
		try {
			BatchDTO batchDTO = getDummyBatchDTO();
			getMockMvc().perform(MockMvcRequestBuilders.post("/v1/recipes/2/batches")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(batchDTO)))
					.andDo(print()).andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.jsonPath("$").exists());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private RecipeDTO getDummyRecipe() {
		int randomId = new Random().nextInt();
		return RecipeDTO.builder()
				.name("Test Recipe " + randomId)
				.style("Test Pale Ale")
				.build();
	}

	private ControlProfileDTO getDummyControlProfile() {
		int randomId = new Random().nextInt();
		return ControlProfileDTO.builder()
				.name("Test Control Profile " + randomId)
                .steps(Arrays.asList(
                		ControlProfileStepDTO.builder().variable(ControlVariable.TEMPERATURE).value(BigDecimal.valueOf(18.0)).timeOffset(3600L).build(),
						ControlProfileStepDTO.builder().variable(ControlVariable.TEMPERATURE).value(BigDecimal.valueOf(10.5)).timeOffset(18000L).build()
				))
				.build();
	}

	private BatchDTO getDummyBatchDTO() {
		int randomId = new Random().nextInt();
		return BatchDTO.builder()
				.name("Test Batch " + randomId)
				.misc("Testing testing testing")
                .controlProfile(ControlProfileDTO.builder().id(3L).build())
				.build();
	}
}
