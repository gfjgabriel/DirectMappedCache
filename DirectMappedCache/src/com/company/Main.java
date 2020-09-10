package com.company;

public class Main {

    public static void main(String[] args) throws InvalidAddress, InvalidSize {
        IO es = new IO(System.out);

        RAM ram = new RAM(128);
        ram.Set(10,120);
        ram.Set(11,127);

        Cache cache = new Cache(12, ram);

        CPU cpu = new CPU(cache, es);
        cpu.Run(10);
    }
}
