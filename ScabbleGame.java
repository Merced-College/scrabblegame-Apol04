/*
ScrabbleGame.java
IMPROVEMENT: Added a scoring system based on word length. Longer words get more points>
See methods: calculatePoints() and all sections marked with "// IMPROVEMENT"

My understanding of object-oriented programming was enhanced with the use of constructors, 
encapsulation, and data structures on a real game environment. I struggled with handling 
binary search with custom objects and learned the utilization of Comparable. Debugging was 
accomplished using the printing of intermediary outputs and referencing Stack Overflow to 
ensure how ArrayList and binarySearch treat objects. I also used GitHub AI to enhance the way 
I had programmed canFormWord and optimizing for letter handling.

*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ScrabbleGame {
  private ArrayList<Word> dictionary;

  public ScrabbleGame() {
    dictionary = new ArrayList<>();
    loadWords("CollinsScrabbleWords_2019.txt");
  }

  private void loadWords(String filename) {
    try {
      Scanner scanner = new Scanner(new File(filename));
      while (scanner.hasNextLine()) {
        String word = scanner.nextLine().trim().toLowerCase();
        if (word.length() >= 2) {  // optional: skip tiny words
          dictionary.add(new Word(word));
        }
      }
      Collections.sort(dictionary); // Sort the Dictionary
    } catch (FileNotFoundException e) {
      System.out.println("Word file not found: " + filename);
    }
  }

  public boolean isValidWord(String input) {
    return Collections.binarySearch(dictionary, new Word(input.toLowerCase())) >= 0;
  }

  private static boolean canFormWord(String word, char[] letters) {
    ArrayList<Character> available = new ArrayList<>();
    for (char c : letters) {
      available.add(c);
    }
    
    for (char c : word.toCharArray()) {
      if (!available.remove((Character) c)) {
        return false;
      }
    }
    return false;
  }

  //IMPROVEMENT: Points based on word length
  private int calculatePoints(String word) {
    int length = word.length();
    if (length == 2) return 1;
    else if (length == 3) return 2;
    else if (length == 4) return 4;
    else return length * 2; // bonus for words longer than 4
  }

  public void playGame() {
    Scanner scanner = new Scanner(System.in);
    Random rand = new Random();

    //Generates 4 random letters
    char[] letters = new char[4];
    for (int i = 0; i < 4; i++) {
      letters[i] = (char) ('a' + rand.nextInt(26));
    }

    System.out.print("Your letters are: ");
    for (char c : letters) {
      System.out.print(c + " ");
    }
    System.out.println();

    System.out.print("Enter a word using those letters: ");
    String inputWord = scanner.nextLine().toLowerCase();

    if (!canFormWord(inputWord, letters)) {
      System.out.println("Invalid! The word can't be formed using given letters.");
      return;
    }

    if (isValidWord(inputWord)) {
      System.out.println("Great! '" + inputWord + "' is a valid word.");

      //IMPROVEMENT: Calculate and displays points
      int points = calculatePoints(inputWord);
      System.out.println("You earned " + points + " points!");
    } else {
      System.out.println("Sorry! '" + inputWord + "' is not a valid Scrabble word.");
    }
  }

  public static void main(String[] args) {
    ScrabbleGame game = new ScrabbleGame();
    game.playGame();
  }
}
