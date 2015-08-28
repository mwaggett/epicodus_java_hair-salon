import org.junit.*;
import static org.junit.Assert.*;

public class ClientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(0, Client.all().size());
  }

  @Test
  public void getId_returnsIdAfterSave() {
    Client client = new Client("Betty Jane", 1);
    client.save();
    assertEquals(Client.all().get(0).getId(), client.getId());
  }

  @Test
  public void getName_returnsName() {
    Client client = new Client("Betty Jane", 1);
    assertEquals("Betty Jane", client.getName());
  }

  @Test
  public void getStylistId_returnsStylistId() {
    Client client = new Client("Betty Jane", 3);
    assertEquals(3, client.getStylistId());
  }

  @Test
  public void equals_returnsTrueWhenParamsMatch() {
    Client firstClient = new Client("Betty Jane", 3);
    Client secondClient = new Client("Betty Jane", 3);
    assertEquals(true, firstClient.equals(secondClient));
  }

  @Test
  public void save_addsClientToDatabase() {
    Client client = new Client("Betty Jane", 3);
    client.save();
    assertEquals(Client.all().get(0), client);
  }

  @Test
  public void updateName_changesNameInDatabase() {
    Client client = new Client("Betty Jane", 3);
    client.save();
    client.updateName("Peggy Sue");
    assertEquals("Peggy Sue", Client.all().get(0).getName());
  }

  @Test
  public void updateName_changesNameInMemory() {
    Client client = new Client("Betty Jane", 3);
    client.save();
    client.updateName("Peggy Sue");
    assertEquals("Peggy Sue", client.getName());
  }

  @Test
  public void updateStylist_changesStylistIdInDatabase() {
    Client client = new Client("Betty Jane", 3);
    client.save();
    client.updateStylist(7);
    assertEquals(7, Client.all().get(0).getStylistId());
  }

  @Test
  public void updateStylist_changesStylistInMemory() {
    Client client = new Client("Betty Jane", 3);
    client.save();
    client.updateStylist(7);
    assertEquals(7, client.getStylistId());
  }

  @Test
  public void delete_removesClientFromDatabase() {
    Client client = new Client("Betty Jane", 3);
    client.save();
    client.delete();
    assertEquals(0, Client.all().size());
  }

  @Test
  public void find_returnsCorrectClient() {
    Client client = new Client("Betty Jane", 3);
    client.save();
    assertEquals(client, Client.find(client.getId()));
  }

}
