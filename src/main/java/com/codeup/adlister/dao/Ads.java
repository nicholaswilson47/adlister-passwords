package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;

import java.util.List;

public interface Ads {
    // get a list of all the ads
    List<Ad> all();
    // insert a new ad and return the new ad's id
    Long insert(Ad ad);

    Ad individualAd(Long id);

    void updateProfile(String username, String password, String email, long id);

    List<Ad> getAdsByUser(long id);

    List<Ad> deleteAd(long id);

    List<Ad> updateAd(long id,String title,String description);

    List<Ad> search(String term);

    Ad byAdID(long id);

}
