//Word.java

public class Word implements Comparable<Word>{
  private String word;

  public Word (String Word) {
    this.word = word.toLowerCase();
  }

  public String getWord(){
    return word;
  }

  @Override
  public int compareTo(Word other) {
    return this.word.compareTo(other.word);
  }

  @Override
  public String toString(){
    return word;
  }

  @Override
  public boolean equals (Object obj) {
    if (obj instanceof Word) {
      Word other = (Word) obj;
      return this.word.equals(other.word);
    }
    return false;
  }
}
