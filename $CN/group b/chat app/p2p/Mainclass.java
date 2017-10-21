package p2p;

public class Mainclass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
    Myserver s1=new Myserver();
    s1.start();
		Myclient c1= new Myclient();
    c1.start();
	}

}
