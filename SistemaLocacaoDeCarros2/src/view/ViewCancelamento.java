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

import banco.CancelamentoBanco;
import model.Cancelamento;

public class ViewCancelamento {
    private CancelamentoBanco cancelamentoBanco;
    protected Shell shell;
    private Table table;
    private Text txtMotivo;

    public ViewCancelamento() {
        this.cancelamentoBanco = new CancelamentoBanco();
    }

    public static void main(String[] args) {
        try {
            ViewCancelamento window = new ViewCancelamento();
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
        shell.setSize(700, 500);
        shell.setText("Cancelamento - Locadora");

        Label lblMotivo = new Label(shell, SWT.NONE);
        lblMotivo.setBounds(20, 30, 150, 20);
        lblMotivo.setText("Motivo do Cancelamento:");

        txtMotivo = new Text(shell, SWT.BORDER);
        txtMotivo.setBounds(200, 30, 300, 20);

        Label lblData = new Label(shell, SWT.NONE);
        lblData.setBounds(20, 70, 150, 20);
        lblData.setText("Data do Cancelamento:");

        DateTime dateCancelamento = new DateTime(shell, SWT.BORDER | SWT.DATE | SWT.DROP_DOWN);
        dateCancelamento.setBounds(200, 70, 150, 25);

        Button btnCadastrar = new Button(shell, SWT.NONE);
        btnCadastrar.setBounds(20, 120, 100, 30);
        btnCadastrar.setText("Cadastrar");

        Button btnConsultar = new Button(shell, SWT.NONE);
        btnConsultar.setBounds(140, 120, 100, 30);
        btnConsultar.setText("Consultar");

        table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
        table.setBounds(20, 170, 650, 250);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        
        TableColumn colMotivo = new TableColumn(table, SWT.NONE);
        colMotivo.setWidth(300);
        colMotivo.setText("Motivo");

        TableColumn colData = new TableColumn(table, SWT.NONE);
        colData.setWidth(300);
        colData.setText("Data");

     
        btnCadastrar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                String motivo = txtMotivo.getText();
                LocalDate data = LocalDate.of(
                        dateCancelamento.getYear(),
                        dateCancelamento.getMonth() + 1,
                        dateCancelamento.getDay()
                );

                Cancelamento cancelamento = new Cancelamento(motivo, data);
                cancelamentoBanco.incluir(cancelamento);

                MessageBox messageBox = new MessageBox(shell, SWT.OK);
                messageBox.setMessage("Cancelamento cadastrado com sucesso!");
                messageBox.open();
            }
        });

       
        btnConsultar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                List<Cancelamento> cancelamentos = cancelamentoBanco.listar();
                table.removeAll();
                for (Cancelamento cancelamento : cancelamentos) {
                    TableItem item = new TableItem(table, SWT.NONE);
                    item.setText(new String[] {
                            cancelamento.getMotivoCancelamento(),
                            cancelamento.getDataCancelamento().toString()
                    });
                }
            }
        });
    }
}
