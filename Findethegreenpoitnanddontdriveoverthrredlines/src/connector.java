import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;



public class connector {
	
private	NXTConnection bluecon;
private	DataInputStream datain;
private DataOutputStream dataout;
	
	public connector() {
		
		bluecon = Bluetooth.waitForConnection();
		bluecon.setIOMode(bluecon.RAW);
		datain = bluecon.openDataInputStream();
		dataout = bluecon.openDataOutputStream();
				
		
	}
	
	public int read() throws IOException {
		
		return datain.read();
		
	}
	
	public void write(int msg) throws IOException{
		dataout.write(msg);
		dataout.flush();
		
	}
	
	

}
