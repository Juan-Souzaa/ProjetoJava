package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

public class ViewFidelidade {

    protected Shell shell;
    private Text txtIdFidelidade;
    private Text txtPontos;
    private Text txtNivel;
    private Table table;

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
        shell.setSize(555, 683);
        shell.setText("Fidelidade");

        Label lblIdFidelidade = new Label(shell, SWT.NONE);
        lblIdFidelidade.setBounds(25, 38, 98, 15);
        lblIdFidelidade.setText("ID Fidelidade");

        txtIdFidelidade = new Text(shell, SWT.BORDER);
        txtIdFidelidade.setBounds(129, 38, 160, 21);

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
        table.setBounds(26, 316, 455, 288);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        TableColumn tblclmnIdFidelidade = new TableColumn(table, SWT.CENTER);
        tblclmnIdFidelidade.setWidth(100);
        tblclmnIdFidelidade.setText("ID Fidelidade");

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
        btnCadastrarFidelidade.setBounds(25, 256, 128, 25);
        btnCadastrarFidelidade.setText("Cadastrar Fidelidade");

        Button btnDeletarFidelidade = new Button(shell, SWT.NONE);
        btnDeletarFidelidade.setBounds(199, 256, 110, 25);
        btnDeletarFidelidade.setText("Deletar Fidelidade");

        Button btnConsultarFidelidade = new Button(shell, SWT.NONE);
        btnConsultarFidelidade.setBounds(353, 256, 128, 25);
        btnConsultarFidelidade.setText("Consultar Fidelidade");
    }
}
