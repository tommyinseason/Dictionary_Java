import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.*;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Dictionary!");
    assertThat(pageSource()).contains("View Word List");
    assertThat(pageSource()).contains("Add a New Word");
  }
  @Test
  public void WordIsCreatedTest() {
    goTo("http://localhost:4567/");
    click("a", withText("Add a New Word"));
    fill("#wordInput").with("Apple");
    submit(".btn");
    assertThat(pageSource()).contains("Your Word has been saved.");
    click("a", withText("View Words"));
    assertThat(pageSource()).contains("Apple");
  }

  @Test
  public void wordIsDisplayedTest() {
    goTo("http://localhost:4567/words");
    click("a", withText("Add New Word"));
    fill("#wordInput").with("Apple");
    submit(".btn");
    click("a", withText("View Words"));
    assertThat(pageSource()).contains("Apple");
  }

  @Test
  public void wordDefinitionsFormIsDisplayed() {
    goTo("http://localhost:4567/words/new");
    fill("#wordInput").with("Apple");
    submit(".btn");
    click("a", withText("View Words"));
    click("a", withText("Apple"));
    click("a", withText("Add a new definition"));
    assertThat(pageSource()).contains("Definition");
  }

  @Test
  public void defintionsIsAddedAndDisplayed() {
    Word.clear();
    goTo("http://localhost:4567/words/new");
    fill("#wordInput").with("Orange");
    submit(".btn");
    click("a", withText("View Words"));
    click("a", withText("Orange"));
    click("a", withText("Add a new definition"));
    fill("#definition").with("fruit that is orange");
    submit(".btn");
    assertThat(pageSource()).contains("Your definition has been saved");
  }





}
