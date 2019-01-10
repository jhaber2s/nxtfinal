import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;



public class connector {
	
	public DataOutputStream dataout;
	public DataInputStream datain;
	public NXTConnection nxt;
	public String adress;
	
public connector() {
	
}

public void connect() {
	nxt = Bluetooth.waitForConnection();
	dataout = nxt.openDataOutputStream();
	datain = nxt.openDataInputStream();
	
	
}

public int readdata() {
	int data;
	
	try {
	data = datain.read();
	return data;
	}catch(IOException e) 
	{
		
		return -1;
		
	}
	
	
}

public void senddata(int data) {
	try {
	dataout.write(data);
	}
	catch(IOException e) {
		
		
	}
	
}
}
