package connection.tcp.server;



public class TCPServersManager{

	private Thread service;
	private Thread gaming;
	private ServiceServer serviceServer;
	private GamingServer gamingServer;
	
	public TCPServersManager() {
		
		service= new Thread(serviceServer);
		gaming= new Thread(gamingServer);
		
	}
	
	public void openServiceServer(){
		service.start();
		
		
	}
	public void openGamingServer(){
		gaming.start();
		
		
		
	}
	public void closeServiceServer(){
		service.interrupt();
	}
	public void closeGamingServer(){
		gaming.interrupt();
	}
	
}
