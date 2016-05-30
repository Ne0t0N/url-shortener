package de.happycar.tryout.controller;

import de.happycar.tryout.Application;
import de.happycar.tryout.bean.response.LinkResponse;
import de.happycar.tryout.bean.type.Message;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * User: Ne0t0N
 * Date: 30.05.2016
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class LinkControllerTest {

    private static final String DEFAULT_VALID_LINK = "https://google.github.io/styleguide/javaguide.html";

    @Autowired
    private LinkController linkControllerStub;

    @Test
    public void registerLink_badRequestWhenInvalidLink() {
        ResponseEntity<LinkResponse> response = linkControllerStub.registerLink("http://google\\sd");
        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assert.assertEquals(Message.INCORRECT_LINK.getMessage(), response.getBody().getMessage());
    }

    @Test
    public void registerLink_okWhenSuccessfullyRegistered() {
        ResponseEntity<LinkResponse> response = linkControllerStub.registerLink(DEFAULT_VALID_LINK);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(Message.OK.getMessage(), response.getBody().getMessage());
        Assert.assertNotNull(response.getBody().getLink());
    }

    @Test
    public void registerLink_alreadyExistsMessageWhenRegisterTwice() {
        linkControllerStub.registerLink(DEFAULT_VALID_LINK);
        ResponseEntity<LinkResponse> response = linkControllerStub.registerLink(DEFAULT_VALID_LINK);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assert.assertEquals(Message.ALREADY_EXISTS.getMessage(), response.getBody().getMessage());
        Assert.assertNotNull(response.getBody().getLink());
    }

    /**
     * Only one testing method for {@link LinkController#registerTrackableLink(String)} as in all other cases it copies
     * behavior from {@link LinkController#registerLink(String)}
     */
    @Test
    public void registerTrackableLink_badRequestWhenNoUtms() {
        ResponseEntity<LinkResponse> response = linkControllerStub.registerTrackableLink(DEFAULT_VALID_LINK);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assert.assertEquals(Message.NO_UTMS.getMessage(), response.getBody().getMessage());
    }
}
