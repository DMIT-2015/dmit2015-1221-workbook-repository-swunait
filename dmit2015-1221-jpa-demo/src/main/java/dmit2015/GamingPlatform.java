package dmit2015;

public enum GamingPlatform {
    PLAYSTATION("Playstation"),
    NINTENDO("Nintendo"),
    XBOX("Xbox"),
    PC_GAMING("PC Gaming");

    public String name;

    GamingPlatform(String platformName) {
        name = platformName;
    }
}