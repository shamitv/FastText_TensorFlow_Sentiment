package in.shamit.nlp.sentiment.demo.services.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import in.shamit.nlp.sentiment.fasttext.FastTextWrapper;
import in.shamit.nlp.sentiment.tensorflow.TensorFlowWrapper;
import in.shamit.nlp.sentiment.yelp.SentimentResolver;

public class SentimentInittializer implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("Initializing FastText");
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				FastTextWrapper.init();
				TensorFlowWrapper.init();
				SentimentResolver.initLog();
			}
		});
		t.setName("FastTextinitializer");
		t.start();
	}

}
