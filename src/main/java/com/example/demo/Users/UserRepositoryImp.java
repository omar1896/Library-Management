package com.example.demo.Users;

import jakarta.persistence.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public abstract class UserRepositoryImp implements UserRepository{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Optional<User> findByUserName(String userName) {
        String jpql = "SELECT user FROM users user WHERE user.userName = :userName";
        TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
        query.setParameter("userName", userName);

        try {
            User user = query.getSingleResult(); // If a user is found
            return Optional.of(user);
        } catch (NoResultException e) {
            return Optional.empty(); // No user found
        } catch (NonUniqueResultException e) {
            throw new IllegalStateException("Multiple users found with the same username: " + userName);
        }
    }
}
