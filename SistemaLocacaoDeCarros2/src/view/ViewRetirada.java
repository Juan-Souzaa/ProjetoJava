package view;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

import banco.RetiradaBanco;
import model.Retirada;

public class ViewRetirada {

    protected Shell shell;
    private Text textLocalRetirada;
    private Text textStatusRetirada;
    private Table table;
    private RetiradaBanco retiradaBanco;

    public ViewRetirada() {
        retiradaBanco = new RetiradaBanco();
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
        shell.setSize(1111, 761);
        shell.setText("Retirada");

        Label lblDataDaRetirada = new Label(shell, SWT.NONE);
        lblDataDaRetirada.setText("Data da Retirada:");
        lblDataDaRetirada.setBounds(10, 13, 101, 15);

        Label lblLocalDeRetirada = new Label(shell, SWT.NONE);
        lblLocalDeRetirada.setText("Local de Retirada:");
        lblLocalDeRetirada.setBounds(10, 42, 101, 15);

        textLocalRetirada = new Text(shell, SWT.BORDER);
        textLocalRetirada.setBounds(120, 39, 167, 21);

        Label lblDocumentosDeRetirada = new Label(shell, SWT.NONE);
        lblDocumentosDeRetirada.setText("Documentos Verificados?");
        lblDocumentosDeRetirada.setBounds(10, 78, 139, 15);

        Button btnCadastrarRetirada = new Button(shell, SWT.NONE);
        btnCadastrarRetirada.setText("Cadastrar Retirada");
        btnCadastrarRetirada.setBounds(66, 168, 150, 25);

        Button btnDeletarRetirada = new Button(shell, SWT.NONE);
        btnDeletarRetirada.setText("Deletar Retirada");
        btnDeletarRetirada.setBounds(330, 168, 134, 25);

        Button btnConsultarRetirada = new Button(shell, SWT.NONE);
        btnConsultarRetirada.setText("Consultar Retirada");
        btnConsultarRetirada.setBounds(585, 168, 150, 25);

        DateTime dateTimeDataRetirada = new DateTime(shell, SWT.BORDER);
        dateTimeDataRetirada.setBounds(117, 4, 80, 24);

        Button btnRadioButtonSim = new Button(shell, SWT.RADIO);
        btnRadioButtonSim.setBounds(155, 77, 46, 16);
        btnRadioButtonSim.setText("Sim");

        Button btnRadioButtonNao = new Button(shell, SWT.RADIO);
        btnRadioButtonNao.setBounds(207, 77, 58, 16);
        btnRadioButtonNao.setText("Não");

        Label lblStatusDeRetirada = new Label(shell, SWT.NONE);
        lblStatusDeRetirada.setText("Status de Retirada:");
        lblStatusDeRetirada.setBounds(10, 121, 101, 15);

        textStatusRetirada = new Text(shell, SWT.BORDER);
        textStatusRetirada.setBounds(126, 118, 126, 21);

        table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
        table.setLinesVisible(true);
        table.setHeaderVisible(true);
        table.setBounds(155, 260, 524, 205);

        TableColumn tblclmnDataDaRetirada = new TableColumn(table, SWT.CENTER);
        tblclmnDataDaRetirada.setWidth(106);
        tblclmnDataDaRetirada.setText("Data da Retirada");

        TableColumn tblclmnLocalRetirada = new TableColumn(table, SWT.CENTER);
        tblclmnLocalRetirada.setWidth(124);
        tblclmnLocalRetirada.setText("Local de Retirada");

        TableColumn tblclmnDocumentosVerificados = new TableColumn(table, SWT.CENTER);
        tblclmnDocumentosVerificados.setWidth(141);
        tblclmnDocumentosVerificados.setText("Documentos Verificados");

        TableColumn tblclmnStatusRetirada = new TableColumn(table, SWT.CENTER);
        tblclmnStatusRetirada.setWidth(147);
        tblclmnStatusRetirada.setText("Status de Retirada");

        // Cadastrar Retirada
        btnCadastrarRetirada.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                String localRetirada = textLocalRetirada.getText();
                String statusRetirada = textStatusRetirada.getText();

                // Obtendo data e hora atuais
                LocalDate dataRetirada = LocalDate.now();

                // Criando a retirada
                Retirada retirada = new Retirada(dataRetirada, localRetirada, true, statusRetirada);
                retiradaBanco.incluir(retirada);

                MessageBox box = new MessageBox(shell, SWT.OK);
                box.setMessage("Retirada cadastrada com sucesso!");
                box.open();
            }
        });

        // Deletar Retirada
        btnDeletarRetirada.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                try {
                    int idRetirada = Integer.parseInt(textLocalRetirada.getText());
                    retiradaBanco.deletar(idRetirada);
                    MessageBox box = new MessageBox(shell, SWT.OK);
                    box.setMessage("Retirada deletada com sucesso!");
                    box.open();
                } catch (NumberFormatException ex) {
                    MessageBox box = new MessageBox(shell, SWT.ERROR);
                    box.setMessage("ID inválido.");
                    box.open();
                }
            }
        });

        // Consultar Retirada
        btnConsultarRetirada.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                List<Retirada> retiradas = retiradaBanco.listar();
                table.removeAll();
                for (Retirada retirada : retiradas) {
                    TableItem item = new TableItem(table, SWT.NONE);
                    item.setText(new String[] {
                        String.valueOf(retirada.getIdRetirada()),
                        retirada.getDataRetirada().toString(),
                        retirada.getLocalRetirada(),
                        retirada.getStatusRetirada()
                    });
                }
            }
        });
    }
}
