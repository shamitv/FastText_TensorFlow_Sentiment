package in.shamit.nlp.sentiment.yelp;

public class Config {
	public static String BASE_DIR="G:/work/nlp/datasets/yelp/yelp_dataset_challenge_round9/";
	public static String LOG_DIR=BASE_DIR+"log/";
	public static String SPLIT_DIR=BASE_DIR+"split/";
	public static String MODEL_DIR=BASE_DIR+"tflearn_model_qa/serving_model";
	public static String REVIEW_JSON=BASE_DIR+"yelp_academic_dataset_review.json";
	public static String BUSINESS_JSON=BASE_DIR+"yelp_academic_dataset_business.json";
	public static String BUSINESS_CSV=BASE_DIR+"yelp_academic_dataset_business.csv";
	public static String REVIEW_CSV=BASE_DIR+"yelp_academic_dataset_review.csv";
	public static String REVIEW_DB="K:/nlp/sentiment/data/yelp/larger_dataset/"+"db/yelp_academic_dataset.db";
	//public static String FASTTEXT_COMMAND="C:\\Windows\\System32\\bash";
	//public static String FASTTEXT_PARAM=" -c '/mnt/k/nlp/fastText/fasttext  print-sentence-vectors  /mnt/k/nlp/sec/edgar/sec_edgar~/sec_edgar~ '";
	public static String FASTTEXT_COMMAND="K:\\nlp\\fastText-windows\\fastText.exe";
	public static String FASTTEXT_PARAM_1="print-sentence-vectors";
	public static String FASTTEXT_PARAM_2="k:\\nlp\\sec\\edgar\\sec_edgar~\\sec_edgar~";
	public static int TRAINING_SIZE=100000;
	public static int TEST_SIZE=20000;
	public static int VALIDATION_SIZE=20000;
}
