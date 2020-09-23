package com.company;

/*
*
* Equipe:
* Davi Pombeiro
* Gabriel Fortunato
* Rafael Crespim
* Yasmin weber
*
* */

public class Main {

    static final int K = 64; // 6 bits
    static final int TCACHE = 8192; // 13
    static final int TRAM = 16*1024*1024; // 24 bits
    static final int TLINHA = K+1;
    static final int MODIF = K;

    static int nbits_x = nbits(TRAM); // 24
    static int nbits_w = nbits(K); // 6
    static int nbits_r = nbits(TCACHE/K); // 7
    static int nbits_t = nbits_x - nbits_w - nbits_r; // 11
    static int M = TCACHE/K;

    public static void main(String[] args) throws InvalidAddress, InvalidSize {
        IO es = new IO(System.out);

        RAM ram = new RAM(TRAM);

        Cache cache = new Cache(TCACHE, ram);
        cache.Set(10560325,14000446);
        cache.Set(10560326,14000470);

        CPU cpu = new CPU(cache, es);
        cpu.Run(10560325);
    }

    private static int nbits(int x) {
        int cont = 0;
        while (x > 0) {
            x = x >> 1;
            ++cont;
        }
        return cont - 1;
    }

    public static int w(int x) {
        return (x & ((1 << nbits_w)-1));
    }

    public static int r(int x) {
        return (x & ((1 << nbits_r+nbits_w)-1))>>nbits_w;
    }
    public static int t(int x) {
        return x>>nbits_r+nbits_w;
    }
    public static int s(int x) {
        return x>>nbits_w;
    }
}
