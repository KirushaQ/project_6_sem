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
        ingredients.put("Капуста", "concept_cabbage");
        ingredients.put("Салат", "concept_salad");
    }

    private void addTools(){
        tools.put("Ложка", "spoon");
        tools.put("Вилка", "fork");
        tools.put("Тарелка", "plate");
        tools.put("Нож", "knife");
        tools.put("Стакан", "glass");
        tools.put("Морозилка", "freezer");
        tools.put("Плита", "cooker");
        tools.put("Кастрюля", "pan");
        tools.put("Сковорода", "frying_pan");
    }

    private void addActions(){
        actions.put("Кипячение","boiling");
        actions.put("Охлаждение","cooling");
        actions.put("Заморозка","freezing");
        actions.put("Прожарка","frying");
        actions.put("Нарезка","cutting");
        actions.put("Разделка","chopping");
        actions.put("Нагревание","heating");
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
