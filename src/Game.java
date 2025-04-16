import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Game {

    ArrayList<Figurine> figurines;

    public Game(){
        figurines = new ArrayList<>();
    }

    public String inicializationOfFigurines(){
        for (int i = 0; i < 16; i++) {
            figurines.add(new Figurine())
        }
    }

    public String addFigurine(boolean white, int line, int column){
        figurines.add()
    }

    public String loadDefaultPosition(){
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader("res/defaultPosition"))){
            while((line = reader.readLine()) != null){
                String parts[] = line.split(";");

            }
        } catch (Exception e){

        }
    }

}
