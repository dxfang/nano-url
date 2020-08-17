package com.nanourl.service;

import com.nanourl.dao.UrlDao;
import com.nanourl.pojo.Url;
import com.nanourl.util.BaseConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UrlService {

    @Autowired
    private UrlDao urlDao;
    @Autowired
    private BaseConverter baseConverter;

    /**
     *
     * @param originalUrl
     * @return (String) generated nano URL
     */
    public String createNanoUrl(Url originalUrl) {
        // First, check whether there are identical URLs already in use
        List<Url> existedUrl = urlDao.findBylongUrlAndExpired(originalUrl.getLongUrl(), 0);
        if (existedUrl.size() > 0) {
            return baseConverter.numToUrl(existedUrl.get(0).getId());
        }

        // If input URL is not in database or expired, new record will be created
        originalUrl.setClicks(0);
        originalUrl.setExpired(0);
        Url urlEntity = urlDao.save(originalUrl);

        return baseConverter.numToUrl(urlEntity.getId());
    }

    /**
     *
     * @param nanoUrl
     * @return (String) the original URL which users store or Null
     */
    public String findOriginalUrl(String nanoUrl) {
        long id = baseConverter.urlToNum(nanoUrl);
        Optional<Url> res = urlDao.findById(id);

        // Check for invalid ID
        if (!res.isPresent()) {
            return null;
        }

        Url urlEntity = res.get();

        // If a nano URL is clicked more than 10 times, set it to expired
        if (urlEntity.getClicks() >= 10) {
            urlEntity.setExpired(1);
            urlDao.save(urlEntity);
            return null;
        } else {
            urlEntity.setClicks(urlEntity.getClicks() + 1);     // Increase number of clicks by 1 and save changes
            urlDao.save(urlEntity);

            return urlEntity.getLongUrl();
        }
    }
}
