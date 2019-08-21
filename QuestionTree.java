// Kellie Gui
// CSE 143 BF with Chrish Thakalath
// Homework 7 20 Questions
// This program is the game adminstrator to implement a yes/no guessing game
// The program is called QuestionTree that the computer will build a tree 
// that the branches are questions and leaves are answers 
// Each round of the game begins with the user to think of an object,
// the computer will try to guess the object by asking the player questions. 
// The player can only answer yes or no. 
// If the computer guesses the object, the computer wins.
// If not, the player wins.
// If the computer guesses it wrong, the player needs to give the new object,
// and the question that can distinguish this obejct with the previous wrong one. 

import java.io.*;
import java.util.*;

public class QuestionTree {
   private QuestionNode overallRoot;
   private Scanner console;
   
   // post: initializes the game with an answer representing the object "computer"       
   public QuestionTree() {
      overallRoot = new QuestionNode("computer"); 
      console = new Scanner(System.in);
   }
   
   // pre: takes in an output file 
   // post: writes the log for the current tree to an output file  
   public void write(PrintStream output) {
      write(overallRoot, output);
   }
   
   // pre: an output file 
   // post: a private helper method to output the question tree to the file
   private void write(QuestionNode root, PrintStream output) {
      if(root.left == null && root.right == null) {
         output.println("A:");
         output.println(root.text);
      } else {
         output.println("Q:");
         output.println(root.text);
         write(root.left, output);
         write(root.right, output);
      }
   }
   
   // pre: an input file
   // post: reads the input file and replaces the current tree 
   //       with the information in the standard form in the file
   public void read(Scanner input) {
      overallRoot = readHelper(input);
   }
   
   // pre: an input file
   // post: a private helper method to help read the file  
   //       and rebuilds the tree
   //       returns a tree node to build to tree
   private QuestionNode readHelper(Scanner input) {
      String type = input.nextLine();
      String words = input.nextLine();
      QuestionNode newNode = new QuestionNode(words);
      if(type.startsWith("Q")) {
         newNode.left = readHelper(input);
         newNode.right = readHelper(input);
      }
      return newNode;
   }
   
   // post: uses the current tree to ask the player questions
   //       until the tree reaches the end or the computer wins.
   //       If the tree ends and computer fails, the player needs to give an new object 
   //       and a new question that distinguishes it with other obejcts.
   public void askQuestions() {
      overallRoot = askQuestions(overallRoot);
   }
   
   // pre: the first question in the game database
   //      If there's  only an answer in the tree,
   //      the first answer will be given in 
   // post: prompt the player to answer the question 
   //       until the tree ends
   //       When computer loses, it prompts the player to type in new object
   //       and a new question that related to it. 
   //       returns the roots that linked the new object and the new question
   private QuestionNode askQuestions(QuestionNode root) {
      if(root.left != null || root.right != null) {
         exploreTree(root);   
      } else {
         if(yesTo("Would your object happen to be " + root.text + "?")) {
            System.out.println("Great, I got it right!");
         } else {
            System.out.print("What is the name of your object? ");
            QuestionNode newAns = new QuestionNode(console.nextLine());
            System.out.println("Please give me a yes/no question that");
            System.out.println("distinguishes between your object");
            System.out.print("and mine--> ");
            QuestionNode newQ = new QuestionNode(console.nextLine());
            if(yesTo("And what is the answer for your object?")) {
               newQ.right = root;
               newQ.left = newAns;
            } else {
               newQ.left = root;
               newQ.right = newAns;
            }
            root = newQ;
         }
      }
      return root;
   }
   
   // post: explores the tree and prompts the player with the questions of the tree 
   private void exploreTree(QuestionNode root){
      if(yesTo(root.text)) {
         root.left = askQuestions(root.left);
      } else {
         root.right = askQuestions(root.right);
      }
   }
   
   // post: asks the user a question, forcing an answer of "y " or "n";
   //       returns true if the answer was yes, returns false otherwise
   public boolean yesTo(String prompt) {
      System.out.print(prompt + " (y/n)? ");
      String response = console.nextLine().trim().toLowerCase();
      while (!response.equals("y") && !response.equals("n")) {
         System.out.println("Please answer y or n.");
         System.out.print(prompt + " (y/n)? ");
         response = console.nextLine().trim().toLowerCase();
      }
      return response.equals("y");
   }
}