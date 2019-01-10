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
		ColorHTSensor ColorS = new ColorHTSensor(SensorPort.S1);
		CompassHTSensor CompassS = new CompassHTSensor(SensorPort.S2);
		LightSensor LightS = new LightSensor(SensorPort.S1);
		ColorSensor ColorSS = new ColorSensor(SensorPort.S1);

		
		
		

		int test = 0;

		lejos.robotics.Color farbe = null;

//		while(test<9000000) 
//		{
//			
//			farbe = ColorS.getColor();
//			
//			LCD.drawInt(farbe.getGreen(), 1, 1);
//			LCD.drawInt(farbe.getRed(), 1, 2);
//			LCD.drawInt(farbe.getBlue(), 1, 3);
//			
//			test = ColorS.getColorID();
//			
//			
//			LCD.drawInt(LightS.getNormalizedLightValue(), 0, 0);
//			LCD.drawInt(LightS.getLightValue(), 0, 1);
//			
//		}

		bluetoothConnection = Bluetooth.waitForConnection();
		bluetoothConnection.setIOMode(NXTConnection.RAW);
		dataOutputStream = bluetoothConnection.openDataOutputStream();
		dataInputStream = bluetoothConnection.openDataInputStream();

		int androidmessage = 0;
		boolean end = false;

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
					if (LightS.getLightValue() == 48 || LightS.getLightValue() == 49) {
						boolean found = false;
						Motor.B.setSpeed(360);
						Motor.C.setSpeed(360);
						while (true) {
							if (LightS.getLightValue() < 48)
								break;
							if (LightS.getLightValue() > 53) {
								found = true;
								Motor.B.stop(true);
								Motor.C.stop(true);
								write(1);
								break;
							}
						}
						if (found == true)
							break;
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
					if (LightS.getLightValue() >= 54) {
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
				Motor.B.backward();
				Motor.C.forward();
				try {
					for (int i = 0; i <= 9; i++) {
						Thread.sleep(100);
						if (LightS.getLightValue() == 48 || LightS.getLightValue() == 49) {
							Motor.B.backward();
							Motor.C.backward();
							Thread.sleep(200);
							Motor.B.stop(true);
							Motor.C.stop(true);
							Motor.B.backward();
							Motor.C.forward();
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
				Motor.B.backward();
				Motor.C.forward();
				try {
					for (int i = 0; i <= 11; i++) {
						Thread.sleep(100);
						if (LightS.getLightValue() == 48 || LightS.getLightValue() == 49) {
							Motor.B.backward();
							Motor.C.backward();
							Thread.sleep(200);
							Motor.B.stop(true);
							Motor.C.stop(true);
							Motor.B.backward();
							Motor.C.forward();
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
				Motor.B.backward();
				Motor.C.forward();
				try {
					for (int i = 0; i <= 14; i++) {
						Thread.sleep(100);
						if (LightS.getLightValue() == 48 || LightS.getLightValue() == 49) {
							Motor.B.backward();
							Motor.C.backward();
							Thread.sleep(200);
							Motor.B.stop(true);
							Motor.C.stop(true);
							Motor.B.backward();
							Motor.C.forward();
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
				Motor.B.backward();
				Motor.C.forward();
				try {
					for (int i = 0; i <= 16; i++) {
						Thread.sleep(100);
						if (LightS.getLightValue() == 48 || LightS.getLightValue() == 49) {
							Motor.B.backward();
							Motor.C.backward();
							Thread.sleep(200);
							Motor.B.stop(true);
							Motor.C.stop(true);
							Motor.B.backward();
							Motor.C.forward();
						}
					}				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				break;
			case 64: {
				Motor.B.setSpeed(180);
				Motor.C.setSpeed(180);
				Motor.B.backward();
				Motor.C.forward();
				try {
					for (int i = 0; i <= 19; i++) {
						Thread.sleep(100);

						if (LightS.getLightValue() == 48 || LightS.getLightValue() == 49) {
							Motor.B.backward();
							Motor.C.backward();
							Thread.sleep(200);
							Motor.B.stop(true);
							Motor.C.stop(true);
							Motor.B.backward();
							Motor.C.forward();
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
				Motor.B.backward();
				Motor.C.forward();
				try {
					for (int i = 0; i <= 21; i++) {
						Thread.sleep(100);
						if (LightS.getLightValue() == 48 || LightS.getLightValue() == 49) {
							Motor.B.backward();
							Motor.C.backward();
							Thread.sleep(200);
							Motor.B.stop(true);
							Motor.C.stop(true);
							Motor.B.backward();
							Motor.C.forward();
						}
					}				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				break;
			case 66: {
				Motor.B.setSpeed(180);
				Motor.C.setSpeed(180);
				Motor.C.backward();
				Motor.B.forward();
				try {
					for (int i = 0; i <= 9; i++) {
						Thread.sleep(100);

						if (LightS.getLightValue() == 48 || LightS.getLightValue() == 49) {
							Motor.B.backward();
							Motor.C.backward();
							Thread.sleep(200);
							Motor.B.stop(true);
							Motor.C.stop(true);
							Motor.B.forward();
							Motor.C.backward();
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
				Motor.C.backward();
				Motor.B.forward();
				try {
					for (int i = 0; i <= 11; i++) {
						Thread.sleep(100);
						if (LightS.getLightValue() == 48 || LightS.getLightValue() == 49) {
							Motor.B.backward();
							Motor.C.backward();
							Thread.sleep(200);
							Motor.B.stop(true);
							Motor.C.stop(true);
							Motor.B.forward();
							Motor.C.backward();
						}
						}				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				break;
			case 68: {
				Motor.B.setSpeed(180);
				Motor.C.setSpeed(180);
				Motor.C.backward();
				Motor.B.forward();
				try {
					for (int i = 0; i <= 14; i++) {
						Thread.sleep(100);

						if (LightS.getLightValue() == 48 || LightS.getLightValue() == 49) {
							Motor.B.backward();
							Motor.C.backward();
							Thread.sleep(200);
							Motor.B.stop(true);
							Motor.C.stop(true);
							Motor.B.forward();
							Motor.C.backward();
						}
						}					} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				break;
			case 69: {
				Motor.B.setSpeed(180);
				Motor.C.setSpeed(180);
				Motor.C.backward();
				Motor.B.forward();
				try {
					for (int i = 0; i <= 17; i++) {
						Thread.sleep(100);
						if (LightS.getLightValue() == 48 || LightS.getLightValue() == 49) {
							Motor.B.backward();
							Motor.C.backward();
							Thread.sleep(200);
							Motor.B.stop(true);
							Motor.C.stop(true);
							Motor.B.forward();
							Motor.C.backward();
						}
						Thread.sleep(100);}					} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				break;
			case 70: {
				Motor.B.setSpeed(180);
				Motor.C.setSpeed(180);
				Motor.C.backward();
				Motor.B.forward();
				try {
					for (int i = 0; i <= 19; i++) {
						Thread.sleep(100);

						if (LightS.getLightValue() == 48 || LightS.getLightValue() == 49) {
							Motor.B.backward();
							Motor.C.backward();
							Thread.sleep(200);
							Motor.B.stop(true);
							Motor.C.stop(true);
							Motor.B.forward();
							Motor.C.backward();
						}
						}					} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				break;
			case 71: {
				Motor.B.setSpeed(180);
				Motor.C.setSpeed(180);
				Motor.C.backward();
				Motor.B.forward();
				try {
					for (int i = 0; i <= 21; i++) {
						Thread.sleep(100);

						if (LightS.getLightValue() == 48 || LightS.getLightValue() == 49) {
							Motor.B.backward();
							Motor.C.backward();
							Thread.sleep(200);
							Motor.B.stop(true);
							Motor.C.stop(true);
							Motor.B.forward();
							Motor.C.backward();
						}
						}					} catch (InterruptedException e) {
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
//					LCD.drawInt(LightS.getLightValue(), 0, 0);
//					write(LightS.getLightValue());
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
