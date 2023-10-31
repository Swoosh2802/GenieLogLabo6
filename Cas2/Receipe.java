package Cas2;

import java.util.ArrayList;
import java.util.List;

public class Receipe {
    List<String> ingredients = new ArrayList<String>();
    List<String> steps = new ArrayList<String>();
    String title = "";
    int time = 0;

    public Receipe() {
        ingredients.add("oeufs");
        ingredients.add("lait");
        ingredients.add("farine");
    }

    public void addIngredient(String ingredient) {
        ingredients.add(ingredient);
    }

    public void addStep(String step) {
        steps.add(step);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public int getTime() {
        return time;
    }

    public List<String> getSteps() {
        return steps;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public String writeReceipe() {
        String infos = "Titre: " + title + "\nTemps: " + time + "\nIngrédients: \n";
        for (String ingredient : ingredients) {
            infos += "- " + ingredient + "\n";
        }
        infos += "\nEtapes: \n";
        for (String step : steps) {
            infos += "- " + step + "\n";
        }
        return infos;
    }
}