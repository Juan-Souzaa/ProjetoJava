package view;

import java.time.LocalDate;
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
import banco.FaturaBanco;
import model.Cliente;
import model.Fatura;
import model.Locacao;
import model.Reserva;

public class ViewSelecionarFatura {

    protected Shell shell;
    private Table table;
    private FaturaBanco faturaBanco;
    private Fatura faturaSelecionada;

    public ViewSelecionarFatura() {
        faturaBanco = new FaturaBanco();
    }

    public static void main(String[] args) {
        try {
            ViewSelecionarFatura window = new ViewSelecionarFatura();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Fatura open() {
        Display display = Display.getDefault();
        createContents();
        shell.open();
        shell.layout();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        return faturaSelecionada; // Retorna a fatura selecionada ao fechar a janela
    }

    protected void createContents() {
        shell = new Shell();
        shell.setSize(645, 401);
        shell.setText("Selecionar Fatura");

        table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
        table.setBounds(10, 10, 580, 300);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        TableColumn tblclmnIdFatura = new TableColumn(table, SWT.NONE);
        tblclmnIdFatura.setWidth(100);
        tblclmnIdFatura.setText("ID Fatura");
        
        TableColumn tblclmnNome = new TableColumn(table, SWT.NONE);
        tblclmnNome.setWidth(100);
        tblclmnNome.setText("Nome");

        TableColumn tblclmnDataEmissao = new TableColumn(table, SWT.NONE);
        tblclmnDataEmissao.setWidth(150);
        tblclmnDataEmissao.setText("Data Emissão");

        TableColumn tblclmnValorTotal = new TableColumn(table, SWT.NONE);
        tblclmnValorTotal.setWidth(100);
        tblclmnValorTotal.setText("Valor Total");

        TableColumn tblclmnObservacoes = new TableColumn(table, SWT.NONE);
        tblclmnObservacoes.setWidth(200);
        tblclmnObservacoes.setText("Observações");
        
        

        Button btnSelecionar = new Button(shell, SWT.NONE);
        btnSelecionar.setBounds(10, 320, 120, 30);
        btnSelecionar.setText("Selecionar");

        // Preencher a tabela com as faturas do banco
        List<Fatura> faturas = faturaBanco.listar();
        for (Fatura fatura : faturas) {
            TableItem item = new TableItem(table, SWT.NONE);
            item.setText(new String[]{
                String.valueOf(fatura.getNumeroFatura()),
                fatura.getLocacaoFatura().getReservaLocacao().getClienteReserva().getNomeCompleto(),
                fatura.getDataEmissao().toString(),
                String.valueOf(fatura.getValorTotal()),
                fatura.getObservacoes(),
                
            });
        }

        // Ação do botão selecionar
        btnSelecionar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                int selectedIndex = table.getSelectionIndex();
                if (selectedIndex >= 0) {
                    TableItem selectedItem = table.getItem(selectedIndex);
                    faturaSelecionada = new Fatura();
                    
                    faturaSelecionada.setNumeroFatura(Integer.parseInt(selectedItem.getText(0)));
                    faturaSelecionada.setDataEmissao((LocalDate.parse(selectedItem.getText(2))));
                    faturaSelecionada.setValorTotal(Double.parseDouble(selectedItem.getText(3)));
                    faturaSelecionada.setObservacoes(selectedItem.getText(4));
                    
                    
                    
                    Cliente cliente = new Cliente();
                    cliente.setNomeCompleto(selectedItem.getText(1));
                    
                    
                    Reserva reserva =  new Reserva();
                    reserva.setClienteReserva(cliente);
                    
                    Locacao locacao = new Locacao();
                    locacao.setReservaLocacao(reserva);
                    
                    faturaSelecionada.setLocacaoFatura(locacao);
                    
                    
                 
                    shell.dispose(); // Fecha a janela após a seleção
                } else {
                    // Exibe mensagem se nada foi selecionado
                    MessageBox box = new MessageBox(shell, SWT.ICON_WARNING);
                    box.setMessage("Selecione uma fatura antes de continuar!");
                    box.open();
                }
            }
        });
    }
}
