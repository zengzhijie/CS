package com.dreawer.shopcenter;

import com.dreawer.shopcenter.form.AddEnterpriseForm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.http.entity.StringEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * <CODE>BastTest</CODE>
 *
 * @author fenrir
 * @Date 18-8-6
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShopCenterApplication.class)
public class BastTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();//建议使用这种
    }


    @Test
    public void testTicket() throws Exception {
        AddEnterpriseForm form = new AddEnterpriseForm();
        form.setAppid("wx1212221341");
        form.setName("11234321256552");
        form.setShortname("123");
        form.setIntro("11111");
        form.setLogo("11111111");

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(form);
        mvc.perform(MockMvcRequestBuilders.post("/enterprise/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJson)
                .header("userId", UUID.randomUUID().toString().replace("-", ""))
                .sessionAttr("111", "111"))
                .andDo(print());
    }
}
