package com.example.tlaitinen.ohutlevymuokkaaja;

public class KerroinLaskija {

    double levynPaksuus;

    /**
     *
     * @param s - Levyn paksuus mm
     */
    public KerroinLaskija(double s)
    {
        this.levynPaksuus = s;
    }

    /**
     *
     * @return - taulukko, jossa sallitut arvot 1-100mm
     */
    public double[] getTaivutussäteet()
    {
        return new double[]{1,1.2,1.6,2,2.5,3,4,5,6,8,10,12,16,20,25,28,32,36,40,45,50,63,80,100};
    }

    /**
     *
     * @param avautumisKulma - B, asteina
     * @param säde - r, mm
     * @return
     */
    public double getKorjaavaTekijä(double avautumisKulma, double säde)
    {
        double v = Math.PI *((180-avautumisKulma)/180)*(säde+0.5*levynPaksuus*getKerroinK(säde))-2*(säde+levynPaksuus);
        if(avautumisKulma >0 && avautumisKulma<=90)
        {
            return v;
        }
        else if(avautumisKulma>90 && avautumisKulma<=165)
        {
            return v * Math.tan(0.5*Math.toRadians(180-avautumisKulma));
        }
        else {
            return 0;
        }
    }

    /**
     *
     * @param r - Säde
     * @return Kerroin k (neutraaliakselin paksuus/2 paikan poikkeama)
     */
    public double getKerroinK(double r)
    {
        if(r/levynPaksuus>5 || levynPaksuus==0){ return 1;}
        else {
            return 0.65 + 0.5 * Math.log10(r / levynPaksuus);
        }
    }

    /**
     *
     * @param k - Korjauskerroin k
     * @return
     */
    public double getKerroinY(double k)
    {
        return 0.25 * Math.PI * k;
    }
}
