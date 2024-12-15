package view;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import banco.ModeloBanco;
import model.Modelo;
import model.Veiculo;

public class ViewModelo {

    protected Shell shell;
    private Text textModeloVeiculo;
    private Text textCategoria;
    private Text textCapacidadePassageiro;
    private Text textValorDiaria;
    private Text textTipoCombustivel;
    private Text textConsumoMedio;
    private Table table;
    private ModeloBanco modeloBanco;
    private Veiculo veiculoSelecionado;
    
  

  
    
    public ViewModelo() {
        modeloBanco = new ModeloBanco();
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
    public static void main(String[] args) {
		try {
			ViewModelo window = new ViewModelo();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

  
    protected void createContents() {
        shell = new Shell();
        shell.setSize(825, 584);
        shell.setText("Modelo Veículo");
        
        Button btnSelecionarVeiculo = new Button(shell, SWT.NONE);
        btnSelecionarVeiculo.setBounds(10, 265, 150, 25);
        btnSelecionarVeiculo.setText("Selecionar Veículo");

        Label lblVeiculoSelecionado = new Label(shell, SWT.NONE);
        lblVeiculoSelecionado.setBounds(184, 265, 200, 25);
        lblVeiculoSelecionado.setText("Nenhum veículo selecionado");

        // Ação do botão selecionar veículo
        btnSelecionarVeiculo.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ViewSelecionarVeiculo viewSelecionarVeiculo = new ViewSelecionarVeiculo();
                veiculoSelecionado = viewSelecionarVeiculo.open();
                if (veiculoSelecionado != null) {
                    lblVeiculoSelecionado.setText("Veículo: " + veiculoSelecionado.getPlaca());
                    // Aqui você pode associar o objeto veiculoSelecionado ao modelo
                }
            }
        });
    
        Label lblModeloVeiculo = new Label(shell, SWT.NONE);
        lblModeloVeiculo.setText("Modelo do Veículo:");
        lblModeloVeiculo.setBounds(10, 26, 110, 15);

        textModeloVeiculo = new Text(shell, SWT.BORDER);
        textModeloVeiculo.setBounds(126, 23, 134, 21);

        Label lblValorDaDiaria = new Label(shell, SWT.NONE);
        lblValorDaDiaria.setText("Valor da Diaria:");
        lblValorDaDiaria.setBounds(10, 75, 89, 15);

        textValorDiaria = new Text(shell, SWT.BORDER);
        textValorDiaria.setBounds(105, 72, 134, 21);

        Label lblCategoria = new Label(shell, SWT.NONE);
        lblCategoria.setText("Categoria:");
        lblCategoria.setBounds(10, 124, 67, 15);

        textCategoria = new Text(shell, SWT.BORDER);
        textCategoria.setBounds(83, 121, 134, 21);

        Label lblCapacidadeDePassageiros = new Label(shell, SWT.NONE);
        lblCapacidadeDePassageiros.setText("Capacidade de Passageiros :");
        lblCapacidadeDePassageiros.setBounds(10, 163, 150, 15);

        textCapacidadePassageiro = new Text(shell, SWT.BORDER);
        textCapacidadePassageiro.setBounds(166, 160, 81, 21);

        Label lblTipoDeCombustivel = new Label(shell, SWT.NONE);
        lblTipoDeCombustivel.setText("Tipo de combustivel:");
        lblTipoDeCombustivel.setBounds(10, 197, 110, 15);

        textTipoCombustivel = new Text(shell, SWT.BORDER);
        textTipoCombustivel.setBounds(126, 194, 81, 21);

        Label lblConsumoMdioDo = new Label(shell, SWT.NONE);
        lblConsumoMdioDo.setText("Consumo médio:");
        lblConsumoMdioDo.setBounds(10, 238, 99, 15);

        textConsumoMedio = new Text(shell, SWT.BORDER);
        textConsumoMedio.setBounds(107, 235, 81, 21);

        Button btnCadastrarModelo = new Button(shell, SWT.NONE);
        btnCadastrarModelo.setText("Cadastrar Modelo");
        btnCadastrarModelo.setBounds(57, 299, 150, 25);

        Button btnDeletarModelo = new Button(shell, SWT.NONE);
        btnDeletarModelo.setText("Deletar Modelo");
        btnDeletarModelo.setBounds(339, 299, 134, 25);

        Button btnListarModelo = new Button(shell, SWT.NONE);
        btnListarModelo.setText("Listar Modelo");
        btnListarModelo.setBounds(597, 299, 150, 25);

        table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
        table.setLinesVisible(true);
        table.setHeaderVisible(true);
        table.setBounds(10, 330, 789, 205);
        
        TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
        tblclmnNewColumn.setWidth(100);
        tblclmnNewColumn.setText("IdModelo");

        TableColumn tblclmnModeloDoVeiculo = new TableColumn(table, SWT.CENTER);
        tblclmnModeloDoVeiculo.setWidth(127);
        tblclmnModeloDoVeiculo.setText("Modelo do veículo");

        TableColumn tblclmnValorDiaria = new TableColumn(table, SWT.CENTER);
        tblclmnValorDiaria.setWidth(122);
        tblclmnValorDiaria.setText("Valor da Diária");

        TableColumn tblclmnCategoria = new TableColumn(table, SWT.CENTER);
        tblclmnCategoria.setWidth(91);
        tblclmnCategoria.setText("Categoria");

        TableColumn tblclmnCapacidadePassageiros = new TableColumn(table, SWT.CENTER);
        tblclmnCapacidadePassageiros.setText("Capacidade de Passageiros");
        tblclmnCapacidadePassageiros.setWidth(156);

        TableColumn tblclmnTipoCombustivel = new TableColumn(table, SWT.CENTER);
        tblclmnTipoCombustivel.setWidth(137);
        tblclmnTipoCombustivel.setText("Tipo de combustivel");

        TableColumn tblclmnConsumoMedio = new TableColumn(table, SWT.CENTER);
        tblclmnConsumoMedio.setWidth(169);
        tblclmnConsumoMedio.setText("Consumo médio");

        
        btnCadastrarModelo.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            	 if (veiculoSelecionado == null) {
                     MessageBox box = new MessageBox(shell, SWT.ICON_WARNING);
                     box.setMessage("Selecione um Veiculo antes de cadastrar a Modelo.");
                     box.open();
                     return;}
                String modeloVeiculo = textModeloVeiculo.getText();
                double valorDiaria = Double.parseDouble(textValorDiaria.getText());
                String categoria = textCategoria.getText();
                int capacidadePassageiros = Integer.parseInt(textCapacidadePassageiro.getText());
                String tipoCombustivel = textTipoCombustivel.getText();
                double consumoMedio = Double.parseDouble(textConsumoMedio.getText());

                Modelo modelo = new Modelo(modeloVeiculo, valorDiaria, categoria, capacidadePassageiros, tipoCombustivel, consumoMedio, veiculoSelecionado);
                modeloBanco.incluir(modelo);
                MessageBox box = new MessageBox(shell, SWT.OK);
                box.setMessage("Modelo de veículo cadastrado com sucesso!");
                box.open();
            }
        });

        
        btnDeletarModelo.addSelectionListener(new SelectionAdapter() {
        	@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] selectedItems = table.getSelection();

				if (selectedItems.length == 0) {
					MessageBox warningBox = new MessageBox(shell, SWT.ICON_WARNING);
					warningBox.setMessage("Selecione um Usuario na tabela para deletar.");
					warningBox.open();
					return;
				}

				try {

					Integer idModelo = Integer.parseInt(selectedItems[0].getText(0));
					
					modeloBanco.deletar(idModelo);
					MessageBox successBox = new MessageBox(shell, SWT.ICON_INFORMATION);
					successBox.setMessage("Modelo deletado com sucesso!");
					successBox.open();

				

					btnListarModelo.notifyListeners(SWT.Selection, null);

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
     
        btnListarModelo.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            	
                List<Modelo> modelos = modeloBanco.listar();
                table.removeAll();
                for (Modelo modelo : modelos) {
                    TableItem item = new TableItem(table, SWT.NONE);
                    item.setText(new String[] {
                    	modelo.getIdModelo().toString(),
                        modelo.getNomeModelo(),
                        String.valueOf(modelo.getValorDiaria()),
                        modelo.getCategoria(),
                        String.valueOf(modelo.getCapacidadePassageiros()),
                        modelo.getTipoCombustivel(),
                        String.valueOf(modelo.getConsumoMedio())
                    });
                }
            }
        });
    }
}
