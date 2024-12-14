package view;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import banco.AgenteLocacaoBanco;
import model.AgenteLocacao;

public class ViewAgenteLocacao {
    private AgenteLocacaoBanco agenteLocacaoBanco;
    protected Shell shell;
    private Text txtCodigoAgente;
    private Text txtRegiaoAtuacao;
    private Table table;

    public ViewAgenteLocacao() {
        this.agenteLocacaoBanco = new AgenteLocacaoBanco();
    }

    public static void main(String[] args) {
        try {
            ViewAgenteLocacao window = new ViewAgenteLocacao();
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
        shell.setSize(600, 500);
        shell.setText("Agente de Locação");

        Label lblCodigoAgente = new Label(shell, SWT.NONE);
        lblCodigoAgente.setBounds(20, 30, 120, 20);
        lblCodigoAgente.setText("Código do Agente:");

        txtCodigoAgente = new Text(shell, SWT.BORDER);
        txtCodigoAgente.setBounds(150, 30, 150, 20);

        Label lblRegiaoAtuacao = new Label(shell, SWT.NONE);
        lblRegiaoAtuacao.setBounds(20, 70, 120, 20);
        lblRegiaoAtuacao.setText("Região de Atuação:");

        txtRegiaoAtuacao = new Text(shell, SWT.BORDER);
        txtRegiaoAtuacao.setBounds(150, 70, 250, 20);

        Button btnCadastrar = new Button(shell, SWT.NONE);
        btnCadastrar.setBounds(20, 120, 120, 30);
        btnCadastrar.setText("Cadastrar");

        Button btnAtualizar = new Button(shell, SWT.NONE);
        btnAtualizar.setBounds(160, 120, 120, 30);
        btnAtualizar.setText("Atualizar");

        Button btnDeletar = new Button(shell, SWT.NONE);
        btnDeletar.setBounds(300, 120, 120, 30);
        btnDeletar.setText("Deletar");

        Button btnConsultar = new Button(shell, SWT.NONE);
        btnConsultar.setBounds(440, 120, 120, 30);
        btnConsultar.setText("Consultar");

        table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
        table.setBounds(20, 200, 540, 200);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        TableColumn colCodigoAgente = new TableColumn(table, SWT.NONE);
        colCodigoAgente.setWidth(200);
        colCodigoAgente.setText("Código do Agente");

        TableColumn colRegiaoAtuacao = new TableColumn(table, SWT.NONE);
        colRegiaoAtuacao.setWidth(300);
        colRegiaoAtuacao.setText("Região de Atuação");
        Button btnListarTodos = new Button(shell, SWT.NONE);
        btnListarTodos.setBounds(300, 160, 120, 30); 
        btnListarTodos.setText("Listar Todos");

        btnListarTodos.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                table.removeAll(); 
                List<AgenteLocacao> agentes = agenteLocacaoBanco.listarAgentes(); 
                for (AgenteLocacao agente : agentes) {
                    TableItem item = new TableItem(table, SWT.NONE);
                    item.setText(new String[] {
                        String.valueOf(agente.getCodigoAgente()),
                        agente.getRegiaoAtuacao()
                    });
                }
                MessageBox messageBox = new MessageBox(shell, SWT.OK);
                messageBox.setMessage("Todos os agentes listados com sucesso!");
                messageBox.open();
            }
        });


      
        btnCadastrar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                String codigo = txtCodigoAgente.getText();
                String regiao = txtRegiaoAtuacao.getText();
                AgenteLocacao agente = new AgenteLocacao(Integer.parseInt(codigo), regiao);
                agenteLocacaoBanco.incluir(agente);

                MessageBox messageBox = new MessageBox(shell, SWT.OK);
                messageBox.setMessage("Agente cadastrado com sucesso!");
                messageBox.open();
            }
        });

        
        btnAtualizar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                String codigo = txtCodigoAgente.getText();
                String regiao = txtRegiaoAtuacao.getText();
                AgenteLocacao agente = new AgenteLocacao(Integer.parseInt(codigo), regiao);
                agenteLocacaoBanco.atualizar(agente);

                MessageBox messageBox = new MessageBox(shell, SWT.OK);
                messageBox.setMessage("Agente atualizado com sucesso!");
                messageBox.open();
            }
        });

        btnConsultar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                String codigo = txtCodigoAgente.getText();

              
                if (codigo.isEmpty()) {
                    MessageBox warningBox = new MessageBox(shell, SWT.ICON_WARNING);
                    warningBox.setMessage("Por favor, insira um código para consultar.");
                    warningBox.open();
                    return;
                }

                try {
                    int codigoAgente = Integer.parseInt(codigo);
                    AgenteLocacao agente = agenteLocacaoBanco.consultar(codigoAgente);

                    table.removeAll();

                    if (agente != null) {
                     
                        TableItem item = new TableItem(table, SWT.NONE);
                        item.setText(new String[] {
                            String.valueOf(agente.getCodigoAgente()),
                            agente.getRegiaoAtuacao()
                        });

                        MessageBox successBox = new MessageBox(shell, SWT.ICON_INFORMATION);
                        successBox.setMessage("Agente encontrado e exibido na tabela!");
                        successBox.open();
                    } else {
                        MessageBox notFoundBox = new MessageBox(shell, SWT.ICON_WARNING);
                        notFoundBox.setMessage("Nenhum agente encontrado com o código fornecido.");
                        notFoundBox.open();
                    }
                } catch (NumberFormatException ex) {
                    MessageBox errorBox = new MessageBox(shell, SWT.ICON_ERROR);
                    errorBox.setMessage("Código inválido! Por favor, insira um número.");
                    errorBox.open();
                } catch (Exception ex) {
                    MessageBox errorBox = new MessageBox(shell, SWT.ICON_ERROR);
                    errorBox.setMessage("Erro ao consultar agente: " + ex.getMessage());
                    errorBox.open();
                }
            }
        });

        
        btnDeletar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                String codigo = txtCodigoAgente.getText();
                agenteLocacaoBanco.deletar(Integer.parseInt(codigo));

                MessageBox messageBox = new MessageBox(shell, SWT.OK);
                messageBox.setMessage("Agente deletado com sucesso!");
                messageBox.open();
            }
        });
    }
    
}


