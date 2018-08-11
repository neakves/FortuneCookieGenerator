package com.monsanto.interview.FortuneCookieGenerator;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 
 * @author Evandro Souza
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class FortuneCookieTest {

	@LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void fortuneCookieShouldBeUnicForEachRequest() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/generateFortuneCookie?client=Sarah&company=MegaMarket",
                String.class)).contains("MegaMarket");
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/generateFortuneCookie?client=Barney&company=SuperStore",
                String.class)).contains("SuperStore");
    }
}
