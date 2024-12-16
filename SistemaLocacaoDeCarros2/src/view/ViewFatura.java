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

import banco.FaturaBanco;
import model.Fatura;
import model.Locacao;

public class ViewFatura {
    private FaturaBanco faturaBanco;
    protected Shell shell;
    private Table table;
    private Locacao locacaoSelecionada;

    public ViewFatura() {
        this.faturaBanco = new FaturaBanco();
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
            ViewFatura window = new ViewFatura();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void createContents() {
        shell = new Shell();
        shell.setSize(814, 733);
        shell.setText("Fatura");
        
        
        Button btnSelecionarLocacao = new Button(shell, SWT.NONE);
        btnSelecionarLocacao.setBounds(229, 8, 150, 25);
        btnSelecionarLocacao.setText("Selecionar Locacao");

        Label lblVeiculoSelecionado = new Label(shell, SWT.NONE);
        lblVeiculoSelecionado.setBounds(23, 13, 200, 25);
        lblVeiculoSelecionado.setText("Nenhuma Locacao selecionada");

        // Ação do botão selecionar veículo
        btnSelecionarLocacao.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ViewSelecionarLocacao viewSelecionarLocacao = new ViewSelecionarLocacao();
                locacaoSelecionada = viewSelecionarLocacao.open();
                if (locacaoSelecionada != null) {
                    lblVeiculoSelecionado.setText("Veículo: " + locacaoSelecionada.getReservaLocacao().getClienteReserva().getNomeCompleto());
                    // Aqui você pode associar o objeto veiculoSelecionado ao modelo
                }
            }
        });

     
        Label lblDataEmissao = new Label(shell, SWT.NONE);
        lblDataEmissao.setBounds(20, 70, 120, 15);
        lblDataEmissao.setText("Data de Emissão:");

        DateTime dateEmissao = new DateTime(shell, SWT.BORDER);
        dateEmissao.setBounds(160, 70, 100, 25);

        Label lblValorTotal = new Label(shell, SWT.NONE);
        lblValorTotal.setBounds(20, 110, 120, 15);
        lblValorTotal.setText("Valor Total:");

        Text txtValorTotal = new Text(shell, SWT.BORDER);
        txtValorTotal.setBounds(160, 110, 200, 25);

        Label lblObservacoes = new Label(shell, SWT.NONE);
        lblObservacoes.setBounds(20, 150, 120, 15);
        lblObservacoes.setText("Observações:");

        Text txtObservacoes = new Text(shell, SWT.BORDER);
        txtObservacoes.setBounds(160, 150, 200, 50);

        Button btnCadastrarFatura = new Button(shell, SWT.NONE);
        btnCadastrarFatura.setBounds(40, 210, 120, 30);
        btnCadastrarFatura.setText("Cadastrar Fatura");

        Button btnListarFatura = new Button(shell, SWT.NONE);
        btnListarFatura.setBounds(180, 210, 120, 30);
        btnListarFatura.setText("Listar Faturas");

        table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
        table.setBounds(35, 319, 587, 293);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        
        TableColumn tblclmnIdFatura = new TableColumn(table, SWT.NONE);
        tblclmnIdFatura.setWidth(100);
        tblclmnIdFatura.setText("Id fatura");

        TableColumn tblclmnNumeroFatura = new TableColumn(table, SWT.NONE);
        tblclmnNumeroFatura.setWidth(100);
        tblclmnNumeroFatura.setText("Id Locacao");
        
        TableColumn tblclmnCliente = new TableColumn(table, SWT.NONE);
        tblclmnCliente.setWidth(100);
        tblclmnCliente.setText("Cliente");

        TableColumn tblclmnDataEmissao = new TableColumn(table, SWT.NONE);
        tblclmnDataEmissao.setWidth(120);
        tblclmnDataEmissao.setText("Data");

        TableColumn tblclmnValorTotal = new TableColumn(table, SWT.NONE);
        tblclmnValorTotal.setWidth(100);
        tblclmnValorTotal.setText("Valor");

        TableColumn tblclmnObservacoes = new TableColumn(table, SWT.NONE);
        tblclmnObservacoes.setWidth(200);
        tblclmnObservacoes.setText("Observações");
        
        TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
        tblclmnNewColumn.setWidth(0);
        tblclmnNewColumn.setText("idfatura");
        
        Button btnDeletarFatura = new Button(shell, SWT.NONE);
        btnDeletarFatura.setBounds(326, 213, 75, 25);
        btnDeletarFatura.setText("Deletar");

    
        btnCadastrarFatura.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
          
                LocalDate dataEmissao = LocalDate.of(dateEmissao.getYear(), dateEmissao.getMonth() + 1, dateEmissao.getDay());
                double valorTotal = Double.parseDouble(txtValorTotal.getText());
                String observacoes = txtObservacoes.getText();

               
                Fatura fatura = new Fatura( dataEmissao, valorTotal, observacoes, locacaoSelecionada);

                faturaBanco.incluir(fatura);
                MessageBox box = new MessageBox(shell, SWT.OK);
                box.setMessage("Fatura cadastrada com sucesso!");
                box.open();
            }
        });

 
        btnListarFatura.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                List<Fatura> faturas = faturaBanco.listar();
                table.removeAll();
                for (Fatura fatura : faturas) {
                    TableItem item = new TableItem(table, SWT.NONE);
                    item.setText(new String[]{
                    		String.valueOf(fatura.getNumeroFatura()),
                            String.valueOf(fatura.getLocacaoFatura().getIdLocacao()),
                            fatura.getLocacaoFatura().getReservaLocacao().getClienteReserva().getNomeCompleto(),
                            fatura.getDataEmissao().toString(),
                            String.format("%.2f", fatura.getValorTotal()),
                            fatura.getObservacoes(),
                            String.valueOf(fatura.getNumeroFatura()),
                    });
                }
            }
        });
        

        
        // colocar botao de deletar, atualizar e listar

         btnDeletarFatura.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                int selectedIndex = table.getSelectionIndex();
                if(selectedIndex != -1) {
                    TableItem item = table.getItem(selectedIndex);
                    int idFatura = Integer.parseInt(item.getText(5));

                    faturaBanco.deletar(idFatura);
                    MessageBox messageBox = new MessageBox(shell, SWT.OK);
                    messageBox.setMessage(" deletado com sucesso!");
                    messageBox.open();  

                    btnListarFatura.notifyListeners(SWT.Selection, null);
                } else {
                        MessageBox warningBox = new MessageBox(shell, SWT.ICON_WARNING);
                    warningBox.setMessage("Selecione uma Fatura na tabela para deletar.");
                    warningBox.open();
                }
    
            }
        });
         
         Button btnAtualizarFatura = new Button(shell, SWT.NONE);
         btnAtualizarFatura.setText("Atualizar Fatura");
         btnAtualizarFatura.setBounds(429, 210, 150, 30);

         btnAtualizarFatura.addSelectionListener(new SelectionAdapter() {
             @Override
             public void widgetSelected(SelectionEvent e) {
                 TableItem[] selectedItems = table.getSelection();

                 if (selectedItems.length == 0) {
                     MessageBox warningBox = new MessageBox(shell, SWT.ICON_WARNING);
                     warningBox.setMessage("Selecione uma fatura na tabela para atualizar.");
                     warningBox.open();
                     btnListarFatura.notifyListeners(SWT.Selection, null);
                     return;
                 }

                 try {
                     // Pega os valores selecionados e preenche os campos da tabela
                     Integer idFatura = Integer.parseInt(selectedItems[0].getText(0));
                     LocalDate dataEmissao = LocalDate.of(dateEmissao.getYear(), dateEmissao.getMonth() + 1, dateEmissao.getDay());
                     double valorTotal = Double.parseDouble(txtValorTotal.getText());
                     String observacoes = txtObservacoes.getText();

                     if (locacaoSelecionada == null) {
                         MessageBox errorBox = new MessageBox(shell, SWT.ICON_WARNING);
                         errorBox.setMessage("É necessário selecionar uma locação para atualizar a fatura.");
                         errorBox.open();
                         return;
                     }

                     // Cria o objeto Fatura com os dados preenchidos
                     Fatura fatura = new Fatura(idFatura, dataEmissao, valorTotal, observacoes, locacaoSelecionada);
                     faturaBanco.atualizar(fatura);

                     // Exibe mensagem de sucesso
                     MessageBox successBox = new MessageBox(shell, SWT.ICON_INFORMATION);
                     successBox.setMessage("Fatura atualizada com sucesso!");
                     successBox.open();

                     // Atualiza a tabela
                     btnListarFatura.notifyListeners(SWT.Selection, null);
                 } catch (Exception ex) {
                     MessageBox errorBox = new MessageBox(shell, SWT.ICON_ERROR);
                     errorBox.setMessage("Erro ao atualizar a fatura: " + ex.getMessage());
                     errorBox.open();
                 }
             }
         });

         Button btnConsultarFaturaId = new Button(shell, SWT.NONE);
         btnConsultarFaturaId.setText("Consultar Fatura por ID");
         btnConsultarFaturaId.setBounds(419, 145, 150, 30);

         // Campo para entrada do ID
         Text txtFaturaId = new Text(shell, SWT.BORDER);
         txtFaturaId.setBounds(591, 147, 50, 25);
         txtFaturaId.setVisible(false);

         // Botão para confirmar a consulta
         Button btnConfirmarFaturaId = new Button(shell, SWT.NONE);
         btnConfirmarFaturaId.setText("Confirmar ID");
         btnConfirmarFaturaId.setBounds(585, 184, 150, 30);
         btnConfirmarFaturaId.setVisible(false);

         // Quando o botão "Consultar Fatura por ID" for clicado
         btnConsultarFaturaId.addSelectionListener(new SelectionAdapter() {
             @Override
             public void widgetSelected(SelectionEvent e) {
                 txtFaturaId.setVisible(true); // Exibe o campo para inserir o ID
                 btnConfirmarFaturaId.setVisible(true); // Exibe o botão de confirmação
             }
         });

         // Quando o botão "Confirmar ID" for clicado
         btnConfirmarFaturaId.addSelectionListener(new SelectionAdapter() {
             @Override
             public void widgetSelected(SelectionEvent e) {
                 try {
                     String idFaturaInput = txtFaturaId.getText(); // Pega o ID inserido
                     if (idFaturaInput != null && !idFaturaInput.isEmpty()) {
                         int idFatura = Integer.parseInt(idFaturaInput); // Converte o ID para inteiro

                         Fatura fatura = faturaBanco.consultar(idFatura); // Consulta no banco de dados

                         if (fatura != null) {
                             // Adiciona os dados da fatura à tabela
                             table.removeAll();
                             TableItem item = new TableItem(table, SWT.NONE);
                             item.setText(new String[]{
                            		 String.valueOf(fatura.getNumeroFatura()),
                            		 String.valueOf(fatura.getLocacaoFatura().getIdLocacao()),
                                     fatura.getLocacaoFatura().getReservaLocacao().getClienteReserva().getNomeCompleto(),
                                     fatura.getDataEmissao().toString(),
                                     String.format("%.2f", fatura.getValorTotal()),
                                     fatura.getObservacoes(),
                                     String.valueOf(fatura.getNumeroFatura()),
                             });
                         } else {
                             // Exibe mensagem caso a fatura não seja encontrada
                             MessageBox messageBox = new MessageBox(shell, SWT.ICON_WARNING);
                             messageBox.setMessage("Fatura não encontrada.");
                             messageBox.open();
                         }
                     } else {
                         // Exibe mensagem caso o campo de ID esteja vazio
                         MessageBox messageBox = new MessageBox(shell, SWT.ICON_ERROR);
                         messageBox.setMessage("Digite um ID válido.");
                         messageBox.open();
                     }
                 } catch (NumberFormatException ex) {
                     // Exibe mensagem caso o ID não seja um número válido
                     MessageBox messageBox = new MessageBox(shell, SWT.ICON_ERROR);
                     messageBox.setMessage("ID inválido.");
                     messageBox.open();
                 }
             }
         });

         

        



    }
}
