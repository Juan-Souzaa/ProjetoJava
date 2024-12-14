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

import banco.BalconistaBanco;
import model.Balconista;

public class ViewBalconista {
    private BalconistaBanco balconistaBanco;
    protected Shell shell;
    private Text txtMatricula;
    private Text txtTurno;
    private Text txtCargo;
    private Text txtFilial;
    private Table table;

    public ViewBalconista() {
        this.balconistaBanco = new BalconistaBanco();
    }

    public static void main(String[] args) {
        try {
            ViewBalconista window = new ViewBalconista();
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
        shell.setSize(700, 500);
        shell.setText("Balconista - Locadora");

        Label lblMatricula = new Label(shell, SWT.NONE);
        lblMatricula.setBounds(20, 30, 80, 20);
        lblMatricula.setText("Matrícula:");

        txtMatricula = new Text(shell, SWT.BORDER);
        txtMatricula.setBounds(120, 30, 150, 20);

        Label lblTurno = new Label(shell, SWT.NONE);
        lblTurno.setBounds(20, 70, 80, 20);
        lblTurno.setText("Turno:");

        txtTurno = new Text(shell, SWT.BORDER);
        txtTurno.setBounds(120, 70, 150, 20);

        Label lblCargo = new Label(shell, SWT.NONE);
        lblCargo.setBounds(20, 110, 80, 20);
        lblCargo.setText("Cargo:");

        txtCargo = new Text(shell, SWT.BORDER);
        txtCargo.setBounds(120, 110, 250, 20);

        Label lblFilial = new Label(shell, SWT.NONE);
        lblFilial.setBounds(20, 150, 80, 20);
        lblFilial.setText("Filial:");

        txtFilial = new Text(shell, SWT.BORDER);
        txtFilial.setBounds(120, 150, 250, 20);

        Button btnCadastrar = new Button(shell, SWT.NONE);
        btnCadastrar.setBounds(20, 200, 100, 30);
        btnCadastrar.setText("Cadastrar");

        Button btnDeletar = new Button(shell, SWT.NONE);
        btnDeletar.setBounds(140, 200, 100, 30);
        btnDeletar.setText("Deletar");

        Button btnConsultar = new Button(shell, SWT.NONE);
        btnConsultar.setBounds(260, 200, 100, 30);
        btnConsultar.setText("Consultar");

        Button btnAtualizar = new Button(shell, SWT.NONE);
        btnAtualizar.setBounds(380, 200, 100, 30);
        btnAtualizar.setText("Atualizar");

        Button btnListarTodos = new Button(shell, SWT.NONE);
        btnListarTodos.setBounds(500, 200, 120, 30);
        btnListarTodos.setText("Listar Todos");

        table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
        table.setBounds(20, 250, 650, 200);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        TableColumn colMatricula = new TableColumn(table, SWT.NONE);
        colMatricula.setWidth(100);
        colMatricula.setText("Matrícula");

        TableColumn colTurno = new TableColumn(table, SWT.NONE);
        colTurno.setWidth(150);
        colTurno.setText("Turno");

        TableColumn colCargo = new TableColumn(table, SWT.NONE);
        colCargo.setWidth(200);
        colCargo.setText("Cargo");

        TableColumn colFilial = new TableColumn(table, SWT.NONE);
        colFilial.setWidth(200);
        colFilial.setText("Filial");

       
        btnCadastrar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                String matricula = txtMatricula.getText();
                String turno = txtTurno.getText();
                String cargo = txtCargo.getText();
                String filial = txtFilial.getText();

                Balconista balconista = new Balconista(matricula, turno,  filial);
                balconistaBanco.incluir(balconista);

                MessageBox messageBox = new MessageBox(shell, SWT.OK);
                messageBox.setMessage("Balconista cadastrado com sucesso!");
                messageBox.open();
            }
        });

     
        btnAtualizar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                String matricula = txtMatricula.getText();
                String turno = txtTurno.getText();
                String cargo = txtCargo.getText();
                String filial = txtFilial.getText();

                Balconista balconista = new Balconista(matricula, turno, filial);
                balconistaBanco.atualizar(balconista);

                MessageBox messageBox = new MessageBox(shell, SWT.OK);
                messageBox.setMessage("Balconista atualizado com sucesso!");
                messageBox.open();
            }
        });

   
        btnConsultar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                String matricula = txtMatricula.getText();
                Balconista balconista = balconistaBanco.consultar(matricula);

                table.removeAll();
                if (balconista != null) {
                    TableItem item = new TableItem(table, SWT.NONE);
                    item.setText(new String[] {
                        balconista.getMatricula(),
                        balconista.getTurno(),
                      
                        balconista.getFilial()
                    });
                } else {
                    MessageBox messageBox = new MessageBox(shell, SWT.ICON_ERROR);
                    messageBox.setMessage("Balconista não encontrado!");
                    messageBox.open();
                }
            }
        });

        
        btnDeletar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                String matricula = txtMatricula.getText();
                balconistaBanco.deletar(matricula);

                MessageBox messageBox = new MessageBox(shell, SWT.OK);
                messageBox.setMessage("Balconista deletado com sucesso!");
                messageBox.open();
            }
        });

       
        btnListarTodos.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                List<Balconista> balconistas = balconistaBanco.listar();
                table.removeAll();
                for (Balconista balconista : balconistas) {
                    TableItem item = new TableItem(table, SWT.NONE);
                    item.setText(new String[] {
                        balconista.getMatricula(),
                        balconista.getTurno(),
                     
                        balconista.getFilial()
                    });
                }
            }
        });
    }
}
