package com.ztp.ztpproject.flyweight;
import com.ztp.ztpproject.models.User;

import java.util.Map;

import com.ztp.ztpproject.models.Role;
import com.ztp.ztpproject.models.RoleList;

public class CategoryFactoryProxy implements IFlyweightFactory<Category> {
    private User user;
    private CategoryFactory factory;

    public CategoryFactoryProxy(User user) {
        this.user = user;
        factory = CategoryFactory.getInstance();
    }

    /**
     * Retrieves an existing Category or creates a new one if it doesn't exist,
     * depending on the user's role. If the user is a MODERATOR or higher, a new
     * Category is created if it doesn't exist. Otherwise, the existing Category
     * is retrieved or null if it doesn't exist.
     *
     * @param repeatingState The shared state for the Category.
     * @return The Flyweight Category instance.
     */
    @Override
    public Category getState(String repeatingState) {
        if (Role.hasAtLeastPrivileges(RoleList.MODERATOR, user.getRole())) { 
            return factory.getState(repeatingState);
        } else {
            return factory.getStateDontAdd(repeatingState);
        }
    }


    /**
     * Retrieves an existing Category without adding a new one if it doesn't
     * exist. Only existing Categories are returned.
     *
     * @param repeatingState The shared state for the Category.
     * @return The Flyweight Category instance or null if it doesn't exist.
     */
    @Override
    public Category getStateDontAdd(String repeatingState) {
        return factory.getStateDontAdd(repeatingState);
    }

    /**
     * Returns a map of all currently managed Flyweight Category instances.
     * 
     * @return Map of categories and their corresponding Flyweight Category
     *         instances.
     */
    @Override
    public Map<String, Category> getAllStates() {
        return factory.getAllStates();
    }
}
