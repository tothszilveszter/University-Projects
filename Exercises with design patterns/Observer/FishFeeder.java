import java.util.Observable;

public class FishFeeder extends Observable {

    public void feedTheFish (){
        setChanged();
        notifyObservers();
    }

}
