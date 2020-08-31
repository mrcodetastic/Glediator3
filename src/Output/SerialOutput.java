// 
// Decompiled by Procyon v0.5.36
// 

package Output;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import com.fazecast.jSerialComm.*;
import com.fazecast.jSerialComm.SerialPort;


public class SerialOutput
{
	private List<SerialPort> serialPortList;
	private SerialPort[] serialPorts;
    private Enumeration enumComm;
    private SerialPort serialPort;
    private OutputStream outputStream;
    private boolean port_open;
    
    SerialOutput() {
        this.port_open = false;
    }
    
    public String[] getComPorts() {
    	
    	ArrayList<String> ports = new ArrayList<String>();
    	
    	String[] com_port_list = new String[0];
    	
        try {
        	
        	this.serialPorts = SerialPort.getCommPorts(); //CommPortIdentifier.getPortIdentifiers();
        	
     //   	System.out.println(this.serialPorts.toString());
        	this.serialPortList = Arrays.asList(this.serialPorts);        	
        	this.enumComm = Collections.enumeration(this.serialPortList);

        	int index = 0;
        	while (this.enumComm.hasMoreElements()) {
            	
            	this.serialPort = (SerialPort) this.enumComm.nextElement();
            	
            	ports.add((String)this.serialPort.getSystemPortName());
            	
            	index++;
            }
    
            System.out.println("Number of COM ports found: " + index);
            
            String[] found_ports = new String[index];
            
            for (int i = 0; i < index; i++)
            {
            	found_ports[i] = ports.get(i);
            }
            
            return found_ports;
            		
            		
        }
        catch (UnsatisfiedLinkError err) {
            System.out.println("It seems like you don't have any RXTX binary in your Glediator folder nor elsewhere!");
           return new String[0];
        }
    }
    
    public String startSerial(final String com_port, final BaudRate baud_rate) {
        String status = com_port + " started";
        boolean foundPort = false;
        if (this.port_open) {
            status = "Serial Port allready opend!";
        }
        else {
        	
        	this.serialPorts = SerialPort.getCommPorts(); //CommPortIdentifier.getPortIdentifiers();
        	serialPortList = Arrays.asList(this.serialPorts);        	
        	this.enumComm = Collections.enumeration(this.serialPortList);
            while (this.enumComm.hasMoreElements()) {
            	
            	this.serialPort = (SerialPort) this.enumComm.nextElement();            	

                final String portName = com_port;
                if (portName.contentEquals(this.serialPort.getSystemPortName())) {
                    foundPort = true;
                    break;
                }
            }
            if (!foundPort) {
                status = "Serial Port not found!";
            }
            else {
                try {
                    this.serialPort.openPort(500); // saftey sleep time
                    this.port_open = true;
                }
                catch ( Exception e) {
                    status = "Serial Port occupied!";
                    this.port_open = false;
                }
                this.outputStream = this.serialPort.getOutputStream();
                try {
                    int br_i = 0;
                    switch (baud_rate) {
                        case B_115200: {
                            br_i = 115200;
                            break;
                        }
                        case B_230400: {
                            br_i = 230400;
                            break;
                        }
                        case B_250000: {
                            br_i = 250000;
                            break;
                        }
                        case B_500000: {
                            br_i = 500000;
                            break;
                        }
                        case B_921600: {
                            br_i = 921600;
                            break;
                        }
                        case B_1000000: {
                            br_i = 1000000;
                            break;
                        }
                        case B_1250000: {
                            br_i = 1250000;
                            break;
                        }
                    }
                    this.serialPort.setComPortParameters(br_i, 8, 1, 0);
                }
                catch (Exception e3) {
                    status = "Could not set interface parameters!";
                    this.port_open = false;
                }
            }
        }
        return status;
    }
    
    public String stopSerial(final String _comport) {
        String status;
        if (this.port_open) {
            try {
                this.serialPort.closePort();
            }
            catch (Exception ex) {
                System.out.println(ex);
            }
            this.port_open = false;
            status = _comport + " closed!";
        }
        else {
            status = _comport + " allready closed!";
        }
        return status;
    }
    
    public void sendSerialData(final byte[] output_buffer, final int size) {
        if (this.port_open) {
            try {
                this.outputStream.write(output_buffer, 0, size);
            }
            catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
