import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.print("Nome da Empresa: ");
        String nomeEmpresa = entrada.nextLine();
        System.out.print("CNPJ da Empresa: ");
        String cnpj = entrada.nextLine();

        Empresa empresa = new Empresa(nomeEmpresa, cnpj);
        empresa.menu();
    }
}

class Empresa {
    private String nomeEmpresa;
    private String cnpj;
    private final ArrayList<Departamento> departamentos;
    private final Scanner entrada;

    public Empresa(String nomeEmpresa, String cnpj) {
        this.nomeEmpresa = nomeEmpresa;
        this.cnpj = cnpj;
        this.departamentos = new ArrayList<>();
        this.entrada = new Scanner(System.in);
    }

    public ArrayList<Departamento> getDepartamentos() {
        return departamentos;
    }

    public Scanner getEntrada() {
        return entrada;
    }

    public void menu() {
        int opcao;

        do {
            System.out.println();
            System.out.println("*************** Empresa " + this.nomeEmpresa + " ****************");
            System.out.println();
            System.out.println(">>>>> Escolha uma opção:");
            System.out.println();
            System.out.println("1. Cadastrar Departamento");
            System.out.println("2. Listar Departamentos");
            System.out.println("3. Acessar Departamento");
            System.out.println("4. Sair");

            System.out.print(">>>>> ");
            opcao = entrada.nextInt();
            entrada.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarDepartamento();
                    break;
                case 2:
                    listarDepartamentos();
                    break;
                case 3:
                    acessarDepartamento();
                    break;
                case 4:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (opcao != 4);
    }

    private void cadastrarDepartamento() {
        System.out.println();
        System.out.print("Informe o nome do departamento: ");

        // Cria uma nova instância de Departamento
        Departamento novoDepartamento = new Departamento(this);
        novoDepartamento.nomeDepartamento = entrada.nextLine();

        // Adiciona o novo departamento à lista
        departamentos.add(novoDepartamento);

        System.out.println("Departamento cadastrado com sucesso!");
    }

    private void listarDepartamentos() {
        System.out.println();
        System.out.println("*************** Lista de Departamentos ***************");
        System.out.println();

        if (departamentos.isEmpty()) {
            System.out.println("Nenhum departamento cadastrado.");
        } else {
            for (int i = 0; i < departamentos.size(); i++) {
                System.out.println((i + 1) + " - " + departamentos.get(i).nomeDepartamento);
            }
        }
    }

    private void acessarDepartamento() {
        listarDepartamentos();

        if (!departamentos.isEmpty()) {
            System.out.print("Selecione o número do departamento: ");
            int opcao = entrada.nextInt();
            entrada.nextLine();

            if (opcao > 0 && opcao <= departamentos.size()) {
                Departamento departamento = departamentos.get(opcao - 1);
                departamento.menu();
            } else {
                System.out.println("Opção inválida.");
            }
        }
    }
}

class Departamento {
    protected String nomeDepartamento;
    private final Empresa empresa;
    private final ArrayList<Funcionario> funcionarios;

    public Departamento(Empresa empresa) {
        this.empresa = empresa;
        this.funcionarios = new ArrayList<>();
    }

    public void menu() {
        int opcao;

        do {
            System.out.println();
            System.out.println("*************** Departamento " + nomeDepartamento + " ***************");
            System.out.println();
            System.out.println(">>>>> Escolha uma opção:");
            System.out.println("1. Cadastrar Funcionário");
            System.out.println("2. Listar Funcionários");
            System.out.println("3. Voltar");

            System.out.print(">>>>> ");
            opcao = empresa.getEntrada().nextInt();
            empresa.getEntrada().nextLine();

            switch (opcao) {
                case 1:
                    cadastrarFuncionario();
                    break;
                case 2:
                    listarFuncionarios();
                    break;
                case 3:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (opcao != 3);
    }

    private void cadastrarFuncionario() {
        System.out.println();
        Funcionario novoFuncionario = new Funcionario();

        System.out.print("Informe o nome do funcionário: ");
        novoFuncionario.setNomeFuncionario(empresa.getEntrada().nextLine());
        System.out.print("Informe o salário do funcionário: ");
        novoFuncionario.setSalarioFuncionario(empresa.getEntrada().nextDouble());
        System.out.print("Informe a data de admissão (aaaa-mm-dd): ");
        novoFuncionario.setDataAdmissao(LocalDate.parse(empresa.getEntrada().next()));

        funcionarios.add(novoFuncionario);

        System.out.println("Funcionário cadastrado com sucesso!");
    }

    private void listarFuncionarios() {
        System.out.println();
        System.out.println("*************** Lista de Funcionários ***************");
        System.out.println();

        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
        } else {
            for (int i = 0; i < funcionarios.size(); i++) {
                Funcionario f = funcionarios.get(i);
                System.out.println((i + 1) + " - " + f.getNomeFuncionario() + 
                    " | Salário: R$ " + f.getSalarioFuncionario() + 
                    " | Data de Admissão: " + f.getDataAdmissao());
            }
        }
    }
}

class Funcionario {
    private String nomeFuncionario;
    private double salarioFuncionario;
    private LocalDate dataAdmissao;

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public double getSalarioFuncionario() {
        return salarioFuncionario;
    }

    public void setSalarioFuncionario(double salarioFuncionario) {
        this.salarioFuncionario = salarioFuncionario;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }
}
