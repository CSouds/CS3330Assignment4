package edu.oop.guild.seal;

import java.util.Objects;

public class GlowStoneSeal implements PackageSeal {

    @Override
    public String apply(String label) {
        Objects.requireNonNull(label);
        return "◆ " + label + " ◆";
    }

    @Override
    public int durability() {
        return 12;
    }
}