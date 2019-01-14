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

		CompassHTSensor CompassS = new CompassHTSensor(SensorPort.S3);
		LightSensor LightSR = new LightSensor(SensorPort.S2);
		LightSensor LightSL = new LightSensor(SensorPort.S1);

		bluetoothConnection = Bluetooth.waitForConnection();
		bluetoothConnection.setIOMode(NXTConnection.RAW);
		dataOutputStream = bluetoothConnection.openDataOutputStream();
		dataInputStream = bluetoothConnection.openDataInputStream();

		int androidmessage = 0;
		boolean end = false;
		boolean rechts = false;
		boolean links = false;

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

				Motor.B.setSpeed(360);
				Motor.C.setSpeed(360);

				Motor.B.forward();
				Motor.C.forward();

				while (true) {
					
					
					
					
					
					// wenn der linke sensor eine rote linie findet
					if (LightSL.getLightValue() == 48 || LightSL.getLightValue() == 49) {
						links = true;
						rechts= false;
						boolean found = false;
						Motor.B.setSpeed(360);
						Motor.C.setSpeed(360);
						while (true) {
							if (LightSL.getLightValue() < 48) {
								break;
							}
							if (LightSL.getLightValue() > 53) {
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
					
					
					if (LightSL.getLightValue() == 48 || LightSL.getLightValue() == 49) {
						links = true;
						rechts= false;
						boolean found = false;
						Motor.B.setSpeed(360);
						Motor.C.setSpeed(360);
						while (true) {
							if (LightSL.getLightValue() < 48) {
								break;
							}
							if (LightSL.getLightValue() > 53) {
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
					
					
					if (LightSR.getLightValue() == 48 || LightSR.getLightValue() == 49) {
						links = false;
						rechts= true;
						boolean found = false;
						Motor.B.setSpeed(360);
						Motor.C.setSpeed(360);
						while (true) {
							if (LightSR.getLightValue() < 48) {
								break;
							}
							if (LightSR.getLightValue() > 53) {
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
					
					
					
					
					
					
					
					if (LightSL.getLightValue() >= 54||LightSR.getLightValue()>=54) {
						Motor.B.stop(true);
						Motor.C.stop(true);
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
					for (int i = 0; i <= 9; i++) {
						Thread.sleep(100);
						if (LightSL.getLightValue() == 48 || LightSL.getLightValue() == 49) {
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
					for (int i = 0; i <= 11; i++) {
						Thread.sleep(100);
						if (LightSL.getLightValue() == 48 || LightSL.getLightValue() == 49) {
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
					for (int i = 0; i <= 14; i++) {
						Thread.sleep(100);
						if (LightSL.getLightValue() == 48 || LightSL.getLightValue() == 49) {
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
					for (int i = 0; i <= 16; i++) {
						Thread.sleep(100);
						if (LightSL.getLightValue() == 48 || LightSL.getLightValue() == 49) {
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
					for (int i = 0; i <= 19; i++) {
						Thread.sleep(100);

						if (LightSL.getLightValue() == 48 || LightSL.getLightValue() == 49) {
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
					for (int i = 0; i <= 21; i++) {
						Thread.sleep(100);
						if (LightSL.getLightValue() == 48 || LightSL.getLightValue() == 49) {
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
					for (int i = 0; i <= 9; i++) {
						Thread.sleep(100);

						if (LightSL.getLightValue() == 48 || LightSL.getLightValue() == 49) {
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
						if (LightSL.getLightValue() == 48 || LightSL.getLightValue() == 49) {
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
					for (int i = 0; i <= 14; i++) {
						Thread.sleep(100);

						if (LightSL.getLightValue() == 48 || LightSL.getLightValue() == 49) {
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
					for (int i = 0; i <= 17; i++) {
						Thread.sleep(100);
						if (LightSL.getLightValue() == 48 || LightSL.getLightValue() == 49) {
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
					for (int i = 0; i <= 19; i++) {
						Thread.sleep(100);

						if (LightSL.getLightValue() == 48 || LightSL.getLightValue() == 49) {
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
					for (int i = 0; i <= 21; i++) {
						Thread.sleep(100);

						if (LightSL.getLightValue() == 48 || LightSL.getLightValue() == 49) {
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
