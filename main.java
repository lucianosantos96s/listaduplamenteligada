package prova;

import java.util.*;

public class main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int pos;
		info[] valores = new info[10];
		int value = 0;
		
		info maior = null;
		info aux = null;
		info fim = null;
		
		int opcaoMenuPrincipal;
		
		do {
			
			System.out.println("\nEscolha uma opcao: ");
			System.out.println("1 - Inserir na lista");
			System.out.println("2 - Remover na lista");
			System.out.println("3 - Exibir a lista");
			System.out.println("4 - Sair\n");
			
			opcaoMenuPrincipal = sc.nextInt();
			
			
			switch(opcaoMenuPrincipal) {
			
			case 1:
				
				info novonome = new info();
				System.out.println("Digite o nome");
				novonome.nome = sc.next();
				System.out.println("Digite a prioridade");
				novonome.prioridade = sc.nextInt();
				if (novonome.prioridade > 1 || novonome.prioridade < 0) {
					System.out.println("A prioridade deve ser entre 0 e 1");
				} else {
					//System.out.println("Digite a posicao:");
					//pos = sc.nextInt();
					
					int key = 0;
					String letters [] = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
					int ASCII[] = {65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,97,98,99,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,119,120,121,122};
					String nameList [] = novonome.nome.split("");
					
			        for(int i = 0; i < nameList.length; i++){
			            int j = 0;
			            while (nameList[i].equals(letters[j]) == false)
			                j++;
			            key = key + ASCII[j];
			        }
			        
			        int hash = key;
			        
			        while (hash >= 10) {
			            hash = hash%10;
			        }
			        
			        System.out.println("-------------------------------");
			        System.out.println("Nome: " + novonome.nome);
			        System.out.println("Prioridade: " + novonome.prioridade);
			        System.out.println("Chave: " + key);
			        System.out.println("Hash: " + hash);
			        System.out.println("-------------------------------");
			        novonome.chave = key;
			        novonome.hash = hash;
			        		        
			        pos = hash;
			        
			        maior = valores[pos];
					
					if (valores[pos] == null) {
						maior = novonome;
						//Erro maior.prox = maior;
						//Erro maior.ant = maior;
						fim = novonome;
						valores[pos] = maior;
					}
					
					else {
						//fim.prox  = novonome;
						//fim.ant = fim;
						//fim = novonome;
						// Looping infinito fim.prox = maior;
						aux = valores[pos];
						
						while (aux != null) {
							if(novonome.prioridade == aux.prioridade && (aux.prox == null || aux.prox.prioridade > novonome.prioridade)) {
								try {
									//if (aux.prox.prioridade >= novonome.prioridade ) {
										novonome.ant = aux;
										novonome.prox = aux.prox;
										aux.prox = novonome;
										break;
									//}
								} catch (NullPointerException erro1){
									novonome.ant = aux;
									novonome.prox = aux.prox;
									aux.prox = novonome;
									break;
								}
							} 
							else if(novonome.prioridade < aux.prioridade && aux.ant == null) {
								novonome.prox = aux;
								novonome.ant = aux.ant;
								aux.ant = novonome;
								valores[pos] = novonome;
								break;
							}
							
							else if (aux.prox == null || aux.prox.prioridade > novonome.prioridade){
								novonome.ant = aux;
								novonome.prox = aux.prox;
								aux.prox = novonome;
								break;
								
							}
							aux = aux.prox;
						}
					}
				}
				
				break;
			case 2:
				System.out.println("Digite o nome a ser removido:");
				String remover = sc.next();
				
				int key = 0;
				String letters [] = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
				int ASCII[] = {65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,97,98,99,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,119,120,121,122};
				String nameList [] = remover.split("");
				
		        for(int i = 0; i < nameList.length; i++){
		            int j = 0;
		            while (nameList[i].equals(letters[j]) == false)
		                j++;
		            key = key + ASCII[j];
		        }
		        
		        
		        int hash = key;
		        
		        while (hash >= 10) {
		            hash = hash%10;
		        }
		        
		        aux = valores[hash];
		        
		        if (aux == null) {
		        	System.out.println("Nome nao encontrado");
		        } else {
		        	System.out.println("Removendo: " + remover);
		        	while(aux != null) {
		        			if (aux.nome.equals(remover)) {
		        				if(aux.ant == null && aux.prox == null ) {
		        					valores[hash] = null;
		        					break;
		        				} else if (aux.ant == null && aux.prox != null) {
		        					valores[hash] = aux.prox;
		        					valores[hash].ant = null;
		        					break;
		        				}
		        				else if (aux.prox == null) {
		        					aux.ant.prox = null;
		        					break;
		        				}
		        				
		        				else {
		        					aux.ant.prox = aux.prox;
		        					aux.ant = aux.ant.prox;
		        					aux.prox.ant = aux.ant;
		        					break;
		        				}
							}
		        		
						aux = aux.prox;
					}
		        }
				
				break;
				
			case 3:
				System.out.println("");
				for(int i = 0; i < valores.length; i++) {
					aux = valores[i];
					if (aux == null) {
						System.out.print(i);
						System.out.println(" | - |");
						continue;
					}
					try {
						System.out.print(i);
						while(aux != null) {
							System.out.print(" | Nome: " + aux.nome +" Prioridade: " + aux.prioridade + " Chave: " + aux.chave + " Hash: "+ aux.hash + " | ");
							aux = aux.prox;

						}
						System.out.println("");
					} catch (NullPointerException erro2) {
						System.out.println(" | - |");
					}
					
				}
				
				break;
			
			case 4:
				System.out.println("Sair");
				break;
			
			default:
				System.out.println("\nOpcao invalida\n");
			
			}
			
		}while(opcaoMenuPrincipal != 4);

	}	
}