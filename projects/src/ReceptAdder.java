import home.com.smarthome.sctp.*;

import java.util.ArrayList;

public class ReceptAdder {
    private static ScAddr RECEPT;
    private static ScAddr INGREDIENTS;
    private static ScAddr COOCKING_TIME;
    private static ScAddr VALUE;
    private static ScAddr MINUTES;
    private static ScAddr STAGE_DECOMPOSITION;
    private static ScAddr RECEPT_STEP;
    private static ScAddr EQUIPMENT;
    private static ScAddr STAGE_PRODUCTS;
    private static ScAddr PROCESSING_METHOD;
    private static ScAddr STEP_DESCRIPTION;
    private static ScAddr ID;

    private SctpClient sctpClient;
    private Recept info;

    public ReceptAdder(Recept info){
        this.info = info;
        connect();
        base();
    }

    public void base(){
        int i;
        ScAddr nd;
        ScAddr nd2;
        ScAddr stages;
        ScAddr step_node;
        ScAddr disc;
        ScAddr ingrd;
        ScAddr tl;
        ScAddr act;
        ScAddr nd_name;

        System.out.println(info.getSc_name());
        System.out.println(info.getName());
        System.out.println(info.getAll_ingredients());

        ScAddr rec = sctpClient.createNode(new ScType(ScType.Node));
        sctpClient.setSystemIdentifier(rec, info.getSc_name());
        sctpClient.createArc(RECEPT, rec, new ScType(ScType.ArcPosConstPerm));
        addToName(rec, info.getName() + " (Рецепт)");
        for (String s: info.getAll_ingredients()){
            nd = sctpClient.findElementBySystemIdentifier(s);
            addNewArc(rec, nd, INGREDIENTS);
        }
        nd = sctpClient.createNode(new ScType(ScType.Node));
        sctpClient.setSystemIdentifier(nd, info.getSc_name() + "_time");
        System.out.println("XXX");
        addNewArc(rec, nd, COOCKING_TIME);
        addToName(nd, "Время приготовления");
        nd2 = sctpClient.createNode(new ScType(ScType.Node));
        sctpClient.setSystemIdentifier(nd2, info.getSc_name() + "_time_value");
        addNewArc(nd, nd2, VALUE);
        addToName(nd2, "Значение");
        nd =  sctpClient.createLink();
        sctpClient.setLinkContent(nd, SctpClient.ByteBufferFromString(String.valueOf(info.getAll_time())));
        addNewArc(nd2, nd, MINUTES);

        stages = sctpClient.createNode(new ScType(ScType.Node));
        sctpClient.setSystemIdentifier(stages, info.getSc_name() + "_stages");
        addToName(stages, "Этапы приготовления");
        addNewArc(rec, stages, STAGE_DECOMPOSITION);
        i = 1;
        for (Step step: info.getSteps()){
            System.out.println(step.getIngredients());
            System.out.println(step.getTools());
            System.out.println(step.getActions());
            step_node = sctpClient.createNode(new ScType(ScType.Node));
            sctpClient.setSystemIdentifier(step_node, info.getSc_name() + "_step_" + String.valueOf(i));
            addToName(step_node, "Шаг" + String.valueOf(i));
            addNewArc(stages, step_node, RECEPT_STEP);

            disc =  sctpClient.createLink();
            sctpClient.setLinkContent(disc, SctpClient.ByteBufferFromString(step.getDescription()));
            addNewArc(step_node, disc, STEP_DESCRIPTION);

            for (String ingredient: step.getIngredients()){
                ingrd = sctpClient.findElementBySystemIdentifier(ingredient);
                addNewArc(step_node, ingrd, STAGE_PRODUCTS);
            }
            for (String tool: step.getTools()){
                tl = sctpClient.findElementBySystemIdentifier(tool);
                addNewArc(step_node, tl, EQUIPMENT);
            }
            for (String action: step.getActions()){
                act = sctpClient.findElementBySystemIdentifier(action);
                addNewArc(step_node, act, PROCESSING_METHOD);
            }
            i++;
        }

        System.out.println("End");
    }

    private void addToName(ScAddr nd, String nodeName){
        ScAddr name = sctpClient.createLink();
        ScAddr ru = sctpClient.findElementBySystemIdentifier("lang_ru");
        sctpClient.setLinkContent(name, SctpClient.ByteBufferFromString(nodeName));
        ScAddr nameArc = sctpClient.createArc(nd, name, new ScType(ScType.ArcCommon));//ArcCommon
        sctpClient.createArc(ID, nameArc, new ScType(ScType.ArcPosConstPerm));//ArcPosConstPerm
        sctpClient.createArc(ru, name, new ScType(ScType.ArcPosConstPerm));
    }

    private void addNewArc(ScAddr name, ScAddr node, ScAddr nrel){
        if(node == null) {
            return;
        }
        ScAddr nameArc = sctpClient.createArc(name, node, new ScType(ScType.ArcCommon));
        sctpClient.createArc(nrel, nameArc, new ScType(ScType.ArcPosConstPerm));
    }

    public boolean connect(){
        boolean flag = false;
        sctpClient = new SctpClient();
        if(sctpClient.connect("localhost", 55770)){
            System.out.println("Connect success");
            flag = true;
        }

        RECEPT = sctpClient.findElementBySystemIdentifier("concept_recept");
        INGREDIENTS = sctpClient.findElementBySystemIdentifier("nrel_ingredients");
        COOCKING_TIME = sctpClient.findElementBySystemIdentifier("nrel_coocking_time");
        VALUE = sctpClient.findElementBySystemIdentifier("nrel_value");
        MINUTES = sctpClient.findElementBySystemIdentifier("rrel_minutes");
        STAGE_DECOMPOSITION = sctpClient.findElementBySystemIdentifier("nrel_stage_decomposition");
        RECEPT_STEP = sctpClient.findElementBySystemIdentifier("nrel_recept_step");
        EQUIPMENT = sctpClient.findElementBySystemIdentifier("nrel_equipment");
        STAGE_PRODUCTS = sctpClient.findElementBySystemIdentifier("nrel_stage_products");
        PROCESSING_METHOD = sctpClient.findElementBySystemIdentifier("nrel_processing_method");
        STEP_DESCRIPTION = sctpClient.findElementBySystemIdentifier("nrel_step_description");
        ID = sctpClient.findElementBySystemIdentifier("nrel_main_idtf");

        return flag;
    }

    public static void main(String[] args){
        Recept info = new Recept("Салат", "salad");
        Data_concepts data = new Data_concepts();
        ArrayList<String> all_ing = new ArrayList<>();
        ArrayList<Step> steps = new ArrayList<>();
        Step step1 = new Step();
        Step step2 = new Step();
        Step step3 = new Step();
        step1.setTime(60);
        step1.setDescription("Описание шага 1");
        all_ing.add(data.getIngredients("Морковь"));
        all_ing.add(data.getIngredients("Лук"));
        step1.setIngredients(all_ing);
        all_ing = new ArrayList<>();
        all_ing.add(data.getTools("Ложка"));
        all_ing.add(data.getTools("Тарелка"));
        step1.setTools(all_ing);
        all_ing = new ArrayList<>();
        all_ing.add(data.getActions("Кипячение"));
        all_ing.add(data.getActions("Нарезка"));
        step1.setActions(all_ing);
        all_ing = new ArrayList<>();
        steps.add(step1);

        step2.setTime(70);
        step2.setDescription("Описание шага 2");
        all_ing.add(data.getIngredients("Морковь"));
        step2.setIngredients(all_ing);
        all_ing = new ArrayList<>();
        all_ing.add(data.getTools("Тарелка"));
        step2.setTools(all_ing);
        all_ing = new ArrayList<>();
        all_ing.add(data.getActions("Кипячение"));
        step2.setActions(all_ing);
        all_ing = new ArrayList<>();
        steps.add(step2);

        step3.setTime(80);
        step3.setDescription("Описание шага 3");
        all_ing.add(data.getIngredients("Морковь"));
        all_ing.add(data.getIngredients("Перец"));
        step3.setIngredients(all_ing);
        all_ing = new ArrayList<>();
        all_ing.add(data.getTools("Ложка"));
        all_ing.add(data.getTools("Морозилка"));
        step3.setTools(all_ing);
        all_ing = new ArrayList<>();
        all_ing.add(data.getActions("Разделка"));
        all_ing.add(data.getActions("Нарезка"));
        step3.setActions(all_ing);
        all_ing = new ArrayList<>();
        steps.add(step3);

        info.setSteps(steps);
        info.setAll_time(210);

        all_ing.add(data.getIngredients("Морковь"));
        all_ing.add(data.getIngredients("Лук"));
        all_ing.add(data.getIngredients("Перец"));
        info.setAll_ingredients(all_ing);
        ReceptAdder adder = new ReceptAdder(info);
    }
}
