package com.allyes.mtp.utils.io;

import java.io.DataInputStream;

import java.io.*;

public class CDataInputStream extends DataInputStream
{

    public CDataInputStream(InputStream in)
    {
        super(in);
    }

    public int readInt(boolean isLittleInt)
        throws IOException
    {
        if(isLittleInt)
            return readLittleInt();
        else
            return readBigInt();
    }

    private int readBigInt()
        throws IOException
    {
        return readInt();
    }

    private int readLittleInt()
        throws IOException
    {
        int ch4 = read();
        int ch3 = read();
        int ch2 = read();
        int ch1 = read();
        if((ch1 | ch2 | ch3 | ch4) < 0)
            throw new EOFException();
        else
            return (ch1 << 24) + (ch2 << 16) + (ch3 << 8) + (ch4 << 0);
    }

    public String readCString()
        throws IOException
    {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int i;
        while((i = in.read()) != 0) 
            out.write(i);
        return new String(out.toByteArray());
    }
}
