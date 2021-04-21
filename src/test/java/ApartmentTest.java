import org.junit.jupiter.api.BeforeEach;
import pl.apartment_comparator.Apartment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ApartmentTest {
    private Apartment apartment;

    @BeforeEach
    void init() {
        apartment = new Apartment(2, 2, 33.02, 4, 10000, 330200, true);
    }

    @org.junit.jupiter.api.Test
    void equals1() {
        Apartment apartment1 = new Apartment(2, 2, 33.02, 4, 10000, 330200, true);
        assertEquals(apartment, apartment1);
        apartment1 = new Apartment(3, 2, 33.02, 4, 10000, 330200, true);
        assertNotEquals(apartment, apartment1);
        apartment1 = new Apartment(2, 3, 33.02, 4, 10000, 330200, true);
        assertNotEquals(apartment, apartment1);
        apartment1 = new Apartment(2, 2, 34.02, 4, 10000, 330200, true);
        assertNotEquals(apartment, apartment1);
        apartment1 = new Apartment(2, 2, 33.02, 5, 10000, 330200, true);
        assertNotEquals(apartment, apartment1);
        apartment1 = new Apartment(2, 2, 33.02, 4, 20000, 330200, true);
        assertNotEquals(apartment, apartment1);
        apartment1 = new Apartment(2, 2, 33.02, 4, 10000, 333200, true);
        assertNotEquals(apartment, apartment1);
        apartment1 = new Apartment(2, 2, 33.02, 4, 10000, 330200, false);
        assertNotEquals(apartment, apartment1);
    }

    @org.junit.jupiter.api.Test
    void hashCode1() {
        Apartment apartment1 = new Apartment(2, 2, 33.02, 4, 10000, 330200, true);
        assertEquals(apartment.hashCode(), apartment1.hashCode());
        apartment1 = new Apartment(3, 2, 33.02, 4, 10000, 330200, true);
        assertNotEquals(apartment.hashCode(), apartment1.hashCode());
        apartment1 = new Apartment(2, 3, 33.02, 4, 10000, 330200, true);
        assertNotEquals(apartment.hashCode(), apartment1.hashCode());
        apartment1 = new Apartment(2, 2, 34.02, 4, 10000, 330200, true);
        assertNotEquals(apartment.hashCode(), apartment1.hashCode());
        apartment1 = new Apartment(2, 2, 33.02, 5, 10000, 330200, true);
        assertNotEquals(apartment.hashCode(), apartment1.hashCode());
        apartment1 = new Apartment(2, 2, 33.02, 4, 20000, 330200, true);
        assertNotEquals(apartment.hashCode(), apartment1.hashCode());
        apartment1 = new Apartment(2, 2, 33.02, 4, 10000, 333200, true);
        assertNotEquals(apartment.hashCode(), apartment1.hashCode());
        apartment1 = new Apartment(2, 2, 33.02, 4, 10000, 330200, false);
        assertNotEquals(apartment.hashCode(), apartment1.hashCode());
    }

    @org.junit.jupiter.api.Test
    void compareTo() {
        Apartment apartment1 = new Apartment(2, 2, 33.02, 4, 10000, 330200, true);
        assertEquals(0, apartment.compareTo(apartment1));
        apartment1 = new Apartment(3, 2, 33.02, 4, 10000, 330200, true);
        assertEquals(-1, apartment.compareTo(apartment1));
        apartment1 = new Apartment(1, 2, 33.02, 4, 10000, 330200, true);
        assertEquals(1, apartment.compareTo(apartment1));
        apartment1 = new Apartment(2, 3, 33.02, 4, 10000, 330200, true);
        assertEquals(0, apartment.compareTo(apartment1));
        apartment1 = new Apartment(2, 2, 34.02, 4, 10000, 330200, true);
        assertEquals(0, apartment.compareTo(apartment1));
        apartment1 = new Apartment(2, 2, 33.02, 5, 10000, 330200, true);
        assertEquals(0, apartment.compareTo(apartment1));
        apartment1 = new Apartment(2, 2, 33.02, 4, 20000, 330200, true);
        assertEquals(0, apartment.compareTo(apartment1));
        apartment1 = new Apartment(2, 2, 33.02, 4, 10000, 333200, true);
        assertEquals(0, apartment.compareTo(apartment1));
        apartment1 = new Apartment(2, 2, 33.02, 4, 10000, 330200, false);
        assertEquals(0, apartment.compareTo(apartment1));
    }

}