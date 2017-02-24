import java.util.*;
import org.sql2o.*;

public class Client {
  private String name;
  private String contacts;
  private int id;
  private int stylistId;

  public Client(String name, String contacts, int stylistId){
    this.name = name;
    this.contacts = contacts;
    this.stylistId = stylistId;
  }

  public String getName() {
    return name;
  }
  public String getContacts() {
    return contacts;
  }
  public int getStylistId() {
    return stylistId;
  }
  public int getId() {
    return id;
  }
  public static List<Client> all() {
    String sql = "SELECT id, name, contacts, stylistId FROM clients";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }
  @Override
  public boolean equals(Object otherClient) {
    if (!(otherClient instanceof Client)) {
      return false;
    } else {
      Client newClient = (Client) otherClient;
      return this.getName().equals(newClient.getName()) &&
             this.getId() == newClient.getId() &&
             this.getContacts().equals(newClient.getContacts()) &&
             this.getStylistId() == newClient.getStylistId();
    }
  }
  public static Client find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients WHERE id=:id";
      Client client = con.createQuery(sql)
          .addParameter("id", id)
          .executeAndFetchFirst(Client.class);
          return client;
    }
  }
  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients(name, contacts, stylistId) VALUES (:name, :contacts, :stylistId) ";
      this.id = (int) con.createQuery(sql, true)
          .addParameter("name", this.name)
          .addParameter("contacts", this.contacts)
          .addParameter("stylistId", this.stylistId)
          .executeUpdate()
          .getKey();
    }
  }
  public void update(String name, String contacts) {
    try(Connection con = DB.sql2o.open()) {
    String sql = "UPDATE clients SET name = :name, contacts = :contacts WHERE id=:id";
    con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("contacts", contacts)
        .addParameter("id", id)
        .executeUpdate();
    }
  }
  public void delete(){
   try(Connection con = DB.sql2o.open()){
   String sql =  "DELETE FROM clients WHERE id =:id";
   con.createQuery(sql)
   .addParameter("id",id)
   .executeUpdate();
   }
  }
}
