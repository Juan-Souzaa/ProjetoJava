package view;

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
import banco.VeiculoBanco;
import model.Veiculo;

public class ViewSelecionarVeiculo {

    protected Shell shell;
    private Table table;
    private VeiculoBanco veiculoBanco;
    private Veiculo veiculoSelecionado;

    public ViewSelecionarVeiculo() {
        veiculoBanco = new VeiculoBanco();
    }

    public Veiculo open() {
        Display display = Display.getDefault();
        createContents();
        shell.open();
        shell.layout();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        return veiculoSelecionado; // Retorna o veículo selecionado ao fechar a janela
    }
    public static void main(String[] args) {
		try {
			ViewSelecionarVeiculo window = new ViewSelecionarVeiculo();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    protected void createContents() {
        shell = new Shell();
        shell.setSize(600, 400);
        shell.setText("Selecionar Veículo");

        table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
        table.setBounds(10, 10, 580, 300);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        TableColumn tblclmnId = new TableColumn(table, SWT.NONE);
        tblclmnId.setWidth(100);
        tblclmnId.setText("ID");

        TableColumn tblclmnPlaca = new TableColumn(table, SWT.NONE);
        tblclmnPlaca.setWidth(150);
        tblclmnPlaca.setText("Placa");

      

        TableColumn tblclmnCor = new TableColumn(table, SWT.NONE);
        tblclmnCor.setWidth(100);
        tblclmnCor.setText("Cor");
        
        TableColumn tblclmnCategoria = new TableColumn(table, SWT.NONE);
        tblclmnCategoria.setWidth(100);
        tblclmnCategoria.setText("Categoria");

        Button btnSelecionar = new Button(shell, SWT.NONE);
        btnSelecionar.setBounds(10, 320, 120, 30);
        btnSelecionar.setText("Selecionar");

        // Preencher a tabela com os veículos do banco
        List<Veiculo> veiculos = veiculoBanco.listar();
        for (Veiculo veiculo : veiculos) {
            TableItem item = new TableItem(table, SWT.NONE);
            item.setText(new String[]{
                String.valueOf(veiculo.getIdVeiculo()),
                veiculo.getPlaca(),
                veiculo.getCor(),
                veiculo.getCategoria()
                
            });
        }

        // Ação do botão selecionar
        btnSelecionar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                int selectedIndex = table.getSelectionIndex();
                if (selectedIndex >= 0) {
                    TableItem selectedItem = table.getItem(selectedIndex);
                    veiculoSelecionado = new Veiculo();
                    veiculoSelecionado.setIdVeiculo(Integer.parseInt(selectedItem.getText(0)));
                    veiculoSelecionado.setPlaca(selectedItem.getText(1));
                    
                    veiculoSelecionado.setCor(selectedItem.getText(2));
                    veiculoSelecionado.setCategoria(selectedItem.getText(3));
                    shell.dispose(); // Fecha a janela após a seleção
                } else {
                    // Exibe mensagem se nada foi selecionado
                    MessageBox box = new MessageBox(shell, SWT.ICON_WARNING);
                    box.setMessage("Selecione um veículo antes de continuar!");
                    box.open();
                }
            }
        });
    }
}
