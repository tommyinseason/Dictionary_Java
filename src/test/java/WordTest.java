import org.junit.*;
import static org.junit.Assert.*;

public class WordTest {

  @Test
  public void newWord_instantiatesCorrectly(){
    Word myWord = new Word("apple");
    assertEquals(true, myWord instanceof Word);
  }

  @Test
  public void Word_instantiatesWithWord(){
    Word myWord = new Word("apple");
    assertEquals("apple", myWord.getWord());
  }


}
