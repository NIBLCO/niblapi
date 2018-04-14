package com.nibl.api.anilist.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nibl.api.anilist.domain.AccessToken;
import com.nibl.api.anilist.domain.Season;
import com.nibl.api.anilist.domain.Series;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;

@Component("AniList")
public class AnilistApiService {
	
    private static final Logger logger = LoggerFactory.getLogger(AnilistApiService.class);

    @Value("${nibl.anilist.id}")
    private String CLIENT_ID;
    @Value("${nibl.anilist.secret}")
    private String CLIENT_SECRET;
    
    private AccessToken token = null;
    
    private AccessToken getAccessToken() {
    	
    	if( null != token && DateUtils.addSeconds(token.getCreated(), token.getExpiresIn()).after(new Date()) ) { 
    		return token;
    	}
    	
        String url = "https://anilist.co/api/auth/access_token";

        URL obj;
        try {
        	// TODO consolidate HTTP connections
            obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // set request params with my client info on AniList.co/settings/developer
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", "NIBL");
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            // hard coded client credentials for now
            String urlParameters = "grant_type=client_credentials&client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET;

            // Send post request
            con.setDoOutput(true);
            con.setDoInput(true);
            OutputStream os = con.getOutputStream();
            BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            wr.write(urlParameters);
            wr.flush();
            wr.close();
            os.close();
            
            int responseCode = con.getResponseCode();
            
            logger.info("\nSending 'POST' request to URL : " + url + " . Param: " + urlParameters);
            logger.info("Response Code : " + responseCode);
            
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder responseBuffer = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                responseBuffer.append(inputLine);
            }
            in.close();

            ObjectMapper mapper = new ObjectMapper();
            
            token = mapper.readValue(responseBuffer.toString(), AccessToken.class);
            
            token.setCreated(new Date());
            
        } catch (Exception e) {
            logger.error(e.toString());
        }

        return token;
        
    }


    public List<Series> fetchAllSeries(Season season) {
    	AccessToken accessToken = getAccessToken();
    	
        String url = "https://anilist.co/api/browse/anime?access_token=" + accessToken.getAccessToken()
                + "&year=" + season.getYear()
                + "&season=" + season.getSeasonName()
                + "&type=Tv"
                + "&airing_data=true"
                + "&full_page=true";

        try {
        	// TODO consolidate HTTP connections
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", "NIBL");

            int responseCode = con.getResponseCode();
            
            
            logger.info("\nSending 'GET' request to URL : " + url);
            logger.info("Response Code : " + responseCode);
            

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer responseBuffer = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                responseBuffer.append(inputLine);
            }
            in.close();

            // Set up the ObjectMapper to map an array of series into an array of objects.
            ObjectMapper mapper = new ObjectMapper();
            List<Series> seriesList = mapper.readValue(responseBuffer.toString(), new TypeReference<List<Series>>() {});
            
            return seriesList;
        } catch (Exception e)

        {
            logger.error(e.toString());
        }

        return null;
    }
    
}
