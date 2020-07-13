public class Main{
	public static void main(String[] args){
		ICert cert = new PersonFactory().create();
		cert.request();
	}
}