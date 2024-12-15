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

import banco.ReservaBanco;
import model.Cliente;
import model.Modelo;
import model.Reserva;

public class ViewReserva {

    protected Shell shell;
   
    private Text textStatusReserva;
    private Text textObservacoes;
   
    private Table table;
    private ReservaBanco reservaBanco;
    private Cliente clienteSelecionado;
    private Modelo modeloSelecionado;

    public ViewReserva() {
        reservaBanco = new ReservaBanco();
    }
    public static void main(String[] args) {
		try {
			ViewReserva window = new ViewReserva();
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
        shell.setSize(1006, 736);
        shell.setText("Reserva");
        
    

        Button btnSelecionarCliente = new Button(shell, SWT.NONE);
        btnSelecionarCliente.setBounds(205, 43, 150, 30);
        btnSelecionarCliente.setText("Selecionar Cliente");

        Label lblClienteSelecionado = new Label(shell, SWT.NONE);
        lblClienteSelecionado.setBounds(10, 51, 193, 30);
        lblClienteSelecionado.setText("Cliente: Nenhum selecionado");

        btnSelecionarCliente.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ViewSelecionarCliente viewClienteSelecionar = new ViewSelecionarCliente(); // Crie a instância da ViewCliente
                viewClienteSelecionar.open(); // Abre a janela de seleção
                clienteSelecionado = viewClienteSelecionar.getClienteSelecionado(); // Obtém o cliente selecionado
                if (clienteSelecionado != null) {
                    lblClienteSelecionado.setText("Cliente: " + clienteSelecionado.getNomeCompleto());
                }
            }
        });
        
        Button btnSelecionarModelo = new Button(shell, SWT.NONE);
        btnSelecionarModelo.setBounds(10, 265, 150, 25);
        btnSelecionarModelo.setText("Selecionar Modelo");

        Label lblModeloSelecionado = new Label(shell, SWT.NONE);
        lblModeloSelecionado.setBounds(184, 265, 200, 25);
        lblModeloSelecionado.setText("Nenhum modelo selecionado");

        // Ação do botão selecionar modelo
        btnSelecionarModelo.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ViewSelecionarModelo viewSelecionarModelo = new ViewSelecionarModelo();
                modeloSelecionado = viewSelecionarModelo.open();
                if (modeloSelecionado != null) {
                    lblModeloSelecionado.setText("Modelo: " + modeloSelecionado.getNomeModelo());
                    
                }
            }
        });

        Label lblStatusDaReserva = new Label(shell, SWT.NONE);
        lblStatusDaReserva.setText("Status da Reserva:");
        lblStatusDaReserva.setBounds(9, 98, 110, 15);

        textStatusReserva = new Text(shell, SWT.BORDER);
        textStatusReserva.setBounds(125, 95, 92, 21);

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

        Button btnListarReserva = new Button(shell, SWT.NONE);
        btnListarReserva.setText("Consultar Reserva");
        btnListarReserva.setBounds(604, 325, 150, 25);

     

        DateTime dateTimeDataDevolucao = new DateTime(shell, SWT.BORDER);
        dateTimeDataDevolucao.setBounds(137, 170, 80, 24);

    

        table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
        table.setLinesVisible(true);
        table.setHeaderVisible(true);
        table.setBounds(10, 377, 970, 311);

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
        
        TableColumn tblclmnNomeCompleto = new TableColumn(table, SWT.NONE);
        tblclmnNomeCompleto.setWidth(100);
        tblclmnNomeCompleto.setText("Nome Completo");
        
        TableColumn tblclmnModelo = new TableColumn(table, SWT.NONE);
        tblclmnModelo.setWidth(100);
        tblclmnModelo.setText("Modelo");


    
        btnCadastrarReserva.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            	if (clienteSelecionado == null || modeloSelecionado == null) {
            	    MessageBox box = new MessageBox(shell, SWT.ICON_WARNING);
            	    box.setMessage("Selecione um cliente e um modelo antes de cadastrar a reserva.");
            	    box.open();
            	    return;
            	}
                
                
                 
                    
                    
                
                

                try {
                    LocalDate dataReserva = LocalDate.now();
                    String statusReserva = textStatusReserva.getText();
                    LocalDate dataRetirada = LocalDate.of(dateTimeDataRetirada.getYear(), dateTimeDataRetirada.getMonth() + 1, dateTimeDataRetirada.getDay());
                    LocalDate dataDevolucao = LocalDate.of(dateTimeDataDevolucao.getYear(), dateTimeDataDevolucao.getMonth() + 1, dateTimeDataDevolucao.getDay());
                    String observacoes = textObservacoes.getText();
                    

                    Reserva reserva = new Reserva( dataReserva, statusReserva, dataRetirada, dataDevolucao, observacoes, clienteSelecionado,modeloSelecionado);
                    reservaBanco.incluir(reserva);

                    MessageBox box = new MessageBox(shell, SWT.OK);
                    box.setMessage("Reserva cadastrada com sucesso!");
                    box.open();
                } catch (Exception ex) {
                    MessageBox box = new MessageBox(shell, SWT.ICON_ERROR);
                    box.setMessage("Erro ao cadastrar reserva: " + ex.getMessage());
                    box.open();
                }
            }
        });

    
        btnDeletarReserva.addSelectionListener(new SelectionAdapter() {
        	@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] selectedItems = table.getSelection();

				if (selectedItems.length == 0) {
					MessageBox warningBox = new MessageBox(shell, SWT.ICON_WARNING);
					warningBox.setMessage("Selecione uma Reserva na tabela para deletar.");
					warningBox.open();
					return;
				}

				try {

					Integer idReserva = Integer.parseInt(selectedItems[0].getText(0));
					
					reservaBanco.deletar(idReserva);
					MessageBox successBox = new MessageBox(shell, SWT.ICON_INFORMATION);
					successBox.setMessage("Reserva deletada com sucesso!");
					successBox.open();

				

					btnListarReserva.notifyListeners(SWT.Selection, null);

				} catch (NumberFormatException ex) {
					MessageBox errorBox = new MessageBox(shell, SWT.ICON_ERROR);
					errorBox.setMessage("ID do Usuario inválido: " + ex.getMessage());
					errorBox.open();
				} catch (Exception ex) {
					MessageBox errorBox = new MessageBox(shell, SWT.ICON_ERROR);
					errorBox.setMessage("Erro ao deletar Usuario: " + ex.getMessage());
					errorBox.open();
				}
			}
		});
        
        btnListarReserva.addSelectionListener(new SelectionAdapter() {
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
                        reserva.getClienteReserva().getNomeCompleto(),
                        reserva.getModeloReserva().getNomeModelo(),
                        
                    });
                }
            }
        });
    }
}
