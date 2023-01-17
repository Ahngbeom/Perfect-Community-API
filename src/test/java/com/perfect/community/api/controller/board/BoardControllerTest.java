package com.perfect.community.api.controller.board;

import com.perfect.community.api.controller.ControllerIntegrationTest;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class BoardControllerTest extends ControllerIntegrationTest {

    @AfterTransaction
    void relocateBoardNumbers() {
        log.info("auto_increment key value: " + relocateService.relocateBoardNumbers("boards"));
    }

    public String getBoardInfo(long bno) throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/board/" + bno))
//                .andExpect(status().isOk())
                .andReturn();
        return mvcResult.getResponse().getContentAsString();
    }

}
