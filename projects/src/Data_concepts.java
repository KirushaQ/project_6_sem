import java.util.HashMap;

public class Data_concepts {
    private HashMap<String, String> ingredients;
    private HashMap<String, String> tools;
    private HashMap<String, String> actions;

    public Data_concepts(){
        ingredients = new HashMap<>();
        tools = new HashMap<>();
        actions = new HashMap<>();

        addIngredients();
        addTools();
        addActions();
    }

    private void addIngredients(){
        ingredients.put("Морковь", "concept_carrot");
        ingredients.put("Картофель", "concept_potato");
        ingredients.put("Лук", "concept_onion");
        ingredients.put("Мясо", "concept_meat");
        ingredients.put("Огурец", "concept_cucumber");
        ingredients.put("Помидор", "concept_tomato");
        ingredients.put("Соль", "concept_salt");
        ingredients.put("Сахар", "concept_sugar");
        ingredients.put("Молоко", "concept_milk");
        ingredients.put("Рыба", "concept_fish");
        ingredients.put("Чесник", "concept_garlic");
        ingredients.put("Перец", "concept_pepper");
    }

    private void addTools(){
        tools.put("Ложка", "concept_spoon");
        tools.put("Вилка", "concept_fork");
        tools.put("Тарелка", "concept_plate");
        tools.put("Нож", "concept_knife");
        tools.put("Стакан", "concept_glass");
        tools.put("Морозилка", "concept_freezer");
        tools.put("Плита", "concept_cooker");
        tools.put("Кастрюля", "concept_pan");
        tools.put("Сковорода", "concept_frying_pan");
    }

    private void addActions(){
        actions.put("Кипячение","nrel_boiling");
        actions.put("Охлаждение","nrel_cooling");
        actions.put("Заморозка","nrel_freezing");
        actions.put("Прожарка","nrel_frying");
        actions.put("Нарезка","nrel_cutting");
        actions.put("Разделка","nrel_chopping");
        actions.put("Нагревание","nrel_heating");
    }

    public String getIngredients(String key){
        if(ingredients.containsKey(key)) return ingredients.get(key);
        else return null;
    }

    public String getTools(String key){
        if(tools.containsKey(key)) return tools.get(key);
        else return null;
    }

    public String getActions(String key){
        if(actions.containsKey(key)) return actions.get(key);
        else return null;
    }
}
