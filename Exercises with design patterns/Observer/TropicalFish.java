import java.util.Observable;
import java.util.Observer;

public class TropicalFish implements Observer {

    private String name;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "TropicalFish{" +
                "name='" + name + '\'' +
                '}';
    }

    public TropicalFish(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(getName() + " was fed");
    }
}
