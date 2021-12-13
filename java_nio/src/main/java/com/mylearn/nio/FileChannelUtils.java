package com.mylearn.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelUtils {

    public static void fastCopy(String src,String dist) throws IOException {
        // 获取源文件的输入字节流
        FileInputStream fileInputStream = new FileInputStream(src);
        // 获取输入字节流的文件通道
        FileChannel fileChannelIn = fileInputStream.getChannel();

        // 获取目标文件的输出字节流
        FileOutputStream fileOutputStream = new FileOutputStream(dist);
        // 获取输出字节流的通道
        FileChannel fileChannelOut = fileOutputStream.getChannel();

        // 为缓冲区分配 1024个字节
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
        while (true){
            // 从输入通道中读取数据到缓冲区中
            int r = fileChannelIn.read(byteBuffer);
            if(r == -1){
                break;
            }

            // 切换读写
            byteBuffer.flip();

            // 把缓冲区的内容写入到输出文件中
            fileChannelOut.write(byteBuffer);

            // 清空缓冲区
            byteBuffer.clear();
        }
    }
}
