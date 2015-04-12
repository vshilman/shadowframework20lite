package connection.tcp.server;



public class TCPServersManager{

	private Thread service;
	private Thread gaming;
	private ServiceServer serviceServer= new ServiceServer();
	private GamingServer gamingServer;
	
	public TCPServersManager() {
		
		gaming= new Thread(gamingServer);
		
	}
	
	
	public void openServiceServer(){
		service= new Thread(serviceServer);
		service.setName("serviceServer");
		service.start();
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

	public GamingServer getGamingServer() {
		return gamingServer;
	}
	
	public ServiceServer getServiceServer() {
		return serviceServer;
	}
	
}
