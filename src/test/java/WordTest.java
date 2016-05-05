import org.junit.*;
import static org.junit.Assert.*;

public class WordTest {

  @After
  public void tearDown() {
    Word.clear();
    // Definition.clear();
  }

  @Test
  public void word_instantiatesCorrectly_true() {
    Word testWord = new Word("apple");
    assertEquals(true, testWord instanceof Word);
  }

  @Test
  public void getTheWord_InstantiatesWithWord_Apple() {
    Word testWord = new Word("Apple");
    assertEquals("Apple", testWord.getTheWord());
  }

  @Test
  public void all_returnsAllInstancesOfWord_true() {
    Word firstWord = new Word("apple");
    Word secondWord = new Word("banana");
    assertTrue(Word.all().contains(firstWord));
    assertTrue(Word.all().contains(secondWord));
  }

  @Test
  public void clear_emptiesAllWordsFromList_0() {
    Word testWord = new Word("apple");
    Word.clear();
    assertEquals(Word.all().size(), 0);
  }

  @Test
  public void getId_WordInstantiateWithAnId_1() {
    Word testWord = new Word("Home");
    assertEquals(1, testWord.getId());
  }
  
  @Test
  public void find_returnsWordWithSameId_secondWord() {
    Word firstWord = new Word("Home");
    Word secondWord = new Word("Work");
    assertEquals(Word.find(secondWord.getId()), secondWord);
  }

  // @Test
  // public void find_returnsNullWhenNoWordFound_null() {
  //   assertTrue(Word.find(999) == null);
  // }
}
