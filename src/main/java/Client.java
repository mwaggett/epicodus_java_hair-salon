import org.sql2o.*;
import java.util.List;

public class Client {

  private int id;
  private String name;
  private int stylist_id;

  public Client (String name, int stylist_id) {
    this.name = name;
    this.stylist_id = stylist_id;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getStylistId() {
    return stylist_id;
  }

  @Override
  public boolean equals(Object otherClientInstance) {
    if (!(otherClientInstance instanceof Client)) {
      return false;
    } else {
      Client newClientInstance = (Client) otherClientInstance;
      return this.getName().equals(newClientInstance.getName()) &&
              this.getStylistId() == newClientInstance.getStylistId() &&
              this.getId() == newClientInstance.getId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients (name, stylist_id) VALUES (:name, :stylist_id);";
      this.id = (int) con.createQuery(sql, true)
          .addParameter("name", name)
          .addParameter("stylist_id", stylist_id)
          .executeUpdate()
          .getKey();
    }
  }

  public void updateName(String newName) {
    name = newName;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE clients SET name = :name WHERE id = :id;";
      con.createQuery(sql)
          .addParameter("name", name)
          .addParameter("id", id)
          .executeUpdate();
    }
  }

  public void updateStylist(int newStylist_id) {
    stylist_id = newStylist_id;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE clients SET stylist_id = :stylist_id WHERE id = :id;";
      con.createQuery(sql)
          .addParameter("stylist_id", stylist_id)
          .addParameter("id", id)
          .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM clients WHERE id = :id;";
      con.createQuery(sql)
          .addParameter("id", id)
          .executeUpdate();
    }
  }

  public static List<Client> all() {
    String sql = "SELECT * FROM clients ORDER BY name ASC;";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }

  public static Client find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients where id = :id;";
      Client client = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Client.class);
      return client;
    }
  }

}
