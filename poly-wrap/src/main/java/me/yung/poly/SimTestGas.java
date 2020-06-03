package me.yung.poly;

import me.yung.poly.jo.Tree;

import java.math.BigInteger;

public class SimTestGas {

    public static final BigInteger TWO = BigInteger.valueOf(2);

    private static BigInteger getPower(BigInteger a, BigInteger p, BigInteger modulus) {
        if (p.equals(BigInteger.ONE))
            return a;
        if (p.equals(BigInteger.ZERO))
            return BigInteger.ONE;
        BigInteger ret = getPower(a, p.shiftRight(1), modulus);
        ret = ret.multiply(ret).mod(modulus);
        if (p.mod(TWO).equals(BigInteger.ONE)) {
            ret = ret.multiply(a).mod(modulus);
        }
        return ret;
    }

    public static boolean verify_node(Tree.ArgNode node) {
        BigInteger bn = getPower(node.getXval(), BigInteger.valueOf(node.getP()), node.getModulus());
        return node.getVal().compareTo(bn.multiply(node.getAval()).add(node.getCval()).mod(node.getModulus())) == 0;
    }

    public static void main(String[] args) {
//        BigInteger xp_c = new BigInteger("000000000000000000000000002d63f089009de88d653da43ed01e52a4667f3e2dd3871409429d9cbd4a2a02818777c3c17ae64395106d7a5e9301d0039b9273", 16);
//        int x = 19237;
//        int p = 6;
//        int a = 5;
//        String cstr = "0";
//        String rval = "253392685493365724675864045";
//        verify("753799239", 1,
//               "1996490590313939276507130076697614479755906817961639803301237620427545528050930325359591854980895052212762329709707109830836419195522432016369557316712369044900552512816491516821019817979921175194241882667426584970450335606826673956674753203389114858834469973579847630185835380003346808632756614387572225816913090949775244726072869252911637405165896800440256636475105270534472117849687824734288016271996606222592750804655055987128477085749145807552388351",
//               "5903378030907716842",
//               "1504953087649308197723285229888673427955383465134395614920582606036454673682644432498082741635197049896845510223044310303373911523069801461368607848104665767933193314840609052029640437997183099141405208336633449839094300467720550033442547935227527001473015893052839249370036138025798641800450566397568394878325241287078366695552492301391290810257987676924400317539633995373774205701812998672271683872650658781173080163465618860599839822466643860536408822523981731");
    }

    private static void verify(String x, int p, String astr, String cstr, String valstr) {
//        System.out.println("VERIFY " + x + "," + p + "," + "," + astr + "," + cstr + "," + valstr);
        BigInteger xp = new BigInteger(x).pow(p);
        System.out.println(xp);

        BigInteger axp = new BigInteger(astr).multiply(xp);
        BigInteger c = new BigInteger(cstr);
        BigInteger r = c.add(axp);
        System.out.println(axp.toString(16));
        System.out.println(c.toString(16));
        System.out.println(r.toString(16).length());
        System.out.println(valstr);
        System.out.println(new BigInteger(valstr).equals(r));
    }
}
