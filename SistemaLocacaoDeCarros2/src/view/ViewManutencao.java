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

import banco.ManutencaoBanco;
import model.Manutencao;

public class ViewManutencao {

    protected Shell shell;
    private Text textIdManu;
    private Text textTipoManu;
    private Text textValorManu;
    private Text textDescricao;
    private Table table;
    private ManutencaoBanco manutencaoBanco;

    public ViewManutencao() {
        manutencaoBanco = new ManutencaoBanco();
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
        shell.setSize(832, 584);
        shell.setText("Manutenção");

        Label lblIdManu = new Label(shell, SWT.NONE);
        lblIdManu.setBounds(10, 26, 90, 15);
        lblIdManu.setText("ID Manutenção:");

        textIdManu = new Text(shell, SWT.BORDER);
        textIdManu.setBounds(101, 23, 81, 21);

        Label lblDataDaManutencao = new Label(shell, SWT.NONE);
        lblDataDaManutencao.setText("Data da Manutenção:");
        lblDataDaManutencao.setBounds(10, 75, 119, 15);

        DateTime dateTimeDataDaManu = new DateTime(shell, SWT.BORDER);
        dateTimeDataDaManu.setBounds(135, 66, 80, 24);

        Label lblTipoDaManutencao = new Label(shell, SWT.NONE);
        lblTipoDaManutencao.setText("Tipo da Manutenção:");
        lblTipoDaManutencao.setBounds(10, 132, 119, 15);

        textTipoManu = new Text(shell, SWT.BORDER);
        textTipoManu.setBounds(135, 129, 246, 21);

        Label lblValorDaManutencao = new Label(shell, SWT.NONE);
        lblValorDaManutencao.setText("Valor da Manutenção:");
        lblValorDaManutencao.setBounds(10, 183, 119, 15);

        textValorManu = new Text(shell, SWT.BORDER);
        textValorManu.setBounds(134, 180, 81, 21);

        Label lblDescricao = new Label(shell, SWT.NONE);
        lblDescricao.setText("Descrição:");
        lblDescricao.setBounds(10, 229, 67, 15);

        textDescricao = new Text(shell, SWT.BORDER);
        textDescricao.setBounds(86, 226, 219, 48);

        Button btnCadastrarManu = new Button(shell, SWT.NONE);
        btnCadastrarManu.setBounds(57, 299, 150, 25);
        btnCadastrarManu.setText("Cadastrar Manutenção");

        Button btnDeletarManutencao = new Button(shell, SWT.NONE);
        btnDeletarManutencao.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                int idManutencao = Integer.parseInt(textIdManu.getText());
                manutencaoBanco.deletar(idManutencao);

                MessageBox box = new MessageBox(shell, SWT.OK);
                box.setMessage("Manutenção deletada com sucesso!");
                box.open();
            }
        });
        btnDeletarManutencao.setText("Deletar Manutenção");
        btnDeletarManutencao.setBounds(339, 299, 134, 25);

        Button btnConsultarManutencao = new Button(shell, SWT.NONE);
        btnConsultarManutencao.setText("Consultar Manutenção");
        btnConsultarManutencao.setBounds(597, 299, 150, 25);
        btnConsultarManutencao.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                List<Manutencao> manutencaoList = manutencaoBanco.listar();
                table.removeAll();
                for (Manutencao manutencao : manutencaoList) {
                    TableItem item = new TableItem(table, SWT.NONE);
                    item.setText(new String[]{
                        String.valueOf(manutencao.getIdManutencao()),
                        manutencao.getDataManutencao().toString(),
                        manutencao.getTipoManutencao(),
                        String.valueOf(manutencao.getCusto()),
                        manutencao.getDescricao()
                    });
                }
            }
        });

        table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
        table.setBounds(121, 330, 590, 180);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        TableColumn tblclmnIdManu = new TableColumn(table, SWT.CENTER);
        tblclmnIdManu.setWidth(100);
        tblclmnIdManu.setText("ID Manutenção");

        TableColumn tblclmnDataManu = new TableColumn(table, SWT.CENTER);
        tblclmnDataManu.setWidth(122);
        tblclmnDataManu.setText("Data da Manutenção");

        TableColumn tblclmnTipoManu = new TableColumn(table, SWT.CENTER);
        tblclmnTipoManu.setWidth(128);
        tblclmnTipoManu.setText("Tipo da Manutenção");

        TableColumn tblclmnValorManu = new TableColumn(table, SWT.CENTER);
        tblclmnValorManu.setWidth(139);
        tblclmnValorManu.setText("Valor da Manutenção");

        TableColumn tblclmnDescricao = new TableColumn(table, SWT.CENTER);
        tblclmnDescricao.setWidth(100);
        tblclmnDescricao.setText("Descrição");

        
        btnCadastrarManu.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                Integer idManutencao = Integer.parseInt(textIdManu.getText());
                LocalDate dataManutencao = LocalDate.of(dateTimeDataDaManu.getYear(), dateTimeDataDaManu.getMonth() + 1, dateTimeDataDaManu.getDay());
                String tipoManutencao = textTipoManu.getText();
                Double valorManutencao = Double.parseDouble(textValorManu.getText());
                String descricao = textDescricao.getText();

                Manutencao manutencao = new Manutencao(idManutencao, dataManutencao, tipoManutencao, valorManutencao, descricao);
                manutencaoBanco.incluir(manutencao);

                MessageBox box = new MessageBox(shell, SWT.OK);
                box.setMessage("Manutenção cadastrada com sucesso!");
                box.open();
            }
        });
    }
}
