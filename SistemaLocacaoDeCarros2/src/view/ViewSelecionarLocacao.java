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
import banco.LocacaoBanco;
import model.Cliente;
import model.Locacao;
import model.Reserva;
import model.Veiculo;

public class ViewSelecionarLocacao {

    protected Shell shell;
    private Table table;
    private LocacaoBanco locacaoBanco;
    private Locacao locacaoSelecionada;

    public ViewSelecionarLocacao() {
        locacaoBanco = new LocacaoBanco();
    }
    
    public static void main(String[] args) {
		try {
			ViewSelecionarLocacao window = new ViewSelecionarLocacao();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    public Locacao open() {
        Display display = Display.getDefault();
        createContents();
        shell.open();
        shell.layout();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        return locacaoSelecionada; // Retorna a locação selecionada ao fechar a janela
    }

    protected void createContents() {
        shell = new Shell();
        shell.setSize(600, 400);
        shell.setText("Selecionar Locação");

        table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
        table.setBounds(10, 10, 580, 300);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        TableColumn tblclmnId = new TableColumn(table, SWT.NONE);
        tblclmnId.setWidth(100);
        tblclmnId.setText("ID Locação");

        TableColumn tblclmnCliente = new TableColumn(table, SWT.NONE);
        tblclmnCliente.setWidth(150);
        tblclmnCliente.setText("Nome Cliente");

        TableColumn tblclmnVeiculo = new TableColumn(table, SWT.NONE);
        tblclmnVeiculo.setWidth(150);
        tblclmnVeiculo.setText("Marca Veículo");

        TableColumn tblclmnDataInicio = new TableColumn(table, SWT.NONE);
        tblclmnDataInicio.setWidth(100);
        tblclmnDataInicio.setText("Data Locacao");

        TableColumn tblclmnDataFim = new TableColumn(table, SWT.NONE);
        tblclmnDataFim.setWidth(100);
        tblclmnDataFim.setText("Categoria");

        Button btnSelecionar = new Button(shell, SWT.NONE);
        btnSelecionar.setBounds(10, 320, 120, 30);
        btnSelecionar.setText("Selecionar");

        // Preencher a tabela com as locações do banco
        List<Locacao> locacoes = locacaoBanco.listar();
        for (Locacao locacao : locacoes) {
            TableItem item = new TableItem(table, SWT.NONE);
            item.setText(new String[]{
                String.valueOf(locacao.getIdLocacao()),
                locacao.getReservaLocacao().getClienteReserva().getNomeCompleto(),
                locacao.getVeiculoLocacao().getMarca(),
                locacao.getDataLocacao().toString(),
                locacao.getVeiculoLocacao().getCategoria()
                
            });
        }

        // Ação do botão selecionar
        btnSelecionar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                int selectedIndex = table.getSelectionIndex();
                if (selectedIndex >= 0) {
                    TableItem selectedItem = table.getItem(selectedIndex);
                    locacaoSelecionada = new Locacao();
                    locacaoSelecionada.setIdLocacao(Integer.parseInt(selectedItem.getText(0)));
                    locacaoSelecionada.setDataLocacao(LocalDate.parse(selectedItem.getText(3)));
                    
                    Reserva reserva = new Reserva();
                    
                    Cliente cliente = new Cliente();
                    cliente.setNomeCompleto(selectedItem.getText(1));
                    reserva.setClienteReserva(cliente);
                    locacaoSelecionada.setReservaLocacao(reserva);
                    
                    Veiculo veiculo = new Veiculo();
                    veiculo.setMarca(selectedItem.getText(2));
                    
                    veiculo.setCategoria(selectedItem.getText(4));
                    
                    locacaoSelecionada.setVeiculoLocacao(veiculo);
                    
                    
                    // Definir outros atributos da locação selecionada conforme necessário
                    shell.dispose(); // Fecha a janela após a seleção
                } else {
                    // Exibe mensagem se nada foi selecionado
                    MessageBox box = new MessageBox(shell, SWT.ICON_WARNING);
                    box.setMessage("Selecione uma locação antes de continuar!");
                    box.open();
                }
            }
        });
    }
}
