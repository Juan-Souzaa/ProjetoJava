package banco;

public class testeConexao {
	
		public static void main(String[] args) {
			
	        try 
	        {   
				DBConnection conexao = new DBConnection();
				System.out.println("Conexão ok");
				
	        }
			catch (Exception e)	
			{	
				System.out.println("Conexão nok");
			}
			       
						
		}

	}



