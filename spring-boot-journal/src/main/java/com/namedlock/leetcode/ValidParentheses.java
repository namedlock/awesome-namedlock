package com.namedlock.leetcode;


import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Stack;

public class ValidParentheses {
    private HashMap<Character, Character> mappings = null;

    public ValidParentheses(){
        mappings = new HashMap<>();
        mappings.put(']', '[');
        mappings.put('}', '{');
        mappings.put(')', '(');
    }

    public boolean isValid(String s){
        Stack<Character> stack = new Stack<Character>();
        for(int i=0; i < s.length(); i++){
            char c = s.charAt(i);

            if(this.mappings.containsKey(c)){
                char top = stack.empty()? '&' : stack.pop();
                if(top != this.mappings.get(c) ){
                    return false;
                }
            }else {
                stack.push(c);
            }
        }

        return stack.empty();
    }

    @Test
    public void test(){
        ValidParentheses validParentheses = new ValidParentheses();
        Assert.assertEquals(true, validParentheses.isValid("{}"));
        Assert.assertEquals(false, validParentheses.isValid("["));
        Assert.assertEquals(false, validParentheses.isValid("[{]"));
        Assert.assertEquals(true, validParentheses.isValid("[{[{}()[{}]]}]"));
        Assert.assertEquals(false, validParentheses.isValid("[{[{}()[{}]]}]{[}"));
    }
}
