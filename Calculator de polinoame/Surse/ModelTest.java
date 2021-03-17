import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JTextField;

import org.junit.jupiter.api.Test;

class ModelTest { 	//am testat cateva metode folosind JUnitTest
	JTextField txt=new JTextField();
	@Test
	void test() {
		Model test=new Model();
		test.Derivare("2*x^2+1*x^0", txt);
		String s=txt.getText();
		assertEquals("+4*x^1",s);
	}
	
	@Test
	void test2() {
		Model test=new Model();
		test.AdunareScadere("2*x^3+1*x^0","3*x^3+4*x^1", txt,0);
		String s=txt.getText();
		assertEquals("+5*x^3+4*x^1+1*x^0",s);
	}
	
	@Test
	void test3() {
		Model test=new Model();
		test.AdunareScadere("2*x^3+1*x^0","3*x^3+4*x^1", txt,0);
		String s=txt.getText();
		assertEquals("+6*x^3+4*x^1+1*x^0",s);
	}

	@Test
	void test4() {
		Model test=new Model();
		test.Integrare("2*x^2+3*x^0", txt);
		String s=txt.getText();
		assertEquals("+2/3*x^3+3*x^1",s);
	}
}
