import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Main {
    private Data_concepts data;
    private Recept recept;
    private Step step;
    ArrayList<Step> steps;
    ArrayList<String> ingredients;
    ArrayList<String> tools;
    ArrayList<String> actions;
    private JFrame frame;

    private JLabel base_label;
    private JTextField base_text;
    private JLabel base_eng_label;
    private JTextField base_eng_text;
    private JButton base_button;

    private JFrame frame_recept;
    private JButton add_ingredient;
    private JButton add_tool;
    private JButton add_action;
    private JButton add_time;
    private JButton add_step;
    private JButton end_recept;
    private JButton add_descripton;

    private JTextField text_ingredient;
    private JTextField text_tool;
    private JTextField text_action;
    private JTextField text_time;
    private JTextField text_description;

        public Main(Data_concepts data){
            this.data = data;

            frame = new JFrame();
            frame.setSize(300, 500);
            frame.getContentPane().setLayout(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            //frame base
            base_label = new JLabel("Введите название рецепта (ru)");
            base_label.setBounds(50, 10, 200, 60);
            base_text = new JTextField();
            base_text.setBounds(50,80,200,60);
            base_eng_label = new JLabel("Введите название рецепта (eng)");
            base_eng_label.setBounds(50, 160, 200, 60);
            base_eng_text = new JTextField();
            base_eng_text.setBounds(50,240,200,60);
            base_button = new JButton("Заполнить рецепт");
            base_button.setBounds(50,320,200,100);
            base_button.addActionListener(new BaseButtonListener());
            frame.add(base_label);
            frame.add(base_text);
            frame.add(base_eng_label);
            frame.add(base_eng_text);
            frame.add(base_button);

            //frame recept
            frame_recept = new JFrame();
            frame_recept.setSize(900, 700);
            frame_recept.getContentPane().setLayout(null);

            text_ingredient = new JTextField();
            text_ingredient.setBounds(20,10,180,60);
            add_ingredient = new JButton("Добавить ингредиент");
            add_ingredient.setBounds(20,100,180,80);
            add_ingredient.addActionListener(new IngredientButtonListener());

            text_tool = new JTextField();
            text_tool.setBounds(230,10,180,60);
            add_tool = new JButton("Добавить инструмент");
            add_tool.setBounds(230,100,180,80);
            add_tool.addActionListener(new ToolButtonListener());

            text_action = new JTextField();
            text_action.setBounds(440,10,180,60);
            add_action = new JButton("Добавить действие");
            add_action.setBounds(440,100,180,80);
            add_action.addActionListener(new ActionButtonListener());

            text_time = new JTextField();
            text_time.setBounds(650,10,180,60);
            add_time = new JButton("Добавить время");
            add_time.setBounds(650,100,180,80);
            add_time.addActionListener(new TimeButtonListener());

            frame_recept.add(text_ingredient);
            frame_recept.add(add_ingredient);
            frame_recept.add(text_tool);
            frame_recept.add(add_tool);
            frame_recept.add(text_action);
            frame_recept.add(add_action);
            frame_recept.add(text_time);
            frame_recept.add(add_time);

            add_step = new JButton("Добавить шаг");
            add_step.setBounds(340,450,180,80);
            add_step.addActionListener(new StepButtonListener());

            end_recept = new JButton("Завершить рецепт");
            end_recept.setBounds(340,550,180,80);
            end_recept.addActionListener(new ReceptButtonListener());

            frame_recept.add(add_step);
            frame_recept.add(end_recept);

            text_description = new JTextField();
            text_description.setBounds(20,220,800,100);
            add_descripton = new JButton("Добавить описание шага");
            add_descripton.setBounds(340,350,180,80);
            add_descripton.addActionListener(new DescriptionButtonListener());

            frame_recept.add(text_description);
            frame_recept.add(add_descripton);

            frame.setVisible(true);
        }

    public class BaseButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            recept = new Recept(base_text.getText(), base_eng_text.getText());
            steps = new ArrayList<>();
            step = new Step();
            ingredients = new ArrayList<>();
            tools = new ArrayList<>();
            actions = new ArrayList<>();
            frame_recept.setVisible(true);
        }
    }

    public class IngredientButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String sc_ingr = data.getIngredients(text_ingredient.getText());
            int x = 0;
            if (sc_ingr == null) JOptionPane.showMessageDialog(frame_recept, "Данного ингредиента нет в базе", "Внимание",  JOptionPane.DEFAULT_OPTION);
            else {
                for (String i: ingredients){
                    if (i.equals(sc_ingr)) x++;
                }
                if (x == 0) {
                    ingredients.add(sc_ingr);
                    JOptionPane.showMessageDialog(frame_recept, "Ингредиент успешно добавлен", "Внимание",  JOptionPane.DEFAULT_OPTION);
                }
                else JOptionPane.showMessageDialog(frame_recept, "Данный ингредиент уже добавлен", "Внимание",  JOptionPane.DEFAULT_OPTION);
            }
        }
    }

    public class ToolButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String sc_tool = data.getTools(text_tool.getText());
            int x = 0;
            if (sc_tool == null) JOptionPane.showMessageDialog(frame_recept, "Данного инструмента нет в базе", "Внимание",  JOptionPane.DEFAULT_OPTION);
            else {
                for (String i: tools){
                    if (i.equals(sc_tool)) x++;
                }
                if (x == 0) {
                    tools.add(sc_tool);
                    JOptionPane.showMessageDialog(frame_recept, "Инструмент успешно добавлен", "Внимание",  JOptionPane.DEFAULT_OPTION);
                }
                else JOptionPane.showMessageDialog(frame_recept, "Данный инструмент уже добавлен", "Внимание",  JOptionPane.DEFAULT_OPTION);
            }
        }
    }

    public class ActionButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String sc_act = data.getActions(text_action.getText());
            int x = 0;
            if (sc_act == null) JOptionPane.showMessageDialog(frame_recept, "Данного действия нет в базе", "Внимание",  JOptionPane.DEFAULT_OPTION);
            else {
                for (String i: actions){
                    if (i.equals(sc_act)) x++;
                }
                if (x == 0) {
                    actions.add(sc_act);
                    JOptionPane.showMessageDialog(frame_recept, "Действие успешно добавлено", "Внимание",  JOptionPane.DEFAULT_OPTION);
                }
                else JOptionPane.showMessageDialog(frame_recept, "Данное действие уже добавлено", "Внимание",  JOptionPane.DEFAULT_OPTION);
            }
        }
    }

    public class TimeButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            step.setTime(Integer.valueOf(text_time.getText()));
            JOptionPane.showMessageDialog(frame_recept, "Время на этап успешно добавлено", "Внимание",  JOptionPane.DEFAULT_OPTION);
        }
    }

    public class DescriptionButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            step.setDescription(text_description.getText());
            JOptionPane.showMessageDialog(frame_recept, "Описание этапа успешно добавлено", "Внимание",  JOptionPane.DEFAULT_OPTION);
        }
    }

    public class StepButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            step.setIngredients(ingredients);
            step.setActions(actions);
            step.setTools(tools);
            steps.add(step);
            JOptionPane.showMessageDialog(frame_recept, "Шаг успешно добавлен", "Внимание",  JOptionPane.DEFAULT_OPTION);
            step = new Step();
            ingredients.clear();
            tools.clear();
            actions.clear();
        }
    }

    public class ReceptButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            recept.setSteps(steps);
            int x;
            int all_time = 0;
            ArrayList<String> all_ingredients = new ArrayList<>();
            for (Step i: steps){
                all_time += i.getTime();
            }
            recept.setAll_time(all_time);
            for (Step i: steps){
                for (String j: i.getIngredients()){
                    x = 0;
                    for (String k: all_ingredients){
                        if (j.equals(k)) x++;
                    }
                    if (x == 0) all_ingredients.add(j);
                }
            }
            recept.setAll_ingredients(all_ingredients);
            JOptionPane.showMessageDialog(frame_recept, "Рецепт успешно создан", "Внимание",  JOptionPane.DEFAULT_OPTION);
            frame_recept.dispose();
        }
    }

    public static void main(String[] args){
        Data_concepts data = new Data_concepts();
        Main main = new Main(data);
    }
}
