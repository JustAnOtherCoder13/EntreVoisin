package com.openclassrooms.entrevoisins.model;

import java.util.Objects;

/**
 * Model object representing a Neighbour
 */
public class Neighbour {

    /**
     * Identifier
     */
    private Integer id;
    /**
     * Full name
     */
    private String name;
    /**
     * Avatar
     */
    private String avatarUrl;

    // add necessary attributes
    /**
     * Address
     */
    private String address;
    /**
     * Phone
     */

    private String phone;
    /**
     * Facebook
     */

    private String facebook;
    /**
     * AboutMeTxt
     */

    private String aboutMeTxt;
    /**
     * IsFavBool
     */

    private boolean isFavorite;

    /**
     * Constructor
     *
     * @param id
     * @param name
     * @param avatarUrl
     * @param address
     * @param phone
     * @param facebook
     * @param aboutMeTxt
     * @param isFavorite
     */
    public Neighbour(Integer id, String name, String avatarUrl, String address, String phone, String facebook, String aboutMeTxt,boolean isFavorite) {
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
        // pass them to the constructor and add them in parameters
        this.address = address;
        this.phone = phone;
        this.facebook = facebook;
        this.aboutMeTxt = aboutMeTxt;
        this.isFavorite = isFavorite;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAvatarUrl() {
        return avatarUrl;
    }
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    //getters and setters for new attributes

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getFacebook() { return facebook; }
    public void setFacebook(String facebook) { this.facebook = facebook; }
    public String getAboutMeTxt() { return aboutMeTxt; }
    public void setAboutMeTxt(String aboutMeTxt) { this.aboutMeTxt = aboutMeTxt; }
    public boolean isFavorite() { return isFavorite; }
    public void setFavorite(boolean favorite) { isFavorite = favorite; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Neighbour neighbour = (Neighbour) o;
        return Objects.equals(id, neighbour.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
