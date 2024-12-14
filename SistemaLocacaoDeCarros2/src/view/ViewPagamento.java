package view;

import java.time.LocalDate;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import banco.PagamentoBanco;
import model.Pagamento;

public class ViewPagamento {

    protected Shell shell;
    private Text textIdPagamento;
    private Text textMetodoPagamento;
    private Text textStatusPagamento;
    private Text textDescricao;
    private Text textValor;
    private Table table;
    private PagamentoBanco pagamentoBanco;

    public ViewPagamento() {
        pagamentoBanco = new PagamentoBanco();
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
        shell.setSize(818, 577);
        shell.setText("Pagamento");

        Label lblIdPagamento = new Label(shell, SWT.NONE);
        lblIdPagamento.setText("ID Pagamento:");
        lblIdPagamento.setBounds(10, 13, 81, 15);

        textIdPagamento = new Text(shell, SWT.BORDER);
        textIdPagamento.setBounds(97, 10, 110, 21);

        Label lblValor = new Label(shell, SWT.NONE);
        lblValor.setText("Valor:");
        lblValor.setBounds(10, 42, 46, 15);

        textValor = new Text(shell, SWT.BORDER);
        textValor.setToolTipText("");
        textValor.setBounds(57, 39, 86, 21);

        Label lblMetodoDePagamento = new Label(shell, SWT.NONE);
        lblMetodoDePagamento.setText("Metodo de Pagamento:");
        lblMetodoDePagamento.setBounds(10, 78, 132, 15);

        textMetodoPagamento = new Text(shell, SWT.BORDER);
        textMetodoPagamento.setBounds(148, 75, 92, 21);

        Label lblDataDoPagamento = new Label(shell, SWT.NONE);
        lblDataDoPagamento.setText("Data do Pagamento :");
        lblDataDoPagamento.setBounds(10, 119, 114, 15);

        DateTime dateTimeDataPagamento = new DateTime(shell, SWT.BORDER);
        dateTimeDataPagamento.setBounds(127, 110, 80, 24);

        textStatusPagamento = new Text(shell, SWT.BORDER);
        textStatusPagamento.setBounds(137, 159, 81, 21);

        Label lblStatusDoPagamento = new Label(shell, SWT.NONE);
        lblStatusDoPagamento.setText("Status do Pagamento:");
        lblStatusDoPagamento.setBounds(10, 162, 121, 15);

        Label lblDescricao = new Label(shell, SWT.NONE);
        lblDescricao.setText("Descrição:");
        lblDescricao.setBounds(10, 200, 65, 15);

        textDescricao = new Text(shell, SWT.BORDER);
        textDescricao.setBounds(80, 197, 209, 44);

        Button btnCadastrarPagamento = new Button(shell, SWT.NONE);
        btnCadastrarPagamento.setText("Cadastrar Pagamento");
        btnCadastrarPagamento.setBounds(57, 286, 150, 25);

        Button btnDeletarPagamento = new Button(shell, SWT.NONE);
        btnDeletarPagamento.setText("Deletar Pagamento");
        btnDeletarPagamento.setBounds(339, 286, 134, 25);

        Button btnConsultarPagamento = new Button(shell, SWT.NONE);
        btnConsultarPagamento.setText("Consultar Pagamento");
        btnConsultarPagamento.setBounds(597, 286, 150, 25);

        table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
        table.setLinesVisible(true);
        table.setHeaderVisible(true);
        table.setBounds(10, 333, 789, 205);

        TableColumn tblclmnIdPagamento = new TableColumn(table, SWT.CENTER);
        tblclmnIdPagamento.setWidth(106);
        tblclmnIdPagamento.setText("ID Pagamento");

        TableColumn tblclmnValor = new TableColumn(table, SWT.CENTER);
        tblclmnValor.setWidth(71);
        tblclmnValor.setText("Valor");

        TableColumn tblclmnMetodoPagamento = new TableColumn(table, SWT.CENTER);
        tblclmnMetodoPagamento.setText("Metodo de Pagamento");
        tblclmnMetodoPagamento.setWidth(138);

        TableColumn tblclmnDataPagamento = new TableColumn(table, SWT.CENTER);
        tblclmnDataPagamento.setWidth(147);
        tblclmnDataPagamento.setText("Data do Pagamento");

        TableColumn tblclmnStatusPagamento = new TableColumn(table, SWT.CENTER);
        tblclmnStatusPagamento.setWidth(164);
        tblclmnStatusPagamento.setText("Status do Pagamento");

        TableColumn tblclmnDescricao = new TableColumn(table, SWT.CENTER);
        tblclmnDescricao.setWidth(192);
        tblclmnDescricao.setText("Descrição");

        
        btnCadastrarPagamento.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                double valor = Double.parseDouble(textValor.getText());
                String metodoPagamento = textMetodoPagamento.getText();
                LocalDate dataPagamento = LocalDate.of(dateTimeDataPagamento.getYear(), dateTimeDataPagamento.getMonth() + 1, dateTimeDataPagamento.getDay());
                String statusPagamento = textStatusPagamento.getText();
                String descricao = textDescricao.getText();

                Pagamento pagamento = new Pagamento(valor, metodoPagamento, dataPagamento, statusPagamento, descricao);
                pagamentoBanco.incluir(pagamento);
                MessageBox box = new MessageBox(shell, SWT.OK);
                box.setMessage("Pagamento cadastrado com sucesso!");
                box.open();
            }
        });

      
        btnDeletarPagamento.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                try {
                    int idPagamento = Integer.parseInt(textIdPagamento.getText());
                    pagamentoBanco.deletar(idPagamento);
                    MessageBox box = new MessageBox(shell, SWT.OK);
                    box.setMessage("Pagamento deletado com sucesso!");
                    box.open();
                } catch (NumberFormatException ex) {
                    MessageBox box = new MessageBox(shell, SWT.ERROR);
                    box.setMessage("ID inválido.");
                    box.open();
                }
            }
        });

        // Consultar Pagamento
        btnConsultarPagamento.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                List<Pagamento> pagamentos = pagamentoBanco.listar();
                table.removeAll();
                for (Pagamento pagamento : pagamentos) {
                    TableItem item = new TableItem(table, SWT.NONE);
                    item.setText(new String[] {
                        String.valueOf(pagamento.getIdPagamento()),
                        String.valueOf(pagamento.getValor()),
                        pagamento.getMetodoPagamento(),
                        pagamento.getDataPagamento().toString(),
                        pagamento.getStatus(),
                        pagamento.getDescricao()
                    });
                }
            }
        });
    }
}
