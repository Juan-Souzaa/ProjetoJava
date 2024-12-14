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

import banco.LocacaoBanco;
import model.Locacao;

public class ViewLocacao {

    protected Shell shell;
    private Text txtIdLocacao;
    private Text txtValorTotal;
    private Text txtTipoLocacao;
    private Table table;
    private LocacaoBanco locacaoBanco;

    public ViewLocacao() {
        locacaoBanco = new LocacaoBanco();
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
        shell.setSize(962, 993);
        shell.setText("Locação");

        Label lblIdLocacao = new Label(shell, SWT.NONE);
        lblIdLocacao.setBounds(27, 28, 85, 15);
        lblIdLocacao.setText("ID da locação");

        txtIdLocacao = new Text(shell, SWT.BORDER);
        txtIdLocacao.setBounds(118, 28, 191, 21);

        Label lblDataLocacao = new Label(shell, SWT.NONE);
        lblDataLocacao.setBounds(27, 73, 85, 15);
        lblDataLocacao.setText("Data da locação");

        DateTime dateLocacao = new DateTime(shell, SWT.BORDER);
        dateLocacao.setBounds(118, 73, 80, 24);

        Label lblDataDevolucaoPrevista = new Label(shell, SWT.NONE);
        lblDataDevolucaoPrevista.setBounds(27, 123, 158, 15);
        lblDataDevolucaoPrevista.setText("Data prevista para devolução");

        DateTime dateDevolucaoPrevista = new DateTime(shell, SWT.BORDER);
        dateDevolucaoPrevista.setBounds(191, 123, 80, 24);

        Label lblDataDevolucao = new Label(shell, SWT.NONE);
        lblDataDevolucao.setBounds(27, 175, 105, 15);
        lblDataDevolucao.setText("Data da devolução");

        DateTime dateDevolucao = new DateTime(shell, SWT.BORDER);
        dateDevolucao.setBounds(138, 175, 80, 24);

        Label lblValorTotal = new Label(shell, SWT.NONE);
        lblValorTotal.setBounds(27, 234, 68, 15);
        lblValorTotal.setText("Valor total");

        txtValorTotal = new Text(shell, SWT.BORDER);
        txtValorTotal.setBounds(118, 234, 153, 21);

        Label lblTipoLocacao = new Label(shell, SWT.NONE);
        lblTipoLocacao.setBounds(27, 289, 85, 15);
        lblTipoLocacao.setText("Tipo de locação");

        txtTipoLocacao = new Text(shell, SWT.BORDER);
        txtTipoLocacao.setBounds(118, 289, 153, 21);

        Button btnCadastrarLocacao = new Button(shell, SWT.NONE);
        btnCadastrarLocacao.setBounds(74, 406, 75, 25);
        btnCadastrarLocacao.setText("Cadastrar");

        Button btnDeletarLocacao = new Button(shell, SWT.NONE);
        btnDeletarLocacao.setBounds(221, 406, 105, 25);
        btnDeletarLocacao.setText("Deletar locação");

        Button btnConsultarLocacao = new Button(shell, SWT.NONE);
        btnConsultarLocacao.setBounds(385, 406, 105, 25);
        btnConsultarLocacao.setText("Consultar locação");

        table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
        table.setBounds(27, 464, 777, 283);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        TableColumn tblclmnIdLocacao = new TableColumn(table, SWT.NONE);
        tblclmnIdLocacao.setWidth(100);
        tblclmnIdLocacao.setText("ID da locação");

        TableColumn tblclmnDataLocacao = new TableColumn(table, SWT.NONE);
        tblclmnDataLocacao.setWidth(100);
        tblclmnDataLocacao.setText("Data da locação");

        TableColumn tblclmnDataDevolucaoPrevista = new TableColumn(table, SWT.NONE);
        tblclmnDataDevolucaoPrevista.setWidth(162);
        tblclmnDataDevolucaoPrevista.setText("Data prevista pra devolução");

        TableColumn tblclmnDataDevolucao = new TableColumn(table, SWT.NONE);
        tblclmnDataDevolucao.setWidth(114);
        tblclmnDataDevolucao.setText("Data da devolução");

        TableColumn tblclmnValorTotal = new TableColumn(table, SWT.CENTER);
        tblclmnValorTotal.setWidth(100);
        tblclmnValorTotal.setText("Valor total");

        TableColumn tblclmnTipoLocacao = new TableColumn(table, SWT.NONE);
        tblclmnTipoLocacao.setWidth(100);
        tblclmnTipoLocacao.setText("Tipo de locação");

        TableColumn tblclmnObservacoes = new TableColumn(table, SWT.NONE);
        tblclmnObservacoes.setWidth(100);
        tblclmnObservacoes.setText("Observações");

     
        btnCadastrarLocacao.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                int idLocacao = Integer.parseInt(txtIdLocacao.getText());
                LocalDate dataLocacao = LocalDate.of(dateLocacao.getYear(), dateLocacao.getMonth() + 1, dateLocacao.getDay());
                LocalDate dataDevolucaoPrevista = LocalDate.of(dateDevolucaoPrevista.getYear(), dateDevolucaoPrevista.getMonth() + 1, dateDevolucaoPrevista.getDay());
                LocalDate dataDevolucao = LocalDate.of(dateDevolucao.getYear(), dateDevolucao.getMonth() + 1, dateDevolucao.getDay());
                double valorTotal = Double.parseDouble(txtValorTotal.getText());
                String tipoLocacao = txtTipoLocacao.getText();

                Locacao locacao = new Locacao(idLocacao, dataLocacao, dataDevolucaoPrevista, dataDevolucao, valorTotal, tipoLocacao, null);
                locacaoBanco.incluir(locacao);

                MessageBox box = new MessageBox(shell, SWT.OK);
                box.setMessage("Locação cadastrada com sucesso!");
                box.open();
            }
        });

        btnConsultarLocacao.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                List<Locacao> locacoes = locacaoBanco.listar();
                table.removeAll();
                for (Locacao locacao : locacoes) {
                    TableItem item = new TableItem(table, SWT.NONE);
                    item.setText(new String[]{
                            String.valueOf(locacao.getIdLocacao()),
                            locacao.getDataLocacao().toString(),
                            locacao.getDataDevolucaoPrevista().toString(),
                            locacao.getDataDevolucaoReal().toString(),
                            String.valueOf(locacao.getValorTotal()),
                            locacao.getTipoLocacao()
                    });
                }
            }
        });


        btnDeletarLocacao.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                int idLocacao = Integer.parseInt(txtIdLocacao.getText());
                locacaoBanco.deletar(idLocacao);

                MessageBox box = new MessageBox(shell, SWT.OK);
                box.setMessage("Locação deletada com sucesso!");
                box.open();
            }
        });
    }
}
