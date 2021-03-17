import java.util.Comparator;

public class Criteriu implements Comparator<Client> {
	public int compare(Client a, Client b) {
		return a.getArr_time() - b.getArr_time();
	}
}