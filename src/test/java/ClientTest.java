import org.sql2o.*;
import org.junit.*;
import java.util.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ClientTest {
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Client_instantiatesCorrectly_true() {
    Client testClient = new Client("Nombu", "Phone 0711-809245", 1);
    assertEquals(true, testClient instanceof Client);
  }
  @Test
  public void Client_instantiesWithProperties_3 () {
    Client testClient = new Client("Nombu", "Phone 0711-809245", 1);
    assertThat(testClient.getName(), is (equalTo("Nombu")));
    assertThat(testClient.getContacts(), is (equalTo("Phone 0711-809245")));
    assertThat(testClient.getStylistId(), is (equalTo(1)));
  }
  @Test
  public void equals_returnsTrueIfNameAndContactsAreTheSame() {
    Client testClient1 = new Client("Nombu", "Phone 0711-809245", 1);
    Client testClient2 = new Client("Nombu", "Phone 0711-809245", 1);
    assertTrue(testClient1.equals(testClient2));
  }
  @Test
  public void save_returnsTrueIfNameAndContactsAreTheSame() {
    Client testClient = new Client("Nombu", "Phone 0711-809245", 1);
    testClient.save();
    assertTrue(Client.all().get(0).equals(testClient));
  }
  @Test
  public void save_assignsIdToObject() {
    Client testClient = new Client("Nombu", "Phone 0711-809245", 1);
    testClient.save();
    Client savedClient = Client.all().get(0);
    assertEquals(testClient.getId(), savedClient.getId());
  }
  @Test
  public void save_savesStylistIdIntoDB_true() {
    Stylist testStylist = new Stylist("Stacy","Natural Hair");
    testStylist.save();
    Client testClient = new Client("Nombu", "Phone 0711-809245", 1);
    testClient.save();
    Client savedClient = Client.find(testClient.getId());
    assertEquals(savedClient.getStylistId(), testStylist.getId());
  }
  @Test
  public void update_updateClientNameAndContacts_true() {
    Client testClient = new Client("Nombu", "Phone 0711-809245", 1);
    testClient.save();
    testClient.update("Nombu Murage", "Phone 071-477777");
    assertEquals("Nombu Murage", Client.find(testClient.getId()).getName());
    assertEquals("Phone 071-477777", Client.find(testClient.getId()).getContacts());
  }
}
