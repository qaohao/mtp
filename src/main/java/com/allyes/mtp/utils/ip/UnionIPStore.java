package com.allyes.mtp.utils.ip;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.allyes.mtp.utils.io.CDataInputStream;

public class UnionIPStore extends IPStore
{

    public UnionIPStore(String ipdb)
    {
        ipInfoList_ = new ArrayList();
        ipdbName_ = ipdb;
    }
    
    public UnionIPStore(URL ipdbUrl)
    {
        ipInfoList_ = new ArrayList();
        ipdbUrl_ = ipdbUrl;
    }

    public void init()
        throws IOException
    {
        ipInfoList_.clear();
        boolean isLittleInt = false;
        CDataInputStream in = null;
        if (ipdbUrl_ == null) {
        	in = new CDataInputStream(new FileInputStream(ipdbName_));
        } else {
        	in = new CDataInputStream(ipdbUrl_.openStream());
        }
        in.skipBytes(8);
        do
            try
            {
                IPStore.IPInfo ipInfo = new IPStore.IPInfo();
                for(int i = 0; i < 9; i++)
                    switch(i)
                    {
                    case 0: // '\0'
                        ipInfo.setStartIP(getUnsignedInt(in.readInt(isLittleInt)));
                        break;

                    case 1: // '\001'
                        ipInfo.setEndIP(getUnsignedInt(in.readInt(isLittleInt)));
                        break;

                    case 2: // '\002'
                        ipInfo.setRegionId(in.readCString());
                        break;

                    case 3: // '\003'
                        ipInfo.setOperatorId(String.valueOf(in.readByte()));
                        break;

                    case 4: // '\004'
                        in.readByte();
                        break;

                    case 5: // '\005'
                        in.readByte();
                        break;

                    case 6: // '\006'
                        in.readByte();
                        break;

                    case 7: // '\007'
                        in.readInt(isLittleInt);
                        break;

                    case 8: // '\b'
                        in.readCString();
                        break;
                    }

                ipInfoList_.add(ipInfo);
            }
            catch(EOFException e)
            {
                return;
            }
        while(true);
    }

    public void clear()
    {
        ipInfoList_.clear();
    }

    public IPStore.IPInfo getIPInfo(String ip)
    {
        long ipLong = getIPLong(ip);
        int low = 0;
        for(int high = ipInfoList_.size() - 1; low <= high;)
        {
            int mid = low + high >>> 1;
            IPStore.IPInfo ipInfo = (IPStore.IPInfo)ipInfoList_.get(mid);
            if(ipLong < ipInfo.getStartIP())
                high = mid - 1;
            else
            if(ipLong > ipInfo.getEndIP())
                low = mid + 1;
            else
                return ipInfo;
        }

        return null;
    }

    private static long getUnsignedInt(int i)
    {
        if(i < 0)
        {
            long tmp = 4294967296L;
            return tmp + (long)i;
        } else
        {
            return (long)i;
        }
    }

    private long getIPLong(String ip)
    {
        String ips[] = ip.split("\\.");
        int ch1 = Integer.parseInt(ips[0]);
        int ch2 = Integer.parseInt(ips[1]);
        int ch3 = Integer.parseInt(ips[2]);
        int ch4 = Integer.parseInt(ips[3]);
        int tmp = (ch1 << 24) + (ch2 << 16) + (ch3 << 8) + (ch4 << 0);
        if(tmp > 0)
        {
            return (long)tmp;
        } else
        {
            long ipLong = 4294967296L;
            return ipLong + (long)tmp;
        }
    }

    private URL ipdbUrl_;
    private String ipdbName_;
    private List ipInfoList_;
}
