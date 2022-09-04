package org.barbaris.generator.models;

public interface IUserService {
    boolean registration(User user);

    User getUser(User user);
}
