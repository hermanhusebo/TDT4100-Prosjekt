package artproject;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import artproject.storageFiles.ArtCollectorStorage;
import artproject.storageFiles.CSVStorage;



public class ArtCollector {
    private List<Painting> artCollection = new ArrayList<>();
    private String pathForStorage = "test.csv";     //Defined static path for storage, all ArtCollector-instances save at same location

    /**
     * addPainting adds Painting-object to the list artCollection
     * @param painting takes in param painting of type Painting
     */
    public void addPainting(Painting painting){
        if (!artCollection.contains(painting)){
            this.artCollection.add(Objects.requireNonNull(painting));
        }
    }
    /**
     * removePainting removes Painting-object from the list artCollection
     * @param painting takes in param painting of type Painting
     */
    public void removePainting(Painting painting){
        if (artCollection.contains(painting)){
            this.artCollection.add(painting);
        }
    }
    /**
     * 
     * @param i index of Painting in list artCollection which will be removed
     * @return boolean value, true if Painting is removed successfully
     */
    public boolean removePaintingByIndex(int i){
        if (artCollection.size() >= (i + 1)){
            artCollection.remove(i);
            return true;
        }
        return false;
    }

    /**
     * 
     * @return length of list artCollection
     */
    public int getSize(){
        return artCollection.size();
    }

    /**
     * 
     * @return double total price of all paintings in artcollection
     */
    public double getTotalPrize(){
        return artCollection.stream().mapToDouble(Painting::getPrice).sum();
    }

    /**
     * Makes artCollection into a stream to collect the string belonging to each painting. Which varibale that is being collected is determined by the parameter "variable"
     * The method then makes a HashMap out of the list before iterating through the keys to dertermine which string is occuring most frequent
     * @param variable  
     * @return
     */
    public String mostCommonValue(String variable){
        List<String> listOfElem;
        if (variable.equals("year")){
            listOfElem = artCollection.stream().map(painting -> ("" + painting.getYear())).collect(Collectors.toList());
        }else if (variable.equals("painter")){
            listOfElem = artCollection.stream().map(painting -> painting.getPainter()).collect(Collectors.toList());
        }else if (variable.equals("country")){
            listOfElem = artCollection.stream().map(painting -> painting.getCountry()).collect(Collectors.toList());
        }
        else{
            throw new IllegalArgumentException("Wanted value is not year,painter or country");  
        }
        
        
        
        Map<String, Integer> dictionary = new HashMap<>();
        for (String str : listOfElem){
            if (!dictionary.containsKey(str)){
                dictionary.put(str, 1);
            }
            else{
                int tempValue = dictionary.get(str);
                dictionary.put(str, tempValue + 1);
            }
        }
        int maxValue = Integer.MIN_VALUE;
        String keyCorrespondingToMaxValue = "";

        for (String key : dictionary.keySet()){
            if (dictionary.get(key) > maxValue){
                maxValue = dictionary.get(key);
                keyCorrespondingToMaxValue = key;
            }
        }
        if (maxValue == Integer.MIN_VALUE){
            throw new IllegalStateException("Kan ikke finne max på tom liste");
        }
        return ("" + keyCorrespondingToMaxValue);
    }
    
    /**
     * get-method for artCollection-list
     * .unmodifiableList ensures that the only way to modify artCollection is through add- and remove- methods
     * @return list artCollection
     */
    public List<Painting> getPaintings(){
        return Collections.unmodifiableList(artCollection); 
    }

    //Unused in graphical interface
    public List<Painting> getPaintingsFromPainter(String painter){
        return artCollection.stream()
                        .filter(a -> a.getPainter().equals(painter))
                        .toList();
    }

    /**
     * Save-method
     * @param artCollection takes in which artCollection's list that will be saved
     * @throws IOException
     */
    public void saveArtCollection(List<Painting> artCollection) throws IOException{
        ArtCollectorStorage storage = new CSVStorage(Path.of(pathForStorage));
        storage.saveArtCollection(artCollection);
    }

    /**
     * Load-method
     * @return  list of paintings
     * @throws IOException
     */
    public List<Painting> loadArtCollection() throws IOException{
        ArtCollectorStorage storage = new CSVStorage(Path.of(pathForStorage));
        return storage.loadArtCollection();  
    }

}
