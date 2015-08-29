import org.sql2o.*;
import java.util.List;

public class Stylist {

  private int id;
  private String name;

  public Stylist (String name) {
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public List<Client> getClients() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients WHERE stylist_id = :id ORDER BY name ASC;";
      return con.createQuery(sql)
          .addParameter("id", id)
          .executeAndFetch(Client.class);
    }
  }

  @Override
  public boolean equals(Object otherStylistInstance) {
    if (!(otherStylistInstance instanceof Stylist)) {
      return false;
    } else {
      Stylist newStylistInstance = (Stylist) otherStylistInstance;
      return this.getName().equals(newStylistInstance.getName()) &&
              this.getId() == newStylistInstance.getId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stylists (name) VALUES (:name);";
      this.id = (int) con.createQuery(sql, true)
          .addParameter("name", name)
          .executeUpdate()
          .getKey();
    }
  }

  public void update(String newName) {
    name = newName;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE stylists SET name = :name WHERE id = :id;";
      con.createQuery(sql)
          .addParameter("name", name)
          .addParameter("id", id)
          .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM stylists WHERE id = :id;";
      con.createQuery(sql)
          .addParameter("id", id)
          .executeUpdate();
    }
  }

  public static List<Stylist> all() {
    String sql = "SELECT * FROM stylists ORDER BY name ASC;";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
  }

  public static Stylist find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stylists where id = :id;";
      Stylist stylist = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Stylist.class);
      return stylist;
    }
  }

}
