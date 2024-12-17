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

import banco.DevolucaoBanco;

import model.Devolucao;
import model.Locacao;

public class ViewDevolucao {
    private DevolucaoBanco devolucaoBanco;
    protected Shell shell;
    private Table table;
    private Locacao locacaoSelecionada;

    public ViewDevolucao() {
        this.devolucaoBanco = new DevolucaoBanco();
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
            ViewDevolucao window = new ViewDevolucao();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    

    protected void createContents() {
        shell = new Shell();
        shell.setSize(854, 707);
        shell.setText("Devolução - Locadora");
        
        
        Button btnSelecionarLocacao = new Button(shell, SWT.NONE);
        btnSelecionarLocacao.setBounds(265, 40, 150, 25);
        btnSelecionarLocacao.setText("Selecionar Locacao");

        Label lblLocacaoSelecionado = new Label(shell, SWT.NONE);
        lblLocacaoSelecionado.setBounds(32, 45, 200, 25);
        lblLocacaoSelecionado.setText("Nenhuma Locacao selecionada");

        // Ação do botão selecionar veículo
        btnSelecionarLocacao.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ViewSelecionarLocacao viewSelecionarLocacao = new ViewSelecionarLocacao();
                locacaoSelecionada = viewSelecionarLocacao.open();
                if (locacaoSelecionada != null) {
                    lblLocacaoSelecionado.setText("Locação do CLiente :" + locacaoSelecionada.getReservaLocacao().getClienteReserva().getNomeCompleto());
                   
                }
            }
        });


        Label lblDataDevolucao = new Label(shell, SWT.NONE);
        lblDataDevolucao.setText("Data da Devolução:");
        lblDataDevolucao.setBounds(32, 97, 120, 15);

        DateTime dateDevolucao = new DateTime(shell, SWT.BORDER);
        dateDevolucao.setBounds(165, 88, 120, 24);

        Label lblCondicao = new Label(shell, SWT.NONE);
        lblCondicao.setText("Condição do Veículo:");
        lblCondicao.setBounds(32, 156, 120, 15);

        Text txtCondicao = new Text(shell, SWT.BORDER);
        txtCondicao.setBounds(159, 147, 300, 37);

        Label lblTaxaAtraso = new Label(shell, SWT.NONE);
        lblTaxaAtraso.setText("Taxa de Atraso:");
        lblTaxaAtraso.setBounds(32, 245, 120, 15);

        Text txtTaxaAtraso = new Text(shell, SWT.BORDER);
        txtTaxaAtraso.setBounds(165, 230, 120, 30);

        Label lblStatus = new Label(shell, SWT.NONE);
        lblStatus.setText("Status da Devolução:");
        lblStatus.setBounds(32, 281, 120, 15);

        Button btnCadastrarDevolucao = new Button(shell, SWT.NONE);
        btnCadastrarDevolucao.setBounds(44, 331, 120, 30);
        btnCadastrarDevolucao.setText("Cadastrar Devolução");

        Button btnConsultarDevolucao = new Button(shell, SWT.NONE);
        btnConsultarDevolucao.setBounds(225, 331, 120, 30);
        btnConsultarDevolucao.setText("Consultar Devolução");

        Button btnDeletarDevolucao = new Button(shell, SWT.NONE);
        btnDeletarDevolucao.setBounds(384, 331, 120, 30);
        btnDeletarDevolucao.setText("Deletar Devolução");

        table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
        table.setBounds(20, 391, 786, 223);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        
        TableColumn tblclmnIdDevolucao = new TableColumn(table, SWT.NONE);
        tblclmnIdDevolucao.setWidth(100);
        tblclmnIdDevolucao.setText("ID Devolucao");
        
        TableColumn tblclmnIdLocacao = new TableColumn(table, SWT.NONE);
        tblclmnIdLocacao.setWidth(100);
        tblclmnIdLocacao.setText("iD Locacao");
        
        TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
        tblclmnNewColumn.setWidth(100);
        tblclmnNewColumn.setText("Nome");

        TableColumn tblclmnData = new TableColumn(table, SWT.NONE);
        tblclmnData.setWidth(100);
        tblclmnData.setText("Data");

        TableColumn tblclmnCondicao = new TableColumn(table, SWT.NONE);
        tblclmnCondicao.setWidth(150);
        tblclmnCondicao.setText("Condição do Veículo");

        TableColumn tblclmnTaxa = new TableColumn(table, SWT.NONE);
        tblclmnTaxa.setWidth(100);
        tblclmnTaxa.setText("Taxa de Atraso");

        TableColumn tblclmnStatus = new TableColumn(table, SWT.NONE);
        tblclmnStatus.setWidth(150);
        tblclmnStatus.setText("Status da Devolução");
        
        Button btnDevolvido = new Button(shell, SWT.RADIO);
        btnDevolvido.setBounds(175, 280, 90, 16);
        btnDevolvido.setText("Concluida");
        
        Button btnNaoDevolvida = new Button(shell, SWT.RADIO);
        btnNaoDevolvida.setBounds(302, 281, 90, 16);
        btnNaoDevolvida.setText("A concluir");
        
        Button btnAtualizar = new Button(shell, SWT.NONE);
        btnAtualizar.setBounds(523, 336, 75, 25);
        btnAtualizar.setText("Atualizar");
        
        Button btnBuscarPorID = new Button(shell, SWT.NONE);
        btnBuscarPorID.setBounds(463, 245, 150, 30);
        btnBuscarPorID.setText("Buscar por ID");

        
        Text txtBuscarID = new Text(shell, SWT.BORDER);
        txtBuscarID.setBounds(634, 242, 50, 25);

      
        Button btnConfirmarBuscarID = new Button(shell, SWT.NONE);
        btnConfirmarBuscarID.setBounds(706, 245, 100, 30);
        btnConfirmarBuscarID.setText("Confirmar");
        btnConfirmarBuscarID.setVisible(false);
        txtBuscarID.setVisible(false);

      
        btnCadastrarDevolucao.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            	
                if (locacaoSelecionada == null) {
                    MessageBox warningBox = new MessageBox(shell, SWT.ICON_WARNING);
                    warningBox.setMessage("Selecione uma locação antes de cadastrar a devolução.");
                    warningBox.open();
                    return;
                }

                String condicao = txtCondicao.getText();
                Double taxaAtraso = Double.parseDouble(txtTaxaAtraso.getText());
                Boolean statusDevolucao = btnDevolvido.getSelection();
                LocalDate dataDevolucao = LocalDate.of(dateDevolucao.getYear(), dateDevolucao.getMonth() + 1, dateDevolucao.getDay());

                Devolucao devolucao = new Devolucao(dataDevolucao, condicao, taxaAtraso,statusDevolucao,locacaoSelecionada);
                devolucaoBanco.incluir(devolucao);
                MessageBox messageBox = new MessageBox(shell, SWT.OK);
                messageBox.setMessage("Devolução cadastrada com sucesso!");
                messageBox.open();
                btnConsultarDevolucao.notifyListeners(SWT.Selection, null);
                
                
                txtCondicao.setText("");
                txtTaxaAtraso.setText("");
              
                btnDevolvido.setSelection(false);
                btnNaoDevolvida.setSelection(false);
            }
        });

 
        btnConsultarDevolucao.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                List<Devolucao> devolucoes = devolucaoBanco.listar();
                table.removeAll();
                for (Devolucao devolucao : devolucoes) {
                    TableItem item = new TableItem(table, SWT.NONE);
                    item.setText(new String[]{
                    		String.valueOf(devolucao.getIdDevolucao()),
                    		String.valueOf(devolucao.getLocacaoDevolucao().getIdLocacao()),
                    		devolucao.getLocacaoDevolucao().getReservaLocacao().getClienteReserva().getNomeCompleto(),
                    		devolucao.getDataDevolucao().toString(),
                            devolucao.getCondicaoVeiculo(),
                            String.valueOf(devolucao.getTaxaAtraso()),
                            devolucao.getStatusDevolucao() ? "Concluida" : "A Concluir"
                    });
                }
            }
        });

       
        btnDeletarDevolucao.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                int selectedIndex = table.getSelectionIndex();
                if (selectedIndex != -1) {
                    TableItem item = table.getItem(selectedIndex);
                    Integer idDevolucao = Integer.parseInt(item.getText(0));  
                    
                    Devolucao devolucaoDeletar = new Devolucao();
                    devolucaoDeletar.setIdDevolucao(idDevolucao);

                    devolucaoBanco.deletar(devolucaoDeletar);
                    MessageBox messageBox = new MessageBox(shell, SWT.OK);
                    messageBox.setMessage("Devolução deletada com sucesso!");
                    messageBox.open();

                    
                    btnConsultarDevolucao.notifyListeners(SWT.Selection, null);
                } else {
                    MessageBox warningBox = new MessageBox(shell, SWT.ICON_WARNING);
                    warningBox.setMessage("Selecione uma devolução na tabela para deletar.");
                    warningBox.open();
                }
            }
        });

   

        btnAtualizar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                TableItem[] selectedItems = table.getSelection();

                if (selectedItems.length == 0) {
                    MessageBox warningBox = new MessageBox(shell, SWT.ICON_WARNING);
                    warningBox.setMessage("Selecione uma devolução na tabela para atualizar.");
                    warningBox.open();
                    btnConsultarDevolucao.notifyListeners(SWT.Selection, null);
                    return;
                }
              
                try {
                  
                	 String condicao = txtCondicao.getText();
                     Double taxaAtraso = Double.parseDouble(txtTaxaAtraso.getText());
                     Boolean statusDevolucao = btnDevolvido.getSelection();
                     LocalDate dataDevolucao = LocalDate.of(dateDevolucao.getYear(), dateDevolucao.getMonth() + 1, dateDevolucao.getDay());

                   
                    TableItem selectedItem = selectedItems[0];
                    Integer idDevolucao = Integer.parseInt(selectedItem.getText(0)); // Assume que o ID está na primeira coluna da tabela

                  
                    Devolucao devolucao = new Devolucao(idDevolucao,dataDevolucao, condicao, taxaAtraso,statusDevolucao,locacaoSelecionada);

                    
                    devolucaoBanco.atualizar(devolucao);

                    MessageBox box = new MessageBox(shell, SWT.ICON_INFORMATION);
                    box.setMessage("Devolução atualizada com sucesso!");
                    box.open();

                   
                    btnConsultarDevolucao.notifyListeners(SWT.Selection, null); 
                } catch (Exception ex) {
                    MessageBox errorBox = new MessageBox(shell, SWT.ICON_ERROR);
                    errorBox.setMessage("Erro ao atualizar devolução: " + ex.getMessage());
                    errorBox.open();
                }
            }
        });


        btnBuscarPorID.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                txtBuscarID.setVisible(true);
                btnConfirmarBuscarID.setVisible(true);
            }
        });

        btnConfirmarBuscarID.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                try {
                    String idText = txtBuscarID.getText();
                    if (idText == null || idText.isEmpty()) {
                        throw new NumberFormatException("ID inválido.");
                    }

                    Integer idDevolucao = Integer.parseInt(idText); 

                    Devolucao devolucaoConsulta = new Devolucao();
                    devolucaoConsulta.setIdDevolucao(idDevolucao);
                    Devolucao devolucao = devolucaoBanco.consultar(devolucaoConsulta);

                    if (devolucao != null) {
                        table.removeAll(); 
                        TableItem item = new TableItem(table, SWT.NONE);
                        item.setText(new String[] {
                        		String.valueOf(devolucao.getIdDevolucao()),
                        		String.valueOf(devolucao.getLocacaoDevolucao().getIdLocacao()),
                        		devolucao.getLocacaoDevolucao().getReservaLocacao().getClienteReserva().getNomeCompleto(),
                        		devolucao.getDataDevolucao().toString(),
                                devolucao.getCondicaoVeiculo(),
                                String.valueOf(devolucao.getTaxaAtraso()),
                                devolucao.getStatusDevolucao() ? "Concluida" : "A Concluir"
                        });
                    } else {
                        MessageBox box = new MessageBox(shell, SWT.ICON_WARNING);
                        box.setMessage("Devolução não encontrada.");
                        box.open();
                    }
                } catch (NumberFormatException ex) {
                    MessageBox box = new MessageBox(shell, SWT.ICON_ERROR);
                    box.setMessage("ID inválido. Digite um número válido.");
                    box.open();
                }
            }
        });
        








    }
}
