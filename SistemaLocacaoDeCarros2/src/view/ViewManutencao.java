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

import banco.ManutencaoBanco;
import model.Manutencao;
import model.Veiculo;

public class ViewManutencao {

    protected Shell shell;
    
    private Text txtDescricao;
    private Text txtCusto;
    private Table table;
    private ManutencaoBanco manutencaoBanco;
    private Veiculo veiculoSelecionado;
    private Text textTipoManu;

    public ViewManutencao() {
        manutencaoBanco = new ManutencaoBanco();
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
            ViewManutencao window = new ViewManutencao();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void createContents() {
        shell = new Shell();
        shell.setSize(1112, 987);
        shell.setText("Manutenção");

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

        Label lblDataManutencao = new Label(shell, SWT.NONE);
        lblDataManutencao.setBounds(27, 73, 150, 15);
        lblDataManutencao.setText("Data da Manutenção");

        DateTime dateManutencao = new DateTime(shell, SWT.BORDER);
        dateManutencao.setBounds(180, 73, 80, 24);

        Label lblDescricao = new Label(shell, SWT.NONE);
        lblDescricao.setBounds(27, 136, 150, 15);
        lblDescricao.setText("Descrição da Manutenção");

        txtDescricao = new Text(shell, SWT.BORDER);
        txtDescricao.setBounds(184, 133, 200, 21);

        Label lblCusto = new Label(shell, SWT.NONE);
        lblCusto.setBounds(27, 170, 150, 15);
        lblCusto.setText("Custo");

        txtCusto = new Text(shell, SWT.BORDER);
        txtCusto.setBounds(180, 167, 153, 21);

        Button btnCadastrarManutencao = new Button(shell, SWT.NONE);
        btnCadastrarManutencao.setBounds(74, 230, 75, 25);
        btnCadastrarManutencao.setText("Cadastrar");

        Button btnDeletarManutencao = new Button(shell, SWT.NONE);
        btnDeletarManutencao.setBounds(221, 230, 105, 25);
        btnDeletarManutencao.setText("Deletar Manutenção");

        Button btnListarManutencao = new Button(shell, SWT.NONE);
        btnListarManutencao.setBounds(385, 230, 105, 25);
        btnListarManutencao.setText("Consultar Manutenção");

        table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
        table.setBounds(41, 280, 964, 269);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        TableColumn tblclmnIdManutencao = new TableColumn(table, SWT.NONE);
        tblclmnIdManutencao.setWidth(100);
        tblclmnIdManutencao.setText("ID Manutenção");
        
        TableColumn tblclmnTipo = new TableColumn(table, SWT.NONE);
        tblclmnTipo.setWidth(100);
        tblclmnTipo.setText("Tipo");

        TableColumn tblclmnDataManutencao = new TableColumn(table, SWT.NONE);
        tblclmnDataManutencao.setWidth(100);
        tblclmnDataManutencao.setText("Data da Manutenção");

        TableColumn tblclmnDescricao = new TableColumn(table, SWT.NONE);
        tblclmnDescricao.setWidth(200);
        tblclmnDescricao.setText("Descrição");
        
        TableColumn tblclmnMarca = new TableColumn(table, SWT.NONE);
        tblclmnMarca.setWidth(100);
        tblclmnMarca.setText("Marca");
        
        TableColumn tblclmnPlaca = new TableColumn(table, SWT.NONE);
        tblclmnPlaca.setWidth(100);
        tblclmnPlaca.setText("Placa");
        
        TableColumn tblclmnDataUltimaManutencao = new TableColumn(table, SWT.NONE);
        tblclmnDataUltimaManutencao.setWidth(159);
        tblclmnDataUltimaManutencao.setText("Data Ultima Manutencao");

        TableColumn tblclmnCusto = new TableColumn(table, SWT.CENTER);
        tblclmnCusto.setWidth(100);
        tblclmnCusto.setText("Custo");
        
        Label lblTipoManu = new Label(shell, SWT.NONE);
        lblTipoManu.setBounds(28, 102, 105, 15);
        lblTipoManu.setText("Tipo Manutencao");
        
        textTipoManu = new Text(shell, SWT.BORDER);
        textTipoManu.setBounds(190, 106, 76, 21);

        btnCadastrarManutencao.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                LocalDate dataManutencao = LocalDate.of(dateManutencao.getYear(), dateManutencao.getMonth() + 1, dateManutencao.getDay());
                String descricao = txtDescricao.getText();
                double custo = Double.parseDouble(txtCusto.getText());
                String tipoManu = textTipoManu.getText();

                Manutencao manutencao = new Manutencao(dataManutencao, tipoManu, custo, descricao, veiculoSelecionado);
                manutencaoBanco.incluir(manutencao);

                MessageBox box = new MessageBox(shell, SWT.OK);
                box.setMessage("Manutenção cadastrada com sucesso!");
                box.open();
            }
        });

        btnListarManutencao.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                List<Manutencao> manutencaoList = manutencaoBanco.listar();
                table.removeAll();
                for (Manutencao manutencao : manutencaoList) {
                    TableItem item = new TableItem(table, SWT.NONE);
                    item.setText(new String[] {
                        String.valueOf(manutencao.getIdManutencao()),
                        manutencao.getTipoManutencao(),
                        manutencao.getDataManutencao().toString(),
                        manutencao.getDescricao(),
                        manutencao.getVeiculoManutencao().getMarca(),
                        manutencao.getVeiculoManutencao().getPlaca(),
                        manutencao.getVeiculoManutencao().getDataUltimaManutencao().toString(),
                        String.valueOf(manutencao.getCusto())
                    });
                }
            }
        });

        btnDeletarManutencao.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                TableItem[] selectedItems = table.getSelection();

                if (selectedItems.length == 0) {
                    MessageBox warningBox = new MessageBox(shell, SWT.ICON_WARNING);
                    warningBox.setMessage("Selecione uma Manutenção na tabela para deletar.");
                    warningBox.open();
                    return;
                }

                try {
                    Integer idManutencao = Integer.parseInt(selectedItems[0].getText(0));
                    manutencaoBanco.deletar(idManutencao);
                    MessageBox successBox = new MessageBox(shell, SWT.ICON_INFORMATION);
                    successBox.setMessage("Manutenção deletada com sucesso!");
                    successBox.open();
                    btnListarManutencao.notifyListeners(SWT.Selection, null);
                } catch (NumberFormatException ex) {
                    MessageBox errorBox = new MessageBox(shell, SWT.ICON_ERROR);
                    errorBox.setMessage("Erro ao deletar manutenção.");
                    errorBox.open();
                }
            }
        });
    }
}
