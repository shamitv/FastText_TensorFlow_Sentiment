package in.shamit.nlp.sentiment.fasttext;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class DecimalFormatTest {

	public static void main(String[] args) {
		NumberFormat nf = NumberFormat.getNumberInstance();
		DecimalFormat df = (DecimalFormat)nf;
		df.applyPattern("#####.##########");
		System.out.println(df.format(0.00001));
		System.out.println(df.format(-0.00001));
		System.out.println(df.format(-230.00001));
		System.out.println(df.format(230.00001423432234324324));
	}

}
