//package edu.belmont.csc.src.stacks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Postfix {
    StackClass<Float> stack;
    private Scanner scan;

    public Postfix(String filename)throws FileNotFoundException {
        stack = new StackClass<>();
        scan = new Scanner(new File(filename));
    }

    // method for calculating the postfix expression
    private Float calc(String input) {
        float x, y, result;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isWhitespace(c)) continue;
            if (c >= '0' && c <= '9') {
                stack.push(Float.parseFloat(String.valueOf(input.charAt(i))));
            }
            else if(Character.isAlphabetic(c) || stack.isEmpty()){
                break;
            }
            else{
                y = stack.pop();
                if(stack.isEmpty()) break;
                x = stack.pop();
                switch (c) {
                    case '*':
                        result = x * y;
                        stack.push(result);
                        break;
                    case '+':
                        result = x + y;
                        stack.push(result);
                        break;
                    case '-':
                        result = x - y;
                        stack.push(result);
                        break;
                    case '/':
//                        if (y == 0) throw new ArithmeticException();
                        result = x / y;
                        stack.push(result);
                        break;
                    case '^':
                        result = (float) Math.pow(x, y);
                        stack.push(result);
                        break;
                    default:
                        break;
                }
            }
        }
        if(stack.size() > 1 || stack.isEmpty()){
            stack.clear();
            return null;
        }
        return stack.pop();
    }

    // method for parsing the expression file
    private void file() throws Exception {
        String output;
        while (scan.hasNextLine()) {
            output = scan.nextLine();
            if(output.isBlank()) continue;
            if(calc(output) == null) {
                System.out.println(output + " =ERR=");
                continue;
            }
            System.out.println(output + " = " + calc(output));
        }
    }

    public static void main(String[] args) throws Exception
    {
        String current = null;
        try {
            current = new File(".").getCanonicalPath();
            System.out.println("Current dir: " + current);
            System.out.print("Filename: ");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("File error");
        }
        Scanner s = new Scanner(System.in);
        String filename = s.nextLine();
        System.out.println("Read: " + filename);
        Postfix pf = new Postfix(filename);
        pf.file();
    }
}
