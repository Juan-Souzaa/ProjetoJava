package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class MenuPrincipal {
    protected Shell shell;

    public static void main(String[] args) {
        try {
            MenuPrincipal window = new MenuPrincipal();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void open() {
        Display display = Display.getDefault();
        createContents();
        shell.open();
        shell.layout();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }

    protected void createContents() {
        shell = new Shell();
        shell.setSize(800, 600); // Tamanho maior para incluir todos os botões
        shell.setText("Sistema de Locação - Menu Principal");

        int x = 30; // 
        int y = 30; // Posição inicia
        int width = 200; // Largura dos botões
        int height = 40; // Altura dos botões
        int spacing = 10; // Espaçamento entre os botões

        // Criar botões para todas as views
        criarBotao(shell, "Usuarios", x, y, width, height, ViewUsuario.class);
        y += height + spacing;

      

        criarBotao(shell, "Veículos", x, y, width, height, ViewVeiculo.class);
        y += height + spacing;

        criarBotao(shell, "Frota", x, y, width, height, ViewFrota.class);
        y += height + spacing;

        criarBotao(shell, "Modelos", x, y, width, height, ViewModelo.class);
        y += height + spacing;

        criarBotao(shell, "Locação", x, y, width, height, ViewLocacao.class);
        y += height + spacing;

        criarBotao(shell, "Reservas", x, y, width, height, ViewReserva.class);
        y += height + spacing;

        criarBotao(shell, "Pagamentos", x, y, width, height, ViewPagamento.class);
        y += height + spacing;

        criarBotao(shell, "Faturas", x, y, width, height, ViewFatura.class);
        y += height + spacing;

        criarBotao(shell, "Multas", x, y, width, height, ViewMulta.class);
        y += height + spacing;

        criarBotao(shell, "Devoluções", x, y, width, height, ViewDevolucao.class);
        y += height + spacing;

        criarBotao(shell, "Manutenção", x, y, width, height, ViewManutencao.class);
        y += height + spacing;

        criarBotao(shell, "Cancelamentos", x, y, width, height, ViewCancelamento.class);
        y += height + spacing;

        criarBotao(shell, "Fidelidade", x, y, width, height, ViewFidelidade.class);
        y += height + spacing;

        criarBotao(shell, "Seguros", x, y, width, height, ViewSeguro.class);
        y += height + spacing;


        if (y > shell.getSize().y - 100) {
            x += width + spacing;
            y = 30;
        }
    }

  
    private void criarBotao(Shell shell, String titulo, int x, int y, int largura, int altura, Class<?> classeView) {
        Button botao = new Button(shell, SWT.NONE);
        botao.setBounds(x, y, largura, altura);
        botao.setText(titulo);

        botao.addListener(SWT.Selection, event -> {
            try {
                Object view = classeView.getDeclaredConstructor().newInstance();
                classeView.getMethod("open").invoke(view);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
