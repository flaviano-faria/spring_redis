package com.redis.domain.adapter.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.redis.app.SpringRedisApplication;
import com.redis.controller.UserController;
import com.redis.domain.User;
import com.redis.domain.UserDTO;
import com.redis.domain.ports.interfaces.UserServicePort;
import com.redis.domain.ports.repository.UserRepositoryPort;
import com.redis.infra.adapters.entity.UserEntity;
import com.redis.infra.adapters.repository.IUserRepository;
import com.redis.infra.adapters.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DataJpaTest
@SpringBootTest(classes= SpringRedisApplication.class)
@ComponentScan(basePackages =
                "com.redis.infra.configuration," +
                "com.redis.infra.adapters.repository," +
                "com.redis.controller," +
                "com.redis.exception")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserServiceTest {

    @Autowired
    private MockMvc mvc;
    @Mock
    private IUserRepository JPAIUserRepository;
    @Mock
    private UserServicePort userServicePort;
    @Mock
    private UserRepositoryPort userRepositoryPort;

    @Before
    public void setup() throws Exception {
        JPAIUserRepository = Mockito.mock(IUserRepository.class);
        userRepositoryPort = new UserRepository(JPAIUserRepository);
        userServicePort = new UserService(userRepositoryPort);
        mvc = MockMvcBuilders.standaloneSetup(new UserController(userServicePort)).build();
    }

    @Test
    public void MyTestController() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void createUserNameNullTest() throws Exception {
        UserDTO userDTO = new UserDTO("123","");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(userDTO );


        mvc.perform(MockMvcRequestBuilders.post("")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().is(400));

    }

    @Test
    public void createUserIdNullTest() throws Exception {
        UserDTO userDTO = new UserDTO("","test");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(userDTO );


        mvc.perform(MockMvcRequestBuilders.post("")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().is(400));
    }


    @Test
    public void FindAllTest() throws Exception {

        UserEntity userEntity = new UserEntity("1234","test");
        List<UserEntity> listUserEntity = Arrays.asList(
                new UserEntity("1234","test"),
                new UserEntity("3456","test2"));

    Mockito.when(JPAIUserRepository.findAll()).thenReturn(listUserEntity);

        mvc.perform(MockMvcRequestBuilders.get("")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn()
                .getResponse().getContentAsString();
    }

    @Test
    public void findByIdSuccessTest() throws Exception {
        UserEntity userEntity = new UserEntity("1234","test");
        Mockito.when(JPAIUserRepository.findById("User:1234")).thenReturn(Optional.of(userEntity));

        String content = mvc.perform(MockMvcRequestBuilders.get("/findbyid/User:1234")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn()
                .getResponse().getContentAsString();

        System.out.println(content);
    }

    @Test
    public void findByIdInvalidIdTest() throws Exception {
        UserEntity userEntity = new UserEntity("2345","test2");
        Mockito.when(JPAIUserRepository.findById("User:1234")).thenReturn(Optional.of(new UserEntity("", "")));

        String content = mvc.perform(MockMvcRequestBuilders.get("/findbyid/User:1234")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn()
                .getResponse().getContentAsString();
    }
}

