package org.thinkingstudio.randomtitlerework.hitokoto;

public class Hitokoto {
    private String id;
    private String uuid;
    private String hitokoto;
    private String type;
    private String from;
    private String fromWho;
    private String creator;
    private int creatorUid;
    private int reviewer;
    private String commitFrom;
    private String createdAt;
    private int length;

    public String getId() {
        return id;
    }

    public String getUuid() {
        return uuid;
    }

    public String getHitokoto() {
        return hitokoto;
    }

    public String getType() {
        return type;
    }

    public String getFrom() {
        return from;
    }

    public String getFromWho() {
        return fromWho;
    }

    public String getCreator() {
        return creator;
    }

    public int getCreatorUid() {
        return creatorUid;
    }

    public int getReviewer() {
        return reviewer;
    }

    public String getCommitFrom() {
        return commitFrom;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public int getLength() {
        return length;
    }

    @Override
    public String toString() {
        return "Hitokoto{" +
                "hitokoto='" + hitokoto + '\'' +
                ", from='" + from + '\'' +
                '}';
    }
}

