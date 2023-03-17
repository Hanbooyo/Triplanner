package com.moim.Triplanner.Plan.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moim.Triplanner.Plan.Service.PlanService;
import com.moim.Triplanner.Plan.VO.PlanVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PlanController.class)
public class PlanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlanService planService;

    @Autowired
    private ObjectMapper objectMapper;

    private PlanVO plan;

    @BeforeEach
    public void setUp() {
        plan = PlanVO.builder()
                .id(1L)
                .title("test plan")
                .locationName("Seoul")
                .latitude("37.5665")
                .longitude("126.9780")
                .date(LocalDate.of(2022, 1, 1))
                .startTime(LocalTime.of(9, 0))
                .endTime(LocalTime.of(12, 0))
                .memo("memo")
                .build();
    }

    @Test
    void getPlanById() throws Exception {
        PlanVO plan = PlanVO.builder()
                .id(1L)
                .title("Test Plan")
                .locationName("Test Location")
                .latitude("37.12345")
                .longitude("127.12345")
                .date(LocalDate.now())
                .startTime(LocalTime.of(9, 0))
                .endTime(LocalTime.of(10, 0))
                .memo("Test Memo")
                .build();

        when(planService.getPlanById(1L)).thenReturn(plan);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/plans/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Plan"))
                .andExpect(jsonPath("$.locationName").value("Test Location"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void updatePlan() throws Exception {
        Long id = 1L;
        PlanVO updatedPlan = PlanVO.builder()
                .id(id)
                .title("Updated Title")
                .locationName("Updated Location Name")
                .latitude("Updated Latitude")
                .longitude("Updated Longitude")
                .date(LocalDate.of(2022, 12, 31))
                .startTime(LocalTime.of(10, 0))
                .endTime(LocalTime.of(12, 0))
                .memo("Updated Memo")
                .build();

        when(planService.updatePlan(id, updatedPlan)).thenReturn(updatedPlan);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/plans/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedPlan)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.title").value("Updated Title"))
                .andExpect(jsonPath("$.locationName").value("Updated Location Name"))
                .andExpect(jsonPath("$.latitude").value("Updated Latitude"))
                .andExpect(jsonPath("$.longitude").value("Updated Longitude"))
                .andExpect(jsonPath("$.date").value("2022-12-31"))
                .andExpect(jsonPath("$.startTime").value("10:00:00"))
                .andExpect(jsonPath("$.endTime").value("12:00:00"))
                .andExpect(jsonPath("$.memo").value("Updated Memo"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void deletePlan() throws Exception {
        Long id = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/plans/{id}", id))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
