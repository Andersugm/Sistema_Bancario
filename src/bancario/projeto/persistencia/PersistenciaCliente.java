package bancario.projeto.persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import bancario.projeto.model.Cliente;

public class PersistenciaCliente {

	private ArrayList<Cliente> clientes;
	
	public PersistenciaCliente() {
		clientes = new ArrayList<>();
	}
	
	public void adicionarCliente(Cliente c) {
		if(clientes.contains(c)) {
			System.out.println("Cliente j� cadastrada");
		}else {
			clientes.add(c);
			System.out.println("Cliente cadastrado com sucesso");
		}
	}
	
	public Cliente encontrarCliente(String cpf) {
		for (Cliente acharCliente : clientes) {
			if(acharCliente.getCpf().equals(cpf)) {
				return acharCliente;
			}
		}
		return null;
	}
	
	public void listarClientes(){
		if(clientes.isEmpty()) {
			System.out.println("Não existe cliente cadastrado");
		}
		else {
			for(Cliente listando : clientes) {
				
			System.out.println(listando);
			}
		}
	}
	
	public void removerCliente(Cliente c) {
		if(clientes.contains(c)) {
			clientes.remove(c);
			System.out.println("Cliente removido com sucesso");
		}else
			System.out.println("Cliente n�o localizada");
	}
	
	public Cliente localizarClientePorCpf(String numero) {
		Cliente temp = new Cliente();
		temp.setCpf(numero);
		
		if(clientes.contains(temp)) {
			int index = clientes.indexOf(temp);
			temp = clientes.get(index);
			return temp;
		}
		return null;
	}
	
	public void atualizarCliente(Cliente c) {
		if(clientes.contains(c)) {
			int index = clientes.indexOf(c);
			clientes.set(index, c);
			System.out.println("Cliente atualizado com sucesso");
		}else
			System.out.println("Cliente n�o localizado");
	}
	
	
	public void carregarArquivo() {
        try (FileInputStream fis = new FileInputStream("dados.txt");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            clientes = (ArrayList<Cliente>) ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void salvarArquivo() {
        try {
            FileOutputStream fos = new FileOutputStream("dados.txt");    
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(clientes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
