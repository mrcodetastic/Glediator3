// 
// Decompiled by Procyon v0.5.36
// 

package Output;

import java.io.IOException;
import java.net.DatagramPacket;
import java.awt.Color;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.net.InetAddress;
import java.net.DatagramSocket;

public class TPM2NetOutput
{
    DatagramSocket tpm2_socket;
    InetAddress dest_ip;
    private int size_x;
    private int size_y;
    //private int num_unis;
    //private InetAddress[] ip_addr;
    //private int[] uni_id;
    //private int[] data_length;
    private int tpm2_port;
    //private int[][] patch_lut;
    private byte[] frame_rgb;
    private int sequence;
    private byte[] output_buffer;
    private byte[] data_buffer;
    private boolean socket_open;
    private static final int TPM2_NET_HEADER_LENGTH = 6;
    private static final int TPM2_NET_MAX_PACKET_PAYLOAD_SIZE = 1200; 
    
    public TPM2NetOutput() {
        this.socket_open = false;
        //this.dest_ip = new InetAddress[1];
        try {
            //this.dest_ip = InetAddress.getByName("localhost");
        	
        	this.dest_ip = InetAddress.getByName("192.168.43.214");
            
            System.out.print("Destination IP Address:");
            System.out.println(dest_ip.toString());
            
        }
        catch (UnknownHostException ex) {
            System.out.println(ex);
        }
        this.tpm2_port = 65506;
        this.sequence = 1;
        this.output_buffer = new byte[TPM2_NET_MAX_PACKET_PAYLOAD_SIZE + TPM2_NET_HEADER_LENGTH + 2]; // header + payload + end byte (0x36)
        //this.data_buffer = new byte[1];
        //this.patch_lut = new int[1][1];
    }
    
    public String startTPM2_Net() {
        String status;
        try {
            this.tpm2_socket = new DatagramSocket(this.tpm2_port);
            status = "TPM2.Net socket open.";
            this.socket_open = true;
        }
        catch (SocketException ex) {
            System.out.println(ex);
            status = "Failed to open TPM2.Net socket!";
            this.socket_open = false;
        }
        return status;
    }
    
    public String stopTPM2_Net() {
        String status;
        System.out.println("Attempting to close socket");
        if (this.socket_open) {
            this.tpm2_socket.close();
            status = "TPM2.Net socket closed.";
            this.socket_open = false;
        }
        else {
            status = "TPM2.Net socket allready closed!";
        }
        return status;
    }
    
    public void set_parameters(final int[][] _unis, final int[][][] _map) {
        //this.num_unis = _unis.length;
        this.size_x = 64; //_map.length;
        this.size_y = 32; //_map[0].length;
        //this.ip_addr = new InetAddress[this.num_unis];
        //this.uni_id = new int[this.num_unis];
        //this.data_length = new int[this.num_unis];
        //final byte[] ip = new byte[4];
        this.frame_rgb = new byte[this.size_x * this.size_y * 3];
        //this.patch_lut = new int[this.num_unis][1];
        //int max_data_length = 0;
        
        /*
        for (int i = 0; i < this.num_unis; ++i) {
            for (int j = 0; j < 4; ++j) {
                ip[j] = (byte)_unis[i][j];
            }
            try {
                this.ip_addr[i] = InetAddress.getByAddress(ip);
            }
            catch (UnknownHostException ex) {
                System.out.println(ex);
            }
            this.uni_id[i] = _unis[i][6];
            this.data_length[i] = _unis[i][7];
            if (this.data_length[i] > max_data_length) {
                max_data_length = this.data_length[i];
            }
            this.patch_lut[i] = new int[this.data_length[i]];
            for (int j = 0; j < this.data_length[i]; ++j) {
                this.patch_lut[i][j] = -1;
            }
        }
        for (int x = 0; x < this.size_x; ++x) {
            for (int y = 0; y < this.size_y; ++y) {
                final int uni_index = _map[x][y][0];
                final int ch_r = _map[x][y][1];
                final int ch_g = _map[x][y][2];
                final int ch_b = _map[x][y][3];
                this.patch_lut[uni_index][ch_r] = (y * this.size_x + x) * 3 + 0;
                this.patch_lut[uni_index][ch_g] = (y * this.size_x + x) * 3 + 1;
                this.patch_lut[uni_index][ch_b] = (y * this.size_x + x) * 3 + 2;
            }
        }
        (this.output_buffer = new byte[6 + max_data_length + 1])[6 + max_data_length] = 0;
        (this.data_buffer = new byte[max_data_length])[max_data_length - 1] = 0;
        */
    }
    
    public boolean get_TPM2_Status() {
        return this.socket_open;
    }
    
    public void send_out_one_frame(final Color[] frame) {
    	
        if (this.socket_open) {
            for (int x = 0; x < this.size_x; ++x) {
                for (int y = 0; y < this.size_y; ++y) {
                    final int index = y * this.size_x + x;
                    this.frame_rgb[3 * index + 0] = (byte)frame[index].getRed();
                    this.frame_rgb[3 * index + 1] = (byte)frame[index].getGreen();
                    this.frame_rgb[3 * index + 2] = (byte)frame[index].getBlue();
                }
            }
            
            int packets_required = (int) Math.ceil((this.frame_rgb.length + TPM2_NET_HEADER_LENGTH) / TPM2_NET_MAX_PACKET_PAYLOAD_SIZE);
            System.out.print("Going to need packets:");
            System.out.println(packets_required);
            
            byte payload[]      = new byte[TPM2_NET_MAX_PACKET_PAYLOAD_SIZE];
            int  payload_len    = TPM2_NET_MAX_PACKET_PAYLOAD_SIZE;
                                    
            for (int packet_num = 0; packet_num < packets_required; packet_num++)
            {
            	
            	if ( packet_num < packets_required ) {
                    System.arraycopy(this.frame_rgb, packet_num*TPM2_NET_MAX_PACKET_PAYLOAD_SIZE, payload, 0, TPM2_NET_MAX_PACKET_PAYLOAD_SIZE);            		            		
            	}
            	else
            	{
            		// Whatever crap is left
            		payload_len = this.frame_rgb.length - (packet_num*TPM2_NET_MAX_PACKET_PAYLOAD_SIZE); 
            		System.arraycopy(this.frame_rgb, packet_num*TPM2_NET_MAX_PACKET_PAYLOAD_SIZE, payload, 0, payload_len);            		            		
            	}
            	
            	// Who cares about the patching crap.
            	this.send_out_tpm2_packet(this.dest_ip, 0, packets_required, payload_len, payload);
            }
            
            
            
            /*
            for (int uni = 0; uni < this.num_unis; ++uni) {
                for (int channel = 0; channel < this.data_length[uni]; ++channel) {
                    final int position = this.patch_lut[uni][channel];
                    if (position == -1) {
                        this.data_buffer[channel] = 0;
                    }
                    else {
                        this.data_buffer[channel] = this.frame_rgb[position];
                    }
                }
                this.send_out_tpm2_packet(this.ip_addr[uni], this.uni_id[uni], this.num_unis, this.data_length[uni], this.data_buffer);
                ++this.sequence;
                if (this.sequence == 256) {
                    this.sequence = 1;
                }
            }
            */
        }
        else
        {
        	System.out.println("TPM2_Net Socket not open!");
        }
    }
    
    private void send_out_tpm2_packet(final InetAddress node_ip, final int universeID, final int total_packet_count, final int length, final byte[] data) {
        this.output_buffer[0] = -100;
        this.output_buffer[1] = -38;
        this.output_buffer[2] = (byte)(length >> 8 & 0xFF);
        this.output_buffer[3] = (byte)(length & 0xFF);
        this.output_buffer[4] = (byte)universeID;
        this.output_buffer[5] = (byte)total_packet_count;
        System.arraycopy(data, 0, this.output_buffer, 6, length);
        this.output_buffer[6 + length] = 54;
        final DatagramPacket tpm2_packet = new DatagramPacket(this.output_buffer, length + 6 + 1, node_ip, this.tpm2_port);
        try {
            this.tpm2_socket.send(tpm2_packet);
        }
        catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
