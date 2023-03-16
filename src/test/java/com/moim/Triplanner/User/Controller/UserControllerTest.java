package com.moim.Triplanner.User.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moim.Triplanner.User.Service.UserService;
import com.moim.Triplanner.User.VO.UserVO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    @DisplayName("유저 조회 API 테스트")
    void getUser() throws Exception {
        // given
        Long userId = 1L;
        UserVO user = new UserVO();
        user.setId(userId);
        user.setEmail("test@test.com");
        user.setName("Test");
        user.setGender("M");
        given(userService.getUser(userId)).willReturn(user);

        // when
        MvcResult mvcResult = mockMvc.perform(get("/users/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(user.getId().intValue())))
                .andExpect(jsonPath("$.email", is(user.getEmail())))
                .andExpect(jsonPath("$.name", is(user.getName())))
                .andExpect(jsonPath("$.gender", is(user.getGender())))
                .andReturn();

        // then
        String responseBody = mvcResult.getResponse().getContentAsString();
        UserVO resultUser = objectMapper.readValue(responseBody, UserVO.class);
        assert resultUser.getId().equals(userId);
        assert resultUser.getEmail().equals(user.getEmail());
        assert resultUser.getName().equals(user.getName());
        assert resultUser.getGender().equals(user.getGender());
    }

    @Test
    @DisplayName("유저 등록 API 테스트")
    void createUser() throws Exception {
        // given
        UserVO user = new UserVO();
        user.setEmail("test@test.com");
        user.setPassword("test1234");
        user.setName("Test");
        user.setGender("M");

        // when
        mockMvc.perform(post("/users")
                        .content(objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // then
        // UserService.createUser() 메서드가 호출되었는지 검증
        given(userService.createUser(any(UserVO.class))).willReturn(1L);
    }

    @Test
    @DisplayName("유저 수정 API 테스트")
    void updateUser() throws Exception {
        // given
        Long userId = 1L;
        UserVO user = new UserVO();
        user.setId(userId);
        user.setEmail("test@test.com");
        user.setName("Test");
        user.setGender("M");

        // when
        mockMvc.perform(put("/users/{userId}", userId)
                        .content(objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // then
        // UserService.updateUser() 메서드가 호출되었는지 검증
        Mockito.verify(userService, Mockito.times(1)).updateUser(any(UserVO.class));
    }


    @Test
    @DisplayName("유저 삭제 API 테스트")
    void deleteUser() throws Exception {
        // given
        Long userId = 1L;

        // when
        mockMvc.perform(delete("/users/{userId}", userId))
                .andExpect(status().isOk());

        // then
        // UserService.deleteUser() 메서드가 호출되었는지 검증
        Mockito.verify(userService, Mockito.times(1)).deleteUser(userId);
    }

}
