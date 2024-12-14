package view;

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

import banco.ReservaBanco;
import model.Reserva;

public class ViewReserva {

    protected Shell shell;
    private Text textReserva;
    private Text textStatusReserva;
    private Text textObservacoes;
    private Text textVeiculoReservado;
    private Table table;
    private ReservaBanco reservaBanco;

    public ViewReserva() {
        reservaBanco = new ReservaBanco();
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
        shell.setSize(949, 737);
        shell.setText("Reserva");

        Label lblIdReserva = new Label(shell, SWT.NONE);
        lblIdReserva.setText("ID Reserva:");
        lblIdReserva.setBounds(10, 13, 81, 15);

        textReserva = new Text(shell, SWT.BORDER);
        textReserva.setBounds(97, 10, 110, 21);

        Label lblDataDaReserva = new Label(shell, SWT.NONE);
        lblDataDaReserva.setText("Data da Reserva:");
        lblDataDaReserva.setBounds(10, 57, 92, 15);

        Label lblStatusDaReserva = new Label(shell, SWT.NONE);
        lblStatusDaReserva.setText("Status da Reserva:");
        lblStatusDaReserva.setBounds(10, 93, 110, 15);

        textStatusReserva = new Text(shell, SWT.BORDER);
        textStatusReserva.setBounds(125, 90, 92, 21);

        DateTime dateTimeDataRetirada = new DateTime(shell, SWT.BORDER);
        dateTimeDataRetirada.setBounds(115, 133, 80, 24);

        Label lblDataDaRetirada = new Label(shell, SWT.NONE);
        lblDataDaRetirada.setText("Data da Retirada:");
        lblDataDaRetirada.setBounds(10, 142, 101, 15);

        Label lblDataDaDevolucao = new Label(shell, SWT.NONE);
        lblDataDaDevolucao.setText("Data da Devolução:");
        lblDataDaDevolucao.setBounds(10, 179, 121, 15);

        Label lblObservacoes = new Label(shell, SWT.NONE);
        lblObservacoes.setText("Observações:");
        lblObservacoes.setBounds(10, 214, 81, 15);

        textObservacoes = new Text(shell, SWT.BORDER);
        textObservacoes.setBounds(97, 211, 209, 44);

        Button btnCadastrarReserva = new Button(shell, SWT.NONE);
        btnCadastrarReserva.setText("Cadastrar Reserva");
        btnCadastrarReserva.setBounds(57, 325, 150, 25);

        Button btnDeletarReserva = new Button(shell, SWT.NONE);
        btnDeletarReserva.setText("Deletar Reserva");
        btnDeletarReserva.setBounds(342, 325, 134, 25);

        Button btnConsultarReserva = new Button(shell, SWT.NONE);
        btnConsultarReserva.setText("Consultar Reserva");
        btnConsultarReserva.setBounds(604, 325, 150, 25);

        DateTime dateTimeDataReserva = new DateTime(shell, SWT.BORDER);
        dateTimeDataReserva.setBounds(107, 48, 80, 24);

        DateTime dateTimeDataDevolucao = new DateTime(shell, SWT.BORDER);
        dateTimeDataDevolucao.setBounds(137, 170, 80, 24);

        Label lblVeiculoReservado = new Label(shell, SWT.NONE);
        lblVeiculoReservado.setText("Veículo Reservado:");
        lblVeiculoReservado.setBounds(10, 273, 110, 15);

        textVeiculoReservado = new Text(shell, SWT.BORDER);
        textVeiculoReservado.setBounds(123, 270, 162, 21);

        table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
        table.setLinesVisible(true);
        table.setHeaderVisible(true);
        table.setBounds(10, 377, 913, 311);

        TableColumn tblclmnIdReserva = new TableColumn(table, SWT.CENTER);
        tblclmnIdReserva.setWidth(106);
        tblclmnIdReserva.setText("ID Reserva");

        TableColumn tblclmnDataReserva = new TableColumn(table, SWT.CENTER);
        tblclmnDataReserva.setWidth(116);
        tblclmnDataReserva.setText("Data da Reserva");

        TableColumn tblclmnStatusReserva = new TableColumn(table, SWT.CENTER);
        tblclmnStatusReserva.setWidth(138);
        tblclmnStatusReserva.setText("Status da Reserva");

        TableColumn tblclmnDataRetirada = new TableColumn(table, SWT.CENTER);
        tblclmnDataRetirada.setWidth(122);
        tblclmnDataRetirada.setText("Data da Retirada");

        TableColumn tblclmnDataDevolucao = new TableColumn(table, SWT.CENTER);
        tblclmnDataDevolucao.setWidth(129);
        tblclmnDataDevolucao.setText("Data da Devolução");

        TableColumn tblclmnObservacoes = new TableColumn(table, SWT.CENTER);
        tblclmnObservacoes.setWidth(172);
        tblclmnObservacoes.setText("Observações");

        TableColumn tblclmnVeiculoReservado = new TableColumn(table, SWT.CENTER);
        tblclmnVeiculoReservado.setWidth(127);
        tblclmnVeiculoReservado.setText("Veículo Reservado");

    
        btnCadastrarReserva.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
               
                String statusReserva = textStatusReserva.getText();
                String observacoes = textObservacoes.getText();
                String veiculoReservado = textVeiculoReservado.getText();
                Reserva reserva = new Reserva(statusReserva, observacoes, veiculoReservado);
                reservaBanco.incluir(reserva);
                MessageBox box = new MessageBox(shell, SWT.OK);
                box.setMessage("Reserva cadastrada com sucesso!");
                box.open();
            }
        });

    
        btnDeletarReserva.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                try {
                    int idReserva = Integer.parseInt(textReserva.getText());
                    reservaBanco.deletar(idReserva);
                    MessageBox box = new MessageBox(shell, SWT.OK);
                    box.setMessage("Reserva deletada com sucesso!");
                    box.open();
                } catch (NumberFormatException ex) {
                    MessageBox box = new MessageBox(shell, SWT.ERROR);
                    box.setMessage("ID inválido.");
                    box.open();
                }
            }
        });

        
        btnConsultarReserva.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                List<Reserva> reservas = reservaBanco.listar();
                table.removeAll();
                for (Reserva reserva : reservas) {
                    TableItem item = new TableItem(table, SWT.NONE);
                    item.setText(new String[] {
                        String.valueOf(reserva.getIdReserva()),
                        reserva.getDataReserva().toString(),
                        reserva.getStatusReserva(),
                        reserva.getDataRetirada().toString(),
                        reserva.getDataDevolucao().toString(),
                        reserva.getObservacoes(),
                        
                    });
                }
            }
        });
    }
}
