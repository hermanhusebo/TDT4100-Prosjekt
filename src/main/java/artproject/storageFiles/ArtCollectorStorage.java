package artproject.storageFiles;

import java.io.IOException;
import java.util.List;

import artproject.Painting;

public interface ArtCollectorStorage {
    
    /**
     * @return list of paintings
     * @throws IOException
     */
    List<Painting> loadArtCollection() throws IOException;
    
    /**
     * @param artCollection takes in list of paintings which will be saved
     * @throws IOException
     */
    void saveArtCollection(List<Painting> artCollection) throws IOException;
}
