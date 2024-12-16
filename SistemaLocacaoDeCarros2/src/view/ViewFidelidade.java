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


import banco.FidelidadeBanco;
import model.Cliente;
import model.Fidelidade;


public class ViewFidelidade {

	protected Shell shell;
	private FidelidadeBanco fidelidadeBanco;
	private Text txtPontos;
	private Text txtNivel;
	private Table table;
	private Cliente clienteSelecionado;

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

	public ViewFidelidade() {
		this.fidelidadeBanco = new FidelidadeBanco();

	}

	public static void main(String[] args) {
		try {
			ViewFidelidade window = new ViewFidelidade();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void createContents() {
		shell = new Shell();
		shell.setSize(740, 715);
		shell.setText("Fidelidade");

		Button btnSelecionarCliente = new Button(shell, SWT.NONE);
		btnSelecionarCliente.setBounds(205, 43, 150, 30);
		btnSelecionarCliente.setText("Selecionar Cliente");

		Label lblClienteSelecionado = new Label(shell, SWT.NONE);
		lblClienteSelecionado.setBounds(10, 51, 193, 30);
		lblClienteSelecionado.setText("Cliente: Nenhum selecionado");

		btnSelecionarCliente.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ViewSelecionarCliente viewClienteSelecionar = new ViewSelecionarCliente(); // Crie a instância da
																							// ViewCliente
				viewClienteSelecionar.open(); // Abre a janela de seleção
				clienteSelecionado = viewClienteSelecionar.getClienteSelecionado(); // Obtém o cliente selecionado
				if (clienteSelecionado != null) {
					lblClienteSelecionado.setText("Cliente: " + clienteSelecionado.getNomeCompleto());
				}
			}
		});

		Label lblPontos = new Label(shell, SWT.NONE);
		lblPontos.setText("Pontos");
		lblPontos.setBounds(25, 88, 55, 15);

		txtPontos = new Text(shell, SWT.BORDER);
		txtPontos.setBounds(129, 88, 160, 21);

		Label lblNivel = new Label(shell, SWT.NONE);
		lblNivel.setBounds(25, 138, 55, 15);
		lblNivel.setText("Nível");

		txtNivel = new Text(shell, SWT.BORDER);
		txtNivel.setBounds(129, 138, 160, 21);

		Label lblDataUltimaAtualizacao = new Label(shell, SWT.NONE);
		lblDataUltimaAtualizacao.setBounds(25, 191, 151, 21);
		lblDataUltimaAtualizacao.setText("Última Atualização");

		DateTime dateUltimaAtualizacao = new DateTime(shell, SWT.BORDER);
		dateUltimaAtualizacao.setBounds(182, 191, 80, 24);

		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(26, 316, 559, 290);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnIdFidelidade = new TableColumn(table, SWT.CENTER);
		tblclmnIdFidelidade.setWidth(100);
		tblclmnIdFidelidade.setText("ID Usuario");
		
		TableColumn tblclmnNome = new TableColumn(table, SWT.NONE);
		tblclmnNome.setWidth(100);
		tblclmnNome.setText("Nome ");

		TableColumn tblclmnPontos = new TableColumn(table, SWT.CENTER);
		tblclmnPontos.setWidth(100);
		tblclmnPontos.setText("Pontos");

		TableColumn tblclmnNivel = new TableColumn(table, SWT.CENTER);
		tblclmnNivel.setWidth(100);
		tblclmnNivel.setText("Nível");

		TableColumn tblclmnDataUltimaAtualizacao = new TableColumn(table, SWT.NONE);
		tblclmnDataUltimaAtualizacao.setWidth(156);
		tblclmnDataUltimaAtualizacao.setText("Última Atualização");

		Button btnCadastrarFidelidade = new Button(shell, SWT.NONE);
		btnCadastrarFidelidade.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Integer pontos = Integer.parseInt(txtPontos.getText());
				String nivel = txtNivel.getText();
				LocalDate dataUltimaAtt = LocalDate.of(dateUltimaAtualizacao.getYear(),
						dateUltimaAtualizacao.getMonth() + 1, dateUltimaAtualizacao.getDay());

				Fidelidade fidelidade = new Fidelidade(pontos, nivel, dataUltimaAtt, clienteSelecionado);
				fidelidadeBanco.incluir(fidelidade);

				MessageBox box = new MessageBox(shell, SWT.OK);
				box.setMessage("Fidelidade cadastrada com sucesso!");
				box.open();
			}
		});
		btnCadastrarFidelidade.setBounds(25, 256, 128, 25);
		btnCadastrarFidelidade.setText("Cadastrar Fidelidade");

		Button btnDeletarFidelidade = new Button(shell, SWT.NONE);
		btnDeletarFidelidade.setBounds(199, 256, 110, 25);
		btnDeletarFidelidade.setText("Deletar Fidelidade");

		Button btnListarFidelidade = new Button(shell, SWT.NONE);
		btnListarFidelidade.setBounds(353, 256, 128, 25);
		btnListarFidelidade.setText("Consultar Fidelidade");

		btnDeletarFidelidade.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int selectedIndex = table.getSelectionIndex();
				if (selectedIndex != -1) {
					TableItem item = table.getItem(selectedIndex);
					int idFidelidade = Integer.parseInt(item.getText(0));

					fidelidadeBanco.deletar(idFidelidade);
					MessageBox messageBox = new MessageBox(shell, SWT.OK);
					messageBox.setMessage(" Deletado com sucesso!");
					messageBox.open();

					btnListarFidelidade.notifyListeners(SWT.Selection, null);
				} else {
					MessageBox warningBox = new MessageBox(shell, SWT.ICON_WARNING);
					warningBox.setMessage("Selecione uma Fidelidade na tabela para deletar.");
					warningBox.open();
				}

			}
		});
//listar
		 btnListarFidelidade.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                List<Fidelidade> fidelidades = fidelidadeBanco.listar();
                table.removeAll();
                for (Fidelidade fidelidade : fidelidades) {
                    TableItem item = new TableItem(table, SWT.NONE);
                    item.setText(new String[] {
                        
                        String.valueOf(fidelidade.getClienteFidelidade().getIdUsuario()),
                        fidelidade.getClienteFidelidade().getNomeCompleto(),
                        String.valueOf(fidelidade.getPontos()),
                        fidelidade.getNivel(),
                        fidelidade.getDataUltimaAtualizacao().toString(),
                    });
                }
            }
        });

	




	}
}
