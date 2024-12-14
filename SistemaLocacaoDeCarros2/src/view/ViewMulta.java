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

import banco.MultaBanco;
import model.Multa;

public class ViewMulta {

    protected Shell shell;
    private Text textIdMulta;
    private Text textMotivo;
    private Text textValorMulta;
    private Text textStatusMulta;
    private Text textObeservacoes;
    private Table table;
    private MultaBanco multaBanco;

    public ViewMulta() {
        multaBanco = new MultaBanco();
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
        shell.setSize(819, 573);
        shell.setText("Multa");

        Label lblIdMulta = new Label(shell, SWT.NONE);
        lblIdMulta.setText("ID Multa:");
        lblIdMulta.setBounds(10, 13, 59, 15);

        textIdMulta = new Text(shell, SWT.BORDER);
        textIdMulta.setBounds(73, 10, 110, 21);

        Label lblMotivo = new Label(shell, SWT.NONE);
        lblMotivo.setText("Motivo:");
        lblMotivo.setBounds(10, 42, 46, 15);

        textMotivo = new Text(shell, SWT.BORDER);
        textMotivo.setBounds(57, 37, 242, 51);

        Label lblValorDaMulta = new Label(shell, SWT.NONE);
        lblValorDaMulta.setText("Valor da Multa:");
        lblValorDaMulta.setBounds(10, 111, 86, 15);

        textValorMulta = new Text(shell, SWT.BORDER);
        textValorMulta.setBounds(102, 108, 92, 21);

        Label lblDataDaOcorrncia = new Label(shell, SWT.NONE);
        lblDataDaOcorrncia.setText("Data da Ocorrência :");
        lblDataDaOcorrncia.setBounds(10, 150, 114, 15);

        DateTime dateTimeDataOcorrencia = new DateTime(shell, SWT.BORDER);
        dateTimeDataOcorrencia.setBounds(127, 141, 80, 24);

        Label lblStatusDaMulta = new Label(shell, SWT.NONE);
        lblStatusDaMulta.setText("Status da Multa:");
        lblStatusDaMulta.setBounds(10, 184, 86, 15);

        textStatusMulta = new Text(shell, SWT.BORDER);
        textStatusMulta.setBounds(102, 181, 81, 21);

        Label lblObeservacoes = new Label(shell, SWT.NONE);
        lblObeservacoes.setText("Obeservações:");
        lblObeservacoes.setBounds(10, 225, 81, 15);

        textObeservacoes = new Text(shell, SWT.BORDER);
        textObeservacoes.setBounds(102, 222, 209, 44);

        Button btnCadastrarMulta = new Button(shell, SWT.NONE);
        btnCadastrarMulta.setText("Cadastrar Multa");
        btnCadastrarMulta.setBounds(57, 286, 150, 25);

        Button btnDeletarMulta = new Button(shell, SWT.NONE);
        btnDeletarMulta.setText("Deletar Multa");
        btnDeletarMulta.setBounds(339, 286, 134, 25);

        Button btnConsultarMulta = new Button(shell, SWT.NONE);
        btnConsultarMulta.setText("Consultar Multa");
        btnConsultarMulta.setBounds(597, 286, 150, 25);

        table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
        table.setLinesVisible(true);
        table.setHeaderVisible(true);
        table.setBounds(10, 329, 789, 205);

        TableColumn tblclmnIdMulta = new TableColumn(table, SWT.CENTER);
        tblclmnIdMulta.setWidth(79);
        tblclmnIdMulta.setText("ID Multa");

        TableColumn tblclmnMotivo = new TableColumn(table, SWT.CENTER);
        tblclmnMotivo.setWidth(122);
        tblclmnMotivo.setText("Motivo");

        TableColumn tblclmnValorMulta = new TableColumn(table, SWT.CENTER);
        tblclmnValorMulta.setWidth(106);
        tblclmnValorMulta.setText("Valor da Multa");

        TableColumn tblclmnDataOcorrencia = new TableColumn(table, SWT.CENTER);
        tblclmnDataOcorrencia.setWidth(138);
        tblclmnDataOcorrencia.setText("Data da Ocorrência");

        TableColumn tblclmnStatusMulta = new TableColumn(table, SWT.CENTER);
        tblclmnStatusMulta.setWidth(97);
        tblclmnStatusMulta.setText("Status da Multa");

        TableColumn tblclmnObeservacoes = new TableColumn(table, SWT.CENTER);
        tblclmnObeservacoes.setWidth(248);
        tblclmnObeservacoes.setText("Obeservações");


        btnCadastrarMulta.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                String motivo = textMotivo.getText();
                double valorMulta = Double.parseDouble(textValorMulta.getText());
                LocalDate dataOcorrencia = LocalDate.of(dateTimeDataOcorrencia.getYear(), dateTimeDataOcorrencia.getMonth() + 1, dateTimeDataOcorrencia.getDay());
                String statusMulta = textStatusMulta.getText();
                String observacoes = textObeservacoes.getText();

                Multa multa = new Multa(motivo, valorMulta, dataOcorrencia, statusMulta, observacoes);
                multaBanco.incluir(multa);
                MessageBox box = new MessageBox(shell, SWT.OK);
                box.setMessage("Multa cadastrada com sucesso!");
                box.open();
            }
        });

       
        btnDeletarMulta.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                try {
                    int idMulta = Integer.parseInt(textIdMulta.getText());
                    multaBanco.deletar(idMulta);
                    MessageBox box = new MessageBox(shell, SWT.OK);
                    box.setMessage("Multa deletada com sucesso!");
                    box.open();
                } catch (NumberFormatException ex) {
                    MessageBox box = new MessageBox(shell, SWT.ERROR);
                    box.setMessage("ID inválido.");
                    box.open();
                }
            }
        });

   
        btnConsultarMulta.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                List<Multa> multas = multaBanco.listar();
                table.removeAll();
                for (Multa multa : multas) {
                    TableItem item = new TableItem(table, SWT.NONE);
                    item.setText(new String[] {
                        String.valueOf(multa.getIdMulta()),
                        multa.getMotivo(),
                        String.valueOf(multa.getValorMulta()),
                        multa.getDataOcorrencia().toString(),
                        multa.getStatusMulta(),
                        multa.getObservacoes()
                    });
                }
            }
        });
    }
}
