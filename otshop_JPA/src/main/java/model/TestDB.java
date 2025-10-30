package model;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TestDB {
    public static void main(String[] args) {
        EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("otshop_JPA");
        emf.close();
    }
}

