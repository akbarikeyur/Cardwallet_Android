package com.example.cardwallet;

public class ModelDashBoard {

    private String Company_name;
    private String Company_address;
    private String name;
    private int logo;
    private String contact_number;
    private String email;
    private String category;

    public ModelDashBoard(String company_name, String company_address, String name, String contact_number,int logo,String email, String category) {
        Company_name = company_name;
        Company_address = company_address;
        this.name = name;
        this.contact_number = contact_number;
        this.logo=logo;
        this.email = email;
        this.category = category;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }



    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }





    public String getCompany_name() {
        return Company_name;
    }

    public void setCompany_name(String company_name) {
        Company_name = company_name;
    }

    public String getCompany_address() {
        return Company_address;
    }

    public void setCompany_address(String company_address) {
        Company_address = company_address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }






}
