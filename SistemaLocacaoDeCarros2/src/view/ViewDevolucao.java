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

public class ViewDevolucao {
    private DevolucaoBanco devolucaoBanco;
    protected Shell shell;
    private Table table;

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

    protected void createContents() {
        shell = new Shell();
        shell.setSize(600, 600);
        shell.setText("Devolução - Locadora");

        Label lblDataDevolucao = new Label(shell, SWT.NONE);
        lblDataDevolucao.setText("Data da Devolução:");
        lblDataDevolucao.setBounds(20, 30, 120, 15);

        DateTime dateDevolucao = new DateTime(shell, SWT.BORDER);
        dateDevolucao.setBounds(140, 30, 120, 24);

        Label lblCondicao = new Label(shell, SWT.NONE);
        lblCondicao.setText("Condição do Veículo:");
        lblCondicao.setBounds(20, 80, 120, 15);

        Text txtCondicao = new Text(shell, SWT.BORDER);
        txtCondicao.setBounds(140, 80, 300, 60);

        Label lblTaxaAtraso = new Label(shell, SWT.NONE);
        lblTaxaAtraso.setText("Taxa de Atraso:");
        lblTaxaAtraso.setBounds(20, 160, 120, 15);

        Text txtTaxaAtraso = new Text(shell, SWT.BORDER);
        txtTaxaAtraso.setBounds(140, 160, 120, 30);

        Label lblStatus = new Label(shell, SWT.NONE);
        lblStatus.setText("Status da Devolução:");
        lblStatus.setBounds(20, 210, 120, 15);

        Text txtStatus = new Text(shell, SWT.BORDER);
        txtStatus.setBounds(140, 210, 120, 30);

        Button btnCadastrarDevolucao = new Button(shell, SWT.NONE);
        btnCadastrarDevolucao.setBounds(20, 260, 120, 30);
        btnCadastrarDevolucao.setText("Cadastrar Devolução");

        Button btnConsultarDevolucao = new Button(shell, SWT.NONE);
        btnConsultarDevolucao.setBounds(160, 260, 120, 30);
        btnConsultarDevolucao.setText("Consultar Devolução");

        Button btnDeletarDevolucao = new Button(shell, SWT.NONE);
        btnDeletarDevolucao.setBounds(300, 260, 120, 30);
        btnDeletarDevolucao.setText("Deletar Devolução");

        table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
        table.setBounds(20, 300, 500, 200);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);

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

      
        btnCadastrarDevolucao.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                String condicao = txtCondicao.getText();
                double taxaAtraso = Double.parseDouble(txtTaxaAtraso.getText());
               
                LocalDate dataDevolucao = LocalDate.of(dateDevolucao.getYear(), dateDevolucao.getMonth() + 1, dateDevolucao.getDay());

                Devolucao devolucao = new Devolucao(dataDevolucao, condicao, taxaAtraso,true);
                devolucaoBanco.incluir(devolucao);
                MessageBox messageBox = new MessageBox(shell, SWT.OK);
                messageBox.setMessage("Devolução cadastrada com sucesso!");
                messageBox.open();
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
                            devolucao.getDataDevolucao().toString(),
                            devolucao.getCondicaoVeiculo(),
                            String.valueOf(devolucao.getTaxaAtraso()),
                            devolucao.getStatusDevolucao() ? "Sim" : "Não"
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
                    int idDevolucao = Integer.parseInt(item.getText(0));  

                    devolucaoBanco.deletar(idDevolucao);
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

        btnAtualizarDevolucao.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] selectedItems = table.getSelection();

				if (selectedItems.length == 0) {
					MessageBox warningBox = new MessageBox(shell, SWT.ICON_WARNING);
					warningBox.setMessage("Selecione uma Devolucao na tabela para atualizar.");
					warningBox.open();
					btnListarDevolucao.notifyListeners(SWT.Selection, null);
					return;
				}

				try {
					        devolucao.getDataDevolucao().toString(),
                            devolucao.getCondicaoVeiculo(),
                            String.valueOf(devolucao.getTaxaAtraso()),
                            devolucao.getStatusDevolucao() ? "Sim" : "Não"

					// Atualizando o banco de dados
					devolucaoBanco.atualizar(fidelidade);

					// Mensagem de sucesso
					MessageBox box = new MessageBox(shell, SWT.OK);
					box.setMessage("Fidelidade atualizada com sucesso!");
					box.open();
					btnListarFidelidade.notifyListeners(SWT.Selection, null);
				} catch (Exception ex) {
					MessageBox errorBox = new MessageBox(shell, SWT.ICON_ERROR);
					errorBox.setMessage("Erro ao atualizar Fidelidade: " + ex.getMessage());
					errorBox.open();
				}
			}
		});












    }
}
