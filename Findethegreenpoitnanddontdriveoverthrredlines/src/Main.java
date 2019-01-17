import lejos.nxt.ColorSensor.Color;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.addon.ColorHTSensor;
import lejos.nxt.addon.CompassHTSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.comm.Bluetooth;
import lejos.nxt.LightSensor;
import lejos.nxt.comm.NXTConnection;
import lejos.nxt.ColorSensor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Main {

	public static DataOutputStream dataOutputStream;
	public static DataInputStream dataInputStream;
	public static NXTConnection bluetoothConnection;

	public static void main(String[] Args) throws IOException {

		
		LightSensor LightSR = new LightSensor(SensorPort.S3);
		LightSensor LightSL = new LightSensor(SensorPort.S1);
		ColorHTSensor ColorS = new ColorHTSensor(SensorPort.S2);

		bluetoothConnection = Bluetooth.waitForConnection();
		bluetoothConnection.setIOMode(NXTConnection.RAW);
		dataOutputStream = bluetoothConnection.openDataOutputStream();
		dataInputStream = bluetoothConnection.openDataInputStream();

		int androidmessage = 0;
		boolean end = false;
		boolean rechts = false;
		boolean links = false;
		boolean found = false;
		
		LightSL.setFloodlight(true);
		LightSR.setFloodlight(true);
//		while(true) {
//			
//			LCD.drawInt(ColorS.getColor().getRed(), 0, 0);
//			LCD.drawInt(ColorS.getColor().getGreen(), 0, 1);
//			LCD.drawInt(ColorS.getColorID(), 0, 2);
//			
//			if(rechts == true) {
//				break;
//			}
//			
//		}
		
		
		
		
		
		
//		while(true) {
//			
//			Motor.B.forward();
//			Motor.C.forward();
//			
//			if(ColorS.getColor().getGreen()>10) {
//				Motor.B.stop();
//				Motor.C.stop();
//		
//				break;
//				
//			}
//			
//			if((ColorS.getColor().getRed()>5)&&(ColorS.getColor().getGreen()<5)) {
//				
//			
//				
//				
//				Motor.B.stop(true);
//				Motor.C.stop(true);
//				
//				
//				Motor.B.backward();
//				Motor.C.backward();
//				try {
//					Thread.sleep(900);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				Motor.B.stop(true);
//				Motor.C.stop(true);
//				
//				
//				
//			}
//			
//			
//			if(rechts == true) {
//				break;
//			}
//			
//		}
		
		
		
		
		

		LCD.drawString("Wartet auf nachricht", 1, 1);

		while (true) {
			try {
				androidmessage = read();
			} catch (IOException e) {

			}
			LCD.clear();
			LCD.drawString(Integer.toString(androidmessage), 1, 1);

			switch (androidmessage) {

			case 100: {
				links= false;
				rechts= false;

				Motor.B.setSpeed(250);
				Motor.C.setSpeed(250);

				if(!found) {
				Motor.B.forward();
				Motor.C.forward();
				
				}

				while (true) {
					
					
					
					
					
					// wenn der linke sensor eine rote linie findet
					if (LightSL.getLightValue() == 47 || LightSL.getLightValue() == 48 || LightSL.getLightValue() == 49) {
						links = true;
						rechts= false;
						
						Motor.B.setSpeed(250);
						Motor.C.setSpeed(250);
						
						
						
						while (true) {
							if (LightSL.getLightValue() < 47) {
								break;
							}
							if (LightSL.getLightValue() > 51) {
								found = true;
								Motor.B.stop(true);
								Motor.C.stop(true);
								write(1);
								break;
							}
						}
						if (found == true) {
							break;
						}
						Motor.B.stop(true);
						Motor.C.stop(true);
						
						Motor.B.setSpeed(360);
						Motor.C.setSpeed(360);
						
						Motor.B.backward();
						Motor.C.backward();
						try {
							Thread.sleep(900);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Motor.B.stop(true);
						Motor.C.stop(true);
						write(50);
						break;
					}
					
					//wenn der rechte Sensor eine rote linie findet
					if (LightSR.getLightValue() == 47 || LightSR.getLightValue() == 48 || LightSR.getLightValue() == 49) {
						links = false;
						rechts= true;
						
						Motor.B.setSpeed(250);
						Motor.C.setSpeed(250);
						while (true) {
							if (LightSL.getLightValue() < 47) {
								break;
							}
							if (LightSL.getLightValue() > 51) {
								found = true;
								Motor.B.stop(true);
								Motor.C.stop(true);
								write(1);
								break;
							}
						}
						if (found == true) {
							break;
						}
						Motor.B.stop(true);
						Motor.C.stop(true);
						

						Motor.B.setSpeed(360);
						Motor.C.setSpeed(360);
						Motor.B.backward();
						Motor.C.backward();
						try {
							Thread.sleep(900);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Motor.B.stop(true);
						Motor.C.stop(true);
						write(50);
						break;
					}
					
					
					if((ColorS.getColor().getGreen()<5)&&(ColorS.getColor().getRed()>5)) {
						
					links= true;
						Motor.B.stop(true);
						Motor.C.stop(true);

						Motor.B.setSpeed(360);
						Motor.C.setSpeed(360);
						Motor.B.backward();
						Motor.C.backward();
						try {
							Thread.sleep(900);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Motor.B.stop(true);
						Motor.C.stop(true);
						write(50);
						break;
						
						
						
						
					}
					
					
					if(ColorS.getColor().getGreen()>20) {
						
						found =true;
						Motor.B.stop(true);
						Motor.C.stop(true);
						write(1);
						break;
						
						
						
						
						
					}
					
					
					
					
					
					if (LightSL.getLightValue() >= 51||LightSR.getLightValue()>=51) {
						Motor.B.stop(true);
						Motor.C.stop(true);
						found=true;
						
						write(1);
						break;
					}
				}

			}
				break;
			case 60: {
				Motor.B.setSpeed(180);
				Motor.C.setSpeed(180);
				
				
				if(links==true) {
					Motor.B.forward();
					Motor.C.backward();
					
				}
				if(rechts==true) {
					
					Motor.B.backward();
					Motor.C.forward();
				}
								try {
					for (int i = 0; i <= 4; i++) {
						Thread.sleep(100);
						if ((LightSL.getLightValue()>=47 && LightSL.getLightValue()<=49)||(LightSR.getLightValue()>=47 && LightSR.getLightValue()<=49)) {
							Motor.B.backward();
							Motor.C.backward();
							Thread.sleep(200);
							Motor.B.stop(true);
							Motor.C.stop(true);

							if(links==true) {
								Motor.B.forward();
								Motor.C.backward();
								
							}
							if(rechts==true) {
								
								Motor.B.backward();
								Motor.C.forward();
							}
						}
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				break;
			case 61: {
				Motor.B.setSpeed(180);
				Motor.C.setSpeed(180);
				if(links==true) {
					Motor.B.forward();
					Motor.C.backward();
					
				}
				if(rechts==true) {
					
					Motor.B.backward();
					Motor.C.forward();
				}
				try {
					for (int i = 0; i <= 5; i++) {
						Thread.sleep(100);
						if ((LightSL.getLightValue()>=47 && LightSL.getLightValue()<=49)||(LightSR.getLightValue()>=47 && LightSR.getLightValue()<=49)) {
							
							Motor.B.backward();
							Motor.C.backward();
							Thread.sleep(200);
							Motor.B.stop(true);
							Motor.C.stop(true);
							if(links==true) {
								Motor.B.forward();
								Motor.C.backward();
								
							}
							if(rechts==true) {
								
								Motor.B.backward();
								Motor.C.forward();
							}
						}
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				break;
			case 62: {

				Motor.B.setSpeed(180);
				Motor.C.setSpeed(180);
				if(links==true) {
					Motor.B.forward();
					Motor.C.backward();
					
				}
				if(rechts==true) {
					
					Motor.B.backward();
					Motor.C.forward();
				}
				try {
					for (int i = 0; i <= 6; i++) {
						Thread.sleep(100);
						if ((LightSL.getLightValue()>=47 && LightSL.getLightValue()<=49)||(LightSR.getLightValue()>=47 && LightSR.getLightValue()<=49)) {
							Motor.B.backward();
							Motor.C.backward();
							Thread.sleep(200);
							Motor.B.stop(true);
							Motor.C.stop(true);
							if(links==true) {
								Motor.B.forward();
								Motor.C.backward();
								
							}
							if(rechts==true) {
								
								Motor.B.backward();
								Motor.C.forward();
							}
						}
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

				break;
			case 63: {
				Motor.B.setSpeed(180);
				Motor.C.setSpeed(180);
				if(links==true) {
					Motor.B.forward();
					Motor.C.backward();
					
				}
				if(rechts==true) {
					
					Motor.B.backward();
					Motor.C.forward();
				}
				try {
					for (int i = 0; i <= 7; i++) {
						Thread.sleep(100);
						if ((LightSL.getLightValue()>=47 && LightSL.getLightValue()<=49)||(LightSR.getLightValue()>=47 && LightSR.getLightValue()<=49)) {
							Motor.B.backward();
							Motor.C.backward();
							Thread.sleep(200);
							Motor.B.stop(true);
							Motor.C.stop(true);
							if(links==true) {
								Motor.B.forward();
								Motor.C.backward();
								
							}
							if(rechts==true) {
								
								Motor.B.backward();
								Motor.C.forward();
							}
						}
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				break;
			case 64: {
				Motor.B.setSpeed(180);
				Motor.C.setSpeed(180);
				if(links==true) {
					Motor.B.forward();
					Motor.C.backward();
					
				}
				if(rechts==true) {
					
					Motor.B.backward();
					Motor.C.forward();
				}
				try {
					for (int i = 0; i <= 8; i++) {
						Thread.sleep(100);

						if ((LightSL.getLightValue()>=47 && LightSL.getLightValue()<=49)||(LightSR.getLightValue()>=47 && LightSR.getLightValue()<=49)) {
							Motor.B.backward();
							Motor.C.backward();
							Thread.sleep(200);
							Motor.B.stop(true);
							Motor.C.stop(true);
							if(links==true) {
								Motor.B.forward();
								Motor.C.backward();
								
							}
							if(rechts==true) {
								
								Motor.B.backward();
								Motor.C.forward();
							}
						}
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				break;
			case 65: {
				Motor.B.setSpeed(180);
				Motor.C.setSpeed(180);
				if(links==true) {
					Motor.B.forward();
					Motor.C.backward();
					
				}
				if(rechts==true) {
					
					Motor.B.backward();
					Motor.C.forward();
				}
				try {
					for (int i = 0; i <= 9; i++) {
						Thread.sleep(100);
						if ((LightSL.getLightValue()>=47 && LightSL.getLightValue()<=49)||(LightSR.getLightValue()>=47 && LightSR.getLightValue()<=49)) {
							Motor.B.backward();
							Motor.C.backward();
							Thread.sleep(200);
							Motor.B.stop(true);
							Motor.C.stop(true);
							if(links==true) {
								Motor.B.forward();
								Motor.C.backward();
								
							}
							if(rechts==true) {
								
								Motor.B.backward();
								Motor.C.forward();
							}
						}
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				break;
			case 66: {
				Motor.B.setSpeed(180);
				Motor.C.setSpeed(180);
				if(links==true) {
					Motor.B.forward();
					Motor.C.backward();
					
				}
				if(rechts==true) {
					
					Motor.B.backward();
					Motor.C.forward();
				}
				try {
					for (int i = 0; i <= 10; i++) {
						Thread.sleep(100);

						if ((LightSL.getLightValue()>=47 && LightSL.getLightValue()<=49)||(LightSR.getLightValue()>=47 && LightSR.getLightValue()<=49)) {
							Motor.B.backward();
							Motor.C.backward();
							Thread.sleep(200);
							Motor.B.stop(true);
							Motor.C.stop(true);
							if(links==true) {
								Motor.B.forward();
								Motor.C.backward();
								
							}
							if(rechts==true) {
								
								Motor.B.backward();
								Motor.C.forward();
							}
						}
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				break;
			case 67: {
				Motor.B.setSpeed(180);
				Motor.C.setSpeed(180);
				if(links==true) {
					Motor.B.forward();
					Motor.C.backward();
					
				}
				if(rechts==true) {
					
					Motor.B.backward();
					Motor.C.forward();
				}
				try {
					for (int i = 0; i <= 11; i++) {
						Thread.sleep(100);
						if ((LightSL.getLightValue()>=47 && LightSL.getLightValue()<=49)||(LightSR.getLightValue()>=47 && LightSR.getLightValue()<=49)) {
							Motor.B.backward();
							Motor.C.backward();
							Thread.sleep(200);
							Motor.B.stop(true);
							Motor.C.stop(true);
							if(links==true) {
								Motor.B.forward();
								Motor.C.backward();
								
							}
							if(rechts==true) {
								
								Motor.B.backward();
								Motor.C.forward();
							}
						}
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				break;
			case 68: {
				Motor.B.setSpeed(180);
				Motor.C.setSpeed(180);
				if(links==true) {
					Motor.B.forward();
					Motor.C.backward();
					
				}
				if(rechts==true) {
					
					Motor.B.backward();
					Motor.C.forward();
				}
				try {
					for (int i = 0; i <= 12; i++) {
						Thread.sleep(100);

						if ((LightSL.getLightValue()>=47 && LightSL.getLightValue()<=49)||(LightSR.getLightValue()>=47 && LightSR.getLightValue()<=49)) {
							Motor.B.backward();
							Motor.C.backward();
							Thread.sleep(200);
							Motor.B.stop(true);
							Motor.C.stop(true);
							if(links==true) {
								Motor.B.forward();
								Motor.C.backward();
								
							}
							if(rechts==true) {
								
								Motor.B.backward();
								Motor.C.forward();
							}
						}
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				break;
			case 69: {
				Motor.B.setSpeed(180);
				Motor.C.setSpeed(180);
				if(links==true) {
					Motor.B.forward();
					Motor.C.backward();
					
				}
				if(rechts==true) {
					
					Motor.B.backward();
					Motor.C.forward();
				}
				try {
					for (int i = 0; i <= 13; i++) {
						Thread.sleep(100);
						if ((LightSL.getLightValue()>=47 && LightSL.getLightValue()<=49)||(LightSR.getLightValue()>=47 && LightSR.getLightValue()<=49)) {
							Motor.B.backward();
							Motor.C.backward();
							Thread.sleep(200);
							Motor.B.stop(true);
							Motor.C.stop(true);
							if(links==true) {
								Motor.B.forward();
								Motor.C.backward();
								
							}
							if(rechts==true) {
								
								Motor.B.backward();
								Motor.C.forward();
							}
						}
						Thread.sleep(100);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				break;
			case 70: {
				Motor.B.setSpeed(180);
				Motor.C.setSpeed(180);
				if(links==true) {
					Motor.B.forward();
					Motor.C.backward();
					
				}
				if(rechts==true) {
					
					Motor.B.backward();
					Motor.C.forward();
				}
				try {
					for (int i = 0; i <= 14; i++) {
						Thread.sleep(100);

						if ((LightSL.getLightValue()>=47 && LightSL.getLightValue()<=49)||(LightSR.getLightValue()>=47 && LightSR.getLightValue()<=49)) {
							Motor.B.backward();
							Motor.C.backward();
							Thread.sleep(200);
							Motor.B.stop(true);
							Motor.C.stop(true);
							if(links==true) {
								Motor.B.forward();
								Motor.C.backward();
								
							}
							if(rechts==true) {
								
								Motor.B.backward();
								Motor.C.forward();
							}
						}
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				break;
			case 71: {
				Motor.B.setSpeed(180);
				Motor.C.setSpeed(180);
				if(links==true) {
					Motor.B.forward();
					Motor.C.backward();
					
				}
				if(rechts==true) {
					
					Motor.B.backward();
					Motor.C.forward();
				}
				try {
					for (int i = 0; i <= 15; i++) {
						Thread.sleep(100);

						if ((LightSL.getLightValue()>=47 && LightSL.getLightValue()<=49)||(LightSR.getLightValue()>=47 && LightSR.getLightValue()<=49)) {
							Motor.B.backward();
							Motor.C.backward();
							Thread.sleep(200);
							Motor.B.stop(true);
							Motor.C.stop(true);
							if(links==true) {
								Motor.B.forward();
								Motor.C.backward();
								
							}
							if(rechts==true) {
								
								Motor.B.backward();
								Motor.C.forward();
							}
						}
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				break;

				
			
//			case 1: {
//
//				
//
//			}
//				break;
//
//			case 2: {
//
//				Motor.B.stop();
//				Motor.C.stop();
//
//				Motor.B.setSpeed(50);
//				Motor.C.setSpeed(-50);
//
//			}
//				break;
//
//			case 3: {
//
//				Motor.B.stop();
//				Motor.A.stop();
//
//				Motor.B.setSpeed(-50);
//				Motor.C.setSpeed(50);
//
//			}
//				break;
//
//			case 4: {
//				
//				
//				try {
//					write(ColorS.getColorID());
//				}
//				catch(IOException e) {
//					
//					
//				}
//
//			}
//				break;
//
//			case 5: {
//				int loop= 0;
//
//				try {
//					
//					
//				
//					LCD.drawInt(LightSL.getLightValue(), 0, 0);
//					write(LightSL.getLightValue());
//					
//				}catch(IOException e){
//					
//					
//				}
//
//			}
//				break;
//			case 6:{
//				try {
//					write(ColorSS.getColorID());
//					
//				}catch(IOException e){
//					
//					
//				}
//				
//				
//			}break;
//			
//			case 7: {
//					
//				end = true;
//				
//			}break;

			}

			if (end == true) {
				// con.senddata(99);
				break;

			}

		}

	}

	public static int read() throws IOException {
		return dataInputStream.read();
	}

	public static void write(int x) throws IOException {
		dataOutputStream.write(x);
		dataOutputStream.flush();
	}

}
