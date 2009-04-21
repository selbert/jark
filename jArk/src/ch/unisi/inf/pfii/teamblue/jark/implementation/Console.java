package ch.unisi.inf.pfii.teamblue.jark.implementation;

/**
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 */

import java.util.Scanner;


public class Console {

    private final Scanner scanner;
    
    public Console() {
        scanner = new Scanner(System.in);
    }
    
    public String readLine() {
        return scanner.nextLine();
    }
    
    public void writeLine(final String line) {
        System.out.println(line);
    }

    public void writeRequest(final String line) {
        System.out.print(line);
    }
}