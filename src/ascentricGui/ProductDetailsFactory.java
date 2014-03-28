package ascentricGui;

public class ProductDetailsFactory {
	
	private static ProductDetails firstProdDetInstance;
	
	private static ProductDetails secondProdDetInstance;
	
	private static ProductDetails jointProdDetInstance;
	
	public static void makeFirst(){
		firstProdDetInstance = new FirstProductDetails();
	}
	
	public static ProductDetails getFirst(){
		return firstProdDetInstance;
	}
	
	public static void makeSecond(){
		secondProdDetInstance = new SecondProductDetails();
	}
	
	public static ProductDetails getSecond(){
		return secondProdDetInstance;
	}
	
	public static void makeThird(){
		jointProdDetInstance = new JointProductDetails();
	}
	
	public static ProductDetails getThird(){
		return jointProdDetInstance;
	}

}
