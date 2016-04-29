import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.ArrayList;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("wordList", request.session().attribute("wordList"));
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/words", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
    //
    //   ArrayList<Word> userInputWords = request.session().attribute("userInputWords");
    //     if (userInputWords == null) {
    //       userInputWords = new ArrayList<Word>();
    //       request.session().attribute("userInputWords", userInputWords);
    // }

      String userInputWord = request.queryParams("userInputWord");
      Word newWord = new Word(userInputWord);
      request.session().attribute("wordList", newWord);

      model.put("template", "templates/success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


  }
}
