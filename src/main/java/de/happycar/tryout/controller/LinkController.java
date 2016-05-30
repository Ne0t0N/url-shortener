package de.happycar.tryout.controller;

import de.happycar.tryout.bean.Link;
import de.happycar.tryout.bean.response.LinkResponse;
import de.happycar.tryout.bean.type.Message;
import de.happycar.tryout.bean.type.UTM;
import de.happycar.tryout.service.LinkService;
import de.happycar.tryout.util.LinkUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: Ne0t0N
 * Date: 28.05.2016
 */
@RestController
@RequestMapping
public class LinkController {

    private LinkService linkService;

    @Autowired
    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @RequestMapping(path = "/link/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<LinkResponse> registerLink(@RequestBody String link) {

        if (!LinkUtils.isValid(link)) {
            return new ResponseEntity<>(new LinkResponse(Message.INCORRECT_LINK), HttpStatus.BAD_REQUEST);
        }

        Link cachedLink = linkService.getByInitial(link);
        if (cachedLink != null) {
            return new ResponseEntity<>(new LinkResponse(cachedLink, Message.ALREADY_EXISTS), HttpStatus.BAD_REQUEST);
        }

        Link newLink = linkService.minifyAndSave(link);
        return new ResponseEntity<>(new LinkResponse(newLink, Message.OK), HttpStatus.OK);
    }

    @RequestMapping(path = "/link/register/trackable", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<LinkResponse> registerTrackableLink(@RequestBody String link) {

        if (!LinkUtils.hasParams(link, UTM.values())) {
            return new ResponseEntity<>(new LinkResponse(Message.NO_UTMS), HttpStatus.BAD_REQUEST);
        }

        return registerLink(link);
    }

    @RequestMapping(path = LinkUtils.SHORT_PATH + "**", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<LinkResponse> redirect(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        String id = path.replace(LinkUtils.SHORT_PATH, "");
        Link link = linkService.getById(id);
        if (link == null) {
            return new ResponseEntity<>(new LinkResponse(Message.NOT_REGISTERED), HttpStatus.BAD_REQUEST);
        }

        response.sendRedirect(link.getInitialLink());
        return new ResponseEntity<>(new LinkResponse(link, Message.OK), HttpStatus.OK);
    }
}
