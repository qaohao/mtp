package com.allyes.mtp.utils.ip;

import java.io.IOException;

public abstract class IPStore
{
    public static class IPInfo
    {

        public String getOperatorId()
        {
            return operatorId_;
        }

        public void setOperatorId(String operatorId)
        {
            operatorId_ = operatorId;
        }

        public long getStartIP()
        {
            return startIP_;
        }

        public void setStartIP(long startIP)
        {
            startIP_ = startIP;
        }

        public long getEndIP()
        {
            return endIP_;
        }

        public void setEndIP(long endIP)
        {
            endIP_ = endIP;
        }

        public String getRegionId()
        {
            return regionId_;
        }

        public void setRegionId(String regionId)
        {
            regionId_ = regionId;
        }

        private long startIP_;
        private long endIP_;
        private String regionId_;
        private String operatorId_;

        public IPInfo()
        {
        }
    }


    public IPStore()
    {
    }

    public abstract void init()
        throws IOException;

    public abstract void clear();

    public abstract IPInfo getIPInfo(String s);
}