package com.ztp.ztpproject;

import com.ztp.ztpproject.command.*;
import com.ztp.ztpproject.memento.*;
import com.ztp.ztpproject.models.Note;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Note note = new Note();
        CommandManager commandManager = new CommandManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Add Message");
            System.out.println("2. Undo");
            System.out.println("3. Display Note");
            System.out.println("4. Display Edit History");
            System.out.println("5. Restore to Specific State");
            System.out.println("6. Clear Mementos");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter message to add: ");
                    String message = scanner.nextLine();
                    Command appendCommand = new AppendCommand(note, message);
                    commandManager.executeCommand(appendCommand, note);
                    break;
                case 2:
                    commandManager.undo(note);
                    break;
                case 3:
                    System.out.println("Current Note Content: " + note.getContent());
                    break;
                case 4:
                    commandManager.showHistory();
                    break;
                case 5:
                    System.out.print("Enter state number to restore: ");
                    int stateIndex = scanner.nextInt();
                    commandManager.restoreToState(note, stateIndex);
                    break;
                case 6:
                    commandManager.clearMementos();
                    break;
                case 7:
                    System.out.println("Exiting.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}