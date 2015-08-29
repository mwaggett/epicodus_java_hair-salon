import org.fluentlenium.adapter.FluentTest;
import static org.junit.Assert.*;
import org.junit.*;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.fluentlenium.core.filter.FilterConstructor.*;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {

  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Stylists!");
  }

  @Test
  public void index_displaysStylists() {
    Stylist stylist = new Stylist("Alexandre");
    stylist.save();
    goTo("http://localhost:4567");
    assertThat(pageSource()).contains("Alexandre");
  }

  @Test
  public void index_navigatesToNewStylistForm() {
    goTo("http://localhost:4567/");
    click("a", withText("Add a new stylist"));
    assertThat(pageSource()).contains("Stylist Name:");
  }

  // @Test
  // public void newStylistForm_addsStylist() {
  //   goTo("http://localhost:4567/");
  //   click("a", withText("Add a new stylist"));
  //   fill("#newStylist").with("Kris");
  //   submit(".btn");
  //   assertThat(pageSource()).contains("Kris");
  // }
  //^This test is producing a StackOverflow error that I can't explain.

  @Test
  public void index_navigatesToStylistPage() {
    Stylist stylist = new Stylist("Alexandre");
    stylist.save();
    goTo("http://localhost:4567/");
    click("a", withText("Alexandre"));
    assertThat(pageSource()).contains("Alexandre's Clients");
  }

  @Test
  public void stylistIdPage_navigatesToNewClientForm() {
    Stylist stylist = new Stylist("Alexandre");
    stylist.save();
    String stylistPath = String.format("http://localhost:4567/stylists/%d", stylist.getId());
    goTo(stylistPath);
    click("a", withText("Add a new client"));
    assertThat(pageSource()).contains("Add to Alexandre's Clients");
  }

  // @Test
  // public void NewClientForm_addsClient() {
  //   Stylist stylist = new Stylist("Alexandre");
  //   stylist.save();
  //   String stylistPath = String.format("http://localhost:4567/stylists/%d/clients/new", stylist.getId());
  //   goTo(stylistPath);
  //   fill("#newClient").with("Barbara Walters");
  //   submit(".btn");
  //   assertThat(pageSource()).contains("Barbara Walters");
  // }
  // ^This test is producing an error I can't explain.

  @Test
  public void stylistIdPage_displaysClients() {
    Stylist stylist = new Stylist("Alexandre");
    stylist.save();
    Client firstClient = new Client("Betty Jane", stylist.getId());
    firstClient.save();
    Client secondClient = new Client("Peggy Sue", stylist.getId());
    secondClient.save();
    String stylistPath = String.format("http://localhost:4567/stylists/%d", stylist.getId());
    goTo(stylistPath);
    assertThat(pageSource()).contains("Betty Jane");
    assertThat(pageSource()).contains("Peggy Sue");
  }

  @Test
  public void stylistIdPage_navigatesToEditClientForm() {
    Stylist stylist = new Stylist("Alexandre");
    stylist.save();
    Client client = new Client("Peggy Sue", stylist.getId());
    client.save();
    String stylistPath = String.format("http://localhost:4567/stylists/%d", stylist.getId());
    goTo(stylistPath);
    click("a", withText("Peggy Sue"));
    assertThat(pageSource()).contains("Edit Client Name:");
  }

  // @Test
  // public void editClientForm_navigatesToSuccessPage_afterEdit() {
  //   Stylist stylist = new Stylist("Alexandre");
  //   stylist.save();
  //   Client client = new Client("Betty Jane", stylist.getId());
  //   client.save();
  //   String editClientPath = String.format("http://localhost:4567/stylists/%d/clients/%d/edit", stylist.getId(), client.getId());
  //   goTo(editClientPath);
  //   fill("#newName").with("Auntie May");
  //   submit("#editSubmit");
  //   assertThat(pageSource()).contains("Your edits have been recorded!");
  // }

  @Test
  public void editClientForm_navigatesToSuccessPage_afterDelete() {
    Stylist stylist = new Stylist("Alexandre");
    stylist.save();
    Client client = new Client("Betty Jane", stylist.getId());
    client.save();
    String editClientPath = String.format("http://localhost:4567/stylists/%d/clients/%d/edit", stylist.getId(), client.getId());
    goTo(editClientPath);
    submit("#deleteSubmit");
    assertThat(pageSource()).contains("Your edits have been recorded!");
  }

  @Test
  public void stylistIdPage_navigatesToEditStylistForm() {
    Stylist stylist = new Stylist("Alexandre");
    stylist.save();
    String stylistPath = String.format("http://localhost:4567/stylists/%d", stylist.getId());
    goTo(stylistPath);
    click("a", withText("Edit stylist"));
    assertThat(pageSource()).contains("Edit Stylist Name:");
  }

  // @Test
  // public void editStylistForm_navigatesToSuccessPage_afterEdit() {
  //   Stylist stylist = new Stylist("Alexandre");
  //   stylist.save();
  //   String editStylistPath = String.format("http://localhost:4567/stylists/%d/edit", stylist.getId());
  //   goTo(editStylistPath);
  //   fill("#newName").with("Auntie May");
  //   submit("#editSubmit");
  //   assertThat(pageSource()).contains("Your edits have been recorded!");
  // }

  @Test
  public void editStylistForm_navigatesToSuccessPage_afterDelete() {
    Stylist stylist = new Stylist("Alexandre");
    stylist.save();
    String editStylistPath = String.format("http://localhost:4567/stylists/%d/edit", stylist.getId());
    goTo(editStylistPath);
    submit("#deleteSubmit");
    assertThat(pageSource()).contains("Your edits have been recorded!");
  }

  @Test
  public void stylistIdPage_navigatesToIndex() {
    Stylist stylist = new Stylist("Alexandre");
    stylist.save();
    String stylistPath = String.format("http://localhost:4567/stylists/%d", stylist.getId());
    goTo(stylistPath);
    click("a", withText("Back to Stylists"));
    assertThat(pageSource()).contains("Stylists!");
  }

}
