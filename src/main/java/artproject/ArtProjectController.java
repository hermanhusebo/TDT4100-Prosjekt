package artproject;

import java.io.IOException;
import java.util.List;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;


public class ArtProjectController {
    private ArtCollector artCollector = new ArtCollector();

    @FXML
    private TextField felt1, felt2, felt3, felt4, felt5, feltRemove;
    @FXML
    private Text feltError;
    @FXML 
    private VBox feltVBox;


    /**
     * save-method which uses save-method from ArtCollector.java
     */
    public void saveArtCollection(){
        try {
            artCollector.saveArtCollection(artCollector.getPaintings());
        } catch (IOException e) {
            this.printError("Save-error");  //Kanksje feil
        }  
    }
    /**
     * load-method which uses load-method from ArtCollector.java
     */
    public void loadArtCollection(){
        try {
            List<Painting> temporaryArtList = artCollector.loadArtCollection();
            //System.out.println(temporaryArtList);
            artCollector = new ArtCollector();
            //artCollector.getPaintings().clear();
            //System.out.println("hei");
            for (Painting p : temporaryArtList){
                artCollector.addPainting(p);
            }
        } catch (IOException e) {
            this.printError("Load-error");  //Kanskje feil
        }
        this.updateVeiwOfArtCollection();
    }

    /**
     * Method which clears the VBox feltVBox before re-adding the elements of the current list artCollection in artCollector
     */
    public void updateVeiwOfArtCollection(){
        feltVBox.getChildren().clear();
        Rectangle space = new Rectangle(190, 16);
        space.setFill(Color.WHITESMOKE);
        feltVBox.getChildren().add(space);

        for (Painting painting : artCollector.getPaintings()){
            Text linje = new Text(painting.getName() + ", " + painting.getYear() + ", " + painting.getPainter() + ", " + painting.getCountry() + ", " + painting.getPrice());
            feltVBox.getChildren().add(linje);
        }
    }
    /**
     * static method which checks if @param streng is a number or not based on wether Double.vaueOf(streng) throws NumberFormatExeption or not
     * @return boolean value true only if streng is a number
     */
    public static boolean isNumber(String streng){
        try{
            Double.valueOf(streng);
            return true;
        }
        catch(NumberFormatException e){
            return false;
        }
    }
    /**
     * method which uses the method addPainting from ArtCollector
     */
    public void addPainting(){
        if (isNumber(felt1.getText()) || !isNumber(felt2.getText()) || isNumber(felt3.getText()) || isNumber(felt4.getText()) || !isNumber(felt5.getText())){
            printError("Invalid data");
        }
        else{
        Painting paintingToBeAdded = new Painting(felt1.getText(), Integer.parseInt(felt2.getText()) , felt3.getText(), felt4.getText(), Double.parseDouble(felt5.getText()));
        artCollector.addPainting(paintingToBeAdded);
        this.updateVeiwOfArtCollection();
        }
        felt1.clear();
        felt2.clear();
        felt3.clear();
        felt4.clear();
        felt5.clear();
    }

    /**
     * method which uses the method removePainting from ArtCollector
     */
    public void removePainting(){   
        if (isNumber(feltRemove.getText()) && Integer.parseInt(feltRemove.getText()) >= 0 && artCollector.removePaintingByIndex(Integer.parseInt(feltRemove.getText()))){
            this.updateVeiwOfArtCollection();
            feltRemove.clear();
        }
        else{
            this.printError("Invalid index");
        }
    }

    /**
     * Method which prints string to screen
     * @param error string, not necessarily an error
     */
    public void printError(String error){
        
        /**
         * Source: https://stackoverflow.com/questions/30543619/how-to-use-pausetransition-method-in-javafx
         */
        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(5));
        pauseTransition.setOnFinished(event -> {
            feltError.setText("");
        });

        feltError.setText(error);
        pauseTransition.play();
    }

    /**
     * Method which uses ArtCollector's method getTotalPrize to print total prize to screen using printError
     */
    public void printTotalPrice(){     
        double A = artCollector.getTotalPrize();
        printError("" + A);
    }
    
    /**
     * Method which calls printMostCommonValue with input parameter "year"
     */
    public void printMostCommonYear(){
        printMostCommonValue("year");
    }

    /**
     * Method which calls printMostCommonValue with input parameter "painter"
     */
    public void printMostCommonPainter(){
        printMostCommonValue("painter");
    }

    /**
     * Method which calls printMostCommonValue with input parameter "country"
     */
    public void printMostCommonCountry(){
        printMostCommonValue("country");
    }
    
    /**
     * Method which uses artCollector's method mostCommonValue to print most occuring @param tekst to screen    
     */
    private void printMostCommonValue(String tekst){
        
        if (artCollector.getPaintings().isEmpty()){
            printError("Error, no paintings");
        } 
        else{   
            printError("Most common " +  tekst + ": " + artCollector.mostCommonValue(tekst));
        }
    }

}
