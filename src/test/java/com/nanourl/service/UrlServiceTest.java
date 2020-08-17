package com.nanourl.service;

import com.nanourl.dao.UrlDao;
import com.nanourl.pojo.Url;
import com.nanourl.util.BaseConverter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UrlServiceTest {

    @Mock
    UrlDao mockUrlDao;
    @Mock
    BaseConverter mockBaseConverter;
    @InjectMocks
    UrlService urlService;

    @Test
    public void createNanoUrlTest() {
        Url mockUrl = new Url();
        mockUrl.setId(28);
        mockUrl.setLongUrl("https://www.intuit.com");
        mockUrl.setClicks(0);
        mockUrl.setExpired(0);

        Mockito.when(mockUrlDao.save(ArgumentMatchers.any(Url.class))).thenReturn(mockUrl);
        Mockito.when(mockBaseConverter.numToUrl(mockUrl.getId())).thenReturn("S");
        Url url = new Url();
        url.setLongUrl("https://www.intuit.com");

        Assert.assertEquals("S", urlService.createNanoUrl(url));
    }

    @Test
    public void findOriginalUrlTest() {
        Mockito.when(mockBaseConverter.urlToNum("KS")).thenReturn((long) 1268);

        Url url = new Url();
        url.setId(1268);
        url.setLongUrl("https://www.github.com");
        url.setClicks(0);
        url.setExpired(0);

        Mockito.when(mockUrlDao.findById((long) 1268)).thenReturn(java.util.Optional.of(url));

        Assert.assertEquals("https://www.github.com", urlService.findOriginalUrl("KS"));
    }
}
