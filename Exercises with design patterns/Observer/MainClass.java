import java.util.ArrayList;
import java.util.List;

public class MainClass {
    public static void main(String[] args) {
        List<TropicalFish> fishes=new ArrayList<>();
        fishes.add(new TropicalFish("gold"));
        fishes.add(new TropicalFish("silver"));

        FishFeeder angajat=new FishFeeder();
        for(TropicalFish fish: fishes){
            angajat.addObserver(fish);
        }

        angajat.feedTheFish();
    }
}
