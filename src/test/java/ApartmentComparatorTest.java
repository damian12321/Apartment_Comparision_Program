import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ApartmentComparatorTest {
    @Test
    void getAllLinesFromFile() throws NoSuchFileException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException //Testing private method
    {
        Method method = ApartmentComparator.class.getDeclaredMethod("getAllLinesFromFile", File.class);
        method.setAccessible(true);
        String pathToIcon = new File(".").getCanonicalPath();
        File file = new File(pathToIcon + "\\src\\test\\java\\test.txt");
        ApartmentComparator apartmentComparator = ApartmentComparator.getINSTANCE();
        List<String> getList = (List<String>) method.invoke(apartmentComparator, file);
        List<String> testList = new ArrayList<>();
        testList.add("abcde");
        testList.add("fgh");
        testList.add("ijkl");
        testList.add("mnop");
        assertEquals(testList, getList);
    }

    @Test
    void modifyString() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = ApartmentComparator.class.getDeclaredMethod("modifyString", String.class);
        ApartmentComparator apartmentComparator = ApartmentComparator.getINSTANCE();
        method.setAccessible(true);
        String tekst = "1zł,m2,-m2--";
        assertEquals("1..", method.invoke(apartmentComparator, tekst));
    }


}