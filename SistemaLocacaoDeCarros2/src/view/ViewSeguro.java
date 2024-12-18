package view;

import java.time.LocalDate;
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

import banco.SeguroBanco;
import model.Locacao;
import model.Seguro;


import org.eclipse.swt.widgets.DateTime;

public class ViewSeguro {

    protected Shell shell;
    private Text textTipoSeguro;
    private Text textValorCobertura;
    private Text textFranquia;
    private Table table;
    private SeguroBanco seguroBanco;
    private Locacao locacaoSelecionada;

    public ViewSeguro() {
        this.seguroBanco = new SeguroBanco();
    }

    public static void main(String[] args) {
        try {
            ViewSeguro window = new ViewSeguro();
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
        shell.setSize(1034, 667);
        shell.setText("Seguro");
        
        Button btnSelecionarLocacao = new Button(shell, SWT.NONE);
        btnSelecionarLocacao.setBounds(229, 8, 150, 25);
        btnSelecionarLocacao.setText("Selecionar Locacao");

        Label lblLocacaoSelecionado = new Label(shell, SWT.NONE);
        lblLocacaoSelecionado.setBounds(23, 13, 200, 25);
        lblLocacaoSelecionado.setText("Nenhuma Locacao selecionada");

        // Ação do botão selecionar veículo
        btnSelecionarLocacao.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ViewSelecionarLocacao viewSelecionarLocacao = new ViewSelecionarLocacao();
                locacaoSelecionada = viewSelecionarLocacao.open();
                if (locacaoSelecionada != null) {
                    lblLocacaoSelecionado.setText("Locação do CLiente :" + locacaoSelecionada.getReservaLocacao().getClienteReserva().getNomeCompleto());
                    // Aqui você pode associar o objeto veiculoSelecionado ao modelo
                }
            }
        });

        Label lblTipoDeSeguro = new Label(shell, SWT.NONE);
        lblTipoDeSeguro.setText("Tipo de Seguro:");
        lblTipoDeSeguro.setBounds(10, 42, 92, 15);

        textTipoSeguro = new Text(shell, SWT.BORDER);
        textTipoSeguro.setBounds(108, 39, 99, 21);

        Label lblValorDeCobertura = new Label(shell, SWT.NONE);
        lblValorDeCobertura.setText("Valor da cobertura:");
        lblValorDeCobertura.setBounds(10, 78, 114, 15);

        textValorCobertura = new Text(shell, SWT.BORDER);
        textValorCobertura.setBounds(130, 75, 110, 21);

        Label lblFranquia = new Label(shell, SWT.NONE);
        lblFranquia.setText("Franquia:");
        lblFranquia.setBounds(10, 119, 66, 15);

        Label lblVigencia = new Label(shell, SWT.NONE);
        lblVigencia.setText("Vigência:");
        lblVigencia.setBounds(10, 162, 66, 15);

        Button btnCadastrarSeguro = new Button(shell, SWT.NONE);
        btnCadastrarSeguro.setText("Cadastrar Seguro");
        btnCadastrarSeguro.setBounds(57, 225, 150, 25);

        Button btnDeletarSeguro = new Button(shell, SWT.NONE);
        btnDeletarSeguro.setText("Deletar Seguro");
        btnDeletarSeguro.setBounds(332, 225, 134, 25);

        Button btnListarSeguro = new Button(shell, SWT.NONE);
        btnListarSeguro.setText("Consultar Seguro");
        btnListarSeguro.setBounds(593, 225, 150, 25);

        textFranquia = new Text(shell, SWT.BORDER);
        textFranquia.setBounds(82, 113, 81, 21);

        table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
        table.setLinesVisible(true);
        table.setHeaderVisible(true);
        table.setBounds(23, 380, 851, 200);

        TableColumn tblclmnIdSeguro = new TableColumn(table, SWT.CENTER);
        tblclmnIdSeguro.setWidth(106);
        tblclmnIdSeguro.setText("ID Cliente");
        
        TableColumn tblclmnIdLocacao = new TableColumn(table, SWT.NONE);
        tblclmnIdLocacao.setWidth(100);
        tblclmnIdLocacao.setText("Id Seguro");
        
        TableColumn tblclmnNome = new TableColumn(table, SWT.NONE);
        tblclmnNome.setWidth(100);
        tblclmnNome.setText("Nome");

        TableColumn tblclmnTipoSeguro = new TableColumn(table, SWT.CENTER);
        tblclmnTipoSeguro.setWidth(142);
        tblclmnTipoSeguro.setText("Tipo de Seguro");

        TableColumn tblclmnValorCobertura = new TableColumn(table, SWT.CENTER);
        tblclmnValorCobertura.setWidth(138);
        tblclmnValorCobertura.setText("Valor da cobertura");

        TableColumn tblclmnFranquia = new TableColumn(table, SWT.CENTER);
        tblclmnFranquia.setWidth(120);
        tblclmnFranquia.setText("Franquia");

        TableColumn tblclmnVigencia = new TableColumn(table, SWT.CENTER);
        tblclmnVigencia.setWidth(131);
        tblclmnVigencia.setText("Vigência");
        
        
        
        
        
        DateTime dateVigencia = new DateTime(shell, SWT.BORDER);
        dateVigencia.setBounds(82, 153, 80, 24);

       
        btnCadastrarSeguro.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                
            	
            	 if (locacaoSelecionada == null) {
                     MessageBox warningBox = new MessageBox(shell, SWT.ICON_WARNING);
                     warningBox.setMessage("Selecione uma locação antes de cadastrar o seguro.");
                     warningBox.open();
                     return;
                 }
                String tipoSeguro = textTipoSeguro.getText();
                Double valorCobertura = Double.parseDouble(textValorCobertura.getText());
                Double franquia = Double.parseDouble(textFranquia.getText());
                LocalDate vigencia = LocalDate.of(dateVigencia.getYear(), dateVigencia.getMonth() + 1, dateVigencia.getDay());

                Seguro seguro = new Seguro(tipoSeguro, valorCobertura, franquia, vigencia,locacaoSelecionada);
                seguroBanco.incluir(seguro);

                MessageBox box = new MessageBox(shell, SWT.OK);
                box.setMessage("Seguro cadastrado com sucesso!");
                box.open();
                btnListarSeguro.notifyListeners(SWT.Selection, null);
            }
        });

        btnDeletarSeguro.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int selectedIndex = table.getSelectionIndex();
				if (selectedIndex != -1) {
					TableItem item = table.getItem(selectedIndex);
					Integer idSeguro = Integer.parseInt(item.getText(1));
					
					Seguro seguroDeletar= new Seguro();
					seguroDeletar.setIdSeguro(idSeguro);
                      
					
					seguroBanco.deletar(seguroDeletar);
					MessageBox messageBox = new MessageBox(shell, SWT.OK);
					messageBox.setMessage("Deletado com sucesso!");
					messageBox.open();

					btnListarSeguro.notifyListeners(SWT.Selection, null);
				} else {
					MessageBox warningBox = new MessageBox(shell, SWT.ICON_WARNING);
					warningBox.setMessage("Selecione um seguro na tabela para deletar.");
					warningBox.open();
				}

			}
		});

        
        btnListarSeguro.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                List<Seguro> listaSeguros = seguroBanco.listar();
                table.removeAll();

              
                for (Seguro seguro : listaSeguros) {
                    TableItem item = new TableItem(table, SWT.NONE);
                    item.setText(new String[]{
                            String.valueOf(seguro.getLocacao().getReservaLocacao().getClienteReserva().getIdUsuario()),
                            String.valueOf(seguro.getIdSeguro()),
                            seguro.getLocacao().getReservaLocacao().getClienteReserva().getNomeCompleto(),
                            seguro.getTipoSeguro(),
                            String.format("%.2f", seguro.getValorCobertura()),
                            String.format("%.2f", seguro.getFranquia()),
                            seguro.getVigencia().toString(),
                            
                            
                            
                    });
                }
            }
        });
        
        Button btnAtualizarSeguro = new Button(shell, SWT.NONE);
        btnAtualizarSeguro.setText("Atualizar Seguro");
        btnAtualizarSeguro.setBounds(57, 260, 150, 25);
        btnAtualizarSeguro.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            	
            	 TableItem[] selectedItems = table.getSelection();
                 
                 if (selectedItems.length == 0) {
                     MessageBox warningBox = new MessageBox(shell, SWT.ICON_WARNING);
                     warningBox.setMessage("Selecione um Seguro na tabela para atualizar.");
                     warningBox.open();
                     btnListarSeguro.notifyListeners(SWT.Selection, null);
                     
                     return;
                 }
                 
             	
            	 if (locacaoSelecionada == null) {
                     MessageBox warningBox = new MessageBox(shell, SWT.ICON_WARNING);
                     warningBox.setMessage("Selecione uma locação antes de cadastrar o seguro.");
                     warningBox.open();
                     return;
                 }
                try {
                    
                    Integer idSeguro = Integer.parseInt(selectedItems[0].getText(1)); // Aqui você pega o ID do Seguro que será atualizado
                    String tipoSeguro = textTipoSeguro.getText();
                    Double valorCobertura = Double.parseDouble(textValorCobertura.getText());
                    Double franquia = Double.parseDouble(textFranquia.getText());
                    LocalDate vigencia = LocalDate.of(dateVigencia.getYear(), dateVigencia.getMonth() + 1, dateVigencia.getDay());

                   
                    Seguro seguro = new Seguro(idSeguro, tipoSeguro, valorCobertura, franquia, vigencia, locacaoSelecionada);
                    
               
                    seguroBanco.atualizar(seguro);
                    
                    
                    MessageBox box = new MessageBox(shell, SWT.OK);
                    box.setMessage("Seguro atualizado com sucesso!");
                    box.open();
                    btnListarSeguro.notifyListeners(SWT.Selection, null);
                    
                } catch (Exception ex) {
                    MessageBox errorBox = new MessageBox(shell, SWT.ICON_ERROR);
                    errorBox.setMessage("Erro ao atualizar seguro: " + ex.getMessage());
                    errorBox.open();
                }
            }
        });
    
        Button btnConsultarSeguroId = new Button(shell, SWT.NONE);
        btnConsultarSeguroId.setText("Consultar Seguro por ID");
        btnConsultarSeguroId.setBounds(331, 257, 150, 30);
        btnConsultarSeguroId.setVisible(false); 

        
        Text txtSeguroId = new Text(shell, SWT.BORDER);
        txtSeguroId.setBounds(497, 256, 100, 25);
        txtSeguroId.setVisible(false); 

        
        Button btnConfirmarSeguroId = new Button(shell, SWT.NONE);
        btnConfirmarSeguroId.setText("Confirmar ID");
        btnConfirmarSeguroId.setBounds(603, 257, 150, 30);
        btnConfirmarSeguroId.setVisible(false); 

        
        btnConsultarSeguroId.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                txtSeguroId.setVisible(true); 
                btnConfirmarSeguroId.setVisible(true); 
            }
        });

    
        btnConfirmarSeguroId.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                try {
                    String idSeguroInput = txtSeguroId.getText(); 
                    if (idSeguroInput != null && !idSeguroInput.isEmpty()) {
                        Integer idSeguro = Integer.parseInt(idSeguroInput); 
                        
                        Seguro seguroConsultar = new Seguro();
                        seguroConsultar.setIdSeguro(idSeguro);
                        
                        
                        
                        Seguro seguro = seguroBanco.consultar(seguroConsultar);
                        
                        
                        
                        
                        
                        
                        if (seguro != null) {
                         
                            table.removeAll();
                            TableItem item = new TableItem(table, SWT.NONE);
                            item.setText(new String[] {
                            		String.valueOf(seguro.getLocacao().getReservaLocacao().getClienteReserva().getIdUsuario()),
                            		String.valueOf(seguro.getIdSeguro()),
                                    seguro.getLocacao().getReservaLocacao().getClienteReserva().getNomeCompleto(),
                                    seguro.getTipoSeguro(),
                                    String.format("%.2f", seguro.getValorCobertura()),
                                    String.format("%.2f", seguro.getFranquia()),
                                    seguro.getVigencia().toString(),
                                    
                            });
                        } else {
                            
                            MessageBox messageBox = new MessageBox(shell, SWT.ICON_WARNING);
                            messageBox.setMessage("Seguro não encontrado.");
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

        
        btnConsultarSeguroId.setVisible(true); 
    }
}
