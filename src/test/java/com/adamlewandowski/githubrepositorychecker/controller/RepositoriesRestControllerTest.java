package com.adamlewandowski.githubrepositorychecker.controller;

import com.adamlewandowski.githubrepositorychecker.exception.NoSuchUserException;
import com.adamlewandowski.githubrepositorychecker.exception.RestExceptionHandler;
import com.adamlewandowski.githubrepositorychecker.service.GithubRepositoriesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

import static com.adamlewandowski.githubrepositorychecker.TestConstants.OWNER;
import static com.adamlewandowski.githubrepositorychecker.TestConstants.REPOSITORY_INFORMATION_DTO_LIST;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_XML;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
class RepositoriesRestControllerTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private GithubRepositoriesService githubRepositoriesService;

    private MockMvc mockMvc;


    @BeforeEach
    public void setUp() {
        mockMvc = standaloneSetup(new RepositoriesRestController(githubRepositoriesService))
                .setControllerAdvice(new RestExceptionHandler(objectMapper))
                .build();
    }

    @Test
    void getRepositoriesList_correctHeaderAndUserExist_returnsRepositoriesList() throws Exception {
        //given

        String expectedJson = objectMapper.writeValueAsString(REPOSITORY_INFORMATION_DTO_LIST);
        when(githubRepositoriesService.getAllOwnerRepositories(OWNER)).thenReturn(REPOSITORY_INFORMATION_DTO_LIST);
        //when
        mockMvc.perform(get("/repositories/" + OWNER).accept(APPLICATION_JSON))
                .andDo(print())
                //then
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(content().json(expectedJson));
        verify(githubRepositoriesService, times(1)).getAllOwnerRepositories(OWNER);
        verifyNoMoreInteractions(githubRepositoriesService);
    }

    @Test
    void getRepositoriesList_userNotExist_returnsErrorMessage() throws Exception {
        //given
        when(githubRepositoriesService.getAllOwnerRepositories(OWNER)).thenThrow(new NoSuchUserException("There is no such user in Github"));
        //when
        mockMvc.perform(get("/repositories/" + OWNER).accept(APPLICATION_JSON))
                .andDo(print())
                //then
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("NOT_FOUND"))
                .andExpect(jsonPath("$.message").value("There is no such user in Github"));
        verify(githubRepositoriesService, times(1)).getAllOwnerRepositories(OWNER);
        verifyNoMoreInteractions(githubRepositoriesService);
    }

    @Test
    void getRepositoriesList_wrongHeader_returnsException() throws Exception {
        //given
        lenient().when(githubRepositoriesService.getAllOwnerRepositories(OWNER)).thenReturn(REPOSITORY_INFORMATION_DTO_LIST);
        //when
        mockMvc.perform(get("/repositories/" + OWNER).accept(APPLICATION_XML))
                .andDo(print())
                //then
                .andExpect(status().isNotAcceptable())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("NOT_ACCEPTABLE"))
                .andExpect(jsonPath("$.message").value("Response can be generated only in json format"));
        verify(githubRepositoriesService, times(0)).getAllOwnerRepositories(any());
        verifyNoMoreInteractions(githubRepositoriesService);
    }

}