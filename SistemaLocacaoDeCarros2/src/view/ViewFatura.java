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

import banco.FaturaBanco;
import model.Fatura;
import model.Locacao;

public class ViewFatura {
    private FaturaBanco faturaBanco;
    protected Shell shell;
    private Table table;

    public ViewFatura() {
        this.faturaBanco = new FaturaBanco();
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
        shell.setSize(600, 733);
        shell.setText("Fatura");

        // Campos para cadastro de fatura
        Label lblNumeroFatura = new Label(shell, SWT.NONE);
        lblNumeroFatura.setBounds(20, 30, 120, 15);
        lblNumeroFatura.setText("Número da Fatura:");

        Text txtNumeroFatura = new Text(shell, SWT.BORDER);
        txtNumeroFatura.setBounds(160, 30, 200, 25);

        Label lblDataEmissao = new Label(shell, SWT.NONE);
        lblDataEmissao.setBounds(20, 70, 120, 15);
        lblDataEmissao.setText("Data de Emissão:");

        DateTime dateEmissao = new DateTime(shell, SWT.BORDER);
        dateEmissao.setBounds(160, 70, 100, 25);

        Label lblValorTotal = new Label(shell, SWT.NONE);
        lblValorTotal.setBounds(20, 110, 120, 15);
        lblValorTotal.setText("Valor Total:");

        Text txtValorTotal = new Text(shell, SWT.BORDER);
        txtValorTotal.setBounds(160, 110, 200, 25);

        Label lblObservacoes = new Label(shell, SWT.NONE);
        lblObservacoes.setBounds(20, 150, 120, 15);
        lblObservacoes.setText("Observações:");

        Text txtObservacoes = new Text(shell, SWT.BORDER);
        txtObservacoes.setBounds(160, 150, 200, 50);

        Button btnCadastrarFatura = new Button(shell, SWT.NONE);
        btnCadastrarFatura.setBounds(40, 210, 120, 30);
        btnCadastrarFatura.setText("Cadastrar Fatura");

        Button btnConsultarFatura = new Button(shell, SWT.NONE);
        btnConsultarFatura.setBounds(180, 210, 120, 30);
        btnConsultarFatura.setText("Consultar Faturas");

        table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
        table.setBounds(40, 260, 500, 300);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        TableColumn tblclmnNumeroFatura = new TableColumn(table, SWT.NONE);
        tblclmnNumeroFatura.setWidth(100);
        tblclmnNumeroFatura.setText("Número");

        TableColumn tblclmnDataEmissao = new TableColumn(table, SWT.NONE);
        tblclmnDataEmissao.setWidth(120);
        tblclmnDataEmissao.setText("Data");

        TableColumn tblclmnValorTotal = new TableColumn(table, SWT.NONE);
        tblclmnValorTotal.setWidth(100);
        tblclmnValorTotal.setText("Valor");

        TableColumn tblclmnObservacoes = new TableColumn(table, SWT.NONE);
        tblclmnObservacoes.setWidth(200);
        tblclmnObservacoes.setText("Observações");

    
        btnCadastrarFatura.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                String numeroFatura = txtNumeroFatura.getText();
                LocalDate dataEmissao = LocalDate.of(dateEmissao.getYear(), dateEmissao.getMonth() + 1, dateEmissao.getDay());
                double valorTotal = Double.parseDouble(txtValorTotal.getText());
                String observacoes = txtObservacoes.getText();

                Locacao locacao = new Locacao();
                locacao.setIdLocacao(1); 
                Fatura fatura = new Fatura(numeroFatura, dataEmissao, valorTotal, observacoes, locacao);

                faturaBanco.incluir(fatura);
                MessageBox box = new MessageBox(shell, SWT.OK);
                box.setMessage("Fatura cadastrada com sucesso!");
                box.open();
            }
        });

 
        btnConsultarFatura.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                List<Fatura> faturas = faturaBanco.listar();
                table.removeAll();
                for (Fatura fatura : faturas) {
                    TableItem item = new TableItem(table, SWT.NONE);
                    item.setText(new String[]{
                            fatura.getNumeroFatura(),
                            fatura.getDataEmissao().toString(),
                            String.format("%.2f", fatura.getValorTotal()),
                            fatura.getObservacoes()
                    });
                }
            }
        });
    }
}
