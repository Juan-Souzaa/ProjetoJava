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

import banco.MultaBanco;
import model.Multa;
import model.Veiculo;

public class ViewMulta {

    protected Shell shell;
    
    private Text txtDescricao;
    private Text txtValor;
    private Table table;
    private MultaBanco multaBanco;
    private Veiculo veiculoSelecionado;
    private Text textMotivoMulta;
    private Text textStatusMulta;

    public ViewMulta() {
        multaBanco = new MultaBanco();
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
            ViewMulta window = new ViewMulta();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void createContents() {
        shell = new Shell();
        shell.setSize(1112, 987);
        shell.setText("Multa");

        Button btnSelecionarVeiculo = new Button(shell, SWT.NONE);
        btnSelecionarVeiculo.setBounds(6, 27, 150, 30);
        btnSelecionarVeiculo.setText("Selecionar Veículo");

        Label lblVeiculoSelecionado = new Label(shell, SWT.NONE);
        lblVeiculoSelecionado.setBounds(184, 27, 200, 25);
        lblVeiculoSelecionado.setText("Nenhum veículo selecionado");

        btnSelecionarVeiculo.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ViewSelecionarVeiculo viewSelecionarVeiculo = new ViewSelecionarVeiculo();
                veiculoSelecionado = viewSelecionarVeiculo.open();

                if (veiculoSelecionado != null) {
                    lblVeiculoSelecionado.setText("Veículo: " + veiculoSelecionado.getPlaca());
                }
            }
        });

        Label lblDataMulta = new Label(shell, SWT.NONE);
        lblDataMulta.setBounds(24, 107, 150, 15);
        lblDataMulta.setText("Data da Multa");

        DateTime dateMulta = new DateTime(shell, SWT.BORDER);
        dateMulta.setBounds(180, 94, 80, 24);

        Label lblDescricao = new Label(shell, SWT.NONE);
        lblDescricao.setBounds(24, 179, 150, 15);
        lblDescricao.setText("Descrição da Multa");

        txtDescricao = new Text(shell, SWT.BORDER);
        txtDescricao.setBounds(184, 176, 200, 21);

        Label lblValor = new Label(shell, SWT.NONE);
        lblValor.setBounds(24, 206, 150, 15);
        lblValor.setText("Valor");

        txtValor = new Text(shell, SWT.BORDER);
        txtValor.setBounds(180, 203, 153, 21);

        Button btnCadastrarMulta = new Button(shell, SWT.NONE);
        btnCadastrarMulta.setBounds(74, 230, 75, 25);
        btnCadastrarMulta.setText("Cadastrar");

        Button btnDeletarMulta = new Button(shell, SWT.NONE);
        btnDeletarMulta.setBounds(221, 230, 105, 25);
        btnDeletarMulta.setText("Deletar Multa");

        Button btnListarMulta = new Button(shell, SWT.NONE);
        btnListarMulta.setBounds(385, 230, 105, 25);
        btnListarMulta.setText("Consultar Multa");

        table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
        table.setBounds(41, 280, 902, 276);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        TableColumn tblclmnIdMulta = new TableColumn(table, SWT.NONE);
        tblclmnIdMulta.setWidth(100);
        tblclmnIdMulta.setText("ID Multa");
        
        TableColumn tblclmnTipo = new TableColumn(table, SWT.NONE);
        tblclmnTipo.setWidth(100);
        tblclmnTipo.setText("Motivo");

        TableColumn tblclmnDataMulta = new TableColumn(table, SWT.NONE);
        tblclmnDataMulta.setWidth(100);
        tblclmnDataMulta.setText("Data da Multa");

        TableColumn tblclmnDescricao = new TableColumn(table, SWT.NONE);
        tblclmnDescricao.setWidth(200);
        tblclmnDescricao.setText("Descrição");
        
        TableColumn tblclmnStatusMulta = new TableColumn(table, SWT.NONE);
        tblclmnStatusMulta.setWidth(100);
        tblclmnStatusMulta.setText("Status Multa");
        
        TableColumn tblclmnPlaca = new TableColumn(table, SWT.NONE);
        tblclmnPlaca.setWidth(100);
        tblclmnPlaca.setText("Placa");
        
        TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
        tblclmnNewColumn.setWidth(100);
        tblclmnNewColumn.setText("Chassi");

        TableColumn tblclmnValor = new TableColumn(table, SWT.CENTER);
        tblclmnValor.setWidth(100);
        tblclmnValor.setText("Valor");
        
        Label lblNewLabel = new Label(shell, SWT.NONE);
        lblNewLabel.setBounds(57, 63, 55, 15);
        lblNewLabel.setText("Motivo");
        
        textMotivoMulta = new Text(shell, SWT.BORDER);
        textMotivoMulta.setBounds(180, 58, 76, 21);
        
        Label lblNewLabel_1 = new Label(shell, SWT.NONE);
        lblNewLabel_1.setBounds(24, 158, 88, 15);
        lblNewLabel_1.setText("Status Multa ");
        
        textStatusMulta = new Text(shell, SWT.BORDER);
        textStatusMulta.setBounds(133, 152, 76, 21);

        btnCadastrarMulta.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                LocalDate dataMulta = LocalDate.of(dateMulta.getYear(), dateMulta.getMonth() + 1, dateMulta.getDay());
                String descricao = txtDescricao.getText();
                double valor = Double.parseDouble(txtValor.getText());
                String statusMulta = textStatusMulta.getText();
                String motivoMulta = textMotivoMulta.getText();
                

                Multa multa = new Multa(motivoMulta, valor,dataMulta,statusMulta, descricao, veiculoSelecionado);
                multaBanco.incluir(multa);

                MessageBox box = new MessageBox(shell, SWT.OK);
                box.setMessage("Multa cadastrada com sucesso!");
                box.open();
            }
        });

        btnListarMulta.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                List<Multa> multaList = multaBanco.listar();
                table.removeAll();
                for (Multa multa : multaList) {
                    TableItem item = new TableItem(table, SWT.NONE);
                    item.setText(new String[] {
                        String.valueOf(multa.getIdMulta()),
                        multa.getMotivo(),
                        multa.getDataOcorrencia().toString(),
                        multa.getObservacoes(),
                        multa.getStatusMulta(),
                        multa.getVeiculo().getPlaca(),
                        multa.getVeiculo().getChassi(),
                        String.valueOf(multa.getValorMulta())
                    });
                }
            }
        });

        btnDeletarMulta.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                TableItem[] selectedItems = table.getSelection();

                if (selectedItems.length == 0) {
                    MessageBox warningBox = new MessageBox(shell, SWT.ICON_WARNING);
                    warningBox.setMessage("Selecione uma Multa na tabela para deletar.");
                    warningBox.open();
                    return;
                }

                try {
                    Integer idMulta = Integer.parseInt(selectedItems[0].getText(0));
                    multaBanco.deletar(idMulta);
                    MessageBox successBox = new MessageBox(shell, SWT.ICON_INFORMATION);
                    successBox.setMessage("Multa deletada com sucesso!");
                    successBox.open();
                    btnListarMulta.notifyListeners(SWT.Selection, null);
                } catch (NumberFormatException ex) {
                    MessageBox errorBox = new MessageBox(shell, SWT.ICON_ERROR);
                    errorBox.setMessage("Erro ao deletar multa.");
                    errorBox.open();
                }
            }
        });
    }
}

