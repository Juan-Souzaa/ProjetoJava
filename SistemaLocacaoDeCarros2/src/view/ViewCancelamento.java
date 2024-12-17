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

import banco.CancelamentoBanco;
import model.Cancelamento;
import model.Locacao;

public class ViewCancelamento {
    private CancelamentoBanco cancelamentoBanco;
    protected Shell shell;
    private Table table;
    private Text txtMotivo;
    private Locacao locacaoSelecionada;

    public ViewCancelamento() {
        this.cancelamentoBanco = new CancelamentoBanco();
    }

    public static void main(String[] args) {
        try {
            ViewCancelamento window = new ViewCancelamento();
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
        shell.setSize(860, 639);
        shell.setText("Cancelamento - Locadora");
        
        
        Button btnSelecionarLocacao = new Button(shell, SWT.NONE);
        btnSelecionarLocacao.setBounds(215, 8, 150, 25);
        btnSelecionarLocacao.setText("Selecionar Locacao");

        Label lblLocacaoSelecionado = new Label(shell, SWT.NONE);
        lblLocacaoSelecionado.setBounds(23, 13, 200, 25);
        lblLocacaoSelecionado.setText("Nenhuma Locacao selecionada");

       
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

        Label lblMotivo = new Label(shell, SWT.NONE);
        lblMotivo.setBounds(23, 44, 150, 20);
        lblMotivo.setText("Motivo do Cancelamento:");

        txtMotivo = new Text(shell, SWT.BORDER);
        txtMotivo.setBounds(200, 41, 300, 20);

        Label lblData = new Label(shell, SWT.NONE);
        lblData.setBounds(20, 70, 150, 20);
        lblData.setText("Data do Cancelamento:");

        DateTime dateCancelamento = new DateTime(shell, SWT.BORDER | SWT.DATE | SWT.DROP_DOWN);
        dateCancelamento.setBounds(200, 70, 150, 25);

        Button btnCadastrar = new Button(shell, SWT.NONE);
        btnCadastrar.setBounds(20, 120, 100, 30);
        btnCadastrar.setText("Cadastrar");

        Button btnConsultar = new Button(shell, SWT.NONE);
        btnConsultar.setBounds(140, 120, 100, 30);
        btnConsultar.setText("Consultar");

        table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
        table.setBounds(20, 170, 650, 250);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        
        TableColumn tblclmnIdCancelamento = new TableColumn(table, SWT.NONE);
        tblclmnIdCancelamento.setWidth(120);
        tblclmnIdCancelamento.setText("ID Cancelamento");
        
        TableColumn tblclmnIdLocacao = new TableColumn(table, SWT.NONE);
        tblclmnIdLocacao.setWidth(100);
        tblclmnIdLocacao.setText("ID Locacao");
        
        TableColumn tblclmnNome = new TableColumn(table, SWT.NONE);
        tblclmnNome.setWidth(100);
        tblclmnNome.setText("Nome");

        
        TableColumn colMotivo = new TableColumn(table, SWT.NONE);
        colMotivo.setWidth(300);
        colMotivo.setText("Motivo");

        TableColumn colData = new TableColumn(table, SWT.NONE);
        colData.setWidth(300);
        colData.setText("Data");
        
        Button btnDeletar = new Button(shell, SWT.NONE);
        btnDeletar.setBounds(275, 125, 75, 25);
        btnDeletar.setText("Deletar");
        
   
        Button btnAtualizar = new Button(shell, SWT.NONE);
        btnAtualizar.setBounds(360, 125, 100, 30);
        btnAtualizar.setText("Atualizar");

      
        Button btnBuscarPorID = new Button(shell, SWT.NONE);
        btnBuscarPorID.setBounds(480, 125, 150, 30);
        btnBuscarPorID.setText("Buscar por ID");

        
        Text txtBuscarID = new Text(shell, SWT.BORDER);
        txtBuscarID.setBounds(640, 130, 50, 25);

        
        Button btnConfirmarBuscarID = new Button(shell, SWT.NONE);
        btnConfirmarBuscarID.setBounds(700, 125, 100, 30);
        btnConfirmarBuscarID.setText("Confirmar");
        btnConfirmarBuscarID.setVisible(false);
        txtBuscarID.setVisible(false);


     
        btnCadastrar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            	
            	
            	if (locacaoSelecionada == null) {
                    MessageBox warningBox = new MessageBox(shell, SWT.ICON_WARNING);
                    warningBox.setMessage("Selecione uma locação antes de cadastrar o cancelamento.");
                    warningBox.open();
                    return;
                }

                String motivo = txtMotivo.getText().trim();
                if (motivo.isEmpty()) {
                    MessageBox warningBox = new MessageBox(shell, SWT.ICON_WARNING);
                    warningBox.setMessage("O motivo do cancelamento é obrigatório.");
                    warningBox.open();
                    return;
                }
            	
            	
                motivo = txtMotivo.getText();
                LocalDate data = LocalDate.of(
                        dateCancelamento.getYear(),
                        dateCancelamento.getMonth() + 1,
                        dateCancelamento.getDay()
                );

                Cancelamento cancelamento = new Cancelamento(motivo, data,locacaoSelecionada);
                cancelamentoBanco.incluir(cancelamento);

                MessageBox messageBox = new MessageBox(shell, SWT.OK);
                messageBox.setMessage("Cancelamento cadastrado com sucesso!");
                messageBox.open();
                
                btnConsultar.notifyListeners(SWT.Selection, null);
                txtMotivo.setText("");
                locacaoSelecionada = null;
                lblLocacaoSelecionado.setText("Nenhuma Locação selecionada");
            }
        });

       
        btnConsultar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                List<Cancelamento> cancelamentos = cancelamentoBanco.listar();
                table.removeAll();
                for (Cancelamento cancelamento : cancelamentos) {
                    TableItem item = new TableItem(table, SWT.NONE);
                    item.setText(new String[] {
                    		String.valueOf(cancelamento.getIdCancelamento()),
                    		String.valueOf(cancelamento.getLocacaoCancelamento().getIdLocacao()),
                    		cancelamento.getLocacaoCancelamento().getReservaLocacao().getClienteReserva().getNomeCompleto(),
                            cancelamento.getMotivoCancelamento(),
                            cancelamento.getDataCancelamento().toString()
                    });
                }
            }
        });

        btnDeletar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                int selectedIndex = table.getSelectionIndex();
                if(selectedIndex != -1) {
                    TableItem item = table.getItem(selectedIndex);
                    Integer idCancelamento = Integer.parseInt(item.getText(0));
                    
                    Cancelamento cancelamentoDeletar = new Cancelamento();
                    cancelamentoDeletar.setIdCancelamento(idCancelamento);

                    cancelamentoBanco.deletar(cancelamentoDeletar);
                    MessageBox messageBox = new MessageBox(shell, SWT.OK);
                    messageBox.setMessage("Cancelamento deletado com sucesso!");
                    messageBox.open();  

                    btnConsultar.notifyListeners(SWT.Selection, null);
                } else {
                        MessageBox warningBox = new MessageBox(shell, SWT.ICON_WARNING);
                    warningBox.setMessage("Selecione um cancelamento na tabela para deletar.");
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
                    warningBox.setMessage("Selecione um cancelamento na tabela para atualizar.");
                    warningBox.open();
                    btnConsultar.notifyListeners(SWT.Selection, null);
                    return;
                }
                
            	if (locacaoSelecionada == null) {
                    MessageBox warningBox = new MessageBox(shell, SWT.ICON_WARNING);
                    warningBox.setMessage("Selecione uma locação antes de atualizar o cancelamento.");
                    warningBox.open();
                    return;
                }

                String motivo = txtMotivo.getText().trim();
                if (motivo.isEmpty()) {
                    MessageBox warningBox = new MessageBox(shell, SWT.ICON_WARNING);
                    warningBox.setMessage("O motivo do cancelamento é obrigatório.");
                    warningBox.open();
                    return;
                }
            	


                try {
                 
                    motivo = txtMotivo.getText();
                    LocalDate data = LocalDate.of(dateCancelamento.getYear(), dateCancelamento.getMonth() + 1, dateCancelamento.getDay());

                    
                    TableItem selectedItem = selectedItems[0];
                    Integer idCancelamento = Integer.parseInt(selectedItem.getText(0));

                  
                    Cancelamento cancelamento = new Cancelamento(idCancelamento, motivo, data, locacaoSelecionada);
                    cancelamentoBanco.atualizar(cancelamento);

                    MessageBox box = new MessageBox(shell, SWT.ICON_INFORMATION);
                    box.setMessage("Cancelamento atualizado com sucesso!");
                    box.open();

                    btnConsultar.notifyListeners(SWT.Selection, null); // Atualiza a tabela
                } catch (Exception ex) {
                    MessageBox errorBox = new MessageBox(shell, SWT.ICON_ERROR);
                    errorBox.setMessage("Erro ao atualizar cancelamento: " + ex.getMessage());
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

                    Integer idCancelamento = Integer.parseInt(idText);
                    
                    Cancelamento cancelamentoConsulta = new Cancelamento();
                    cancelamentoConsulta.setIdCancelamento(idCancelamento);
                    
                    Cancelamento cancelamento = cancelamentoBanco.consultar(cancelamentoConsulta);

                    if (cancelamento != null) {
                        table.removeAll();
                        TableItem item = new TableItem(table, SWT.NONE);
                        item.setText(new String[] {
                        		String.valueOf(cancelamento.getIdCancelamento()),
                        		String.valueOf(cancelamento.getLocacaoCancelamento().getIdLocacao()),
                        		cancelamento.getLocacaoCancelamento().getReservaLocacao().getClienteReserva().getNomeCompleto(),
                                cancelamento.getMotivoCancelamento(),
                                cancelamento.getDataCancelamento().toString()
                        });
                    } else {
                        MessageBox box = new MessageBox(shell, SWT.ICON_WARNING);
                        box.setMessage("Cancelamento não encontrado.");
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
