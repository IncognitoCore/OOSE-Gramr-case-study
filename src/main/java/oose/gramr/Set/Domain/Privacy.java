package oose.gramr.Set.Domain;

/**
 * Class to hold the privacy boolean
 * This requirement covers the following requirements
 * Foto kan in een set prive zijn.
 */
public class Privacy {
    boolean privacy;

    public Privacy(){
        this.privacy = false;
    }

    public void togglePrivate() {
        this.privacy = !this.privacy;
    }

    public boolean isPrivacy() {
        return privacy;
    }
}
