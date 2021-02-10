import java.util.Objects;

public class Apartment {
    private int number;
    private int level;
    private double dimension;
    private int numberOfRooms;
    private int pricePerSquare;
    private int totalprice;
    private boolean availability;

    public Apartment(int number, int level, double dimension, int numberOfRooms, int pricePerSquare, int totalprice, boolean availability) {
        this.number = number;
        this.level = level;
        this.dimension = dimension;
        this.numberOfRooms = numberOfRooms;
        this.pricePerSquare = pricePerSquare;
        this.totalprice = totalprice;
        this.availability = availability;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apartment apartment = (Apartment) o;
        return number == apartment.number &&
                level == apartment.level &&
                Double.compare(apartment.dimension, dimension) == 0 &&
                numberOfRooms == apartment.numberOfRooms &&
                pricePerSquare == apartment.pricePerSquare &&
                totalprice == apartment.totalprice &&
                availability == apartment.availability;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, level, dimension, numberOfRooms, pricePerSquare, totalprice, availability);
    }

    @Override
    public String toString() {
        String pattern = "Apartment Number = %3d \t Level = %2d \t Dimension = %.2f \t Number of Rooms = %d \t Price per square = %6d zł \t Total Price = %d zł \t Reserved = %s";
        return String.format(pattern, number, level, dimension, numberOfRooms, pricePerSquare, totalprice, availability);
    }

    public int getNumber() {
        return number;
    }

    public int getLevel() {
        return level;
    }

    public double getDimension() {
        return dimension;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public int getPricePerSquare() {
        return pricePerSquare;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public boolean isAvailability() {
        return availability;
    }
}


