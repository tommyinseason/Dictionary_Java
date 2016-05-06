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
    assertThat(pageSource()).contains("Apple");
  }

  @Test
  public void wordIsDisplayedTest() {
    goTo("http://localhost:4567/words");
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
    click("a", withText("View words"));
    click("a", withText("Apple"));
    click("a", withText("Add a new definition"));
    assertThat(pageSource()).contains("Add a definition to Apple");
  }

  @Test
  public void defintionsIsAddedAndDisplayed() {
    goTo("http://localhost:4567/words/new");
    fill("#wordInput").with("Apple");
    submit(".btn");
    click("a", withText("View words"));
    click("a", withText("Apple"));
    click("a", withText("Add a new defintion"));
    fill("#definition").with("fruit from a tree");
    submit(".btn");
    click("a", withText("View words"));
    click("a", withText("Apple"));
    assertThat(pageSource()).contains("fruit from a tree");
  }





}
