// Kellie Gui
// CSE 143 BF with Chrish Thakalath
// Homework 7 20 Questions
// The class is the tree nodes for the QuestionTree program
// to store question or answer

public class QuestionNode {
   public String text; // text stored at this QuestionNode 
   public QuestionNode left; // reference to left subtree
   public QuestionNode right; // reference to right subtree
   
   // pre: a question with two following left and right questions or answers
   // post: constructs a question node with the given text and links      
   public QuestionNode(String text, QuestionNode left, QuestionNode right) {
      this.text = text;
      this.left = left;
      this.right = right;
   }
   
   // post: constructs an answer node with the given text 
   public QuestionNode(String text) {
      this(text, null, null);
   }
}