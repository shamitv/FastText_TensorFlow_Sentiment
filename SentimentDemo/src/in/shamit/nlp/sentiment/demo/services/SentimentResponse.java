package in.shamit.nlp.sentiment.demo.services;

public class SentimentResponse {
	double positiveScore;
	double negativeScore;
	String sentiment;
	String text;
	double processingTime_milliseconds;
	String error;
	String vector;
	public double getNegativeScore() {
		return negativeScore;
	}
	public void setNegativeScore(double negativeScore) {
		this.negativeScore = negativeScore;
	}
	public String getSentiment() {
		return sentiment;
	}
	public void setSentiment(String sentiment) {
		this.sentiment = sentiment;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public double getPositiveScore() {
		return positiveScore;
	}
	public void setPositiveScore(double positiveScore) {
		this.positiveScore = positiveScore;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public double getProcessingTime_milliseconds() {
		return processingTime_milliseconds;
	}
	public void setProcessingTime_milliseconds(double processingTime_milliseconds) {
		this.processingTime_milliseconds = processingTime_milliseconds;
	}
	public String getVector() {
		return vector;
	}
	public void setVector(String vector) {
		this.vector = vector;
	}
}
