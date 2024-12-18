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
import banco.ReservaBanco;
import model.Cliente;
import model.Modelo;
import model.Reserva;

public class ViewSelecionarReserva {

    protected Shell shell;
    private Table table;
    private ReservaBanco reservaBanco;
    private Reserva reservaSelecionada;

    public ViewSelecionarReserva() {
        reservaBanco = new ReservaBanco();
    }
    
    public static void main(String[] args) {
		try {
			ViewSelecionarReserva window = new ViewSelecionarReserva();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    public Reserva open() {
        Display display = Display.getDefault();
        createContents();
        shell.open();
        shell.layout();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        return reservaSelecionada; // Retorna a reserva selecionada ao fechar a janela
    }

    protected void createContents() {
        shell = new Shell();
        shell.setSize(1000, 473);
        shell.setText("Selecionar Reserva");

        table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
        table.setBounds(48, 10, 744, 304);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        TableColumn tblclmnIdReserva = new TableColumn(table, SWT.NONE);
        tblclmnIdReserva.setWidth(100);
        tblclmnIdReserva.setText("ID Reserva");

        TableColumn tblclmnNomeCliente = new TableColumn(table, SWT.NONE);
        tblclmnNomeCliente.setWidth(200);
        tblclmnNomeCliente.setText("Nome Cliente");

        TableColumn tblclmnDataReserva = new TableColumn(table, SWT.NONE);
        tblclmnDataReserva.setWidth(150);
        tblclmnDataReserva.setText("Data Reserva");

        TableColumn tblclmnModelo = new TableColumn(table, SWT.NONE);
        tblclmnModelo.setWidth(150);
        tblclmnModelo.setText("Modelo");
        
        TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
        tblclmnNewColumn.setWidth(100);
        tblclmnNewColumn.setText("Categoria");

        Button btnSelecionar = new Button(shell, SWT.NONE);
        btnSelecionar.setBounds(58, 320, 120, 30);
        btnSelecionar.setText("Selecionar");

        // Preencher a tabela com as reservas do banco
        List<Reserva> reservas = reservaBanco.listar();
        for (Reserva reserva : reservas) {
            TableItem item = new TableItem(table, SWT.NONE);
            item.setText(new String[]{
                String.valueOf(reserva.getIdReserva()),
                reserva.getClienteReserva().getNomeCompleto(),
                reserva.getDataReserva().toString(),
                reserva.getModeloReserva().getNomeModelo(),
                reserva.getModeloReserva().getCategoria(),
            });
        }

        // Ação do botão selecionar
        btnSelecionar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                int selectedIndex = table.getSelectionIndex();
                if (selectedIndex >= 0) {
                    TableItem selectedItem = table.getItem(selectedIndex);
                    reservaSelecionada = new Reserva();
                    reservaSelecionada.setIdReserva(Integer.parseInt(selectedItem.getText(0)));
                   
                    reservaSelecionada.setDataReserva((LocalDate.parse(selectedItem.getText(2))));
                    
                    Cliente clienteReserva = new Cliente();
                    clienteReserva.setNomeCompleto(selectedItem.getText(1));
                    
                    reservaSelecionada.setClienteReserva(clienteReserva);
                    
                    Modelo modeloReserva = new Modelo();
                    modeloReserva.setNomeModelo(selectedItem.getText(3));
                    modeloReserva.setCategoria(selectedItem.getText(4));
                    reservaSelecionada.setModeloReserva(modeloReserva);
                    
                    shell.dispose(); // Fecha a janela após a seleção
                } else {
                    // Exibe mensagem se nada foi selecionado
                    MessageBox box = new MessageBox(shell, SWT.ICON_WARNING);
                    box.setMessage("Selecione uma reserva antes de continuar!");
                    box.open();
                }
            }
        });
    }
}
