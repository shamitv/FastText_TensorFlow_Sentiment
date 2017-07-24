package in.shamit.nlp.sentiment.data;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import in.shamit.nlp.sentiment.fasttext.FastTextWrapper;

public class DatasetProcessor {
	private final static Logger LOG = Logger.getLogger("DatasetProcessor");
	public static void init(){
		FastTextWrapper.init();
	}
	
	public static void convertToVector(File input, File text, File vector){
		try {
			List<String> inputLines=Files.readAllLines(input.toPath());
			List<String> validLines, vectorLines;
			validLines=new ArrayList<>();
			vectorLines=new ArrayList<>();
			for (String l : inputLines) {
				try {
					double vec[]=FastTextWrapper.getVectors(l);
					validLines.add(l);
					String vecLine=getStringForVector(vec);
					vectorLines.add(vecLine);
				} catch (Exception e) {
					LOG.info("No vector for :: "+l);
				}
			}
			Files.write(text.toPath(), validLines, Charset.forName("UTF-8"));
			Files.write(vector.toPath(), vectorLines, Charset.forName("UTF-8"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private static String getStringForVector(double[] vec) {
		DecimalFormat df = new DecimalFormat("#.######");
		StringBuffer sb=new StringBuffer();
		for (double d : vec) {
			String str=df.format(d);
			sb.append(str);
			sb.append(" ");
		}
		String ret=sb.toString();
		return ret.trim();
	}

	public static void processFile(String name){
		LOG.info("Processing input :: "+name);
		String baseLoc="G:/work/nlp/datasets/finance_sentiment/";
		File inputDir=new File(baseLoc,"input");
		File textDir=new File(baseLoc,"text");
		File vecDir=new File(baseLoc,"vector");
		if(!textDir.exists()){
			LOG.info("Creating dir :: "+textDir.getAbsolutePath());
			textDir.mkdir();
		}
		if(!vecDir.exists()){
			LOG.info("Creating dir :: "+vecDir.getAbsolutePath());
			vecDir.mkdir();
		}		
		File inputFile=new File(inputDir,name+".txt");
		LOG.info("Input file :: "+inputFile.getAbsolutePath());
		File textFile=new File(textDir,name+".txt");
		File vecFile=new File(vecDir,name+".txt");
		LOG.info("Output files :: "+textFile.getAbsolutePath()+" :: "+vecFile.getAbsolutePath());
		convertToVector(inputFile, textFile, vecFile);
	}
	
	public static void main(String[] args) {
		init();
		processFile("positive");
		processFile("negative");
		processFile("neutral");
	}

}
