import java.util.ArrayList;

public class Recept {
    private String name;
    private String sc_name;
    private ArrayList<Step> steps;
    private int all_time;
    private ArrayList<String> all_ingredients;

    public Recept(String name, String sc_name){
       this.name = name;
       this.sc_name = "concept_recept_" + sc_name;
       steps = new ArrayList<>();
       all_ingredients = new ArrayList<>();
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }

    public String getSc_name() {
        return sc_name;
    }

    public String getName() {
        return name;
    }

    public void setSteps(ArrayList<Step> steps) {
        this.steps = steps;
    }

    public int getAll_time() {
        return all_time;
    }

    public void setAll_time(int all_time) {
        this.all_time = all_time;
    }

    public ArrayList<String> getAll_ingredients() {
        return all_ingredients;
    }

    public void setAll_ingredients(ArrayList<String> all_ingredients) {
        this.all_ingredients = all_ingredients;
    }
}
