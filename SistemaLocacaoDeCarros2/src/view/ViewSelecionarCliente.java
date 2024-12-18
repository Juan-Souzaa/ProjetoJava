package view;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import banco.ClienteBanco;
import model.Cliente;


public class ViewSelecionarCliente {

    private Shell shell;
    private Table table;
    private ClienteBanco clienteBanco;
    private Cliente clienteSelecionado; // Cliente selecionado

    public ViewSelecionarCliente() {
        this.clienteBanco = new ClienteBanco();
    }

    public Cliente getClienteSelecionado() {
        return clienteSelecionado;
    }
    public static void main(String[] args) {
        try {
            ViewSelecionarCliente window = new ViewSelecionarCliente();
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
        shell.setSize(600, 400);
        shell.setText("Selecionar Cliente");

        // Tabela para exibir clientes
        table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
        table.setBounds(10, 10, 560, 300);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        // Colunas da tabela
        TableColumn colId = new TableColumn(table, SWT.NONE);
        colId.setWidth(50);
        colId.setText("ID");

        TableColumn colNome = new TableColumn(table, SWT.NONE);
        colNome.setWidth(200);
        colNome.setText("Nome");

        TableColumn colEmail = new TableColumn(table, SWT.NONE);
        colEmail.setWidth(150);
        colEmail.setText("Email");

        TableColumn colTelefone = new TableColumn(table, SWT.NONE);
        colTelefone.setWidth(100);
        colTelefone.setText("Telefone");

        // Botão para confirmar seleção
        Button btnSelecionar = new Button(shell, SWT.NONE);
        btnSelecionar.setBounds(10, 320, 150, 30);
        btnSelecionar.setText("Selecionar Cliente");
        
        
        List<Cliente> clientes = clienteBanco.listarclientes();
        for (Cliente cliente : clientes) {
            TableItem item = new TableItem(table, SWT.NONE);
            item.setText(new String[]{
                String.valueOf(cliente.getIdUsuario()),
                cliente.getNomeCompleto(),
                cliente.getEmail(),
                cliente.getTelefone()
                
            });
        }

        btnSelecionar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            	int selectedIndex = table.getSelectionIndex();
                if (selectedIndex >= 0) {
                    TableItem selectedItem = table.getItem(selectedIndex);
                    clienteSelecionado = new Cliente();
                    clienteSelecionado.setIdUsuario(Integer.parseInt(selectedItem.getText(0)));
                    clienteSelecionado.setNomeCompleto(selectedItem.getText(1));
                    
                    clienteSelecionado.setEmail(selectedItem.getText(2));
                    clienteSelecionado.setTelefone(selectedItem.getText(3));
                    shell.dispose(); // Fecha a janela após a seleção
                } else {
                    //
                    MessageBox box = new org.eclipse.swt.widgets.MessageBox(shell, SWT.ICON_WARNING);
                    box.setMessage("Selecione um cliente.");
                    box.open();
                }
                
            }
        });

        // Botão para cancelar a seleção
        Button btnCancelar = new Button(shell, SWT.NONE);
        btnCancelar.setBounds(180, 320, 150, 30);
        btnCancelar.setText("Cancelar");

        btnCancelar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                clienteSelecionado = null; // Nenhum cliente selecionado
                shell.close(); // Fecha a janela
            }
        });

        listarClientes(); // Carrega os clientes na tabela
        
    }

    private void listarClientes() {
        table.removeAll(); // Limpa a tabela antes de listar
        List<Cliente> clientes = clienteBanco.listarclientes(); // Obtém a lista de clientes
        for (Cliente cliente : clientes) {
            TableItem item = new TableItem(table, SWT.NONE);
            item.setText(new String[]{
                    String.valueOf(cliente.getIdUsuario()),
                    cliente.getNomeCompleto(),
                    cliente.getEmail(),
                    cliente.getTelefone()
            });
        }
    }
}
