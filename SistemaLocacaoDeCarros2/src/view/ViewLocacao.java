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

import banco.LocacaoBanco;
import model.Locacao;
import model.Reserva;
import model.Veiculo;

public class ViewLocacao {

    protected Shell shell;
  
    private Text txtValorTotal;
    private Text txtTipoLocacao;
    private Table table;
    private LocacaoBanco locacaoBanco;
    private Veiculo veiculoSelecionado;
    private Reserva reservaSelecionada;
    private Text txtObservacoes;

    public ViewLocacao() {
        locacaoBanco = new LocacaoBanco();
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
            ViewLocacao window = new ViewLocacao();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    protected void createContents() {
        shell = new Shell();
        shell.setSize(1112, 987);
        shell.setText("Locação");
        
        Button btnSelecionarReserva = new Button(shell, SWT.NONE);
        btnSelecionarReserva.setBounds(234, 27, 150, 30);
        btnSelecionarReserva.setText("Selecionar Reserva");

        Label lblClienteSelecionado = new Label(shell, SWT.NONE);
        lblClienteSelecionado.setBounds(6, 27, 193, 30);
        lblClienteSelecionado.setText("Reserva do Cliente: Nenhum selecionado");

        btnSelecionarReserva.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ViewSelecionarReserva viewSelecionarReserva = new ViewSelecionarReserva(); // Crie a instância da ViewCliente
                reservaSelecionada =viewSelecionarReserva.open(); // Abre a janela de seleção
                  // Obtém o cliente selecionado
                if (reservaSelecionada != null) {
                    lblClienteSelecionado.setText("Reserva do Cliente:" + reservaSelecionada.getClienteReserva().getNomeCompleto());
                }
            }
        });
        
        
        Button btnSelecionarVeiculo = new Button(shell, SWT.NONE);
        btnSelecionarVeiculo.setBounds(6, 359, 150, 25);
        btnSelecionarVeiculo.setText("Selecionar Veículo");

        Label lblVeiculoSelecionado = new Label(shell, SWT.NONE);
        lblVeiculoSelecionado.setBounds(184, 359, 200, 25);
        lblVeiculoSelecionado.setText("Nenhum veículo selecionado");

        btnSelecionarVeiculo.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ViewSelecionarVeiculo viewSelecionarVeiculo = new ViewSelecionarVeiculo();
                veiculoSelecionado = viewSelecionarVeiculo.open();

                // Verificar se a reserva foi selecionada
                if (reservaSelecionada == null) {
                    MessageBox box = new MessageBox(shell, SWT.ICON_WARNING);
                    box.setMessage("Nenhuma reserva foi selecionada.");
                    box.open();
                    return;
                }

                // Verificar se o modelo da reserva é válido
                if (reservaSelecionada.getModeloReserva() == null) {
                    MessageBox box = new MessageBox(shell, SWT.ICON_WARNING);
                    box.setMessage("O modelo da reserva não está associado.");
                    box.open();
                    return;
                }

                // Verificar se o veículo pertence à mesma categoria do modelo da reserva
                if (veiculoSelecionado != null && 
                    !veiculoSelecionado.getCategoria().equals(reservaSelecionada.getModeloReserva().getCategoria())) {
                    MessageBox box = new MessageBox(shell, SWT.ICON_WARNING);
                    box.setMessage("Selecione um veículo da categoria escolhida.");
                    box.open();
                    return;
                }

                // Exibir o veículo selecionado
                if (veiculoSelecionado != null) {
                    lblVeiculoSelecionado.setText("Veículo: " + veiculoSelecionado.getPlaca());
                }
            }
        });


      
        Label lblDataLocacao = new Label(shell, SWT.NONE);
        lblDataLocacao.setBounds(27, 73, 85, 15);
        lblDataLocacao.setText("Data da locação");

        DateTime dateLocacao = new DateTime(shell, SWT.BORDER);
        dateLocacao.setBounds(118, 73, 80, 24);

        Label lblDataDevolucaoPrevista = new Label(shell, SWT.NONE);
        lblDataDevolucaoPrevista.setBounds(27, 123, 158, 15);
        lblDataDevolucaoPrevista.setText("Data prevista para devolução");

        DateTime dateDevolucaoPrevista = new DateTime(shell, SWT.BORDER);
        dateDevolucaoPrevista.setBounds(191, 123, 80, 24);

        Label lblDataDevolucao = new Label(shell, SWT.NONE);
        lblDataDevolucao.setBounds(27, 175, 105, 15);
        lblDataDevolucao.setText("Data da devolução");

        DateTime dateDevolucao = new DateTime(shell, SWT.BORDER);
        dateDevolucao.setBounds(138, 175, 80, 24);

        Label lblValorTotal = new Label(shell, SWT.NONE);
        lblValorTotal.setBounds(27, 234, 68, 15);
        lblValorTotal.setText("Valor total");

        txtValorTotal = new Text(shell, SWT.BORDER);
        txtValorTotal.setBounds(118, 234, 153, 21);

        Label lblTipoLocacao = new Label(shell, SWT.NONE);
        lblTipoLocacao.setBounds(27, 289, 85, 15);
        lblTipoLocacao.setText("Tipo de locação");

        txtTipoLocacao = new Text(shell, SWT.BORDER);
        txtTipoLocacao.setBounds(118, 289, 153, 21);

        Button btnCadastrarLocacao = new Button(shell, SWT.NONE);
        btnCadastrarLocacao.setBounds(74, 406, 75, 25);
        btnCadastrarLocacao.setText("Cadastrar");

        Button btnDeletarLocacao = new Button(shell, SWT.NONE);
        btnDeletarLocacao.setBounds(221, 406, 105, 25);
        btnDeletarLocacao.setText("Deletar locação");

        Button btnListarLocacao = new Button(shell, SWT.NONE);
        btnListarLocacao.setBounds(385, 406, 105, 25);
        btnListarLocacao.setText("Consultar locação");

        table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
        table.setBounds(41, 463, 1045, 269);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        TableColumn tblclmnIdLocacao = new TableColumn(table, SWT.NONE);
        tblclmnIdLocacao.setWidth(100);
        tblclmnIdLocacao.setText("ID da locação");
        
        TableColumn tblclmnNomeCliente = new TableColumn(table, SWT.NONE);
        tblclmnNomeCliente.setWidth(100);
        tblclmnNomeCliente.setText("Nome Cliente");

        TableColumn tblclmnDataLocacao = new TableColumn(table, SWT.NONE);
        tblclmnDataLocacao.setWidth(100);
        tblclmnDataLocacao.setText("Data da locação");

        TableColumn tblclmnDataDevolucaoPrevista = new TableColumn(table, SWT.NONE);
        tblclmnDataDevolucaoPrevista.setWidth(162);
        tblclmnDataDevolucaoPrevista.setText("Data prevista pra devolução");

        TableColumn tblclmnDataDevolucao = new TableColumn(table, SWT.NONE);
        tblclmnDataDevolucao.setWidth(114);
        tblclmnDataDevolucao.setText("Data da devolução");

        TableColumn tblclmnValorTotal = new TableColumn(table, SWT.CENTER);
        tblclmnValorTotal.setWidth(100);
        tblclmnValorTotal.setText("Valor total");

        TableColumn tblclmnTipoLocacao = new TableColumn(table, SWT.NONE);
        tblclmnTipoLocacao.setWidth(100);
        tblclmnTipoLocacao.setText("Tipo de locação");

        TableColumn tblclmnObservacoes = new TableColumn(table, SWT.NONE);
        tblclmnObservacoes.setWidth(100);
        tblclmnObservacoes.setText("Observações");
        
        TableColumn tblclmnPlaca = new TableColumn(table, SWT.NONE);
        tblclmnPlaca.setWidth(100);
        tblclmnPlaca.setText("Placa");
        
        TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
        tblclmnNewColumn.setWidth(100);
        tblclmnNewColumn.setText("Categoria");
        
        txtObservacoes = new Text(shell, SWT.BORDER);
        txtObservacoes.setBounds(118, 319, 153, 21);
        
        Label lblObservacoes = new Label(shell, SWT.NONE);
        lblObservacoes.setBounds(27, 325, 55, 15);
        lblObservacoes.setText("Observacoes");
        
 
     
        btnCadastrarLocacao.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
               
                LocalDate dataLocacao = LocalDate.of(dateLocacao.getYear(), dateLocacao.getMonth() + 1, dateLocacao.getDay());
                LocalDate dataDevolucaoPrevista = LocalDate.of(dateDevolucaoPrevista.getYear(), dateDevolucaoPrevista.getMonth() + 1, dateDevolucaoPrevista.getDay());
                LocalDate dataDevolucao = LocalDate.of(dateDevolucao.getYear(), dateDevolucao.getMonth() + 1, dateDevolucao.getDay());
                double valorTotal = Double.parseDouble(txtValorTotal.getText());
                String tipoLocacao = txtTipoLocacao.getText();
                String observacoes = txtObservacoes.getText();

                Locacao locacao = new Locacao( dataLocacao, dataDevolucaoPrevista, dataDevolucao, valorTotal, tipoLocacao, observacoes,veiculoSelecionado,reservaSelecionada);
                locacaoBanco.incluir(locacao);

                MessageBox box = new MessageBox(shell, SWT.OK);
                box.setMessage("Locação cadastrada com sucesso!");
                box.open();
            }
        });

        btnListarLocacao.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                List<Locacao> locacoes = locacaoBanco.listar(); // Lista de locações
                table.removeAll(); // Remove itens antigos da tabela
                for (Locacao locacao : locacoes) {
                    // Adicionando nova linha na tabela
                    TableItem item = new TableItem(table, SWT.NONE);
                    
                    // Preenchendo a linha com os dados de locação, reserva, cliente e veiculo
                    item.setText(new String[] {
                        String.valueOf(locacao.getIdLocacao()), // ID da locação
                        locacao.getReservaLocacao().getClienteReserva().getNomeCompleto(), // Nome do cliente (da reserva)
                        locacao.getDataLocacao().toString(), // Data da locação
                        locacao.getDataDevolucaoPrevista().toString(), // Data de devolução prevista
                        locacao.getDataDevolucaoReal() != null ? locacao.getDataDevolucaoReal().toString() : "Não devolvido", // Data de devolução real
                        String.valueOf(locacao.getValorTotal()), // Valor total
                        locacao.getTipoLocacao(), // Tipo de locação
                        locacao.getObservacoes(), // Observações
                        
                        locacao.getVeiculoLocacao().getPlaca(), // Placa do veículo
                        locacao.getVeiculoLocacao().getCategoria() // Categoria do veículo
                    });
                }
            }
        });


        btnDeletarLocacao.addSelectionListener(new SelectionAdapter() {
        	@Override
    			public void widgetSelected(SelectionEvent e) {
    				TableItem[] selectedItems = table.getSelection();

    				if (selectedItems.length == 0) {
    					MessageBox warningBox = new MessageBox(shell, SWT.ICON_WARNING);
    					warningBox.setMessage("Selecione uma Locacao na tabela para deletar.");
    					warningBox.open();
    					return;
    				}

    				try {

    					Integer idLocacao = Integer.parseInt(selectedItems[0].getText(0));
    					locacaoBanco.deletar(idLocacao);
    					MessageBox successBox = new MessageBox(shell, SWT.ICON_INFORMATION);
    					successBox.setMessage("Locacao deletada com sucesso!");
    					successBox.open();

    				

    					btnListarLocacao.notifyListeners(SWT.Selection, null);

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
        





    }
}
