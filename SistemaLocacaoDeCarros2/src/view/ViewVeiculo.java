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

import banco.VeiculoBanco;
import model.Veiculo;

public class ViewVeiculo {

    protected Shell shell;
    
    private Text textPlaca;
    private Text textChassi;
    private Text textCor;
    private Text textCategoria;
    private Text textMarca;
    private Text textQuilometragem;
    private Table table;
    private VeiculoBanco veiculoBanco;
    
    public ViewVeiculo() {
        this.veiculoBanco = new VeiculoBanco();
    }

    public static void main(String[] args) {
        try {
            ViewVeiculo window = new ViewVeiculo();
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
        shell.setSize(1425, 1018);
        shell.setText("Veículo");
        
       
        
        Label lblPlaca = new Label(shell, SWT.NONE);
        lblPlaca.setText("Placa:");
        lblPlaca.setBounds(10, 44, 45, 15);
        
        textPlaca = new Text(shell, SWT.BORDER);
        textPlaca.setBounds(61, 41, 134, 21);
        
        textChassi = new Text(shell, SWT.BORDER);
        textChassi.setBounds(69, 76, 134, 21);
        
        Label lblChassis = new Label(shell, SWT.NONE);
        lblChassis.setText("Chassi:");
        lblChassis.setBounds(10, 79, 53, 15);
        
        Label lblCor = new Label(shell, SWT.NONE);
        lblCor.setText("Cor:");
        lblCor.setBounds(10, 113, 45, 15);
        
        textCor = new Text(shell, SWT.BORDER);
        textCor.setBounds(57, 110, 80, 21);
        
        Label lblAno = new Label(shell, SWT.NONE);
        lblAno.setText("Ano:");
        lblAno.setBounds(10, 146, 45, 15);
        
        Label lblQuilometragen = new Label(shell, SWT.NONE);
        lblQuilometragen.setText("Quilometragem:");
        lblQuilometragen.setBounds(10, 182, 99, 15);
        
        Label lblStatusDeDisponibilidadew = new Label(shell, SWT.NONE);
        lblStatusDeDisponibilidadew.setText("Disponível?");
        lblStatusDeDisponibilidadew.setBounds(10, 219, 67, 15);
        
        Label lblCategoria = new Label(shell, SWT.NONE);
        lblCategoria.setText("Categoria:");
        lblCategoria.setBounds(10, 256, 67, 15);
        
        textCategoria = new Text(shell, SWT.BORDER);
        textCategoria.setBounds(83, 253, 81, 21);
        
        Label lblSeguroAtivo = new Label(shell, SWT.NONE);
        lblSeguroAtivo.setText("Seguro Ativo?");
        lblSeguroAtivo.setBounds(10, 293, 89, 15);
        
        Group group = new Group(shell, SWT.NONE);
        group.setBounds(100, 288, 155, 34);
        Button btnNo = new Button(group, SWT.RADIO);
        btnNo.setBounds(76, 10, 69, 16);
        btnNo.setText("Não");
        Button btnSim = new Button(group, SWT.RADIO);
        btnSim.setBounds(10, 10, 45, 16);
        btnSim.setText("Sim");
        
        
        textMarca = new Text(shell, SWT.BORDER);
        textMarca.setBounds(61, 328, 81, 21);
        
        Label lblMarca = new Label(shell, SWT.NONE);
        lblMarca.setText("Marca:");
        lblMarca.setBounds(10, 331, 53, 15);
        
        Label lblDataUltimaManutencao = new Label(shell, SWT.NONE);
        lblDataUltimaManutencao.setText("Data da Última Manutenção:");
        lblDataUltimaManutencao.setBounds(10, 368, 166, 15);
        DateTime dateTimeMan = new DateTime(shell, SWT.BORDER);
        dateTimeMan.setBounds(182, 368, 80, 24);
        
        DateTime dateTimeAno = new DateTime(shell, SWT.BORDER);
        dateTimeAno.setBounds(57, 137, 80, 24);
        
        textQuilometragem = new Text(shell, SWT.BORDER);
        textQuilometragem.setBounds(115, 176, 110, 21);
        
        Button btnDisponivel = new Button(shell, SWT.RADIO);
        btnDisponivel.setBounds(105, 218, 50, 16);
        btnDisponivel.setText("Sim");
        
        Button btnNaoDisponivel = new Button(shell, SWT.RADIO);
        btnNaoDisponivel.setBounds(171, 218, 53, 16);
        btnNaoDisponivel.setText("Não");

        Button btnCadastrarVeiculo = new Button(shell, SWT.NONE);
        btnCadastrarVeiculo.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
               
                String placaVeiculo = textPlaca.getText();
                String chassiVeiculo = textChassi.getText();
                String corVeiculo = textCor.getText();
                LocalDate anoVeiculo = LocalDate.of(dateTimeAno.getYear(), dateTimeAno.getMonth() + 1, dateTimeAno.getDay());
                LocalDate anoManu = LocalDate.of(dateTimeMan.getYear(), dateTimeMan.getMonth() + 1, dateTimeMan.getDay());
                Double quilometragemVeiculo = Double.parseDouble(textQuilometragem.getText());
                Boolean disponivelVeiculo = btnDisponivel.getSelection();
                String categoriaVeiculo = textCategoria.getText();
                Boolean seguroAtivo = btnSim.getSelection();
                String marcaVeiculo = textMarca.getText();

            
                Veiculo veiculo = new Veiculo( placaVeiculo, chassiVeiculo, corVeiculo, anoVeiculo, quilometragemVeiculo, disponivelVeiculo, categoriaVeiculo,seguroAtivo, marcaVeiculo, anoManu);
                veiculoBanco.incluir(veiculo);

                MessageBox box = new MessageBox(shell, SWT.OK);
                box.setMessage("Veículo cadastrado com sucesso!");
                box.open();
            }
        });
        btnCadastrarVeiculo.setText("Cadastrar Veículo");
        btnCadastrarVeiculo.setBounds(61, 412, 150, 25);
        
        
        Button btnListarVeiculo = new Button(shell, SWT.NONE);
        
        btnListarVeiculo.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                List<Veiculo> listaVeiculos = veiculoBanco.listar();
                table.removeAll();

               
                for (Veiculo veiculo : listaVeiculos) {
                    TableItem item = new TableItem(table, SWT.NONE);
                    item.setText(new String[]{
                        String.valueOf(veiculo.getIdVeiculo()),
                        veiculo.getPlaca(),
                        veiculo.getChassi(),
                        veiculo.getCor(),
                        veiculo.getAno().toString(),
                        String.format("%.2f", veiculo.getQuilometragem()),
                        veiculo.isStatusDisponibilidade() ? "Sim" : "Não",
                        veiculo.getCategoria(),
                        veiculo.isSeguroAtivo()? "Sim" : "Não",
                        veiculo.getMarca(),
                        veiculo.getDataUltimaManutencao().toString(),
                        
                    });
                }
            }
        });
        btnListarVeiculo.setText("Listar  Todos os Veículos");
        btnListarVeiculo.setBounds(688, 412, 150, 25);

        Button btnDeletarVeiculo = new Button(shell, SWT.NONE);
        btnDeletarVeiculo.setText("Deletar Veículo");
        btnDeletarVeiculo.setBounds(371, 412, 134, 25);
       
        btnDeletarVeiculo.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {

                TableItem[] selectedItems = table.getSelection();

          
                if (selectedItems.length == 0) {
                    MessageBox warningBox = new MessageBox(shell, SWT.ICON_WARNING);
                    warningBox.setMessage("Selecione um veículo na tabela para deletar.");
                    warningBox.open();
                    return;
                }

                try {
                   
                   Integer idVeiculo = Integer.parseInt(selectedItems[0].getText(0));
                   
                   Veiculo veiculoDeletar = new Veiculo();
                   veiculoDeletar.setIdVeiculo(idVeiculo);

                    veiculoBanco.deletar(veiculoDeletar);

                    MessageBox successBox = new MessageBox(shell, SWT.ICON_INFORMATION);
                    successBox.setMessage("Veículo deletado com sucesso!");
                    successBox.open();

                   
                    btnListarVeiculo.notifyListeners(SWT.Selection, null);
                } catch (NumberFormatException ex) {
                    MessageBox errorBox = new MessageBox(shell, SWT.ICON_ERROR);
                    errorBox.setMessage("ID do veículo inválido: " + ex.getMessage());
                    errorBox.open();
                } catch (Exception ex) {
                    MessageBox errorBox = new MessageBox(shell, SWT.ICON_ERROR);
                    errorBox.setMessage("Erro ao deletar veículo: " + ex.getMessage());
                    errorBox.open();
                }
            }
        });

        

        table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
        table.setLinesVisible(true);
        table.setHeaderVisible(true);
        table.setBounds(10, 458, 1227, 187);

        TableColumn tblclmnIdVeiculo = new TableColumn(table, SWT.CENTER);
        tblclmnIdVeiculo.setWidth(107);
        tblclmnIdVeiculo.setText("ID Veículo");

        TableColumn tblclmnPlaca = new TableColumn(table, SWT.CENTER);
        tblclmnPlaca.setWidth(136);
        tblclmnPlaca.setText("Placa");

        TableColumn tblclmnChassi = new TableColumn(table, SWT.CENTER);
        tblclmnChassi.setWidth(104);
        tblclmnChassi.setText("Chassi");

        TableColumn tblclmnCor = new TableColumn(table, SWT.CENTER);
        tblclmnCor.setWidth(75);
        tblclmnCor.setText("Cor");

        TableColumn tblclmnAno = new TableColumn(table, SWT.CENTER);
        tblclmnAno.setWidth(86);
        tblclmnAno.setText("Ano");

        TableColumn tblclmnQuilometragem = new TableColumn(table, SWT.CENTER);
        tblclmnQuilometragem.setWidth(111);
        tblclmnQuilometragem.setText("Quilometragem");

        TableColumn tblclmnStatusDeDisponibilidade = new TableColumn(table, SWT.CENTER);
        tblclmnStatusDeDisponibilidade.setWidth(146);
        tblclmnStatusDeDisponibilidade.setText("Status de Disponibilidade");

        TableColumn tblclmnCategoria = new TableColumn(table, SWT.CENTER);
        tblclmnCategoria.setWidth(100);
        tblclmnCategoria.setText("Categoria");

        TableColumn tblclmnMarca = new TableColumn(table, SWT.CENTER);
        tblclmnMarca.setWidth(83);
        tblclmnMarca.setText("Seguro Ativo?");
        
        TableColumn tblclmnMarca_1 = new TableColumn(table, SWT.NONE);
        tblclmnMarca_1.setWidth(100);
        tblclmnMarca_1.setText("Marca");
        
        
        Button btnAtualizarVeiculo = new Button(shell, SWT.NONE);
        btnAtualizarVeiculo.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                TableItem[] selectedItems = table.getSelection();
                
                if (selectedItems.length == 0) {
                    MessageBox warningBox = new MessageBox(shell, SWT.ICON_WARNING);
                    warningBox.setMessage("Selecione um veículo na tabela para atualizar.");
                    warningBox.open();
                    btnListarVeiculo.notifyListeners(SWT.Selection, null);
                    
                    return;
                }

                try {
                  
                    Integer idVeiculo = Integer.parseInt(selectedItems[0].getText(0));
                    String placaVeiculo = textPlaca.getText();
                    String chassiVeiculo = textChassi.getText();
                    String corVeiculo = textCor.getText();
                    LocalDate anoVeiculo = LocalDate.of(dateTimeAno.getYear(), dateTimeAno.getMonth() + 1, dateTimeAno.getDay());
                    LocalDate anoManu = LocalDate.of(dateTimeMan.getYear(), dateTimeMan.getMonth() + 1, dateTimeMan.getDay());
                    Double quilometragemVeiculo = Double.parseDouble(textQuilometragem.getText());
                    Boolean disponivelVeiculo = btnDisponivel.getSelection();
                    String categoriaVeiculo = textCategoria.getText();
                    Boolean seguroAtivo = btnSim.getSelection();
                    String marcaVeiculo = textMarca.getText();

                    
                    Veiculo veiculo = new Veiculo( idVeiculo,placaVeiculo, chassiVeiculo, corVeiculo, anoVeiculo, quilometragemVeiculo,
                                                   disponivelVeiculo, categoriaVeiculo, seguroAtivo, marcaVeiculo, anoManu);

                    veiculoBanco.atualizar(veiculo);

                    MessageBox successBox = new MessageBox(shell, SWT.ICON_INFORMATION);
                    successBox.setMessage("Veículo atualizado com sucesso!");
                    successBox.open();

                    
                    btnListarVeiculo.notifyListeners(SWT.Selection, null);
                } catch (NumberFormatException ex) {
                    MessageBox errorBox = new MessageBox(shell, SWT.ICON_ERROR);
                    errorBox.setMessage("Erro ao atualizar veículo: ID inválido ou valores incorretos.");
                    errorBox.open();
                } catch (Exception ex) {
                    MessageBox errorBox = new MessageBox(shell, SWT.ICON_ERROR);
                    errorBox.setMessage("Erro ao atualizar veículo: " + ex.getMessage());
                    errorBox.open();
                }
            }
        });
        btnAtualizarVeiculo.setText("Atualizar Veículo");
        btnAtualizarVeiculo.setBounds(231, 412, 134, 25);
       
     
        Button btnConsultarId = new Button(shell, SWT.NONE);
        btnConsultarId.setText("Consultar Veículo por ID");
        btnConsultarId.setBounds(521, 409, 150, 30);
        btnConsultarId.setVisible(false); 

        
        Text txtVeiculoId = new Text(shell, SWT.BORDER);
        txtVeiculoId.setBounds(540, 328, 100, 25);
        txtVeiculoId.setVisible(false); 

        
        Button btnConfirmarId = new Button(shell, SWT.NONE);
        btnConfirmarId.setText("Confirmar ID");
        btnConfirmarId.setBounds(521, 360, 150, 30);
        btnConfirmarId.setVisible(false); 

       
        btnConsultarId.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                txtVeiculoId.setVisible(true); 
                btnConfirmarId.setVisible(true); 
            }
        });

        
        btnConfirmarId.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                try {
                    String idVeiculoInput = txtVeiculoId.getText(); 
                    if (idVeiculoInput != null && !idVeiculoInput.isEmpty()) {
                       Integer idVeiculo = Integer.parseInt(idVeiculoInput); 
                       
                       Veiculo veiculoConsultar = new Veiculo();
                       veiculoConsultar.setIdVeiculo(idVeiculo);

                        Veiculo veiculo = veiculoBanco.consultar(veiculoConsultar); 
                        if (veiculo != null) {
                            
                            table.removeAll();
                            TableItem item = new TableItem(table, SWT.NONE);
                            item.setText(new String[]{
                                String.valueOf(veiculo.getIdVeiculo()),
                                veiculo.getPlaca(),
                                veiculo.getChassi(),
                                veiculo.getCor(),
                                veiculo.getAno().toString(),
                                String.format("%.2f", veiculo.getQuilometragem()),
                                veiculo.isStatusDisponibilidade() ? "Sim" : "Não",
                                veiculo.getCategoria(),
                                veiculo.isSeguroAtivo() ? "Sim" : "Não",
                                veiculo.getMarca(),
                                veiculo.getDataUltimaManutencao().toString()
                            });
                        } else {
                            
                            MessageBox messageBox = new MessageBox(shell, SWT.ICON_WARNING);
                            messageBox.setMessage("Veículo não encontrado.");
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

    
        btnConsultarId.setVisible(true); 
    }
}
