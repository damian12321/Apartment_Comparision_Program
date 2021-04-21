package pl.apartment_comparator;

import java.util.Objects;

public class Apartment implements Comparable<Apartment> {
    private int number;
    private int level;
    private double surface;
    private int numberOfRooms;
    private int pricePerSquare;
    private int totalPrice;
    private boolean availability;

    public Apartment(int number, int level, double surface, int numberOfRooms, int pricePerSquare, int totalPrice, boolean availability) {
        this.number = number;
        this.level = level;
        this.surface = surface;
        this.numberOfRooms = numberOfRooms;
        this.pricePerSquare = pricePerSquare;
        this.totalPrice = totalPrice;
        this.availability = availability;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apartment apartment = (Apartment) o;
        return number == apartment.number &&
                level == apartment.level &&
                Double.compare(apartment.surface, surface) == 0 &&
                numberOfRooms == apartment.numberOfRooms &&
                pricePerSquare == apartment.pricePerSquare &&
                totalPrice == apartment.totalPrice &&
                availability == apartment.availability;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, level, surface, numberOfRooms, pricePerSquare, totalPrice, availability);
    }

    @Override
    public String toString() {
        String pattern = "Apartment Number = %3d \t Level = %2d \t Surface = %.2f m2 \t Number of Rooms = %d \t Price per square = %6d zł \t Total Price = %d zł \t Reserved = %s";
        return String.format(pattern, number, level, surface, numberOfRooms, pricePerSquare, totalPrice, availability);
    }

    public int getNumber() {
        return number;
    }

    public int getLevel() {
        return level;
    }

    public double getSurface() {
        return surface;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public int getPricePerSquare() {
        return pricePerSquare;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public boolean isAvailability() {
        return availability;
    }

    @Override
    public int compareTo(Apartment o) {
        return this.getNumber() - o.getNumber();
    }
}


