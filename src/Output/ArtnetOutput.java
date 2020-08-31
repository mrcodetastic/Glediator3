// 
// Decompiled by Procyon v0.5.36
// 

package Output;

import java.io.IOException;
import java.net.DatagramPacket;
import java.awt.Color;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.net.InetAddress;
import java.net.DatagramSocket;

public class ArtnetOutput
{
    DatagramSocket artnet_socket;
    private int size_x;
    private int size_y;
    private int num_unis;
    private InetAddress[] ip_addr;
    private int[] net_id;
    private int[] sub_net_id;
    private int[] uni_id;
    private int[] data_length;
    private int[][] patch_lut;
    private byte[] frame_rgb;
    private int sequence;
    private byte[] output_buffer;
    private byte[] data_buffer;
    private boolean socket_open;
    private int artnetport;
    private int art_dmx_header_length;
    private byte[] ID;
    private int ProdVer;
    private int OpCode;
    
    public ArtnetOutput() {
        this.socket_open = false;
        this.ip_addr = new InetAddress[1];
        try {
            this.ip_addr[0] = InetAddress.getByName("localhost");
        }
        catch (UnknownHostException ex) {
            System.out.println(ex);
        }
        this.artnetport = 6454;
        this.art_dmx_header_length = 18;
        this.ID = "Art-Net\u0000".getBytes();
        this.ProdVer = 14;
        this.OpCode = 20480;
        this.sequence = 1;
        this.output_buffer = new byte[this.art_dmx_header_length];
        this.data_buffer = new byte[1];
        this.patch_lut = new int[1][1];
    }
    
    public String startArtnet() {
        String status;
        try {
            (this.artnet_socket = new DatagramSocket((SocketAddress)null)).setReuseAddress(true);
            this.artnet_socket.setBroadcast(true);
            this.artnet_socket.bind(new InetSocketAddress(InetAddress.getByName("0.0.0.0"), this.artnetport));
            status = "Artnet socket open.";
            this.socket_open = true;
        }
        catch (Exception ex) {
            System.out.println(ex);
            status = "Failed to open Artnet socket!";
            this.socket_open = false;
        }
        return status;
    }
    
    public String stopArtnet() {
        String status;
        if (this.socket_open) {
            this.artnet_socket.close();
            status = "Artnet socket closed.";
            this.socket_open = false;
        }
        else {
            status = "Artnet socket allready closed!";
        }
        return status;
    }
    
    public void set_parameters(final int[][] _unis, final int[][][] _map) {
        this.num_unis = _unis.length;
        this.size_x = _map.length;
        this.size_y = _map[0].length;
        this.ip_addr = new InetAddress[this.num_unis];
        this.net_id = new int[this.num_unis];
        this.sub_net_id = new int[this.num_unis];
        this.uni_id = new int[this.num_unis];
        this.data_length = new int[this.num_unis];
        final byte[] ip = new byte[4];
        this.frame_rgb = new byte[this.size_x * this.size_y * 3];
        this.patch_lut = new int[this.num_unis][1];
        int max_data_length = 0;
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
            this.net_id[i] = _unis[i][4];
            this.sub_net_id[i] = _unis[i][5];
            this.uni_id[i] = _unis[i][6];
            this.data_length[i] = _unis[i][7];
            if (this.data_length[i] % 2 == 1) {
                final int[] data_length = this.data_length;
                final int n = i;
                ++data_length[n];
            }
            if (this.data_length[i] > 512) {
                this.data_length[i] = 512;
            }
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
        (this.output_buffer = new byte[this.art_dmx_header_length + max_data_length])[this.art_dmx_header_length + max_data_length - 1] = 0;
        (this.data_buffer = new byte[max_data_length])[max_data_length - 1] = 0;
    }
    
    public boolean get_artnet_status() {
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
                this.send_out_artnet_packet(this.ip_addr[uni], this.net_id[uni], this.sub_net_id[uni], this.uni_id[uni], this.data_length[uni], this.data_buffer);
                ++this.sequence;
                if (this.sequence == 256) {
                    this.sequence = 1;
                }
            }
        }
    }
    
    private void send_out_artnet_packet(final InetAddress node_ip, final int net, final int subnetID, final int universeID, final int length, final byte[] data) {
        System.arraycopy(this.ID, 0, this.output_buffer, 0, 8);
        this.output_buffer[8] = (byte)(this.OpCode & 0xFF);
        this.output_buffer[9] = (byte)(this.OpCode >> 8 & 0xFF);
        this.output_buffer[10] = (byte)(this.ProdVer >> 8 & 0xFF);
        this.output_buffer[11] = (byte)(this.ProdVer & 0xFF);
        this.output_buffer[12] = (byte)(this.sequence & 0xFF);
        this.output_buffer[13] = 0;
        final int actual_net = net & 0x7F;
        final int actual_subnet_id = subnetID & 0xF;
        final int actual_univers_id = universeID & 0xF;
        final int SubUniNet = actual_net << 8 | actual_subnet_id << 4 | actual_univers_id;
        this.output_buffer[14] = (byte)(SubUniNet & 0xFF);
        this.output_buffer[15] = (byte)(SubUniNet >> 8 & 0xFF);
        this.output_buffer[16] = (byte)(length >> 8 & 0xFF);
        this.output_buffer[17] = (byte)(length & 0xFF);
        System.arraycopy(data, 0, this.output_buffer, this.art_dmx_header_length, length);
        final DatagramPacket artnet_packet = new DatagramPacket(this.output_buffer, length + this.art_dmx_header_length, node_ip, this.artnetport);
        try {
            this.artnet_socket.send(artnet_packet);
        }
        catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
