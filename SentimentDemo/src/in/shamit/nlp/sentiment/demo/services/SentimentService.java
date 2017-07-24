package in.shamit.nlp.sentiment.demo.services;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.springframework.stereotype.Service;

import in.shamit.nlp.sentiment.yelp.SentimentResolver;

@Service( "sentimentService" )
public class SentimentService {
    @GET
    @Path("textSentiment/")
    @Produces("application/json")
    public SentimentResponse textSentiment(@QueryParam("text") String text){
    	return SentimentResolver.getSentiment(text);
    }
    @POST
    @Path("textSentimentPost/")
    @Produces("application/json")
    public SentimentResponse textSentimentPost(String text){
    	return SentimentResolver.getSentiment(text);
    }
}
