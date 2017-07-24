package in.shamit.nlp.sentiment.yelp;

import java.util.Arrays;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import in.shamit.nlp.sentiment.demo.services.SentimentResponse;
import in.shamit.nlp.sentiment.fasttext.FastTextWrapper;
import in.shamit.nlp.sentiment.tensorflow.TensorFlowWrapper;

public class SentimentResolver {

	static Logger logger=null;
	
	static void log(String msg){
		if(logger==null){
			System.out.println(msg);
		}else{
			logger.info(msg);
		}
	}
	
	public static void initLog(){
		try{
			logger = Logger.getLogger("SentimentResolver");
			FileHandler fh;  
			 fh = new FileHandler(Config.LOG_DIR+"/sentiment.log",100*1024*1024,100);
		       logger.addHandler(fh);
		       SimpleFormatter formatter = new SimpleFormatter();  
		       fh.setFormatter(formatter);
		       logger.setUseParentHandlers(false);
		       logger.info("Log initialized");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public static String preProcess(String s){
		s=s.replace('\n', ' ');
		s=s.replace('\r', ' ');
		return s;
	}
	public static SentimentResponse getSentiment(String text){
		long startTime=new Date().getTime();
		SentimentResponse resp=new SentimentResponse();
		log("Processing :: "+text);
		resp.setText(text);
		text=preProcess(text);
		try{
			double[] vec = FastTextWrapper.getVectors(text);
			float[] inp = new float[vec.length];
			for (int i = 0; i < inp.length; i++) {
				inp[i] = (float) vec[i];
			}
			float[] result = TensorFlowWrapper.solveForInput(inp);
			if(result[0]>result[2]){
				resp.setSentiment("Negative");
			}else{
				resp.setSentiment("Positive");
			}
			resp.setNegativeScore(result[0]);
			resp.setPositiveScore(result[2]);
			resp.setVector(Arrays.toString(inp));
		}catch (Exception e) {
			resp.setError(e.getMessage());
		}
		long endTime=new Date().getTime();
		long elapsedTime=endTime-startTime;

		resp.setProcessingTime_milliseconds(elapsedTime);
		return resp;
	}
	public static void main(String args[]) {
		FastTextWrapper.init();
		TensorFlowWrapper.init();
		String sampleReview = "Servie was pathetic";
		double[] vec = FastTextWrapper.getVectors(sampleReview);
		float[] inp = new float[vec.length];
		for (int i = 0; i < inp.length; i++) {
			inp[i] = (float) vec[i];
		}
		float[] res = TensorFlowWrapper.solveForInput(inp);
		System.out.println(Arrays.toString(res));
	}
}
