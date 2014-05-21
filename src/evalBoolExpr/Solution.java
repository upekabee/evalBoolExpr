package evalBoolExpr;

import java.util.HashMap;
import java.util.*;

public class Solution {
  public static void main(String[] args) {
    HashMap<String, Boolean> map = new HashMap<String, Boolean>();
    map.put("a", false);
    map.put("b", true);
    map.put("c", false);
    
    ArrayList<String> expr = new ArrayList<String>();
    expr.add("a"); 
    expr.add("b"); 
    expr.add("&&"); 
    expr.add("c"); 
    expr.add("||"); 
    System.out.println("Result: " + evaluate(expr, map));
  }
  
  static Boolean evaluate(ArrayList<String> expr, HashMap<String, Boolean> vars) {
    
    String AND = "&&"; 
    String OR = "||"; 
    String NOT = "!"; 
    Stack<Boolean> operands = new Stack<Boolean>();
    
    for (int i=0; i<expr.size(); i++) {
      
      if (expr.get(i) == AND) {   
        Boolean one = operands.pop();
        Boolean two = operands.pop();
        Boolean temp = one && two; 
        operands.push(temp);
        
      } else if (expr.get(i) == OR) {
        Boolean one = operands.pop();
        Boolean two = operands.pop();
        Boolean temp = one || two; 
        operands.push(temp);  
        
        
      } else if (expr.get(i) == NOT) {
          Boolean one = operands.pop();
          operands.push(!one);
                
      } else {
        Boolean temp = vars.get(expr.get(i));
        operands.push(temp);
      }  
     
    } 
    
    return operands.pop(); 
  }
  
}
/** 
 * Notes from exercise
 * Each variable can have two values: true, false
 * variables: a, b, c... (booleans)
 * Operators supported: a && b, a || b, !a
 * !(a || (b && !c))
 * evaluate(expr, Map<String, Boolean> vars) --> true/false
 * 
 *  &&
 * / \
 * a   b
 * 3 + 5 ==> 3 5 +
 * (a && b) || c
 * ["a", "b", "&&", "c", "||"]
 * [{a, true}, {b, false}]
 * evaluate(["a","b", "&&"])
 * ["a", "b", "&&", "c", "||"]
 * ["a", "!"]
 * a b c d e f g h i && && && && && && 
 * a b c && && ! = !(a && (b && c))
 */