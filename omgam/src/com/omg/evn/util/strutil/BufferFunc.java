/*
 * 实现缓冲区有关数据操作
 */
package com.omg.evn.util.strutil;

public class BufferFunc {
	// 反转windows和Unix的字节顺序
	public static int ReverseInt32(int nValue)
	{
		return ((nValue&0xff)<<24)|((nValue&0xff00)<<8)|((nValue&0xff0000)>>8)|((nValue&0xff000000)>>24);
	}
	// 将整数转换为byte[]
	public static byte[] Int32ToByteArray(int nNumber)
	{
		int nTemp = nNumber;
		byte[] retArray = new byte[4];
		for( int i = 3; i >= 0; i-- )
		{
			retArray[i] = new Integer(nTemp&0xff).byteValue();
			nTemp >>= 8;
		}
		return retArray;
	}
	// 将byte[]转换为整数
	public static int ByteArrayToInt32(byte[] byteArray)
	{
		int nValue = 0;
		for( int i = 0; i <=3; i++ )
		{
			nValue <<= 8;
			nValue &= 0xFFFFFF00;
			nValue |= (((int)byteArray[i])&0xFF);
			
		}
		return nValue;
	}
	// 将64为长整数转换为byte[]
	public static byte[] Int64ToByteArray(long lNumber)
	{
		long lTmp = lNumber;
		byte[] retArray = new byte[8];
		for( int i = 7; i >= 0; i--)
		{
			retArray[i] = new Long(lTmp&0xff).byteValue();
			lTmp >>= 8;
		}
		return retArray;
	}
	// 将byte[]转换为长整数
	public static long ByteArrayToInt64(byte[] byteArray)
	{
		long lValue = 0;
		for( int i = 0; i <=7; i++ )
		{
			lValue <<= 8;
			lValue &= 0xffffffffffffff00l;
			lValue |= (((int)byteArray[i])&0xFF);
			
		}
		return lValue;
	}
	// 在缓冲区指定位置写入32位整数
	public static void WriteInt32ToByteBuffer(int nValue, byte[] bytes, int nOffset)
	{
		// 转换整数
		byte [] byteArray = Int32ToByteArray(nValue);
		// 复制字节
		for(int i = 0; i < 4; i++)
		{
			bytes[nOffset + i] = byteArray[i];
		}
	}

	// 从缓冲区指定位置读出32位整数
	public static int ReadInt32FromByteBuffer(byte[] bytes, int nOffset)
	{
		byte [] byteArray = new byte[4];
		// 复制字节
		for(int i = 0; i < 4; i++ )
		{
			byteArray[i] = bytes[nOffset + i];
		}
		// 交换网络字节顺序
		//SwitchBytes(byteArray,4);
		//返回
		return ByteArrayToInt32(byteArray);
	}
	// 在缓冲区指定位置写入64位整数
	public static void WriteInt64ToByteBuffer(long lValue, byte[] bytes, int nOffset)
	{
		// 转换整数
		byte [] byteArray = Int64ToByteArray(lValue);
		// 复制字节
		for(int i = 0; i < 8; i++)
		{
			bytes[nOffset + i] = byteArray[i];
		}
	}
	// 从缓冲区指定位置读出32位整数
	public static long ReadInt64FromByteBuffer(byte[] bytes, int nOffset)
	{
		byte [] byteArray = new byte[8];
		// 复制字节
		for(int i = 0; i < 4; i++ )
		{
			byteArray[i] = bytes[nOffset + i];
		}
		// 交换网络字节顺序
		//SwitchBytes(byteArray,8);
		//返回
		return ByteArrayToInt64(byteArray);
	}
	// 从缓冲区读一个byte
	public static byte ReadByteFromByteBuffer(byte[] bytes, int nOffset)
	{
		return bytes[nOffset];
	}
	// 写入一个byte到缓冲区
	public static void WriteByteToByteBuffer(byte b, byte[] bytes, int nOffset)
	{
		bytes[nOffset] = b;
	}
	// 从缓冲区读指定长度的字串
	public static String ReadStringFromByteBuffer(byte[] bytes, int nStringLen, int nOffset)
	{
		byte [] byteArray = new byte[nStringLen];
		
		for( int i = 0; i < nStringLen; i++ )
		{
			byteArray[i] = bytes[nOffset+i];
			if(  bytes[nOffset+i] == 0 )
				break; // 不必要把过多的'\0'读出来
		}
		
		return new String(byteArray);
	}
	public static byte[] ReadBytesFromByteBuffer(byte[] bytes, int nStringLen, int nOffset)
	{
		byte [] byteArray = new byte[nStringLen];
		
		for( int i = 0; i < nStringLen; i++ )
		{
			byteArray[i] = bytes[nOffset+i];
		}
		return byteArray;
	}
	// 从缓冲区读指定长度字串到另一个缓冲区
	public static void ReadBytesFromByteBuffer(byte[] SourBuff, int nStringLen, int nSourOffset, byte[] DestBuff, int nDestOffset)
	{
		for( int i = 0; i < nStringLen; i++ )
		{
			DestBuff[nDestOffset+i] = SourBuff[nSourOffset+i];
		}
	}
	// 将byte[]写入缓冲区
	public static void WriteBytesToByteBuffer(byte[] Sour, int nLen, byte[] Dest, int nOffset)
	{
		for( int i = 0; i < nLen; i++ )
		{
			Dest[nOffset+i] = Sour[i];
		}
	}
	// 将串写入缓冲区
	public static void WriteStringToByteBuffer(String str, int nMaxLen, byte[] bytes, int nOffset)
	{
		byte[] b = str.getBytes();
		for( int i = 0; i < nMaxLen; i++ )
		{
			// 信息不足右补零
			if( i < str.length() )
				bytes[nOffset+i] = b[i];
			else
				bytes[nOffset+i] = 0;
		}
	}
}
