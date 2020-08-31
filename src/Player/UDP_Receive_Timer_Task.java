// 
// Decompiled by Procyon v0.5.36
// 

package Player;

import java.util.TimerTask;

class UDP_Receive_Timer_Task extends TimerTask
{
    @Override
    public void run() {
        Player.receive_udp();
    }
}
