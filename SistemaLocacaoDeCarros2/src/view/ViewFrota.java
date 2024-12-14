package view;

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

import banco.FrotaBanco;
import model.Frota;

public class ViewFrota {

    protected Shell shell;
    private Text txtTotalVeiculos;
    private Text txtTotalDisponiveis;
    private Text txtTotalEmManutencao;
    private Text txtLocalizacao;
    private Table table;
    private FrotaBanco frotaBanco;

    public ViewFrota() {
        frotaBanco = new FrotaBanco();
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
        shell.setSize(597, 770);
        shell.setText("Frota");

        Label lblTotalVeiculos = new Label(shell, SWT.NONE);
        lblTotalVeiculos.setBounds(29, 34, 96, 15);
        lblTotalVeiculos.setText("Total de veículos");

        txtTotalVeiculos = new Text(shell, SWT.BORDER);
        txtTotalVeiculos.setBounds(134, 34, 175, 21);

        Label lblTotalDisponiveis = new Label(shell, SWT.NONE);
        lblTotalDisponiveis.setBounds(29, 81, 96, 15);
        lblTotalDisponiveis.setText("Total disponíveis");

        txtTotalDisponiveis = new Text(shell, SWT.BORDER);
        txtTotalDisponiveis.setBounds(134, 81, 175, 21);

        Label lblTotalEmManutencao = new Label(shell, SWT.NONE);
        lblTotalEmManutencao.setBounds(29, 135, 121, 15);
        lblTotalEmManutencao.setText("Total em manutenção");

        txtTotalEmManutencao = new Text(shell, SWT.BORDER);
        txtTotalEmManutencao.setBounds(156, 135, 153, 21);

        Label lblLocalizacaoVeiculo = new Label(shell, SWT.NONE);
        lblLocalizacaoVeiculo.setBounds(29, 187, 121, 15);
        lblLocalizacaoVeiculo.setText("Localização do veículo");

        txtLocalizacao = new Text(shell, SWT.BORDER);
        txtLocalizacao.setBounds(156, 187, 268, 21);

        table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
        table.setBounds(40, 323, 467, 321);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        TableColumn tblclmnTotalVeiculos = new TableColumn(table, SWT.NONE);
        tblclmnTotalVeiculos.setWidth(100);
        tblclmnTotalVeiculos.setText("Total de veículos");

        TableColumn tblclmnTotalDisponiveis = new TableColumn(table, SWT.NONE);
        tblclmnTotalDisponiveis.setWidth(100);
        tblclmnTotalDisponiveis.setText("Total disponíveis");

        TableColumn tblclmnTotalEmManutencao = new TableColumn(table, SWT.NONE);
        tblclmnTotalEmManutencao.setWidth(134);
        tblclmnTotalEmManutencao.setText("Total em manutenção");

        TableColumn tblclmnLocalizacao = new TableColumn(table, SWT.NONE);
        tblclmnLocalizacao.setWidth(132);
        tblclmnLocalizacao.setText("Localização");

        Button btnCadastrar = new Button(shell, SWT.NONE);
        btnCadastrar.setBounds(100, 258, 75, 25);
        btnCadastrar.setText("Cadastrar");

        Button btnConsultar = new Button(shell, SWT.NONE);
        btnConsultar.setBounds(349, 258, 75, 25);
        btnConsultar.setText("Consultar");

     
        btnCadastrar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                int totalVeiculos = Integer.parseInt(txtTotalVeiculos.getText());
                int totalDisponiveis = Integer.parseInt(txtTotalDisponiveis.getText());
                int totalEmManutencao = Integer.parseInt(txtTotalEmManutencao.getText());
                String localizacao = txtLocalizacao.getText();

                Frota frota = new Frota(totalVeiculos, totalDisponiveis, totalEmManutencao, localizacao);
                frotaBanco.incluir(frota);
                MessageBox box = new MessageBox(shell, SWT.OK);
                box.setMessage("Frota cadastrada com sucesso!");
                box.open();
            }
        });

        
        btnConsultar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                List<Frota> frotas = frotaBanco.listar();
                table.removeAll();
                for (Frota frota : frotas) {
                    TableItem item = new TableItem(table, SWT.NONE);
                    item.setText(new String[]{
                            String.valueOf(frota.getTotalVeiculos()),
                            String.valueOf(frota.getTotalDisponiveis()),
                            String.valueOf(frota.getTotalEmManutencao()),
                            frota.getLocalizacao()
                    });
                }
            }
        });
    }
}
