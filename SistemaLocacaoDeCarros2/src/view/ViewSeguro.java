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

import banco.SeguroBanco;
import model.Seguro;

public class ViewSeguro {

    protected Shell shell;
    private Text textIdSeguro;
    private Text textTipoSeguro;
    private Text textValorCobertura;
    private Text textVigencia;
    private Text textFranquia;
    private Table table;
    private SeguroBanco seguroBanco;

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

        Label lblIdSeguro = new Label(shell, SWT.NONE);
        lblIdSeguro.setText("ID Seguro:");
        lblIdSeguro.setBounds(10, 13, 66, 15);

        textIdSeguro = new Text(shell, SWT.BORDER);
        textIdSeguro.setBounds(82, 10, 80, 21);

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

        textVigencia = new Text(shell, SWT.BORDER);
        textVigencia.setBounds(82, 159, 81, 21);

        Button btnCadastrarSeguro = new Button(shell, SWT.NONE);
        btnCadastrarSeguro.setText("Cadastrar Seguro");
        btnCadastrarSeguro.setBounds(57, 225, 150, 25);

        Button btnDeletarSeguro = new Button(shell, SWT.NONE);
        btnDeletarSeguro.setText("Deletar Seguro");
        btnDeletarSeguro.setBounds(332, 225, 134, 25);

        Button btnConsultarSeguro = new Button(shell, SWT.NONE);
        btnConsultarSeguro.setText("Consultar Seguro");
        btnConsultarSeguro.setBounds(593, 225, 150, 25);

        textFranquia = new Text(shell, SWT.BORDER);
        textFranquia.setBounds(82, 113, 81, 21);

        table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
        table.setLinesVisible(true);
        table.setHeaderVisible(true);
        table.setBounds(85, 273, 639, 205);

        TableColumn tblclmnIdSeguro = new TableColumn(table, SWT.CENTER);
        tblclmnIdSeguro.setWidth(106);
        tblclmnIdSeguro.setText("ID Seguro");

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

        // Cadastrar Seguro
        btnCadastrarSeguro.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                Integer idSeguro = Integer.parseInt(textIdSeguro.getText());
                String tipoSeguro = textTipoSeguro.getText();
                Double valorCobertura = Double.parseDouble(textValorCobertura.getText());
                String franquia = textFranquia.getText();
                String vigencia = textVigencia.getText();

                Seguro seguro = new Seguro(idSeguro, tipoSeguro, valorCobertura, franquia, vigencia);
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
                try {
                    int idSeguro = Integer.parseInt(textIdSeguro.getText());
                    seguroBanco.deletar(idSeguro);
                    MessageBox box = new MessageBox(shell, SWT.OK);
                    box.setMessage("Seguro deletado com sucesso!");
                    box.open();
                } catch (NumberFormatException ex) {
                    MessageBox box = new MessageBox(shell, SWT.ERROR);
                    box.setMessage("ID inválido.");
                    box.open();
                }
            }
        });

        // Consultar Seguro
        btnConsultarSeguro.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                List<Seguro> listaSeguros = seguroBanco.listar();
                table.removeAll();

                // Popula a tabela com os dados
                for (Seguro seguro : listaSeguros) {
                    TableItem item = new TableItem(table, SWT.NONE);
                    item.setText(new String[]{
                            String.valueOf(seguro.getIdSeguro()),
                            seguro.getTipoSeguro(),
                            String.format("%.2f", seguro.getValorCobertura()),
                            seguro.getFranquia(),
                            seguro.getVigencia()
                    });
                }
            }
        });
    }
}
