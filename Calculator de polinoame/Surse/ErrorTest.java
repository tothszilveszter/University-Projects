
public class ErrorTest {		//in aceasta clasa am rezolvat erori care pot aparea
	public void Test(String p1) {
		if (p1.length()<5)
		{
			System.out.println("Possible trying to work w/ 0 or w/ wrong input!");
			System.exit(0);
		}
		else if (p1.length() == 0) {
			System.out.println("Empty Input!");
			System.exit(0);
		} else {
			String verif = "0123456789x^*+-";
			for (int i = 0; i < p1.length(); i++) {
				if (verif.indexOf(p1.charAt(i)) == -1) {
					System.out.println("Wrong Characters Used!");
					System.exit(0);
				}
			}
		}
	}
}
