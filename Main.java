// importando as classes
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math;

// classe principal
public class Main {
    public static void main(String[] args) {

        // instanciando a classe com menuzinho fuleiro
        Menus menus = new Menus();

        // instanciando a classe do scanner
        Scanner entrada = new Scanner(System.in);

        // apresenta o menu inicial
        menus.tituloInicial();

        // captura dados inicias sobre a empresa para passar no construtor
        System.out.print("Nome da Empresa: ");
        String nomeEmpresa = entrada.nextLine();
        System.out.print("CNPJ da Empresa: ");
        String cnpj = entrada.nextLine();

        // construtor da empresa com as informacoes capturadas acima
        Empresa empresa = new Empresa(nomeEmpresa, cnpj);

        // exibe o metodo menu da classe empresa
        empresa.menu();
    }
}

// classe com o menu fuleiro
class Menus {

    // metodo void, ou seja, sem retorno
    void tituloInicial() {
        System.out.println();
        System.out.println("*************** BEM VINDO ***************");
        System.out.println();
        System.out.println("Primeiro cadastre a empresa!");
        System.out.println();
    }
}

// classe Empresa
class Empresa {

    // definindo os atributos como privados para que eles possam ser acessados apenas dentro da classe
    private String nomeEmpresa;
    private String cnpj;

    // o atributo final e usado para garantir que a referencia ao objeto nao sera alterada apos a inicializacao
    private final ArrayList<Departamento> departamentos; // o nome que e passado dentro do arraylist deve ser o mesmo da classe que ira usar ( class Departamento {} )
    private final Scanner entrada;

    // construtor da empresa com as informacoes passadas como parametro
    public Empresa(String nomeEmpresa, String cnpj) {

        // this e usado para referenciar os atributos da propria classe
        this.nomeEmpresa = nomeEmpresa;
        this.cnpj = cnpj;
        this.departamentos = new ArrayList<>();
        this.entrada = new Scanner(System.in);
    }

    // metodo get e usado para acessar a variavel
    public ArrayList<Departamento> getDepartamentos() {
        return departamentos;
    }

    // metodo get e usado para acessar a variavel
    public Scanner getEntrada() {
        return entrada;
    }

    // metodo que exibe o menu principal da empresa
    public void menu() {

        // declarando a variavel opcao do tipo inteiro para armazenar a escolha do usuario
        int opcao;

        // loop infinito que executa enquanto o usuario nao escolher a opcao 4 (sair)
        do {
            System.out.println();
            System.out.println("*************** Empresa " + this.nomeEmpresa + " ****************");
            System.out.println();
            System.out.println(">>>>> Escolha uma opcao:");
            System.out.println();
            System.out.println("1. Cadastrar Departamento");
            System.out.println("2. Listar Departamentos");
            System.out.println("3. Acessar Departamento");
            System.out.println("4. Sair");

            // captura a opcao do usuario
            System.out.print(">>>>> ");
            opcao = entrada.nextInt();
            entrada.nextLine();

            // leva para um metodo diferente dentro da classe com base na escolha
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
                    System.out.println("Opção invalida!");
                    break;
            }
        } while (opcao != 4);
    }

    // metodo privado que cadastra um novo departamento na empresa
    private void cadastrarDepartamento() {
        System.out.println();
        System.out.print("Informe o nome do departamento: ");

        // instanciando um novo departamento da classe Departamento passando a referencia da empresa como parametro para o construtor
        Departamento novoDepartamento = new Departamento(this);

        // captura o nome do departamento
        novoDepartamento.nomeDepartamento = entrada.nextLine();

        // adiciona o departamento
        departamentos.add(novoDepartamento);

        System.out.println("Departamento cadastrado com sucesso!");
    }

    // metodo privado que lista todos os departamentos cadastrados na empresa
    private void listarDepartamentos() {
        System.out.println();
        System.out.println("*************** Lista de Departamentos ***************");
        System.out.println();

        // verifica se nao existem departamentos cadastrados
        if (departamentos.isEmpty()) {
            System.out.println("Nenhum departamento cadastrado.");
        } else {
            // se existem departamentos cadastrados, entao lista
            for (int i = 0; i < departamentos.size(); i++) {
                // imprime o indice (i + 1) e o nome do departamento
                // (metodo get(i) acessa o departamento na posicao "i" do arraylist)
                // (nomeDepartamento e o atributo do departamento que contem o nome)
                System.out.println((i + 1) + " - " + departamentos.get(i).nomeDepartamento);
            }
        }
    }

    // metodo privado que acessa um departamento especifico cadastrado na empresa
    private void acessarDepartamento() {

        // chama o escopo da classe listarDepartamentos() que ja esta pronto
        listarDepartamentos();

        // verifica se existem departamentos cadastrados
        if (!departamentos.isEmpty()) {
            System.out.println();
            System.out.print("Selecione o numero do departamento: ");

            // captura a opcao do usuario
            int opcao = entrada.nextInt();
            entrada.nextLine();

            // verifica se a opcao do usuario esta dentro do indice dos departamentos cadastrados
            if (opcao > 0 && opcao <= departamentos.size()) {
                // se a opcao do usuario esta dentro do indice, entao acessa o departamento na posicao "opcao - 1" do arraylist
                Departamento departamento = departamentos.get(opcao - 1);

                // chama o metodo menu() do departamento
                departamento.menu();
            } else {
                System.out.println("Opção inválida.");
            }
        }
    }
}


// classe Funcionario
class Departamento {

    // definindo os atributos como privados para que eles possam ser acessados apenas dentro da classe
    protected String nomeDepartamento; // o protected permite o acesso pelas classes derivadas alem da propria classe

    // o atributo final e usado para garantir que a referencia ao objeto nao sera alterada apos a inicializacao
    private final Empresa empresa; // "empresa" e uma referencia a classe Empresa, associando este departamento a uma empresa
    private final ArrayList<Funcionario> funcionarios; // o nome que e passado dentro do arraylist deve ser o mesmo da classe que ira usar ( class Funcionario {} )

    // construtor da classe Departamento que recebe a referencia da empresa como parametro para inicializar o atributo empresa
    public Departamento(Empresa empresa) {

        // this e usado para referenciar os atributos da propria classe

        // inicializando o atributo empresa com a referencia passada como parametro no construtor
        this.empresa = empresa;
        this.funcionarios = new ArrayList<>();
    }

    // metodo que exibe o menu para departamentos
    public void menu() {

        // declarando a variavel opcao do tipo inteiro para armazenar a escolha do usuario
        int opcao;

        // loop infinito que executa enquanto o usuario nao escolher a opcao 4 (voltar)
        do {
            System.out.println();
            System.out.println("*************** Departamento " + nomeDepartamento + " ***************");
            System.out.println();
            System.out.println(">>>>> Escolha uma opção:");
            System.out.println("1. Cadastrar Funcionario");
            System.out.println("2. Listar Funcionários");
            System.out.println("3. Dar aumento para Funcionários");
            System.out.println("4. Voltar");

            // captura a opcao do usuario
            System.out.print(">>>>> ");
            opcao = empresa.getEntrada().nextInt();
            empresa.getEntrada().nextLine();

            // leva para um metodo diferente dentro da classe com base na escolha
            switch (opcao) {
                case 1:
                    cadastrarFuncionario();
                    break;
                case 2:
                    listarFuncionarios();
                    break;
                case 3:
                    aumentoFuncionarios();
                    break;
                case 4:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opção invalida!");
                    break;
            }
        } while (opcao != 4);
    }

    // metodo privado que cadastra um novo funcionario ao departamento
    private void cadastrarFuncionario() {
        System.out.println();

        // instanciando um novo funcionario da classe Funcionario
        Funcionario novoFuncionario = new Funcionario();

        // solicitando ao usuario os dados do novo funcionario
        System.out.print("Informe o nome do funcionário: ");
        novoFuncionario.setNomeFuncionario(empresa.getEntrada().nextLine());
        System.out.print("Informe o salario do funcionário: ");
        novoFuncionario.setSalarioFuncionario(empresa.getEntrada().nextDouble());
        System.out.print("Informe a data de admissao (aaaa-mm-dd): ");
        novoFuncionario.setDataAdmissao(LocalDate.parse(empresa.getEntrada().next()));

        // "novoFuncionario" e o nome do objeto
        // usa-se o metodo "set" para definir ou atualizar o valor do atributo
        // empresa.getEntrada() captura os dados pelo scanner que esta publico na classe Empresa com seu respectivo tipo
        // LocalDate.parse() converte essa string para um objeto LocalDate, que representa a data de admissao

        // adicionando o novo funcionario ao arraylist de funcionarios do departamento
        funcionarios.add(novoFuncionario);

        System.out.println("Funcionário cadastrado com sucesso!");
    }

    // metodo privado que lista todos os funcionarios cadastrados no departamento
    private void listarFuncionarios() {
        System.out.println();
        System.out.println("*************** Lista de Funcionários ***************");
        System.out.println();

        // verificando se nao existem funcionarios cadastrados no departamento
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
        } else {
            // se existem funcionario cadastrados no departamento, entao lista
            for (int i = 0; i < funcionarios.size(); i++) {

                // pega cada funcionario do objeto e armazena na variavel "f"
                Funcionario f = funcionarios.get(i);

                // imprime o indice (i + 1) e o nome do departamento
                // (metodo get() acessa as informacoes desse funcionario
                System.out.println((i + 1) + " - " + f.getNomeFuncionario() +
                        " | Salário: R$ " + String.format("%.2f", f.getSalarioFuncionario()) +
                        " | Data de Admissão: " + f.getDataAdmissao());
            }
        }
    }

    // falta corrigir as casas decimais dessa bagaça de aumento
    void aumentoFuncionarios() {

        // declarando a variavel aumento do tipo double para armazenar a escolha do usuario
        double aumento;

        System.out.println();
        System.out.print("Qual sera o valor do aumento em (%): ");
        aumento = empresa.getEntrada().nextDouble() / 100;

        for (Funcionario f : funcionarios) {
            f.setSalarioFuncionario(f.getSalarioFuncionario() * (1 + aumento));
        }

        System.out.println("Aumento realizado com sucesso!");
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

