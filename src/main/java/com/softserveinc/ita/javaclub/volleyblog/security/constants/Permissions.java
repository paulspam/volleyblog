package com.softserveinc.ita.javaclub.volleyblog.security.constants;

public final class Permissions {

    private Permissions() {
    }

    public static final String READ_AMPLUA = "hasAuthority('READ_AMPLUA')";

    public static final String MANAGE_AMPLUA = "hasAuthority('MANAGE_AMPLUA')";

    public static final String READ_COMMENT = "hasAuthority('READ_COMMENT')";

    public static final String MANAGE_COMMENTS = "hasAuthority('MANAGE_COMMENTS')";

    public static final String READ_LIKE = "hasAuthority('READ_LIKE')";

    public static final String MANAGE_LIKES = "hasAuthority('MANAGE_LIKES')";

    public static final String READ_PERMITION = "hasAuthority('READ_PERMITION')";

    public static final String MANAGE_PERMITIONS = "hasAuthority('MANAGE_PERMITIONS')";

    public static final String READ_PLAYER = "hasAuthority('READ_PLAYER')";

    public static final String MANAGE_PLAYERS = "hasAuthority('MANAGE_PLAYERS')";

    public static final String READ_POST = "hasAuthority('READ_POST')";

    public static final String MANAGE_POSTS = "hasAuthority('MANAGE_POSTS')";

    public static final String READ_POST_CATEGORY = "hasAuthority('READ_POST_CATEGORY')";

    public static final String MANAGE_POST_CATEGORIES = "hasAuthority('MANAGE_POST_CATEGORIES')";

    public static final String READ_POST_STATUS = "hasAuthority('READ_POST_STATUS')";

    public static final String MANAGE_POST_STATUSES = "hasAuthority('MANAGE_POST_STATUSES')";

    public static final String READ_ROLE = "hasAuthority('READ_ROLE')";

    public static final String MANAGE_ROLES = "hasAuthority('MANAGE_ROLES')";

    public static final String READ_TAG = "hasAuthority('READ_TAG')";

    public static final String MANAGE_TAGS = "hasAuthority('MANAGE_TAGS')";

    public static final String READ_TEAM = "hasAuthority('READ_TEAM')";

    public static final String MANAGE_TEAMS = "hasAuthority('MANAGE_TEAMS')";

    public static final String READ_USER = "hasAuthority('READ_USER')";

    public static final String MANAGE_USERS = "hasAuthority('MANAGE_USERS')";




    public static final String READ_COOPERATION = "hasAuthority('READ_COOPERATION')";

    public static final String CREATE_DELETE_COOPERATION = "hasAuthority('CREATE_DELETE_COOPERATION')";

    public static final String UPDATE_COOPERATION = "hasAuthority('UPDATE_COOPERATION')";

    public static final String READ_COOPERATION_DATA = "hasAuthority('READ_COOPERATION_DATA')";

    public static final String MANAGE_COOPERATION_DATA = "hasAuthority('MANAGE_COOPERATION_DATA')";

    public static final String MANAGE_IN_COOPERATION = "hasAuthority('MANAGE_IN_COOPERATION')";
}