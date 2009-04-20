package ch.unisi.inf.pfii.teamblue.jark;

/**
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate: 2009-04-13 10:37:16 +0200 (Mon, 13 Apr 2009) $
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