package edu.uiowa.ars.service;

import edu.uiowa.ars.model.User;

public interface UserService extends EntityManagementService<User> {

    boolean isValidId(final String userId);

    boolean isValidAdmin(final String userId);

    User getUserById(final String userId);
}
