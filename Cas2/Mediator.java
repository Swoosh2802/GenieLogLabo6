package Cas2;

import javax.swing.*;


public class Mediator {
    private Receipe receipe;

    private JButton printButton;
    private JButton addStepButton;
    private String currentIngredient;
    private String currentStep;

    public Mediator(JButton printButton, JButton addStepButton){
        this.receipe = new Receipe();
        this.currentIngredient = "";
        this.currentStep = "";
        this.printButton = printButton;
        this.addStepButton = addStepButton;

        checkPrintable();
        checkAddStepClickable();
    }

    public void checkPrintable() {
        if (!receipe.getTitle().equals("") && receipe.getTime() != 0 && receipe.getSteps().size() > 0) {
            printButton.setEnabled(true);
        } else {
            printButton.setEnabled(false);
        }
    }

    public void stepAdded(String step) {
        receipe.addStep(step);
        checkPrintable();
    }

    public void ingredientAdded() {
        checkAddStepClickable();
    }

    private void checkAddStepClickable() {
        if (!receipe.getTitle().equals("") && receipe.getTime() != 0 && !currentIngredient.equals("") && !this.currentStep.equals("")) {
            addStepButton.setEnabled(true);
        } else {
            addStepButton.setEnabled(false);
        }
    }

    public void titleSet(String title) {

        if(title.equals(null)){
            receipe.setTitle(""); 
        }else{
            receipe.setTitle(title);
        }
        
        checkPrintable();
        checkAddStepClickable();
    }

    public void timeSet(String receipeTime) {
        if(!receipeTime.equals(null)&&!receipeTime.equals("")){
            try{
                receipe.setTime(Integer.parseInt(receipeTime));
            }catch(Error error){
                System.out.println(error);
                receipe.setTime(0);
            }
        }else{
            receipe.setTime(0);
        }
        checkPrintable();
        checkAddStepClickable();
    }

    public void ingredientSelected(String selectedIngredient){
        if(!selectedIngredient.equals(null)){
            this.currentIngredient = selectedIngredient;
        }else{
            this.currentIngredient = "";
        }
        checkAddStepClickable();
    }

    public void currentStepEdited(String text) {
        if(!text.equals(null)){
            this.currentStep = text;
        }else{
            this.currentStep = "";
        }
        checkAddStepClickable();
    }

    public String print(){
        System.out.println(this.receipe.writeReceipe());
        return this.receipe.writeReceipe();
    }
}