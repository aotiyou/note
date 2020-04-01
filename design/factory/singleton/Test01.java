public class Test01{
	
	private static final Test01 test = new Test01();
	
	private Test01(){
		
	}
	
	private static Test01 getInstance(){
		return test;
	}
	
	public static void main(String[] args){
		Test01 t1 = Test01.getInstance();
		Test01 t2 = Test01.getInstance();
		System.out.print(t1 == t2);
	}
}