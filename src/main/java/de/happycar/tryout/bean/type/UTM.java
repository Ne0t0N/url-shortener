package de.happycar.tryout.bean.type;

/**
 * User: Ne0t0N
 * Date: 28.05.2016
 */
public enum UTM {

    UTM_SOURCE("utm_source"),
    UTM_MEDIUM("utm_medium"),
    UTM_CAMPAIGN("utm_campaign");

    private String name;

    UTM(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
