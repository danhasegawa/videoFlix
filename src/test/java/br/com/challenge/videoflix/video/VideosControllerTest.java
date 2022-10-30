package br.com.challenge.videoflix.video;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = VideosController.class)
@TestPropertySource(locations = "classpath:application-test.properties")

public class VideosControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	VideosRepository videosRepository;

	@Test
	public void shouldReturnListOfVideos() throws Exception {
		var videos = new Videos();
		videos.setTitle("Rogue One");
		videos.setDescription("Star Wars spin-off movie");
		videos.setUrl("linkwhatever.com");

		var videos2 = new Videos();
		videos2.setTitle("Lord of The Rings");
		videos2.setDescription("Fellowship trying to destroy the ring");
		videos2.setUrl("linkwhatever.com");

		Mockito.when(videosRepository.videosByDescription(Mockito.any())).thenReturn(0L);
		mockMvc.perform(MockMvcRequestBuilders.get("/videos").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

	}

	@Test
	public void shouldReturnOkWhensearchingByDescription() throws Exception {
		var videos = new Videos();
		videos.setTitle("Rogue One");
		videos.setDescription("Star Wars spin-off movie");
		videos.setUrl("linkwhatever.com");

		String json = "[{\"title\":\"Rogue One\",\"description\":\"Star Wars spin-off movie\",\"url\":\"linkwhatever.com\"}]";

		Mockito.when(videosRepository.findByTitle("Rogue One")).thenReturn(List.of(videos));
		mockMvc.perform(MockMvcRequestBuilders.get("/videos").content(json).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void shouldGetOkWhenSearchingById()throws Exception{
		var videos = new Videos();
		videos.setTitle("Rogue One");
		videos.setDescription("Star Wars spin-off movie");
		videos.setUrl("linkwhatever.com");
		
		String json = "[{\"title\":\"Rogue One\",\"description\":\"Star Wars spin-off movie\",\"url\":\"linkwhatever.com\"}]";
		
		Mockito.when(videosRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(videos));
		mockMvc.perform(MockMvcRequestBuilders.get("/videos/1").content(json).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
		
	}

	@Test
	public void shouldAddNewVideo() throws Exception {
		var videos = new Videos();
		videos.setTitle("Rogue One");
		videos.setDescription("Star Wars spin-off movie");
		videos.setUrl("linkwhatever.com");

		var dto = new VideoForm();
		dto.setTitle("Rogue One");
		dto.setDescription("Star Wars spin-off movie");
		dto.setUrl("linkwhatever.com");

		String requestJson = objectMapper.writeValueAsString(dto);

		Mockito.when(videosRepository.save(Mockito.any())).thenReturn(videos);
		mockMvc.perform(MockMvcRequestBuilders.post("/videos").contentType(MediaType.APPLICATION_JSON)
				.content(requestJson).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.header().stringValues("Location", "http://localhost/topics/"))
				.andReturn();
	}

	@Test
	public void shouldDeleteAVideo() throws Exception {
		var videos = new Videos();
		videos.setTitle("Rogue One");
		videos.setDescription("Star Wars spin-off movie");
		videos.setUrl("linkwhatever.com");
		
		Mockito.when(videosRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(videos));
		mockMvc.perform(MockMvcRequestBuilders.delete("/videos/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		verify(videosRepository, times(1)).deleteById(Mockito.anyLong());
		

	}
}
