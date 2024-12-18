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

import banco.PagamentoBanco;
import model.Fatura;
import model.Pagamento;

public class ViewPagamento {

    protected Shell shell;
  
    private Text textMetodoPagamento;
    private Text textStatusPagamento;
    private Text textDescricao;
    private Text textValor;
    private Table table;
    private Fatura faturaSelecionada;
    private PagamentoBanco pagamentoBanco;

    public ViewPagamento() {
        pagamentoBanco = new PagamentoBanco();
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
            ViewPagamento window = new ViewPagamento();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void createContents() {
        shell = new Shell();
        shell.setSize(1158, 795);
        shell.setText("Pagamento");

        Button btnSelecionarFatura = new Button(shell, SWT.NONE);
        btnSelecionarFatura.setBounds(229, 8, 150, 25);
        btnSelecionarFatura.setText("Selecionar Fatura");

        Label lblVeiculoSelecionado = new Label(shell, SWT.NONE);
        lblVeiculoSelecionado.setBounds(23, 13, 200, 25);
        lblVeiculoSelecionado.setText("Nenhuma Fatura selecionada");

        // Ação do botão selecionar veículo
        btnSelecionarFatura.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ViewSelecionarFatura viewSelecionarFatura = new ViewSelecionarFatura();
                faturaSelecionada = viewSelecionarFatura.open();
                if (faturaSelecionada != null) {
                    lblVeiculoSelecionado.setText("Fatura do cliente " + faturaSelecionada.getLocacaoFatura().getReservaLocacao().getClienteReserva().getNomeCompleto());
                    // Aqui você pode associar o objeto veiculoSelecionado ao modelo
                }
            }
        });

        Label lblValor = new Label(shell, SWT.NONE);
        lblValor.setText("Valor:");
        lblValor.setBounds(10, 42, 46, 15);

        textValor = new Text(shell, SWT.BORDER);
        textValor.setToolTipText("");
        textValor.setBounds(57, 39, 86, 21);

        Label lblMetodoDePagamento = new Label(shell, SWT.NONE);
        lblMetodoDePagamento.setText("Metodo de Pagamento:");
        lblMetodoDePagamento.setBounds(10, 78, 132, 15);

        textMetodoPagamento = new Text(shell, SWT.BORDER);
        textMetodoPagamento.setBounds(148, 75, 92, 21);

        Label lblDataDoPagamento = new Label(shell, SWT.NONE);
        lblDataDoPagamento.setText("Data do Pagamento :");
        lblDataDoPagamento.setBounds(10, 119, 114, 15);

        DateTime dateTimeDataPagamento = new DateTime(shell, SWT.BORDER);
        dateTimeDataPagamento.setBounds(127, 110, 80, 24);

        textStatusPagamento = new Text(shell, SWT.BORDER);
        textStatusPagamento.setBounds(137, 159, 81, 21);

        Label lblStatusDoPagamento = new Label(shell, SWT.NONE);
        lblStatusDoPagamento.setText("Status do Pagamento:");
        lblStatusDoPagamento.setBounds(10, 162, 121, 15);

        Label lblDescricao = new Label(shell, SWT.NONE);
        lblDescricao.setText("Descrição:");
        lblDescricao.setBounds(10, 200, 65, 15);

        textDescricao = new Text(shell, SWT.BORDER);
        textDescricao.setBounds(80, 197, 209, 44);

        Button btnCadastrarPagamento = new Button(shell, SWT.NONE);
        btnCadastrarPagamento.setText("Cadastrar Pagamento");
        btnCadastrarPagamento.setBounds(57, 286, 150, 25);

        Button btnDeletarPagamento = new Button(shell, SWT.NONE);
        btnDeletarPagamento.setText("Deletar Pagamento");
        btnDeletarPagamento.setBounds(299, 286, 134, 25);

        Button btnListarPagamento = new Button(shell, SWT.NONE);
        btnListarPagamento.setText("Listar Pagamento");
        btnListarPagamento.setBounds(613, 286, 150, 25);

        table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
        table.setLinesVisible(true);
        table.setHeaderVisible(true);
        table.setBounds(46, 325, 920, 230);

        TableColumn tblclmnIdPagamento = new TableColumn(table, SWT.CENTER);
        tblclmnIdPagamento.setWidth(106);
        tblclmnIdPagamento.setText("ID Pagamento");
        
        TableColumn tblclmnNome = new TableColumn(table, SWT.NONE);
        tblclmnNome.setWidth(100);
        tblclmnNome.setText("Nome");

        TableColumn tblclmnValor = new TableColumn(table, SWT.CENTER);
        tblclmnValor.setWidth(71);
        tblclmnValor.setText("Valor");

        TableColumn tblclmnMetodoPagamento = new TableColumn(table, SWT.CENTER);
        tblclmnMetodoPagamento.setText("Metodo de Pagamento");
        tblclmnMetodoPagamento.setWidth(138);

        TableColumn tblclmnDataPagamento = new TableColumn(table, SWT.CENTER);
        tblclmnDataPagamento.setWidth(147);
        tblclmnDataPagamento.setText("Data do Pagamento");

        TableColumn tblclmnStatusPagamento = new TableColumn(table, SWT.CENTER);
        tblclmnStatusPagamento.setWidth(164);
        tblclmnStatusPagamento.setText("Status do Pagamento");

        TableColumn tblclmnDescricao = new TableColumn(table, SWT.CENTER);
        tblclmnDescricao.setWidth(192);
        tblclmnDescricao.setText("Descrição");

        
        btnCadastrarPagamento.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            	
            	 
                if (faturaSelecionada == null) {
                    MessageBox box = new MessageBox(shell, SWT.ICON_WARNING);
                    box.setMessage("Selecione uma fatura antes de cadastrar o pagamento.");
                    box.open();
                    return;
                }
            	
            	 if (textValor.getText().isEmpty() || textMetodoPagamento.getText().isEmpty() || textStatusPagamento.getText().isEmpty() || textDescricao.getText().isEmpty()) {
                     MessageBox box = new MessageBox(shell, SWT.ICON_WARNING);
                     box.setMessage("Preencha todos os campos obrigatórios.");
                     box.open();
                     return;
                 }
                
            	
                Double valor = Double.parseDouble(textValor.getText());
                String metodoPagamento = textMetodoPagamento.getText();
                LocalDate dataPagamento = LocalDate.of(dateTimeDataPagamento.getYear(), dateTimeDataPagamento.getMonth() + 1, dateTimeDataPagamento.getDay());
                String statusPagamento = textStatusPagamento.getText();
                String descricao = textDescricao.getText();

                Pagamento pagamento = new Pagamento(valor, metodoPagamento, dataPagamento, statusPagamento, descricao,faturaSelecionada);
                pagamentoBanco.incluir(pagamento);
                MessageBox box = new MessageBox(shell, SWT.OK);
                box.setMessage("Pagamento cadastrado com sucesso!");
                box.open();
                btnListarPagamento.notifyListeners(SWT.Selection, null);
            }
        });

      
        btnDeletarPagamento.addSelectionListener(new SelectionAdapter() {
        	@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] selectedItems = table.getSelection();

				if (selectedItems.length == 0) {
					MessageBox warningBox = new MessageBox(shell, SWT.ICON_WARNING);
					warningBox.setMessage("Selecione um Pagamento na tabela para deletar.");
					warningBox.open();
					return;
				}

				try {

					Integer idPagamento= Integer.parseInt(selectedItems[0].getText(0));
					
					Pagamento pagamentoDeletar = new Pagamento();
                    pagamentoDeletar.setIdPagamento(idPagamento);
					
					pagamentoBanco.deletar(pagamentoDeletar);
					MessageBox successBox = new MessageBox(shell, SWT.ICON_INFORMATION);
					successBox.setMessage("Pagamento deletado com sucesso!");
					successBox.open();

				

					btnListarPagamento.notifyListeners(SWT.Selection, null);

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

        // Listar Pagamento
        btnListarPagamento.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                List<Pagamento> pagamentos = pagamentoBanco.listar();
                table.removeAll();
                for (Pagamento pagamento : pagamentos) {
                    TableItem item = new TableItem(table, SWT.NONE);
                    item.setText(new String[] {
                        String.valueOf(pagamento.getIdPagamento()),
                        pagamento.getFatura().getLocacaoFatura().getReservaLocacao().getClienteReserva().getNomeCompleto(),
                        String.valueOf(pagamento.getValor()),
                        pagamento.getMetodoPagamento(),
                        pagamento.getDataPagamento().toString(),
                        pagamento.getStatus(),
                        pagamento.getDescricao()
                    });
                }
            }
        });
        
        Button btnConsultarPagamentoId = new Button(shell, SWT.NONE);
        btnConsultarPagamentoId.setText("Consultar Pagamento por ID");
        btnConsultarPagamentoId.setBounds(374, 225, 162, 30);
        btnConsultarPagamentoId.setVisible(true); 

       
        Text txtPagamentoId = new Text(shell, SWT.BORDER);
        txtPagamentoId.setBounds(557, 230, 44, 25);
        txtPagamentoId.setVisible(false); 

       
        Button btnConfirmarPagamentoId = new Button(shell, SWT.NONE);
        btnConfirmarPagamentoId.setText("Confirmar ID");
        btnConfirmarPagamentoId.setBounds(637, 225, 150, 30);
        btnConfirmarPagamentoId.setVisible(false); 

  
        btnConsultarPagamentoId.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                txtPagamentoId.setVisible(true); 
                btnConfirmarPagamentoId.setVisible(true); 
            }
        });
        Button btnAtualizarPagamento = new Button(shell, SWT.NONE);
        btnAtualizarPagamento.setText("Atualizar Pagamento");
        btnAtualizarPagamento.setBounds(441, 286, 150, 25);
        btnAtualizarPagamento.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                
                TableItem[] selectedItems = table.getSelection();
                
                if (selectedItems.length == 0) {
                    MessageBox warningBox = new MessageBox(shell, SWT.ICON_WARNING);
                    warningBox.setMessage("Selecione um Pagamento na tabela para atualizar.");
                    warningBox.open();
                    btnListarPagamento.notifyListeners(SWT.Selection, null);
                    
                    return;
                }
                
                
                if (faturaSelecionada == null) {
                    MessageBox box = new MessageBox(shell, SWT.ICON_WARNING);
                    box.setMessage("Selecione uma fatura antes de cadastrar o pagamento.");
                    box.open();
                    return;
                }
            	
            	 if (textValor.getText().isEmpty() || textMetodoPagamento.getText().isEmpty() || textStatusPagamento.getText().isEmpty() || textDescricao.getText().isEmpty()) {
                     MessageBox box = new MessageBox(shell, SWT.ICON_WARNING);
                     box.setMessage("Preencha todos os campos obrigatórios.");
                     box.open();
                     return;
                 }
                try {
                    Integer idPagamento = Integer.parseInt(selectedItems[0].getText(0)); 

                   
                    Double valor = Double.parseDouble(textValor.getText());
                    String metodoPagamento = textMetodoPagamento.getText();
                    LocalDate dataPagamento = LocalDate.of(dateTimeDataPagamento.getYear(), dateTimeDataPagamento.getMonth() + 1, dateTimeDataPagamento.getDay());
                    String statusPagamento = textStatusPagamento.getText();
                    String descricao = textDescricao.getText();
                    
                  
                    Pagamento pagamento = new Pagamento(idPagamento, valor, metodoPagamento, dataPagamento, statusPagamento, descricao,faturaSelecionada);
                    pagamentoBanco.atualizar(pagamento);
                    
                  
                    MessageBox box = new MessageBox(shell, SWT.OK);
                    box.setMessage("Pagamento atualizado com sucesso!");
                    box.open();
                    btnListarPagamento.notifyListeners(SWT.Selection, null);
                    
                } catch (Exception ex) {
                    MessageBox errorBox = new MessageBox(shell, SWT.ICON_ERROR);
                    errorBox.setMessage("Erro ao atualizar Pagamento: " + ex.getMessage());
                    errorBox.open();
                }
            }
        });

       
        btnConfirmarPagamentoId.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                try {
                    String idPagamentoInput = txtPagamentoId.getText(); 
                    if (idPagamentoInput != null && !idPagamentoInput.isEmpty()) {
                        Integer idPagamento = Integer.parseInt(idPagamentoInput); 
                        
                       Pagamento pagamentoConsultar = new Pagamento();
                       pagamentoConsultar.setIdPagamento(idPagamento);

                        Pagamento pagamento = pagamentoBanco.consultar(pagamentoConsultar); 

                        if (pagamento != null) {
                           
                            table.removeAll();
                            TableItem item = new TableItem(table, SWT.NONE);
                            item.setText(new String[] {
                            		String.valueOf(pagamento.getIdPagamento()),
                                    pagamento.getFatura().getLocacaoFatura().getReservaLocacao().getClienteReserva().getNomeCompleto(),
                                    String.valueOf(pagamento.getValor()),
                                    pagamento.getMetodoPagamento(),
                                    pagamento.getDataPagamento().toString(),
                                    pagamento.getStatus(),
                                    pagamento.getDescricao()
                            });
                        } else {
                            
                            MessageBox messageBox = new MessageBox(shell, SWT.ICON_WARNING);
                            messageBox.setMessage("Pagamento não encontrado.");
                            messageBox.open();
                        }
                    } else {
                      
                        MessageBox messageBox = new MessageBox(shell, SWT.ICON_ERROR);
                        messageBox.setMessage("Digite um ID válido.");
                        messageBox.open();
                    }
                } catch (NumberFormatException ex) {
                    
                    MessageBox messageBox = new MessageBox(shell, SWT.ICON_ERROR);
                    messageBox.setMessage("ID inválido.");
                    messageBox.open();
                }
            }
        });

    }
}
