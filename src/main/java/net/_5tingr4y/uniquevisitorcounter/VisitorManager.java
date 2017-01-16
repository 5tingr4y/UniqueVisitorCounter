/*
 * Copyright (c) 2017 Raymond Kampmann <https://5tingr4y.net/>
 *
 * This file is part of UniqueVisitorCounter, licensed under the MIT License (MIT). See the LICENSE.txt file
 * at the root of this project for more details.
 */
package net._5tingr4y.uniquevisitorcounter;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.profile.GameProfile;
import org.spongepowered.api.service.user.UserStorageService;

import java.util.*;

public class VisitorManager {

    private boolean edited = true;
    private boolean userStorageAvailable = false;

    private HashSet<User> users = new HashSet<>();
    private Set<User> publicUsers = Collections.unmodifiableSet(users);

    private UserStorageService userStorageService;

    VisitorManager() {
        Optional<UserStorageService> oUSS = Sponge.getServiceManager().provide(UserStorageService.class);

        if(oUSS.isPresent()) {
            userStorageService = oUSS.get();
            userStorageAvailable = true;
        } else {
            UniqueVisitorCounter.get().getLogger().warn("Unable to retreive UserStorageService; " +
                    "[NYI] will attempt to retreive it in <time from config>");
            //TODO: add scheduler to attempt retreiving it a certain time later
        }
    }

    //getters
    public Set<User> getUsers() {
        return publicUsers;
    }

    //setters
    public boolean sync() {
        UniqueVisitorCounter.get().getLogger().info("Synchronizing...");

        if(userStorageAvailable) {
            users.clear();

            Collection<GameProfile> profiles = userStorageService.getAll();
            for(GameProfile profile: profiles) {
                Optional<User> op = userStorageService.get(profile);
                if(op.isPresent())
                    users.add(op.get());
            }

            return true;
        }
        return false;
    }
}
