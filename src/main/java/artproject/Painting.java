package artproject;

import java.util.Objects;

public class Painting{
    private final String name;
    private final int year;
    private final String painter;
    private final String country;
    private final double price;
    
    /**
     * The constuctor requires all parameters to be "NonNull"
     * @param name  name of painting
     * @param year  year of painting
     * @param painter   name of painter
     * @param country   name of country where the painting was made
     * @param price price of painting
     */
    public Painting(String name, int year, String painter, String country, double price) {
        if (price < 0){
            throw new IllegalArgumentException("Price can not be negative");
        }
        
        this.name = Objects.requireNonNull(name);
        this.year = Objects.requireNonNull(year);
        this.painter = Objects.requireNonNull(painter);
        this.country = Objects.requireNonNull(country);
        this.price = Objects.requireNonNull(price);  
    }
    /**
     * get method
     * @return year 
     */
    public int getYear() {
        return year;
    }
    /**
     * get method
     * @return painter
     */
    public String getPainter() {
        return painter;
    }

    /**
     * get metod
     * @return  country
     */
    public String getCountry() {
        return country;
    }

    /**
     * get method
     * @return price
     */
    public double getPrice() {
        return price;
    }

    /**
     * get method
     * @return  name
     */
    public String getName() {
        return name;
    }

    /**
     * toString-method returning a sting-representation of Painting object
     */
    @Override
    public String toString() {
        return "Painting [year=" + year + ", painter=" + painter + ", country=" + country + ", price=" + price + "]";
    }

    


    
}
