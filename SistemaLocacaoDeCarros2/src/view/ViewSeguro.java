package view;

import java.time.LocalDate;
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

import banco.SeguroBanco;
import model.Locacao;
import model.Seguro;
import org.eclipse.swt.widgets.DateTime;

public class ViewSeguro {

    protected Shell shell;
    private Text textTipoSeguro;
    private Text textValorCobertura;
    private Text textFranquia;
    private Table table;
    private SeguroBanco seguroBanco;
    private Locacao locacaoSelecionada;

    public ViewSeguro() {
        this.seguroBanco = new SeguroBanco();
    }

    public static void main(String[] args) {
        try {
            ViewSeguro window = new ViewSeguro();
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
        shell.setSize(1034, 667);
        shell.setText("Seguro");
        
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

        Label lblTipoDeSeguro = new Label(shell, SWT.NONE);
        lblTipoDeSeguro.setText("Tipo de Seguro:");
        lblTipoDeSeguro.setBounds(10, 42, 92, 15);

        textTipoSeguro = new Text(shell, SWT.BORDER);
        textTipoSeguro.setBounds(108, 39, 99, 21);

        Label lblValorDeCobertura = new Label(shell, SWT.NONE);
        lblValorDeCobertura.setText("Valor da cobertura:");
        lblValorDeCobertura.setBounds(10, 78, 114, 15);

        textValorCobertura = new Text(shell, SWT.BORDER);
        textValorCobertura.setBounds(130, 75, 110, 21);

        Label lblFranquia = new Label(shell, SWT.NONE);
        lblFranquia.setText("Franquia:");
        lblFranquia.setBounds(10, 119, 66, 15);

        Label lblVigencia = new Label(shell, SWT.NONE);
        lblVigencia.setText("Vigência:");
        lblVigencia.setBounds(10, 162, 66, 15);

        Button btnCadastrarSeguro = new Button(shell, SWT.NONE);
        btnCadastrarSeguro.setText("Cadastrar Seguro");
        btnCadastrarSeguro.setBounds(57, 225, 150, 25);

        Button btnDeletarSeguro = new Button(shell, SWT.NONE);
        btnDeletarSeguro.setText("Deletar Seguro");
        btnDeletarSeguro.setBounds(332, 225, 134, 25);

        Button btnListarSeguro = new Button(shell, SWT.NONE);
        btnListarSeguro.setText("Consultar Seguro");
        btnListarSeguro.setBounds(593, 225, 150, 25);

        textFranquia = new Text(shell, SWT.BORDER);
        textFranquia.setBounds(82, 113, 81, 21);

        table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
        table.setLinesVisible(true);
        table.setHeaderVisible(true);
        table.setBounds(82, 356, 851, 200);

        TableColumn tblclmnIdSeguro = new TableColumn(table, SWT.CENTER);
        tblclmnIdSeguro.setWidth(106);
        tblclmnIdSeguro.setText("ID Cliente");
        
        TableColumn tblclmnIdLocacao = new TableColumn(table, SWT.NONE);
        tblclmnIdLocacao.setWidth(100);
        tblclmnIdLocacao.setText("Id Locacao");
        
        TableColumn tblclmnNome = new TableColumn(table, SWT.NONE);
        tblclmnNome.setWidth(100);
        tblclmnNome.setText("Nome");

        TableColumn tblclmnTipoSeguro = new TableColumn(table, SWT.CENTER);
        tblclmnTipoSeguro.setWidth(142);
        tblclmnTipoSeguro.setText("Tipo de Seguro");

        TableColumn tblclmnValorCobertura = new TableColumn(table, SWT.CENTER);
        tblclmnValorCobertura.setWidth(138);
        tblclmnValorCobertura.setText("Valor da cobertura");

        TableColumn tblclmnFranquia = new TableColumn(table, SWT.CENTER);
        tblclmnFranquia.setWidth(120);
        tblclmnFranquia.setText("Franquia");

        TableColumn tblclmnVigencia = new TableColumn(table, SWT.CENTER);
        tblclmnVigencia.setWidth(131);
        tblclmnVigencia.setText("Vigência");
        
        TableColumn tblclmnIdseguro = new TableColumn(table, SWT.NONE);
        tblclmnIdseguro.setWidth(0);
        tblclmnIdseguro.setText("idseguro");
        
        
        
        DateTime dateVigencia = new DateTime(shell, SWT.BORDER);
        dateVigencia.setBounds(82, 153, 80, 24);

        // Cadastrar Seguro
        btnCadastrarSeguro.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                
                String tipoSeguro = textTipoSeguro.getText();
                Double valorCobertura = Double.parseDouble(textValorCobertura.getText());
                Double franquia = Double.parseDouble(textFranquia.getText());
                LocalDate vigencia = LocalDate.of(dateVigencia.getYear(), dateVigencia.getMonth() + 1, dateVigencia.getDay());

                Seguro seguro = new Seguro(tipoSeguro, valorCobertura, franquia, vigencia,locacaoSelecionada);
                seguroBanco.incluir(seguro);

                MessageBox box = new MessageBox(shell, SWT.OK);
                box.setMessage("Seguro cadastrado com sucesso!");
                box.open();
            }
        });

        // Deletar Seguro
        btnDeletarSeguro.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int selectedIndex = table.getSelectionIndex();
				if (selectedIndex != -1) {
					TableItem item = table.getItem(selectedIndex);
					int idSeguro = Integer.parseInt(item.getText(7));

					seguroBanco.deletar(idSeguro);
					MessageBox messageBox = new MessageBox(shell, SWT.OK);
					messageBox.setMessage("Deletado com sucesso!");
					messageBox.open();

					btnListarSeguro.notifyListeners(SWT.Selection, null);
				} else {
					MessageBox warningBox = new MessageBox(shell, SWT.ICON_WARNING);
					warningBox.setMessage("Selecione um seguro na tabela para deletar.");
					warningBox.open();
				}

			}
		});

        // Listar Seguro
        btnListarSeguro.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                List<Seguro> listaSeguros = seguroBanco.listar();
                table.removeAll();

                // Popula a tabela com os dados
                for (Seguro seguro : listaSeguros) {
                    TableItem item = new TableItem(table, SWT.NONE);
                    item.setText(new String[]{
                            String.valueOf(seguro.getLocacao().getReservaLocacao().getClienteReserva().getIdUsuario()),
                            String.valueOf(seguro.getLocacao().getIdLocacao()),
                            seguro.getLocacao().getReservaLocacao().getClienteReserva().getNomeCompleto(),
                            seguro.getTipoSeguro(),
                            String.format("%.2f", seguro.getValorCobertura()),
                            String.format("%.2f", seguro.getFranquia()),
                            seguro.getVigencia().toString(),
                            String.valueOf(seguro.getIdSeguro()),
                            
                            
                    });
                }
            }
        });
        
    }
}
