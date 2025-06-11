package br.edu.ifba.inf008.util;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;
import org.fusesource.jansi.AnsiConsole;

public class ConsolePrinter {

    public static void init() {
        AnsiConsole.systemInstall();
    }

    public static void shutdown() {
        AnsiConsole.systemUninstall();
    }

    public static void printTitle(String text) {
        System.out.println(ansi().bold().fg(CYAN).a("\n=== " + text.toUpperCase() + " ===").reset());
    }

    public static void printSectionHeader(String text) {
        System.out.println(ansi().fg(YELLOW).a(text).reset());
    }

    public static void printSuccess(String text) {
        System.out.println(ansi().fg(GREEN).a("✓ " + text).reset());
    }

    public static void printError(String text) {
        System.out.println(ansi().bold().fg(RED).a("Error: " + text).reset());
    }

    public static void printInfo(String label, String data) {
        System.out.println("  " + label + ": " + ansi().fg(WHITE).a(data).reset());
    }

    public static void printText(String text) {
        System.out.println("  " + text);
    }
    
    public static void printSeparator() {
        // Usamos a cor PRETA que em muitos terminais com fundo escuro aparece como um cinza sutil.
        System.out.println(ansi().fg(BLACK).a("------------------------------------------------------------").reset());
    }
}