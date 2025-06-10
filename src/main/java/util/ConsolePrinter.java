package util;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;
import org.fusesource.jansi.AnsiConsole;

/**
 * Classe utilitária para imprimir mensagens formatadas e coloridas no console.
 * Foco em hierarquia visual e uso semântico de cores.
 */
public class ConsolePrinter {

    public static void init() {
        AnsiConsole.systemInstall();
    }

    public static void shutdown() {
        AnsiConsole.systemUninstall();
    }

    /**
     * Imprime um título principal, em ciano e negrito.
     * Ideal para o topo de relatórios ou para iniciar seções importantes.
     */
    public static void printTitle(String text) {
        System.out.println(ansi().bold().fg(CYAN).a("\n=== " + text.toUpperCase() + " ===").reset());
    }

    /**
     * Imprime um cabeçalho de seção secundário, em amarelo.
     * Ótimo para agrupar informações, como o cabeçalho de um certificado.
     */
    public static void printSectionHeader(String text) {
        System.out.println(ansi().fg(YELLOW).a(text).reset());
    }

    /**
     * Imprime uma mensagem de sucesso, em verde.
     */
    public static void printSuccess(String text) {
        System.out.println(ansi().fg(GREEN).a("✓ " + text).reset());
    }

    /**
     * Imprime uma mensagem de erro, em vermelho e negrito para dar ênfase.
     */
    public static void printError(String text) {
        System.out.println(ansi().bold().fg(RED).a("✗ Error: " + text).reset());
    }

    /**
     * Imprime um par de [Rótulo]: [Dado], destacando o DADO em branco.
     * Faz com que a informação principal salte aos olhos.
     */
    public static void printInfo(String label, String data) {
        System.out.println("  " + label + ": " + ansi().fg(WHITE).a(data).reset());
    }

    /**
     * Imprime um texto comum, com leve indentação para alinhar com os 'printInfo'.
     */
    public static void printText(String text) {
        System.out.println("  " + text);
    }
    
    /**
     * Imprime uma linha separadora para organizar visualmente a saída.
     */
    public static void printSeparator() {
        // Usamos a cor PRETA que em muitos terminais com fundo escuro aparece como um cinza sutil.
        System.out.println(ansi().fg(BLACK).a("------------------------------------------------------------").reset());
    }
}