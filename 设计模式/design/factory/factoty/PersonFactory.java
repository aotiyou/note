public class PersonFactory{
	public ICert create(){
		System.out.print("创建人员证书");
		return new Person();
	}
}