package ascentricGui;

public class ProductDetailsFactory {
	
	private static ProductDetailsGui firstProdDetInstance;
	
	private static ProductDetailsGui secondProdDetInstance;
	
	private static ProductDetailsGui jointProdDetInstance;
	
	public static void makeFirst(){
		firstProdDetInstance = new FirstProductDetails();
	}
	
	public static ProductDetailsGui getFirst(){
		return firstProdDetInstance;
	}
	
	public static void makeSecond(){
		secondProdDetInstance = new SecondProductDetails();
	}
	
	public static ProductDetailsGui getSecond(){
		return secondProdDetInstance;
	}
	
	public static void makeThird(){
		jointProdDetInstance = new JointProductDetails();
	}
	
	public static ProductDetailsGui getThird(){
		return jointProdDetInstance;
	}

}
