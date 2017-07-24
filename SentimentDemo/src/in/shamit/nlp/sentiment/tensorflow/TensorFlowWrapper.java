package in.shamit.nlp.sentiment.tensorflow;

import java.nio.FloatBuffer;
import java.util.List;

import org.tensorflow.SavedModelBundle;
import org.tensorflow.Session;
import org.tensorflow.Tensor;

import in.shamit.nlp.sentiment.yelp.Config;

public class TensorFlowWrapper {
	static String modelDir=Config.MODEL_DIR;
	static SavedModelBundle modelBundle=null;
	static Session session=null;
	public static void init(){
		modelBundle = SavedModelBundle.load(modelDir,"serve");
		session=modelBundle.session();
	}
	public static float [] solveForInput(float[] inp) {
		float inp2[][]={inp};
		try{
			try(Tensor x = Tensor.create(inp2); ){
			Session.Runner rnr=session.runner().feed("InputData/X", x);
			rnr=rnr.fetch("FullyConnected_2/Softmax");
			List<Tensor> results=rnr.run();
			Tensor y = results.get(0);
			FloatBuffer op = FloatBuffer.allocate(y.numElements());
			y.writeTo(op);
			y.close();
			return op.array();
			}
		}catch(Exception ex){
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}
}
