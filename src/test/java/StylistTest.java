import org.junit.*;
import static org.junit.Assert.*;

public class StylistTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(0, Stylist.all().size());
  }

  @Test
  public void getId_returnsIdAfterSave() {
    Stylist stylist = new Stylist("Clementine");
    stylist.save();
    assertEquals(Stylist.all().get(0).getId(), stylist.getId());
  }

  @Test
  public void getName_returnsName() {
    Stylist stylist = new Stylist("Clementine");
    assertEquals("Clementine", stylist.getName());
  }

  @Test
  public void getClients_returnsAlphabeticalListOfClients() {
    Stylist stylist = new Stylist("Clementine");
    stylist.save();
    Client firstClient = new Client("Peggy Sue", stylist.getId());
    firstClient.save();
    Client secondClient = new Client("Betty Jane", stylist.getId());
    secondClient.save();
    assertEquals(secondClient, stylist.getClients().get(0));
    assertEquals(firstClient, stylist.getClients().get(1));
  }

  @Test
  public void equals_returnsTrueWhenParamsMatch() {
    Stylist firstStylist = new Stylist("Clementine");
    Stylist secondStylist = new Stylist("Clementine");
    assertEquals(true, firstStylist.equals(secondStylist));
  }

  @Test
  public void save_addsStylistToDatabase() {
    Stylist stylist = new Stylist("Clementine");
    stylist.save();
    assertEquals(Stylist.all().get(0), stylist);
  }

  @Test
  public void update_changesNameInDatabase() {
    Stylist stylist = new Stylist("Clementine");
    stylist.save();
    stylist.update("Peggy Sue");
    assertEquals("Peggy Sue", Stylist.all().get(0).getName());
  }

  @Test
  public void update_changesNameInMemory() {
    Stylist stylist = new Stylist("Clementine");
    stylist.save();
    stylist.update("Peggy Sue");
    assertEquals("Peggy Sue", stylist.getName());
  }

  @Test
  public void delete_removesStylistFromDatabase() {
    Stylist stylist = new Stylist("Clementine");
    stylist.save();
    stylist.delete();
    assertEquals(0, Stylist.all().size());
  }

  @Test
  public void find_returnsCorrectStylist() {
    Stylist stylist = new Stylist("Clementine");
    stylist.save();
    assertEquals(stylist, Stylist.find(stylist.getId()));
  }

}
