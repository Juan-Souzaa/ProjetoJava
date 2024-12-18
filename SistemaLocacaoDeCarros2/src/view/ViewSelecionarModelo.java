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
import banco.ModeloBanco;
import model.Modelo;


public class ViewSelecionarModelo {

    protected Shell shell;
    private Table table;
    private ModeloBanco modeloBanco;
    private Modelo modeloSelecionado;

    public ViewSelecionarModelo() {
        modeloBanco = new ModeloBanco();
    }
    
    public static void main(String[] args) {
		try {
			ViewSelecionarModelo window = new ViewSelecionarModelo();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    public Modelo open() {
        Display display = Display.getDefault();
        createContents();
        shell.open();
        shell.layout();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        return modeloSelecionado; // Retorna o modelo selecionado ao fechar a janela
    }

    protected void createContents() {
        shell = new Shell();
        shell.setSize(600, 400);
        shell.setText("Selecionar Modelo de Veículo");

        table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
        table.setBounds(10, 10, 580, 300);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);

  

        TableColumn tblclmnNome = new TableColumn(table, SWT.NONE);
        tblclmnNome.setWidth(150);
        tblclmnNome.setText("Id");

        TableColumn tblclmnCategoria = new TableColumn(table, SWT.NONE);
        tblclmnCategoria.setWidth(150);
        tblclmnCategoria.setText("Nome Modelo");

        TableColumn tblclmnValorDiaria = new TableColumn(table, SWT.NONE);
        tblclmnValorDiaria.setWidth(100);
        tblclmnValorDiaria.setText("Categoria");
        
        TableColumn tblclmnValorDiaria_1 = new TableColumn(table, SWT.NONE);
        tblclmnValorDiaria_1.setWidth(100);
        tblclmnValorDiaria_1.setText("Valor Diaria");

        Button btnSelecionar = new Button(shell, SWT.NONE);
        btnSelecionar.setBounds(10, 320, 120, 30);
        btnSelecionar.setText("Selecionar");

        // Preencher a tabela com os modelos do banco
        List<Modelo> modelos = modeloBanco.listar();
        for (Modelo modelo : modelos) {
            TableItem item = new TableItem(table, SWT.NONE);
            item.setText(new String[]{
                modelo.getIdModelo().toString(),
                modelo.getNomeModelo(),
                modelo.getCategoria(),
                String.valueOf(modelo.getValorDiaria())
            });
        }

        // Ação do botão selecionar
        btnSelecionar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                int selectedIndex = table.getSelectionIndex();
                if (selectedIndex >= 0) {
                    TableItem selectedItem = table.getItem(selectedIndex);
                    modeloSelecionado = new Modelo();
                    modeloSelecionado.setIdModelo(Integer.parseInt(selectedItem.getText(0)));
                    
                    modeloSelecionado.setNomeModelo(selectedItem.getText(1));
                    modeloSelecionado.setCategoria(selectedItem.getText(2));
                    modeloSelecionado.setValorDiaria(Double.parseDouble(selectedItem.getText(3)));
                    shell.dispose(); // Fecha a janela após a seleção
                } else {
                    // Exibe mensagem se nada foi selecionado
                    MessageBox box = new MessageBox(shell, SWT.ICON_WARNING);
                    box.setMessage("Selecione um modelo antes de continuar!");
                    box.open();
                }
            }
        });
    }
}
