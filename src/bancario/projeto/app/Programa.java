package bancario.projeto.app;

import java.util.ArrayList;
import java.util.Scanner;

import bancario.projeto.model.Cliente;
import bancario.projeto.model.ContaBancaria;
import bancario.projeto.persistencia.PersistenciaCliente;

public class Programa {

	public static void main(String[] args) {
		
		PersistenciaCliente p = new PersistenciaCliente();
		Scanner sc = new Scanner(System.in);
		
		boolean sair = true;
		int opcao = 0;
		System.out.println("Bem vindo ao sistema banc�rio!!");
		
		while(sair) {
			System.out.println("\n\n\nDigite a opcao desejada:\n"
					+ "\n1 - Cadastro de cliente;\n"
					+ "2 - Remover cliente\n"
					+ "3 - Listar clientes\n"
					+ "4 - Para sair\n"
					+ "5 - consultar cliente\n"
					+ "6 - Adicionar conta\n"
					+ "7 - consultar contas do cliente\n"
					+ "8 - remover conta\n"
					+ "9 - realizar deposito\n"
					+ "10 - realizar saque\n"
					+ "11 - realizar transferência\n"
					+ "12 - Consultar saldo\n"
					+ "13 - Consultar balanço das contas\n"
					+ "14 - SALVAR ALTERAÇÕES\n\n\n");
			opcao = sc.nextInt();
			
			switch (opcao) {
			case 1: {
				String cpf;
				String nome;
				System.out.println("Insira o seu CPF: ");
				cpf = sc.next();
				System.out.println("Insira o seu nome: ");
				nome = sc.next();
				p.adicionarCliente(new Cliente(cpf, nome));
				break;
			}
			case 2: {
				Cliente temp;
				String cpf;
				System.out.println("Insira o seu CPF: ");
				cpf = sc.next();
				temp = new Cliente();
				temp.setCpf(cpf);
				p.removerCliente(temp);
				break;
			}
			case 3: {
				p.carregarArquivo();
				p.listarClientes();
				break;
			}
			case 4: {
				sair = false;
				sc.close();
				System.out.println("Sistema encerrado");
				break;
			}
			case 5: {
				String cpf;
				System.out.println("Digite o cpf do cliente: ");
				cpf = sc.next();
				if(p.encontrarCliente(cpf) == null) {
					System.out.println("Cliente não encontrado");
				}
				else {
					System.out.println(p.encontrarCliente(cpf));
				}
				break;
				
			}
			
			case 6: {
				p.carregarArquivo();
				String cpf;
				System.out.println("Digite o cpf do cliente que deseja criar a conta: ");
				cpf = sc.next();
				if(p.encontrarCliente(cpf) == null) {
					System.out.println("Cliente não encontrado");
				}
				else {
					Cliente cliente = p.localizarClientePorCpf(cpf);
					System.out.println("Digite o número da conta");
					int numeroConta = sc.nextInt();
					ContaBancaria novaConta = new ContaBancaria(numeroConta);
					cliente.adicionarConta(novaConta);
				}
				
				
				break;
				
				
			}
			case 7: {
				System.out.println("Digite o cpf do cliente que deseja ver as contas: ");
				String cpf = sc.next();
				Cliente cliente = p.localizarClientePorCpf(cpf);
				if(cliente == null) {
					System.out.println("Cliente não encontrado");
				}
				else {
					ArrayList<ContaBancaria> contas = cliente.getContas();
					if(contas == null) {
						System.out.println("O cliente não tem contas associadas a ele");
					}
					else {
						System.out.println(contas);
					}
					
				}
				break;
			}
			
			case 8 : {
				System.out.println("Digite o cpf do cliente que deseja remover a conta");
				String cpf = sc.next();
				Cliente cliente = p.localizarClientePorCpf(cpf);
				if(cliente == null) {
					System.out.println("O cliente não foi encontrado");
				}
				else {
					System.out.println("Qual o número da conta que deseja remover?");
					int numeroDaConta = sc.nextInt();
					ContaBancaria contaRemovida = cliente.localizarContaPorNumero(numeroDaConta);
					cliente.removerConta(contaRemovida);
				}
			}
			
			case 9: {
				System.out.println("Digite o cpf do cliente que deseja realizar o depósito: ");
				String cpf = sc.next();
				Cliente cliente = p.localizarClientePorCpf(cpf);
				if(cliente == null) {
					System.out.println("Cliente não encontrado");
				}
				else {
					System.out.println("Digite o número da conta em que deseja depositar o dinheiro:");
					int numeroConta = sc.nextInt();
					ContaBancaria contaDeposito = cliente.localizarContaPorNumero(numeroConta);
					if(contaDeposito == null) {
						System.out.println("Conta não encontrada");
					}
					else {
					System.out.println("Quanto deseja depositar? ");
					float deposito = sc.nextFloat();
					contaDeposito.depositar(deposito);
					System.out.println(contaDeposito);
					}
				}
				break;
			}
			
			case 10: {
				System.out.println("Qual o cpf do cliente que deseja realizar o saque ?");
				String cpf =  sc.next();
				Cliente cliente = p.localizarClientePorCpf(cpf);
				if(cliente == null) {
					System.out.println("Cliente não encontrado");
				}
				else {
					System.out.println("Qual o número da conta que deseja realizar o saque?");
					int numeroDaConta = sc.nextInt();
					ContaBancaria contaSacar = cliente.localizarContaPorNumero(numeroDaConta);
					if(contaSacar ==  null) {
						System.out.println("Conta não encontrada");
					}
					else {
					System.out.println("Quanto deseja sacar? ");
					float ValorSaque = sc.nextFloat();
					contaSacar.sacar(ValorSaque);
					System.out.println(contaSacar);
					}
				}
				break;
			}
			case 11: {
				System.out.println("Digite o cpf do cliente que deseja realizar a transferência: ");
				String cpf = sc.next();
				Cliente cliente = p.localizarClientePorCpf(cpf);
				if(cliente == null) {
					System.out.println("Cliente não encontrado");
				}
				else {
					System.out.println("Digite o número da conta que irá realizar a transferência: ");
					int numeroDaContaT = sc.nextInt();
					ContaBancaria contaTransferir = cliente.localizarContaPorNumero(numeroDaContaT);
					if(contaTransferir == null) {
						System.out.println("Conta não encontrada");
					}
					else {
						
					System.out.println("Digite o número da conta que irá receber: ");
					int numeroDaContaR = sc.nextInt();
					ContaBancaria contaReceber = cliente.localizarContaPorNumero(numeroDaContaR);
						if(contaReceber == null) {
							System.out.println("Conta destinatária não encontrada");
						}
						else {
							System.out.println("Quanto deseja tranferir? ");
							int valor = sc.nextInt();
							contaTransferir.transferir(contaReceber, valor);
						}
					}
					
				}
				break;
			}
			
			case 12: {
				System.out.println("Digite o cpf do cliente que deseja consultar o saldo: ");
				String cpf = sc.next();
				Cliente cliente = p.localizarClientePorCpf(cpf);
				if(cliente == null) {
					System.out.println("Cliente não encontrado");
				}
				else {
					System.out.println("Digite o numero da conta que deseja consultar: ");
					int numeroDaConta = sc.nextInt();
					ContaBancaria conta = cliente.localizarContaPorNumero(numeroDaConta);
					if(conta == null) {
						System.out.println("Conta não encontrada");
					}
					else {
						System.out.println(conta.getSaldo());
					}
				}
				break;
				}
			
			case 13: {
				float saldoTotal = 0;
				System.out.println("Digite o cpf do cliente que deseja fazer o balanço das contas: ");
				String cpf = sc.next();
				Cliente cliente = p.localizarClientePorCpf(cpf);
				if(cliente == null) {
					System.out.println("Cliente não encontrado");
				}
				else {
					for(ContaBancaria contas : cliente.getContas()) {
						ContaBancaria contaAtual = contas;
						saldoTotal= saldoTotal + contaAtual.getSaldo();					
						}
					System.out.println("O saldo total somado de todas as contas desse cliente é de: " + saldoTotal);
				}
				break;
			}
			
			case 14: {
			    p.salvarArquivo();
			    System.out.println("Dados salvos com sucesso!");
			    break;
			}
			
			default:

				throw new IllegalArgumentException("Unexpected value: " + opcao);
			}
		}
	}
}
