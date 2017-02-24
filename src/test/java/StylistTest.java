import org.sql2o.*;
import org.junit.*;
import java.util.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class StylistTest {
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Stylist_instantiatesCorrectly_true() {
    Stylist testStylist = new Stylist("Stacy", "Natural African Hair");
    assertEquals(true, testStylist instanceof Stylist);
  }
  @Test
  public void Stylist_instantiesWithProperties_2 () {
    Stylist testStylist = new Stylist("Stacy", "Natural African Hair");
    assertThat(testStylist.getName(), is (equalTo("Stacy")));
    assertThat(testStylist.getSpecialization(), is (equalTo("Natural African Hair")));
  }
  @Test
  public void getId_categoriesInstantiateWithAnId_1() {
    Stylist testStylist = new Stylist("Stacy", "Natural African Hair");
    testStylist.save();
    assertTrue(testStylist.getId() > 0);
  }
  @Test
  public void find_returnsStylistWithSameId_secondStylist() {
    Stylist firstStylist = new Stylist("Stacy", "Natural African Hair");
    firstCategory.save();
    Stylist secondStylist = new Stylist("Stacy", "Natural African Hair");
    secondCategory.save();
    assertEquals(Category.find(secondCategory.getId()), secondCategory);
  }
  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
  Stylist firstStylist = new Stylist("Stacy", "Natural African Hair");
  Stylist secondStylist = new Stylist("Stacy", "Natural African Hair");
  assertTrue(firstCategory.equals(secondStylist));
  }




}
