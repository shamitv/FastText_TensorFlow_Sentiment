package in.shamit.nlp.sentiment.fasttext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.lang.ProcessBuilder.Redirect;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import in.shamit.nlp.sentiment.yelp.Config;


public class FastTextWrapper {

	private final static Logger LOG = Logger.getLogger("FastTextWrapper");
	
	static int vectorDim=300;
	static BufferedReader fastTextOutput=null;
	static PrintWriter  fastTextInput=null;
	
	public static void init(){
		try{
			String command=Config.FASTTEXT_COMMAND;
			LOG.info("Command is :: "+command);
			ProcessBuilder pb = new ProcessBuilder(command,Config.FASTTEXT_PARAM_1,Config.FASTTEXT_PARAM_2);
			pb.redirectErrorStream(true);
			//pb.redirectInput(Redirect.INHERIT);
			//pb.redirectOutput(Redirect.INHERIT);
			LOG.info("Starting process");
			Process process = pb.start();
			LOG.info("process started");
			//Get STDOUT
			InputStream stdOutStream = process.getInputStream();
			InputStreamReader r= new InputStreamReader(stdOutStream, Charset.forName("UTF-8"));
			BufferedReader br = new BufferedReader(r);
			fastTextOutput = br;
			OutputStream stdIntream = process.getOutputStream();
			PrintWriter pw = new PrintWriter(stdIntream);
			fastTextInput=pw;
			LOG.info("Stream setup complete");
			LOG.info("Fasttext test run");
			pw.println("This is a test");
			pw.flush();
			LOG.info(br.readLine());
		}catch(Exception ex){
			throw new RuntimeException(ex);
		}
	}
	
	public static synchronized double[] getVectors(String text){
		//LOG.info("Fasttext input::"+text);
		try {
			fastTextInput.println(text);
			fastTextInput.flush();
			String resp=fastTextOutput.readLine();
			if(resp.contains("-nan(ind)")){
				LOG.info("Invalid Fasttext Output::"+resp);
				throw new UnknownFasttextVector(); 
			}
			String []parts=resp.split("\\s");
			//System.out.println(parts.length);
			String nums[]=Arrays.copyOfRange(parts, (parts.length-vectorDim), parts.length);
			List<Double> vals=new ArrayList<>();
			for (String s : nums) {
				vals.add(Double.parseDouble(s));
			}
			double ret[]=vals.stream().mapToDouble(i->i).toArray();
			return ret;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void main(String[] args) throws Exception {
		init();
		double vec[]=getVectors("============================== ==  I'm writing to let you know that we've removed your review update of Pork on a Fork. Your review was flagged by the Yelp community, and our Support team has determined that it falls outside our Content Guidelines (http://www.yelp.com/gu...) because it does not contain a new firsthand experience with the business.  We hope you will continue to participate on Yelp while keeping our Content Guidelines in mind.  ============================== ==============    I've been to POAF several (4-5) times since this review of 12-29-10, and there are credit reciepts and the restaurant's own record of spent living social deals to prove it.    the quality of food and portions has gone down terribley.    the price of the food has gone up drastically.  based on these several recent experiences i had decided that my opinion of them and the score i award them should change.   ====================== review of  12/29/2010  below  --------------------------------------   My previous review held one back for a few reasons, but because the responsiveness of the owners of POAF is pretty impressive, they deserve the 5th star overall.  They've now added several menu items, including brats in many flavors, seasoned waffle fries and sweet potato fries.    While finishing up our HUGE sandwich of smoky, porky goodness, the owner invited Daren and I over to taste a sample of a new HOT sauce he was developing (in response to customer comments, of course). It was tangy and had a nice smokiness and a bit more heat than the other sauces.  Looking forward to a final version on the tables, but I have to recommend these midwesterners get out and sample more of what this part of the country considers a baseline as far as Scoville units!  8^ )  ... going back to work in a Hickory/Mesquite cloud gave them some great free advertising.    Show owner comment »    Edit Remove People thought this was: Useful (15) Funny (12) Cool (12)    Comment from Wes H. of Pork on a Fork 10/21/2011    « Hide  In Response........I just read your updated review and just wanted to let you know that it is unfortunate that you took away a star based on someone else's review. At Pork on a Fork we take pride in what we do and let our food speak for itself and welcome competition. We have a huge base of customers and some of them are die hard fans of good BBQ. I can assure you that no one from POAF has written any bogus reviews. I have reached out to the review in question and the reviewer responded back by saying he called Ric's Smokehouse to let him know that it was him that left the review and not us. As I said before, we accept that this is competition and we at POAF will let the food do our talking because we can't control what other reviewers post...good or bad.  We made the effort to call you before replying to your recent review, but had to leave a message. We appreciate your passion and we hope you reconsider these responses on something POAF has no control over. Message Wes H. Flag this comment  1 Previous Review: Hide »     11/24/2010  Reviewing their food for the first time at their new restaurant location in far north phoenix (Deer Valley Airport only USED TO be the boonies), but i've been hearing about their meat for some time now.  A delicious smoky aroma gently smacks you around and lulls you into submission once you enter.  A simple menu offers the lunch selection (soon to offer breakfast too). I knew i wanted pulled pork as an entree, and honestly didn't notice what else they had.    The usual side offerings of beans, potato salad, and coleslaw never appeal to me, personally.  I don't fault them for that, but a pickle spear being my only other available side left me feeling less than satiated.  Maybe some french fries, or at least chips?    The pulled pork sandwich had a nice smoky flavor, and the meat was juicy without being fatty.  The bun was simply generic.  Maybe these guys could get into cahoots with a local baker to put their special meat on something a little more worthy?    The sauces offered were \"Sweet Mild\" (which was both) and \"Hog Wild Hot\" (which was neither, but still tasty).  Both had a distinct molasses sweetness in a thick tomatoey base, the Hot with a little more spice flavor to it, but not much heat at all.  The atmosphere is simple and clean.  Ribs, breakfast, and \"Swine & Dine\" nights are on the way, according to the owners. Look forward to many more days of coming back to work smelling like a meat smoker !");
		System.out.println(vec);
	}

}
