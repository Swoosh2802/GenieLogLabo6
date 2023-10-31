package Cas2;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.text.NumberFormat;
import javax.swing.text.NumberFormatter;
public class Application{
    public static void main(String[] args) {
        JFrame frame = new JFrame("Recettes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,500);

        String[] items = {"Oeufs", "Farine", "Sucre", "Beurre"};
        JList<String> itemList = new JList<>(items);
        itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        DefaultListModel<String> steps = new DefaultListModel<>();
        JList<String> stepList = new JList<>(steps);

        JTextArea textArea = new JTextArea(5,20);
        // Créer un champ de texte
        JTextField receipeTitle = new JTextField(10);
        JButton addStepButton = new JButton("Ajouter l'étape");
        JButton printButton = new JButton("Imprimer");
        JLabel titleLabel = new JLabel("Titre de la recette");
        JLabel timeLabel = new JLabel("Temps de la recette");
        JLabel ingredientsLabel = new JLabel("Ingrédients");
        JLabel stepsDescriptionLabel = new JLabel("Description étape");
        JLabel stepsLabel = new JLabel("Etapes");

        NumberFormat format = NumberFormat.getIntegerInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        JFormattedTextField receipeTime = new JFormattedTextField(format);
        receipeTime.setColumns(10);

        
        Mediator mediator = new Mediator(printButton,addStepButton);

        itemList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                mediator.ingredientSelected(itemList.getSelectedValue());
            }
        });

        receipeTitle.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                mediator.titleSet(receipeTitle.getText());
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                mediator.titleSet(receipeTitle.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                mediator.titleSet(receipeTitle.getText());
            }
        });

        receipeTime.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
               mediator.timeSet(receipeTime.getText());
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                mediator.timeSet(receipeTime.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                mediator.timeSet(receipeTime.getText());
            }
        });

        textArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                mediator.currentStepEdited(textArea.getText());
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                mediator.currentStepEdited(textArea.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                mediator.currentStepEdited(textArea.getText());
            }
        });

        addStepButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedValue = itemList.getSelectedValue();
                String inputText = textArea.getText();
                JOptionPane.showMessageDialog(frame, "Bouton 1 cliqué!\nÉlément sélectionné : " + selectedValue + "\nTexte du champ : " + inputText);
                steps.addElement(inputText);
                mediator.stepAdded(inputText);
            }
        });

        // Ajouter un gestionnaire d'événements au bouton 2
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, mediator.print());
            }
        });


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel headerTitles = new JPanel();
        headerTitles.setLayout(new BoxLayout(headerTitles, BoxLayout.Y_AXIS));
        headerTitles.add(titleLabel);
        headerTitles.add(timeLabel);
        JPanel headerContent = new JPanel();
        headerContent.setLayout(new BoxLayout(headerContent, BoxLayout.Y_AXIS));
        JPanel receipeTitleContainer = new JPanel();
        receipeTitleContainer.add(receipeTitle);
        headerContent.add(receipeTitleContainer);
        JPanel receipeTimeContainer = new JPanel();
        receipeTimeContainer.add(receipeTime);
        headerContent.add(receipeTitleContainer);
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.X_AXIS));
        headerPanel.add(headerTitles);
        headerPanel.add(headerContent);

        JPanel textAreaTitles = new JPanel();
        textAreaTitles.setLayout(new BoxLayout(textAreaTitles, BoxLayout.Y_AXIS));
        textAreaTitles.add(ingredientsLabel);
        textAreaTitles.add(itemList);
        JPanel textAreaContent = new JPanel();
        textAreaContent.setLayout(new BoxLayout(textAreaContent, BoxLayout.Y_AXIS));
        JPanel stepTitle = new JPanel();
        stepTitle.add(stepsDescriptionLabel);
        textAreaContent.add(stepTitle);
        JPanel stepContent = new JPanel();
        stepContent.add(textArea);
        textAreaContent.add(stepContent);
        JPanel textAreaPanel = new JPanel();
        textAreaPanel.setLayout(new BoxLayout(textAreaPanel, BoxLayout.X_AXIS));
        textAreaPanel.add(textAreaTitles);
        textAreaPanel.add(textAreaContent);

        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new FlowLayout());
        footerPanel.add(addStepButton);
        footerPanel.add(printButton);
        // Créer un conteneur pour les composants
        JPanel titleHeaderPanel = new JPanel();
        titleHeaderPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,0));
        titleHeaderPanel.add(headerPanel);

        JPanel stepsPannel = new JPanel();
        stepsPannel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        stepsPannel.add(stepsLabel);
        stepsPannel.add(stepList);

        mainPanel.add(titleHeaderPanel);
        mainPanel.add(textAreaPanel);
        mainPanel.add(stepsPannel);
        mainPanel.add(footerPanel);

        frame.add(mainPanel);
        // Afficher la fenêtre
        frame.setVisible(true);   
    }
}