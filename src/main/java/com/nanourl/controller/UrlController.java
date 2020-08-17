package com.nanourl.controller;

import com.nanourl.pojo.Url;
import com.nanourl.service.UrlService;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping(value = "/url")
public class UrlController {

    @Autowired
    private UrlService urlService;

    @RequestMapping(method = RequestMethod.POST)
    public String createNanoUrl(@RequestBody Url url, HttpServletResponse response) throws IOException {
        UrlValidator urlValidator = new UrlValidator(new String[] {"http", "https"});

        // Validate whether input URL is valid before proceed
        if (!urlValidator.isValid(url.getLongUrl())) {
            response.sendError(HttpStatus.NOT_ACCEPTABLE.value());
            return "Invalid URL";
        }

        return urlService.createNanoUrl(url);
    }

    @RequestMapping(value = "/{nanoUrl}", method = RequestMethod.GET)
    public void findOriginalUrl(@PathVariable String nanoUrl, HttpServletResponse response) throws IOException {
        String originalUrl = urlService.findOriginalUrl(nanoUrl);

        if (originalUrl != null) {
            response.sendRedirect(originalUrl);
        } else {
            response.sendError(HttpStatus.NOT_FOUND.value());
        }
    }
}
