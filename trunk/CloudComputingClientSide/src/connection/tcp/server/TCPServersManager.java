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
			service.run();
	}
	public void openGamingServer(){
		gaming.run();
		
		
		
	}
	public void closeServiceServer(){
		service.interrupt();
		try {
			service.join();
		} catch (InterruptedException e) {

			
		}
}
	public void closeGamingServer(){
		gaming.interrupt();
		try {
			gaming.join();
		} catch (InterruptedException e) {

			
		}
	}
	
}
