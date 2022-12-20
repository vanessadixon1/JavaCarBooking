package com.amcSoftware.user.interfaces;

import com.amcSoftware.user.User;

import java.util.UUID;

public interface ILocateUser {
     User getUser(UUID userId);
}
