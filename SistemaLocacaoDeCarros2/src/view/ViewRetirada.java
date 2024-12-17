package view;

import java.time.LocalDate;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import banco.RetiradaBanco;
import model.Locacao;
import model.Retirada;

public class ViewRetirada {

    protected Shell shell;
    private Text textLocalRetirada;
    private Table table;
    private RetiradaBanco retiradaBanco;
    private Locacao locacaoSelecionada;

    public ViewRetirada() {
        retiradaBanco = new RetiradaBanco();
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
            ViewRetirada window = new ViewRetirada();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    protected void createContents() {
        shell = new Shell();
        shell.setSize(1111, 761);
        shell.setText("Retirada");
        
        Button btnSelecionarLocacao = new Button(shell, SWT.NONE);
        btnSelecionarLocacao.setBounds(230, 8, 150, 25);
        btnSelecionarLocacao.setText("Selecionar Locacao");

        Label lblLocacaoSelecionado = new Label(shell, SWT.NONE);
        lblLocacaoSelecionado.setBounds(25, 13, 200, 25);
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

        Label lblDataDaRetirada = new Label(shell, SWT.NONE);
        lblDataDaRetirada.setText("Data da Retirada:");
        lblDataDaRetirada.setBounds(10, 58, 101, 15);

        Label lblLocalDeRetirada = new Label(shell, SWT.NONE);
        lblLocalDeRetirada.setText("Local de Retirada:");
        lblLocalDeRetirada.setBounds(10, 90, 101, 15);

        textLocalRetirada = new Text(shell, SWT.BORDER);
        textLocalRetirada.setBounds(117, 87, 167, 21);

        Label lblDocumentosDeRetirada = new Label(shell, SWT.NONE);
        lblDocumentosDeRetirada.setText("Documentos Verificados?");
        lblDocumentosDeRetirada.setBounds(10, 125, 139, 15);

        Button btnCadastrarRetirada = new Button(shell, SWT.NONE);
        btnCadastrarRetirada.setText("Cadastrar Retirada");
        btnCadastrarRetirada.setBounds(70, 207, 150, 25);

        Button btnDeletarRetirada = new Button(shell, SWT.NONE);
        btnDeletarRetirada.setText("Deletar Retirada");
        btnDeletarRetirada.setBounds(272, 207, 134, 25);

        Button btnListarRetirada = new Button(shell, SWT.NONE);
        btnListarRetirada.setText("Listar Retirada");
        btnListarRetirada.setBounds(481, 207, 150, 25);

        DateTime dateTimeDataRetirada = new DateTime(shell, SWT.BORDER);
        dateTimeDataRetirada.setBounds(117, 49, 80, 24);
        
        dateTimeDataRetirada.setEnabled(false);

        Button btnDocVerifica = new Button(shell, SWT.RADIO);
        btnDocVerifica.setBounds(151, 124, 46, 16);
        btnDocVerifica.setText("Sim");

        Button btnRadioButtonNao = new Button(shell, SWT.RADIO);
        btnRadioButtonNao.setBounds(203, 124, 58, 16);
        btnRadioButtonNao.setText("Não");

        Label lblStatusDeRetirada = new Label(shell, SWT.NONE);
        lblStatusDeRetirada.setText("Status de Retirada:");
        lblStatusDeRetirada.setBounds(10, 160, 101, 15);
        
        Group group = new Group(shell, SWT.NONE);
        group.setBounds(129, 146, 200, 34);
        Button btnNo = new Button(group, SWT.RADIO);
        btnNo.setBounds(101, 10, 69, 16);
        btnNo.setText("A retirar");
        Button btnRetirado = new Button(group, SWT.RADIO);
        btnRetirado.setBounds(10, 10, 60, 16);
        btnRetirado.setText("Retirado");
        
        // Botão Atualizar
        Button btnAtualizar = new Button(shell, SWT.NONE);
        btnAtualizar.setBounds(360, 125, 100, 30);
        btnAtualizar.setText("Atualizar");

        // Botão Consultar por ID
        Button btnConsultarPorID = new Button(shell, SWT.NONE);
        btnConsultarPorID.setBounds(480, 125, 150, 30);
        btnConsultarPorID.setText("Consultar por ID");

        // Campo de texto para inserir o ID da Retirada
        Text txtBuscarID = new Text(shell, SWT.BORDER);
        txtBuscarID.setBounds(640, 130, 50, 25);

        // Botão Confirmar para buscar por ID
        Button btnConfirmarBuscarID = new Button(shell, SWT.NONE);
        btnConfirmarBuscarID.setBounds(700, 125, 100, 30);
        btnConfirmarBuscarID.setText("Confirmar");
        btnConfirmarBuscarID.setVisible(false); // Começa invisível
        txtBuscarID.setVisible(false); // Começa invisível

        table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
        table.setLinesVisible(true);
        table.setHeaderVisible(true);
        table.setBounds(70, 442, 833, 200);
        
        TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
        tblclmnNewColumn.setWidth(100);
        tblclmnNewColumn.setText("Id Retirada");
        
        TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
        tblclmnNewColumn_1.setWidth(100);
        tblclmnNewColumn_1.setText("ID Locacao");
        
        TableColumn tblclmnNome = new TableColumn(table, SWT.NONE);
        tblclmnNome.setWidth(100);
        tblclmnNome.setText("Nome");

        TableColumn tblclmnDataDaRetirada = new TableColumn(table, SWT.CENTER);
        tblclmnDataDaRetirada.setWidth(106);
        tblclmnDataDaRetirada.setText("Data da Retirada");

        TableColumn tblclmnLocalRetirada = new TableColumn(table, SWT.CENTER);
        tblclmnLocalRetirada.setWidth(124);
        tblclmnLocalRetirada.setText("Local de Retirada");

        TableColumn tblclmnDocumentosVerificados = new TableColumn(table, SWT.CENTER);
        tblclmnDocumentosVerificados.setWidth(141);
        tblclmnDocumentosVerificados.setText("Documentos Verificados");

        TableColumn tblclmnStatusRetirada = new TableColumn(table, SWT.CENTER);
        tblclmnStatusRetirada.setWidth(147);
        tblclmnStatusRetirada.setText("Status de Retirada");

        // Cadastrar Retirada
        btnCadastrarRetirada.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                String localRetirada = textLocalRetirada.getText();
                Boolean statusRetirada = btnRetirado.getSelection();
                // Obtendo data e hora atuais
                LocalDate dataRetirada = LocalDate.now();
                Boolean docVerificado = btnDocVerifica.getSelection();

                // Criando a retirada
                Retirada retirada = new Retirada(dataRetirada, localRetirada, docVerificado, statusRetirada,locacaoSelecionada);
                retiradaBanco.incluir(retirada);

                MessageBox box = new MessageBox(shell, SWT.OK);
                box.setMessage("Retirada cadastrada com sucesso!");
                box.open();
            }
        });

        // Deletar Retirada
        
        btnDeletarRetirada.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                int selectedIndex = table.getSelectionIndex();
                if(selectedIndex != -1) {
                    TableItem item = table.getItem(selectedIndex);
                    int idCancelamento = Integer.parseInt(item.getText(0));

                    retiradaBanco.deletar(idCancelamento);
                    MessageBox messageBox = new MessageBox(shell, SWT.OK);
                    messageBox.setMessage("Retirada deletado com sucesso!");
                    messageBox.open();  

                    btnListarRetirada.notifyListeners(SWT.Selection, null);
                } else {
                        MessageBox warningBox = new MessageBox(shell, SWT.ICON_WARNING);
                    warningBox.setMessage("Selecione uma Retirada na tabela para deletar.");
                    btnListarRetirada.notifyListeners(SWT.Selection, null);
                    warningBox.open();
                }
    
            }
        });
        // Listar Retirada
        btnListarRetirada.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                List<Retirada> retiradas = retiradaBanco.listar();
                table.removeAll();
                for (Retirada retirada : retiradas) {
                    TableItem item = new TableItem(table, SWT.NONE);
                    item.setText(new String[] {
                    		String.valueOf(retirada.getIdRetirada()),
                    		String.valueOf(retirada.getLocacaoRetirada().getIdLocacao()),
                    		retirada.getLocacaoRetirada().getReservaLocacao().getClienteReserva().getNomeCompleto(),
                    		retirada.getDataRetirada().toString(),
                            retirada.getLocalRetirada(),
                            retirada.getDocumentosVerificados() ? "Sim" : "Nao",
                            
                            retirada.getStatusRetirada() ? "Concluida" : "A Concluir"
                    });
                }
            }
        });
        
     // Lógica para o botão "Atualizar"
        btnAtualizar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                // Verifica se algum item da tabela foi selecionado
                TableItem[] selectedItems = table.getSelection();

                if (selectedItems.length == 0) {
                    MessageBox warningBox = new MessageBox(shell, SWT.ICON_WARNING);
                    warningBox.setMessage("Selecione uma retirada na tabela para atualizar.");
                    warningBox.open();
                    btnListarRetirada.notifyListeners(SWT.Selection, null);
                    
                    return;
                }

                try {
                    TableItem selectedItem = selectedItems[0];
                    Integer idRetirada = Integer.parseInt(selectedItem.getText(0)); // Assume que o ID está na primeira coluna da tabela
                	String localRetirada = textLocalRetirada.getText();
                	Boolean statusRetirada = btnRetirado.getSelection();

                    // Obtendo data e hora atuais
                    LocalDate dataRetirada = LocalDate.now();
                    Boolean docVerificado = btnDocVerifica.getSelection();

                    // Criando a retirada
                    Retirada retirada = new Retirada(idRetirada,dataRetirada, localRetirada, docVerificado, statusRetirada,locacaoSelecionada);
                    
                

                 

                   
                    retiradaBanco.atualizar(retirada);

                    MessageBox box = new MessageBox(shell, SWT.ICON_INFORMATION);
                    box.setMessage("Retirada atualizada com sucesso!");
                    box.open();

                    
                    btnListarRetirada.notifyListeners(SWT.Selection, null); // Força a consulta novamente para atualizar os dados na tabela
                } catch (Exception ex) {
                    MessageBox errorBox = new MessageBox(shell, SWT.ICON_ERROR);
                    errorBox.setMessage("Erro ao atualizar retirada: " + ex.getMessage());
                    errorBox.open();
                }
            }
        });

        // Lógica para o botão "Consultar por ID"
        btnConsultarPorID.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                // Exibe o campo para inserir o ID
                txtBuscarID.setVisible(true);
                btnConfirmarBuscarID.setVisible(true);
            }
        });

        // Lógica para o botão "Confirmar" após inserir o ID
        btnConfirmarBuscarID.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                try {
                    // Verifica se o campo de ID foi preenchido corretamente
                    String idText = txtBuscarID.getText();
                    if (idText == null || idText.isEmpty()) {
                        throw new NumberFormatException("ID inválido.");
                    }

                    Integer idRetirada = Integer.parseInt(idText); // Converte o ID inserido pelo usuário

                    // Chama o método para consultar a retirada
                    Retirada retirada = retiradaBanco.consultar(idRetirada);

                    if (retirada != null) {
                        // Se a retirada for encontrada, preenche a tabela com os dados
                        table.removeAll(); // Limpa a tabela antes de adicionar os novos dados
                        TableItem item = new TableItem(table, SWT.NONE);
                        item.setText(new String[] {
                        		String.valueOf(retirada.getIdRetirada()),
                        		String.valueOf(retirada.getLocacaoRetirada().getIdLocacao()),
                        		retirada.getLocacaoRetirada().getReservaLocacao().getClienteReserva().getNomeCompleto(),
                        		retirada.getDataRetirada().toString(),
                                retirada.getLocalRetirada(),
                                retirada.getDocumentosVerificados() ? "Sim" : "Nao",
                                retirada.getStatusRetirada() ? "Concluida" : "A Concluir"
                        });
                    } else {
                        // Exibe mensagem caso a retirada não seja encontrada
                        MessageBox box = new MessageBox(shell, SWT.ICON_WARNING);
                        box.setMessage("Retirada não encontrada.");
                        box.open();
                    }
                } catch (NumberFormatException ex) {
                    // Exibe mensagem de erro se o ID for inválido
                    MessageBox box = new MessageBox(shell, SWT.ICON_ERROR);
                    box.setMessage("ID inválido. Digite um número válido.");
                    box.open();
                }
            }
        });


        
        
    }
}
